package Game.Arco;

public class ViewPort {
    public int getViewport_width() {
        return viewport_width;
    }

    public int getViewport_height() {
        return viewport_height;
    }

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    int viewport_width;
    int viewport_height;

    int position_x;
    int position_y;

    public ViewPort(int frameWidth, int frameHeight){
        this.viewport_width = frameWidth;
        this.viewport_height = frameHeight;
    }

}

