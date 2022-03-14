import java.awt.*;

public class Score {
    private int scoreCount;
    private int size;
    private Color color;

    public void setSize(int size) {
        this.size = size;
        this.color=Color.WHITE;
    }

    public int getSize() {
        return size;
    }

    public Score(int scoreCount) {
        this.scoreCount = scoreCount;
        this.size=25;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getScoreCount() {
        return scoreCount;
    }
}
