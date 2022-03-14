import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameController implements KeyListener, Runnable {
    final private Ball ball;
    final private Paddle paddle;
    final private int maxX;
    final private Map bMap;
    final private Game game;
    final private Menu gmenu;
    private HighScore hscore;
    private int bricksRemaining;
    final private Score score;
    final private ScoreBasic scoreBasic;
    final private ScoreCombo scoreCombo;
    final private Sound sounds;
    private long startTime,endTime;
    final private GameView gv;
    final private int speed;

    public void setHscore(HighScore hscore) {
        this.hscore = hscore;
    }

    public Score getScore() {
        return score;
    }

    public GameController(Ball ball, Paddle paddle,Sound sounds, int maxX, Map bMap,Menu menu,GameView gv,Game game) {
        this.ball = ball;
        this.paddle = paddle;
        this.maxX = maxX;
        this.bMap = bMap;
        this.gmenu=menu;
        this.gv=gv;
        this.game=game;
        this.speed=10;
        this.bricksRemaining=game.getNumBricks();
        this.sounds=sounds;
        this.startTime=0;
        this.endTime=0;

        //Score system init
        score=new Score(0);
        scoreBasic=new ScoreBasic(score);
        scoreCombo=new ScoreCombo(score);

        gv.addKeyListener(this);
        gv.setVisible(true);
        gv.setFocusable(true);
    }

    public void moveRight(){
//        System.out.println("Moving right");
        game.setStarted(true);
        paddle.setX(paddle.getX()+20);
        paddle.updateRect();
    }

    public void moveLeft(){
//        System.out.println("Moving left");
        game.setStarted(true);
        paddle.setX(paddle.getX()-20);
        paddle.updateRect();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(paddle.getX()<(maxX-paddle.getWidth())){
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(paddle.getX()>10){
                moveLeft();
            }
        }

        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            System.out.println("Returning to Main Manu");
            try {
                hscore.saveScore(score.getScoreCount(),game.getNumBricks(),game.getPlayername());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            gmenu.setFirstScreen();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public int getBricksRemaining() {
        return bricksRemaining;
    }

    @Override
    public void run() {
        while(game.isStarted()) {

            //ha nincs fokuszba a listenerek
            if (!gv.hasFocus()) {
                gv.requestFocus();
            }

            if (game.isStarted()) {
                ball.updateRect();
                if (paddle.getRect().intersects(ball.getRect())) {
                    try {
                        sounds.playSoundPad();
                    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    }
                    ball.setDiry(-ball.getDiry());
                }

                boolean megvan = false;
                for (int i = 0; i < bMap.getRow() && !megvan; i++) {
                    for (int j = 0; j < bMap.getCol() && !megvan; j++) {
                        Brick br = bMap.getMap()[i][j];
                        int state=br.getState();
                        if (state > 0 && ball.getRect().intersects(br.getBrect())) {
                            megvan = true;

                            try {
                                sounds.playBrickHit();
                            } catch (LineUnavailableException | IOException | InterruptedException | UnsupportedAudioFileException e) {
                                e.printStackTrace();
                            }

                            //ha jobb vagy bal oldalan erintkeztek
                            if (ball.getX() + ball.getWidth() - 3 <= br.getX() || ball.getX() + 3 >= br.getX() + br.getWidth()) {
                                ball.setDirx(-ball.getDirx());
                            }
                            //kulonben fent vagy lent erintkeztek
                            else {
                                ball.setDiry(-ball.getDiry());
                            }

                            br.setState(state-1);
                            if(state==1) bricksRemaining--;


                            if (startTime != 0) {
                                endTime = System.nanoTime() - startTime;
                            }
                            startTime = System.nanoTime();

                            //ha tobb bricket utott ki keves idon belul akkor combo
                            if(endTime!=0 && TimeUnit.SECONDS.convert(endTime,TimeUnit.NANOSECONDS)<2){
                                scoreCombo.addScore(5);
                            }
                            else {
                                scoreBasic.addScore(5);
                            }
                        }
                    }
                }

                //labda mozgasa
                ball.setX(ball.getX() + ball.getDirx());
                ball.setY(ball.getY() + ball.getDiry());

                if (ball.getX() < 0) {
                    ball.setDirx(-ball.getDirx());
                }
                if (ball.getY() < 0) {
                    ball.setDiry(-ball.getDiry());
                }
                if (ball.getX() > maxX - 30) {
                    ball.setDirx(-ball.getDirx());
                }


                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //minden actionnel ujrarajzolunk
                gv.repaint();

            }
        }
    }


}
