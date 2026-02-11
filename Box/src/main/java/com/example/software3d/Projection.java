
package com.example.software3d;

import java.awt.Point;

public class Projection {

    public static Point perspective(Vec3 v, int w, int h, double fov) {
        double z = Math.max(v.z, 0.1);

        double scale = fov / z;

        int sx = (int) (w / 2.0 + v.x * scale);
        int sy = (int) (h / 2.0 - v.y * scale);

        return new Point(sx, sy);
    }

    public static Point parallel(Vec3 v, int w, int h, double scale) {
        int sx = (int) (w / 2.0 + v.x * scale);
        int sy = (int) (h / 2.0 - v.y * scale);
        return new Point(sx, sy);
    }
}
