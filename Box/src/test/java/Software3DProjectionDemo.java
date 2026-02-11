import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Software3DProjectionDemo extends JPanel {

    // ===================== 3D TYPES =====================
    static class Vec3 {
        double x, y, z;
        Vec3(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
    }

    static Vec3 rotateX(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x, v.y * c - v.z * s, v.y * s + v.z * c);
    }
    static Vec3 rotateY(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x * c + v.z * s, v.y, -v.x * s + v.z * c);
    }
    static Vec3 rotateZ(Vec3 v, double a) {
        double c = Math.cos(a), s = Math.sin(a);
        return new Vec3(v.x * c - v.y * s, v.x * s + v.y * c, v.z);
    }

    // ===================== PROJECTIONS =====================

    // Perspective: expects points already in CAMERA SPACE (z > 0 means in front)
    static Point projectPerspective(Vec3 v, int w, int h, double fov) {
        if (v.z <= 0.1) return null; // behind/too close to camera
        double scale = fov / v.z;
        int sx = (int) Math.round(w * 0.5 + v.x * scale);
        int sy = (int) Math.round(h * 0.5 - v.y * scale);
        return new Point(sx, sy);
    }

    // Parallel/Orthographic: expects points in CAMERA SPACE too, but ignores z
    static Point projectParallel(Vec3 v, int w, int h, double scale) {
        int sx = (int) Math.round(w * 0.5 + v.x * scale);
        int sy = (int) Math.round(h * 0.5 - v.y * scale);
        return new Point(sx, sy);
    }

    // ===================== MODEL (CUBE) =====================
    private final Vec3[] verts = {
            new Vec3(-1, -1, -1), new Vec3( 1, -1, -1),
            new Vec3( 1,  1, -1), new Vec3(-1,  1, -1),
            new Vec3(-1, -1,  1), new Vec3( 1, -1,  1),
            new Vec3( 1,  1,  1), new Vec3(-1,  1,  1),
    };

    private final int[][] edges = {
            {0,1},{1,2},{2,3},{3,0},
            {4,5},{5,6},{6,7},{7,4},
            {0,4},{1,5},{2,6},{3,7}
    };
    // Each face is 4 vertex indices (quad)
    private final int[][] faces = {
            {0,1,2,3}, // back
            {4,5,6,7}, // front
            {0,1,5,4}, // bottom
            {2,3,7,6}, // top
            {1,2,6,5}, // right
            {0,3,7,4}  // left
    };

    private final Color[] faceColors = {
            new Color(200, 80, 80),
            new Color(80, 200, 80),
            new Color(80, 80, 200),
            new Color(200, 200, 80),
            new Color(80, 200, 200),
            new Color(200, 80, 200)
    };

    // ===================== STATE =====================
    private double ax = 0, ay = 0, az = 0;
    private double cx = 0, cy = 0, cz = 0;
    // Projection mode
    private boolean perspective = true;

    // Camera position (world space). We'll convert points into camera space by subtracting this.
    // Camera looking down +Z (so points in front have larger world Z than the camera Z).
    private Vec3 camera = new Vec3(0, 0, -600);

    // Tuning knobs
    private double modelScale = 120;      // cube size
    private double fov = 500;             // perspective strength
    private double orthoScale = 1.0;      // parallel scale (zoom)
    static class FaceDraw {
        int[] idx;
        double depth;
        Color color;

        FaceDraw(int[] idx, double depth, Color color) {
            this.idx = idx;
            this.depth = depth;
            this.color = color;
        }
    }

    public Software3DProjectionDemo() {
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
        setFocusable(true);

        // Animation
        new Timer(16, e -> {
            ax += 0.01;
            ay += 0.015;
            az += 0.008;
            repaint();
        }).start();


        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P -> perspective = !perspective;
                    case KeyEvent.VK_W -> {
                        if (perspective) camera.z += 20;   // closer
                        else orthoScale *= 1.08;           // zoom in
                    }
                    case KeyEvent.VK_S -> {
                        if (perspective) camera.z -= 20;   // farther
                        else orthoScale /= 1.08;           // zoom out
                    }
                    case KeyEvent.VK_D -> {
                        camera.x += 20;
                    }
                    case KeyEvent.VK_A -> {
                        camera.x -= 20;

                    }
                    case KeyEvent.VK_R -> {
                        camera = new Vec3(0, 0, -600);
                        orthoScale = 1.0;
                        perspective = true;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();

        // 1) Model transform (scale + rotation) in WORLD space
        Vec3[] world = new Vec3[verts.length];
        for (int i = 0; i < verts.length; i++) {
            Vec3 v = verts[i];

            Vec3 s = new Vec3(v.x * modelScale, v.y * modelScale, v.z * modelScale);

            Vec3 r = rotateX(s, ax);
            r = rotateY(r, ay);
            r = rotateZ(r, az);

            world[i] = r;
        }

        // 2) Camera transform: WORLD -> CAMERA space (subtract camera position)
        Vec3[] camSpace = new Vec3[verts.length];
        for (int i = 0; i < world.length; i++) {
            Vec3 v = world[i];
            camSpace[i] = new Vec3(v.x - camera.x, v.y - camera.y, v.z - camera.z);
        }

        // 3) Projection: CAMERA -> SCREEN
        Point[] pts = new Point[camSpace.length];
        for (int i = 0; i < camSpace.length; i++) {
            pts[i] = perspective
                    ? projectPerspective(camSpace[i], w, h, fov)
                    : projectParallel(camSpace[i], w, h, orthoScale);
        }

        // Draw edges (skip edges if a point is not projectable in perspective)
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);
        for (int[] e : edges) {
            Point a = pts[e[0]];
            Point b = pts[e[1]];
            if (a != null && b != null) {
                g2.drawLine(a.x, a.y, b.x, b.y);
            }
        }
        java.util.List<FaceDraw> drawFaces = new java.util.ArrayList<>();

// Build face list with average Z depth
        for (int i = 0; i < faces.length; i++) {
            int[] f = faces[i];

            double avgZ = 0;
            boolean ok = true;

            for (int idx : f) {
                avgZ += camSpace[idx].z;
                if (pts[idx] == null) ok = false;
            }
            avgZ /= f.length;

            if (ok) {
                drawFaces.add(new FaceDraw(f, avgZ, faceColors[i]));
            }
        }

// Painter's algorithm: far → near
        drawFaces.sort((a, b) -> Double.compare(b.depth, a.depth));

// Draw filled polygons
        for (FaceDraw fd : drawFaces) {
            Polygon poly = new Polygon();
            for (int idx : fd.idx) {
                poly.addPoint(pts[idx].x, pts[idx].y);
            }

            g2.setColor(fd.color);
            g2.fillPolygon(poly);

            g2.setColor(Color.BLACK);
            g2.drawPolygon(poly); // outline
        }


        // Optional: draw vertices
        g2.setColor(new Color(40, 40, 40));
        for (Point p : pts) {
            if (p != null) g2.fillOval(p.x - 3, p.y - 3, 6, 6);
        }

        // HUD
        g2.setColor(Color.GRAY);
        String mode = perspective ? "Perspective" : "Parallel (Orthographic)";
        String help = "P=toggle, W/S=zoom, R=reset";
        String camInfo = String.format("camera.z=%.1f   orthoScale=%.3f", camera.z, orthoScale);
        g2.drawString("Projection: " + mode, 15, 20);
        g2.drawString(help, 15, 38);
        g2.drawString(camInfo, 15, 56);

        g2.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Software 3D (No OpenGL/JavaFX) - Perspective + Parallel");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new Software3DProjectionDemo());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
