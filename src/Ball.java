import java.awt.*;

public class Ball {
    private int x,y;
    private int dirx,diry;
    final private int width,height;
    private Rectangle rect;

    public Ball(int x, int y,int width,int height, int dirx, int diry) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height= height;
        this.dirx=dirx;
        this.diry=diry;
        updateRect();
    }

    public void updateRect(){
        rect= new Rectangle(x,y,width,height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setDirx(int dirx) {
        this.dirx = dirx;
    }

    public void setDiry(int diry) {
        this.diry = diry;
    }

    public int getDirx() {
        return dirx;
    }

    public int getDiry() {
        return diry;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
