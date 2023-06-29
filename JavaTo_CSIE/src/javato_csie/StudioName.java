package javato_csie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;

public class StudioName{
    private Frame StudioNameF;
    private Panel JCB_P;
    private Panel B_P;
    private JComboBox choose;
    private Button Confirm;
    private Button Cancel;
    Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    StudioName(DB_JDBC DB,Frame F,TextArea Layout){
        this.StudioNameF=new Frame("Search MovieStar");
        this.JCB_P=new Panel();
        this.B_P=new Panel();
        this.Confirm=new Button("Confirm");
        this.Cancel=new Button("Cancel");
        this.choose = new JComboBox();
        this.StudioNameF.setSize(270,150);
        this.StudioNameF.setVisible(true);
        this.StudioNameF.setLocation((myScreen.width-150)/2,(myScreen.height-150)/2);
        this.StudioNameF.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.StudioNameF.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                StudioNameF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StudioNameF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DB.CooseStudio((String)choose.getSelectedItem(),Layout);
                StudioNameF.setVisible(false);
            }
        });
        this.JCB_P.add(choose);
        this.B_P.add(Confirm);
        this.B_P.add(Cancel);
        this.StudioNameF.add(JCB_P);
        this.StudioNameF.add(B_P);
        DB.JComboBoxAdd(3,choose);
    }
}