import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    final private Ball ball;
    final private Paddle paddle;
    final private int maxX,maxY;
    final private Map bMap;
    final private Game game;
    private GameController gc;

    public void setGc(GameController gc) {
        this.gc = gc;
    }

    public GameView(Ball ball, Paddle paddle, int maxX, int maxY, Map bMap, Game game) {
        this.ball = ball;
        this.paddle = paddle;
        this.maxX = maxX;
        this.maxY = maxY;
        this.bMap = bMap;
        this.game=game;
        setLayout(null);
        setVisible(true);
        setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,maxX-8,maxY-8);

        //map
        bMap.draw((Graphics2D) g);

        //score
        g.setColor(gc.getScore().getColor());
        g.setFont(new Font("serif",Font.BOLD,gc.getScore().getSize()));
        g.drawString(""+gc.getScore().getScoreCount(),maxX-75,35);

        //border
        g.setColor(Color.BLUE);
        g.fillRect(0,0,3,maxY-8);
        g.fillRect(0,0,maxX-8,3);
        g.fillRect(maxX-15,0,3,maxY-8);

        //paddle
        g.setColor(Color.CYAN);
        ((Graphics2D) g).fill(paddle.getRect());

        //ball
        g.setColor(Color.BLUE);
        g.fillOval(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());

        //endgame
        if(gc.getBricksRemaining()==0){
            game.setStarted(false);
            ball.setDirx(0);
            ball.setDiry(0);
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won, Score: "+gc.getScore().getScoreCount()+"",200,300);

            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("  Press Enter to continue",200,350);
        }
        //lost the ball
        else if(ball.getY()>maxY){
            game.setStarted(false);
            ball.setDirx(0);
            ball.setDiry(0);
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over, Score: "+gc.getScore().getScoreCount()+"",200,300);

            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("  Press Enter to continue",200,350);
        }
    }

}
