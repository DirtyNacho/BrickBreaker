import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game {
    private boolean started;

    final private Ball ball;
    final private Paddle paddle;
    final private Sound sounds;
    private String playername;
    final private int maxX,maxY,speed,rows,cols,numBricks;
    final private GameView gv;
    final private GameController gc;
    final private Map bMap;
    public GameView getGv() {
        return gv;
    }

    public GameController getGc() {
        return gc;
    }

    public int getNumBricks() {
        return numBricks;
    }

    public String getPlayername() {
        return playername;
    }

    public Game(Menu menu, int rows, int cols, String playername) throws UnsupportedAudioFileException, IOException {
        //inicializalas
        maxX = 700;
        maxY = 600;
        speed = 6;
        numBricks=rows*cols;
        started=true;
        this.rows=rows;
        this.cols=cols;
        this.playername=playername;
        sounds=new Sound();

        //ball, paddle, map letrehozasa
        ball = new Ball(120, 350, 20, 20, speed, -speed);
        paddle = new Paddle(310, 550, 100, 8);
        bMap = new Map(rows, cols);

        gv= new GameView(ball,paddle,maxX,maxY,bMap,this);
        gc=new GameController(ball,paddle,sounds,maxX,bMap,menu,gv,this);
        gv.setGc(gc);

        Thread th=new Thread(gc);
        th.start();
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }
}

