import java.awt.*;

public class Paddle {
    private int x,y;
    final private int width,height;
    private Rectangle rect;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        updateRect();
    }

    public void updateRect(){
        rect= new Rectangle(x,y,width,height);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
