import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener{
    private JButton start,help,quit,options;
    private BcgImage backImage,titleImage;
    private PlayerName playerName;
    private Game game;
    private HighScore hscore;
    private int rows,cols;

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public Menu() throws FileNotFoundException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        setResizable(false);
        setBounds(50,50,700,600);
        backImage=new BcgImage("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\bcg4.jpg");
        titleImage=new BcgImage("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\title.png");
        //pre-initialize highscore
        hscore=new HighScore(this);
        rows=4;
        cols=4;
        setFirstScreen();
    }

    public void setFirstScreen(){
        JPanel all = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backImage.getImg(),0,0,null);
                g.drawImage(titleImage.getImg(),80,80,null);
            }
        };
        start=new JButton("Start Game");
        help = new JButton("Scores");
        options = new JButton("Options");
        quit = new JButton("Quit");

        start.setBounds(250,200,200,40);
        help.setBounds(300,270,100,30);
        options.setBounds(300,340,100,30);
        quit.setBounds(300,410,100,30);

        Font font=new Font("Segoe Script", Font.BOLD,16);
        start.setFont(font);
        start.setForeground(Color.WHITE);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);

        help.setFont(font);
        help.setForeground(Color.WHITE);
        help.setOpaque(false);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);

        options.setFont(font);
        options.setForeground(Color.WHITE);
        options.setOpaque(false);
        options.setContentAreaFilled(false);
        options.setBorderPainted(false);

        quit.setFont(font);
        quit.setForeground(Color.WHITE);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);

        start.addActionListener(this);
        help.addActionListener(this);
        options.addActionListener(this);
        quit.addActionListener(this);

        all.setLayout(null);
        all.add(start);
        all.add(help);
        all.add(options);
        all.add(quit);
        setContentPane(all);
        setVisible(true);
    }

    public void playPlayerNameScreen() throws UnsupportedAudioFileException, IOException {
        System.out.println("Playing player Name screen");
        getContentPane().removeAll();

        //TODO
//        game=new Game(this ,rows,cols);
        playerName=new PlayerName(this,rows,cols,hscore);
//        game.getGc().setHscore(hscore);
        setContentPane(playerName);
        revalidate();
        repaint();
    }

    public void playGameScreen(){
        System.out.println("Playing player Name screen");
        getContentPane().removeAll();
        setContentPane(playerName.getGame().getGv());
        revalidate();
        repaint();
    }
    public void playHighScores() throws FileNotFoundException {
        System.out.println("helping");
        getContentPane().removeAll();
        hscore=new HighScore(this);
        setContentPane(hscore);
        revalidate();
        repaint();
    }

    public void playOptions(){
        getContentPane().removeAll();
        Options opt=new Options(this,game);
        setContentPane(opt);
        revalidate();
        repaint();
    }

    public BcgImage getBackImage() {
        return backImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            System.out.println("Starting game...");
            try {
                playPlayerNameScreen();
            } catch (UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==help){
            System.out.println("Opening help");
            try {
                playHighScores();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==options){
            System.out.println("Setting options");
            playOptions();
        }
        if(e.getSource()==quit){
            System.out.println("Quitting game");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Menu gamemenu=new Menu();
    }

}
