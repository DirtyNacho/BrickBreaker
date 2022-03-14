import java.awt.*;

public class Map {
    public Brick[][] map;
    public int bwidth;
    public int bheight;
    final private int row,col;

    public Brick[][] getMap() {
        return map;
    }

    public Map(int row, int col) {
        this.row = row;
        this.col = col;

        bwidth=540/col;
        bheight=150/row;

        map =new Brick[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j]=new Brick(j*bwidth+80,i*bheight+50,bwidth,bheight,3);
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void draw(Graphics2D g){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int state = map[i][j].getState();
                if(state>0){
                    g.setColor(new Color(255-200/state,255-200/state,255-200/state));
                    g.fill(map[i][j].getBrect());

                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.BLACK);
                    g.draw(map[i][j].getBrect());
                }
            }
        }
    }

    public void setBValue(int value,int row,int col){
        map[row][col].setState(value);
    }
}
