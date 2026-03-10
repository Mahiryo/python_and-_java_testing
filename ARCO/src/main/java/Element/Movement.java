//package Element;
//
//
//import Player.Controls;
//import Player.Resolve;
//import world.GamePanel;
//import world.ViewPort;
//
//public class Movement {
//    public double s_x = 0;
//    public double s_y = 0;
//    public double a;
//    public double max_speed;
//    public double drag;
//    public double grav;
//
//    boolean on_ground = false;
//    double cool_down = 0.0;
//    double counter_aux = 0.0;
//
//    GamePanel gamePanel;
//    Controller controller;
//    Resolve resolve;
//    ViewPort viewPort;
//    public Movement(GamePanel panel,
//                    Controller controller,
//                    double acceleration,
//                    double max_speed,
//                    double drag,
//                    double gravity) {
//
//
//        this.gamePanel = panel;
//        this.controller = controller;
//        this.resolve = new Resolve(this.gamePanel);
//
//        this.a = acceleration;
//        this.max_speed = max_speed;
//        this.drag = drag;
//        this.grav = gravity;
//    }
//
//    public void tick(double dt) {
//        if (dt > 0.05) dt = 0.05;
//        if (dt <= 0) return;
//
//        double target_X = 0.0;
//
//        cool_down += dt;
//
//        double maxChange = a * dt;
//
//        double diff_X = target_X - s_x;
//
//
//        if (diff_X >  maxChange) diff_X =  maxChange;
//        if (diff_X < -maxChange) diff_X = -maxChange;
//
//        int prevX = this.viewPort.position_x;
//        int prevY = this.viewPort.position_y;
//
//        s_x += diff_X;
//        int d_x = ((int)(Math.round(s_x * dt)));
//        boolean can_move_x = this.resolve.solveX(d_x);
//        if(can_move_x) {
//            this.viewPort.position_x += (int) (Math.round(s_x * dt));
//        }
//        else {
//            this.viewPort.position_x = prevX;
//            s_x = 0;
//        }
//
//
//        s_y += grav * dt;
//        int d_y = ((int)(Math.round(s_y * dt)));
//        boolean can_move_y = this.resolve.solveY(d_y);
//        if(can_move_y) {
//            this.viewPort.position_y += (int) (Math.round(s_y * dt));
//        }
//        else {
//            this.viewPort.position_y = prevY;
//            if(s_y < 0){
//                on_ground = true;
//            }
//            s_y = 0;
//        }
//
//
//        if(s_y == 0 && on_ground ){
//            jump();
//        }
//
//
//        s_x *= drag;
//        s_y *= drag;
//    }
//    void jump(){
//            s_y = 450;
//            on_ground = false;
//    }
//}