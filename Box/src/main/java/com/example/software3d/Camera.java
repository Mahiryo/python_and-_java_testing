package com.example.software3d;

public class Camera {
    public Vec3 position = new Vec3(0, 0, -600);

    // radians
    public double yaw = 0;    // left/right
    public double pitch = 0;  // up/down
    public double roll = 0;   // barol roll

    public Vec3 forward() {
        Vec3 f = new Vec3(0, 0, 1);          // camera-local forward
        f = RendererPanel.rotateY(f, yaw);   // yaw
        f = RendererPanel.rotateX(f, pitch); // pitch
        f = RendererPanel.rotateZ(f, roll);  // roll

        return f.normalize();
    }
}
