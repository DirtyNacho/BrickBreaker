import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PlayerName extends JPanel implements KeyListener,ActionListener {
    private Menu menu;
    private int rows,cols;
    private String name;
    private final JButton backbutton;
    private HighScore highScore;
    private Game game;
    private JTextField textArea;
    private JLabel label;

    public PlayerName(Menu menu,int rows,int cols,HighScore highScore) {
        this.menu = menu;
        this.rows=rows;
        this.cols=cols;
        this.highScore=highScore;
        setLayout(null);

        Font font=new Font("Segoe Script", Font.BOLD,20);

        label=new JLabel("Player Name");
        label.setBounds(290,100,250,200);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setOpaque(false);

        //insert text
        textArea = new JTextField();
        textArea.setBounds(250,250,250,30);
        textArea.setFont(font);
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);
        textArea.addActionListener(this);
        textArea.addKeyListener(this);

        //BackButton
        backbutton=new JButton("Back to Menu");
        backbutton.setBounds(40,500,200,30);
        backbutton.addActionListener(this);
        backbutton.setFont(font);
        backbutton.setForeground(Color.WHITE);
        backbutton.setOpaque(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setBorderPainted(false);

        add(label);
        add(textArea);
        add(backbutton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menu.getBackImage().getImg(),0,0,null);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            name=textArea.getText();
            try {
                game=new Game(menu,rows,cols,name);
                game.getGc().setHscore(highScore);
            } catch (UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
            menu.playGameScreen();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backbutton){
            System.out.println("Returning to Menu");
            menu.setFirstScreen();
        }

    }
}
