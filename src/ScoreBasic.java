public class ScoreBasic implements ScoreStrategy{
    final private Score score;

    public ScoreBasic(Score score) {
        this.score = score;
    }

    @Override
    public void addScore(int count){
        score.setScoreCount(score.getScoreCount()+count);
        score.setSize(25);
    }
}
