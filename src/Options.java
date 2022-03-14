import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel implements ActionListener {
    final private Menu menu;
    final private JButton backbutton;
    final JComboBox<Integer> cb1;
    final JComboBox<Integer> cb2;

    public Options(Menu menu, Game game) {
        this.menu = menu;
        Font font=new Font("Segoe Script", Font.BOLD,20);


        setLayout(null);
        backbutton=new JButton("Back to Menu");
        backbutton.setBounds(40,500,200,30);
        backbutton.addActionListener(this);
        backbutton.setFont(font);
        backbutton.setForeground(Color.WHITE);
        backbutton.setOpaque(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setBorderPainted(false);
        add(backbutton);

        JLabel rowLab=new JLabel("Rows");
        JLabel colLab=new JLabel("Columns");
        rowLab.setBounds(280,220,60,30);
        colLab.setBounds(360,220,100,30);
        rowLab.setFont(font);
        rowLab.setForeground(Color.WHITE);
        colLab.setFont(font);
        colLab.setForeground(Color.WHITE);
        add(rowLab);
        add(colLab);

        Integer[] choices={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        cb1=new JComboBox<Integer>(choices);
        cb2=new JComboBox<Integer>(choices);
        cb1.setBounds(280,250,50,30);
        cb2.setBounds(380,250,50,30);
        cb1.setSelectedIndex(4);
        cb2.setSelectedIndex(4);
        add(cb1);
        add(cb2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menu.getBackImage().getImg(),0,0,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!hasFocus())requestFocus();
        if(e.getSource()==backbutton){
            System.out.println("Returning to Menu");
            menu.setRows((Integer) cb1.getSelectedItem());
            menu.setCols((Integer) cb2.getSelectedItem());
            menu.setFirstScreen();
        }
    }
}
