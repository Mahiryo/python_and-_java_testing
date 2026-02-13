package phy_sim;


public class Test{
    double p_x, p_y; // position X/Y in 2d Plane
    double s_x, s_y; // current speed inside the X/Y axis in the 2D Plane
    double a_x, a_y; // current acceleration inside the X/Y axis in the 2D Plane
    double r_x, r_y; // base resistance in all directions

    int height, width;



    public void applyForce(double forceX, double forceY){
        a_x += forceX;
        a_y += forceY;
    }
    public void update(double dt){
        r_x = a_x * 0.2;
        r_y = a_y * 0.2;

        s_x += a_x * dt;
        s_y += a_y * dt;

        p_x += s_x * dt;
        p_y += s_y * dt;

        a_x += r_x * dt;
        a_y += r_y * dt;
    }

}