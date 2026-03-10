package Player;


import world.GamePanel;
import world.ViewPort;

public class Movement {
    public double s_x = 0;
    public double s_y = 0;
    public double a;          
    public double max_speed;  
    public double drag;       
    public double grav;

    boolean on_ground = false;
    double cool_down = 0.0;
    double counter_aux = 0.0;

    public Player player;
    public GamePanel gamePanel;
    public Controls controls;
    public Resolve resolve;
    public ViewPort viewPort;
    public Movement(Controls controller,
                    Resolve solve,
                    Player player,
                    double acceleration,
                    double max_speed,
                    double drag,
                    double gravity) {

        this.controls = controller;
        this.gamePanel = this.controls.panel;
        this.viewPort = this.controls.panel.viewPort;
        this.resolve = solve;
        this.player = player;
        this.a = acceleration;
        this.max_speed = max_speed;
        this.drag = drag;
        this.grav = gravity;
    }

    public void tick(double dt) {
//        player.update();
        if (dt > 0.05) dt = 0.05;
        if (dt <= 0) return;

        double target_X = 0.0;

        cool_down += dt;

        if (controls.aDown && !controls.dDown) target_X = max_speed;
        if (controls.dDown && !controls.aDown) target_X = -max_speed;



        double maxChange = a * dt;

        double diff_X = target_X - s_x;


        if (diff_X >  maxChange) diff_X =  maxChange;
        if (diff_X < -maxChange) diff_X = -maxChange;



        int prevX = this.player.centerX;
        int prevY = this.player.centerY;

        s_x += diff_X;
        int d_x = ((int)(Math.round(s_x * dt)));
        boolean can_move_x = this.resolve.solveX(d_x);
        if(can_move_x) {
            this.player.tick((int) (Math.round(s_x * dt)), 0);
        }
        else {
            this.player.centerX = prevX;
            s_x = 0;
        }


        s_y += grav * dt;
        int d_y = ((int)(Math.round(s_y * dt)));
        boolean can_move_y = this.resolve.solveY(d_y);
        if(can_move_y) {
            this.player.tick(0, (int) (Math.round(s_y * dt)));
        }
        else {
            this.player.centerY = prevY;
            if(s_y < 0){
                on_ground = true;
            }
            s_y = 0;
        }
        if(s_y == 0 && on_ground ){
            jump();
        }
        if(controls.shiftDown && cool_down >= 1){
            dash();
            controls.shiftDown = false;
        }
        s_x *= drag;
        s_y *= drag;

        counter_aux -= dt;

        this.player.update();
    }
    void jump(){
        if(controls.spaceDown){
            s_y = 900;

            on_ground = false;
        }
    }
    void dash(){
        if (controls.dDown && !controls.aDown) {
            s_x = -650;
            cool_down = 0;

            counter_aux = 0.5;
            return;
        }
        if (controls.aDown && !controls.dDown) {
            s_x = 650;
            cool_down = 0;

            counter_aux = 0.5;
            return;
        }
    }




}