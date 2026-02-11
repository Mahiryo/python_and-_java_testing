package com.example.software3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;


public class RendererPanel extends JPanel {

    private final CubeModel cube = new CubeModel();
    private final Camera camera = new Camera();

    private boolean perspective = true;

    private ZBuffer zb;
    private final double NEAR = 0.1;

    private double ax, ay, az;
    private double modelScale = 120;
    private double fov = 333;
    private double orthoScale = 1.0;
        private boolean wDown, sDown, aDown, dDown;   // movement/turn
        private boolean upDown, downDown;             // tilt (pitch)

// tune speeds
        private final double moveStepPerTick = 8.0;   // pixels/units per timer tick (~60fps)
        private final double yawStepPerTick  = 0.05;  // radians per tick
        private final double pitchStepPerTick = 0.04; // radians per tick

    public RendererPanel() {
        setPreferredSize(new Dimension(900,600));
        setBackground(Color.WHITE);
        setFocusable(true);

        new Timer(16, e -> {
            if (aDown) camera.yaw -= yawStepPerTick;
            if (dDown) camera.yaw += yawStepPerTick;

            if (upDown) camera.pitch -= pitchStepPerTick;
            if (downDown) camera.pitch += pitchStepPerTick;

            // optional: clamp pitch to avoid flipping over
            camera.pitch = Math.max(-1.4, Math.min(1.4, camera.pitch));

            // move while holding keys (uses current facing direction)
            Vec3 fwd = camera.forward(); // should reflect yaw/pitch
            if (wDown) camera.position = camera.position.add(fwd.scale(moveStepPerTick));
            if (sDown) camera.position = camera.position.sub(fwd.scale(moveStepPerTick));

            repaint();
        }).start();


// in constructor (only ONE listener)
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> wDown = true;
                    case KeyEvent.VK_S -> sDown = true;
                    case KeyEvent.VK_A -> aDown = true;
                    case KeyEvent.VK_D -> dDown = true;

                    // tilt up/down (use arrow keys for pitch)
                    case KeyEvent.VK_UP -> upDown = true;
                    case KeyEvent.VK_DOWN -> downDown = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> wDown = false;
                    case KeyEvent.VK_S -> sDown = false;
                    case KeyEvent.VK_A -> aDown = false;
                    case KeyEvent.VK_D -> dDown = false;

                    case KeyEvent.VK_UP -> upDown = false;
                    case KeyEvent.VK_DOWN -> downDown = false;
                }
            }
        });



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth(), h = getHeight();

        if (zb == null || zb.width() != w || zb.height() != h) {
            zb = new ZBuffer(w, h);
        }

        // clear to white, depth to very far
        zb.clear(0xFFFFFFFF, Double.POSITIVE_INFINITY);

        // ---- 1) build world vertices (scale + rotation) ----
        Vec3[] world = new Vec3[cube.vertices.length];
        for (int i = 0; i < cube.vertices.length; i++) {
            Vec3 v = cube.vertices[i].scale(modelScale);
            v = rotateX(v, ax);
            v = rotateY(v, ay);
            v = rotateZ(v, az);
            world[i] = v;
        }

        // ---- 2) camera space (subtract + inverse camera rotation if you have it) ----
        Vec3[] cam = new Vec3[world.length];
        for (int i = 0; i < world.length; i++) {
            Vec3 v = world[i].sub(camera.position);

            // If you implemented camera angles, apply inverse rotation here:
             v = rotateY(v, -camera.yaw);
             v = rotateX(v, -camera.pitch);
             v = rotateZ(v, -camera.roll);

            cam[i] = v;
        }

        // ---- 3) project to screen (keep x,y plus depth z for Z-buffer) ----
        // For Z-buffer we need: screenX, screenY, depthZ (camera-space z)
        double[] sx = new double[cam.length];
        double[] sy = new double[cam.length];
        double[] sz = new double[cam.length];

        for (int i = 0; i < cam.length; i++) {
            Vec3 v = cam[i];

            // near clamp so projection doesn't explode (proper clipping is better, but this works)
            double z = Math.max(v.z, NEAR);

            // perspective projection
            double scale = fov / z;
            sx[i] = w * 0.5 + v.x * scale;
            sy[i] = h * 0.5 - v.y * scale;

            // store depth (use z itself)
            sz[i] = z;
        }

        // ---- 4) draw each face as 2 triangles into the Z-buffer ----
        for (int fi = 0; fi < cube.faces.length; fi++) {
            Face face = cube.faces[fi];
            int[] idx = face.indices;

            // skip if all vertices behind near plane (optional but helps)
            boolean anyInFront = false;
            for (int k : idx) if (cam[k].z >= NEAR) { anyInFront = true; break; }
            if (!anyInFront) continue;

            int c = ZBuffer.argb(face.color);

            // Quad (0,1,2,3) -> triangles (0,1,2) and (0,2,3)
            int i0 = idx[0], i1 = idx[1], i2 = idx[2], i3 = idx[3];

            zb.fillTriangle(sx[i0], sy[i0], sz[i0],
                    sx[i1], sy[i1], sz[i1],
                    sx[i2], sy[i2], sz[i2], c);

            zb.fillTriangle(sx[i0], sy[i0], sz[i0],
                    sx[i2], sy[i2], sz[i2],
                    sx[i3], sy[i3], sz[i3], c);
        }

        // ---- 5) blit framebuffer to screen ----
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(zb.image(), 0, 0, null);
    }

    static Vec3 rotateCamera(Vec3 v, Camera cam) {


        v = rotateY(v, -cam.yaw);
        v = rotateX(v, -cam.pitch);
        v = rotateZ(v, -cam.roll);

        return v;
    }

    // ===== rotations =====
    static Vec3 rotateX(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x,
                v.y*c - v.z*s,
                v.y*s + v.z*c);
    }

    static Vec3 rotateY(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x*c + v.z*s,
                v.y,
                -v.x*s + v.z*c);
    }

    static Vec3 rotateZ(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x*c - v.y*s,
                v.x*s + v.y*c,
                v.z);
    }
}
