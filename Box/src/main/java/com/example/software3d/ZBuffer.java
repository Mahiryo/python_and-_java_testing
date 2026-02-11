package com.example.software3d;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

    public class ZBuffer {

        private final BufferedImage color;
        private final double[] depth; // 1D: y*w + x
        private final int w, h;

        public ZBuffer(int w, int h) {
            this.w = w;
            this.h = h;
            this.color = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            this.depth = new double[w * h];
        }

        public int width() { return w; }
        public int height() { return h; }
        public BufferedImage image() { return color; }

        public void clear(int argb, double farDepth) {
            // clear color
            int[] pixels = color.getRGB(0, 0, w, h, null, 0, w);
            Arrays.fill(pixels, argb);
            color.setRGB(0, 0, w, h, pixels, 0, w);

            // clear depth (bigger = farther, so start with VERY far)
            Arrays.fill(depth, farDepth);
        }

        private static double edge(double ax, double ay, double bx, double by, double px, double py) {
            return (px - ax) * (by - ay) - (py - ay) * (bx - ax);
        }

        // Simple triangle rasterization with barycentric weights.
        // Depth is interpolated in *camera-space z*.
        public void fillTriangle(
                double x0, double y0, double z0,
                double x1, double y1, double z1,
                double x2, double y2, double z2,
                int argb
        ) {
            // Bounding box
            int minX = (int) Math.floor(Math.min(x0, Math.min(x1, x2)));
            int maxX = (int) Math.ceil (Math.max(x0, Math.max(x1, x2)));
            int minY = (int) Math.floor(Math.min(y0, Math.min(y1, y2)));
            int maxY = (int) Math.ceil (Math.max(y0, Math.max(y1, y2)));

            // Clamp to screen
            minX = Math.max(minX, 0);
            minY = Math.max(minY, 0);
            maxX = Math.min(maxX, w - 1);
            maxY = Math.min(maxY, h - 1);

            double area = edge(x0, y0, x1, y1, x2, y2);
            if (area == 0) return;

            // To support both CW/CCW triangles, we’ll accept pixels with same sign as area
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    double px = x + 0.5;
                    double py = y + 0.5;

                    double w0 = edge(x1, y1, x2, y2, px, py);
                    double w1 = edge(x2, y2, x0, y0, px, py);
                    double w2 = edge(x0, y0, x1, y1, px, py);

                    if ((area > 0 && (w0 < 0 || w1 < 0 || w2 < 0)) ||
                            (area < 0 && (w0 > 0 || w1 > 0 || w2 > 0))) {
                        continue;
                    }

                    // Normalize barycentric
                    w0 /= area;
                    w1 /= area;
                    w2 /= area;

                    // Interpolate camera-space depth
                    double z = w0 * z0 + w1 * z1 + w2 * z2;

                    int idx = y * w + x;
                    if (z < depth[idx]) { // smaller z = closer (if you use z = camera-space distance)
                        depth[idx] = z;
                        color.setRGB(x, y, argb);
                    }
                }
            }
        }

        public static int argb(Color c) {
            return (c.getAlpha() << 24) | (c.getRed() << 16) | (c.getGreen() << 8) | c.getBlue();
        }
    }



