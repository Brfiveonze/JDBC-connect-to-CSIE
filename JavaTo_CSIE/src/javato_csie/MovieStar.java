package javato_csie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;

public class MovieStar{
    private Frame star;
    private Panel JCB_P;
    private Panel B_P;
    private JComboBox choose;
    private Button Confirm;
    private Button Cancel;
    Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    MovieStar(DB_JDBC DB,Frame F,TextArea Layout){
        this.star=new Frame("Search MovieStar");
        this.JCB_P=new Panel();
        this.B_P=new Panel();
        this.Confirm=new Button("Confirm");
        this.Cancel=new Button("Cancel");
        this.choose = new JComboBox();
        this.star.setSize(270,150);
        this.star.setVisible(true);
        this.star.setLocation((myScreen.width-150)/2,(myScreen.height-150)/2);
        this.star.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.star.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                star.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                star.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DB.ChooseMovieStar((String)choose.getSelectedItem(),Layout);
                star.setVisible(false);
            }
        });
        this.JCB_P.add(choose);
        this.B_P.add(Confirm);
        this.B_P.add(Cancel);
        this.star.add(JCB_P);
        this.star.add(B_P);
        DB.JComboBoxAdd(1,choose);
    }
}
