package com.example.software3d;

import java.awt.Color;

public class CubeModel {

    public Vec3[] vertices = {
            new Vec3(-1, -1, -1),
            new Vec3( 1, -1, -1),
            new Vec3( 1,  1, -1),
            new Vec3(-1,  1, -1),
            new Vec3(-1, -1,  1),
            new Vec3( 1, -1,  1),
            new Vec3( 1,  1,  1),
            new Vec3(-1,  1,  1)
    };

    public Face[] faces = {
            new Face(new int[]{0,1,2,3}, new Color(200,80,80)),
            new Face(new int[]{4,5,6,7}, new Color(80,200,80)),
            new Face(new int[]{0,1,5,4}, new Color(80,80,200)),
            new Face(new int[]{2,3,7,6}, new Color(200,200,80)),
            new Face(new int[]{1,2,6,5}, new Color(80,200,200)),
            new Face(new int[]{0,3,7,4}, new Color(200,80,200))
    };
}
