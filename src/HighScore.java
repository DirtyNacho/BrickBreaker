import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class HighScore extends JPanel implements ActionListener {
    final private Menu menu;
    final private JTextArea hscores;
    final private JButton backbutton;
    final private char[] loadS;
    private String scores;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menu.getBackImage().getImg(),0,0,null);
    }

    public HighScore(Menu menu) throws FileNotFoundException {
        this.menu = menu;
        loadS=new char[300];
        setLayout(null);
        Font font=new Font("Segoe Script", Font.BOLD,20);


        backbutton=new JButton("Back to Menu");
        backbutton.setBounds(40,500,200,30);
        backbutton.addActionListener(this);
        backbutton.setFont(font);
        backbutton.setForeground(Color.WHITE);
        backbutton.setOpaque(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setBorderPainted(false);

        hscores=new JTextArea();
        hscores.setBounds(200,150,400,200);
        hscores.setFont(font);
        hscores.setForeground(Color.WHITE);
        hscores.setOpaque(false);
        hscores.setEditable(false);
        add(backbutton);
        add(hscores);
        loadHighScores();
    }

    private void loadHighScores() throws FileNotFoundException {
        File file= new File("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\highscores.txt");
        Scanner myReader = new Scanner(file);

        String scoresDisp;
        scores="";
        while(myReader.hasNextLine()){
            scores+=myReader.nextLine();
            scores+='\n';
        }

        //add spaces
        scoresDisp=scores.replace(" ","             ");
        myReader.close();
        hscores.setText("SCORES - PLAYER - BRICKS\n"+scoresDisp);
    }

    public void saveScore(int score,int numBricks,String playername) throws IOException {
        System.out.println("Saving score");
        FileWriter myWriter = new FileWriter("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\highscores.txt");
        myWriter.write(score+" "+playername+" "+numBricks+'\n'+scores);
        myWriter.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!hasFocus())requestFocus();
        if(e.getSource()==backbutton){
            System.out.println("Returning to Menu");
            menu.setFirstScreen();
        }
    }
}
