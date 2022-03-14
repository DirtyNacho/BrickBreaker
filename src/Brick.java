import java.awt.*;

public class Brick {
    final private int x,y;
    final private Rectangle brect;
    final private int width,height;
    private int state;

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public Brick(int x, int y, int width, int height, int state) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.state=state;

        brect=new Rectangle(x,y,width,height);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public Rectangle getBrect() {
        return brect;
    }
}
