import java.awt.*;

public class ScoreCombo implements ScoreStrategy{
    final private Score score;

    public ScoreCombo(Score score) {
        this.score = score;
    }

    @Override
    public void addScore(int count){
        score.setScoreCount(score.getScoreCount()+2*count);
        score.setSize(35);
        score.setColor(Color.ORANGE);
    }
}
