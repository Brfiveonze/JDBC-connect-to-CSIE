package javato_csie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;

public class Producer{
    private Frame ProducerF;
    private Panel JCB_P;
    private Panel B_P;
    private JComboBox choose;
    private Button Confirm;
    private Button Cancel;
    Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    Producer(DB_JDBC DB,Frame F,TextArea Layout){
        this.ProducerF=new Frame("Search MovieStar");
        this.JCB_P=new Panel();
        this.B_P=new Panel();
        this.Confirm=new Button("Confirm");
        this.Cancel=new Button("Cancel");
        this.choose = new JComboBox();
        this.ProducerF.setSize(270,150);
        this.ProducerF.setVisible(true);
        this.ProducerF.setLocation((myScreen.width-150)/2,(myScreen.height-150)/2);
        this.ProducerF.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.ProducerF.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ProducerF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ProducerF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DB.ChooseProducer((String)choose.getSelectedItem(),Layout);
                ProducerF.setVisible(false);
            }
        });
        this.JCB_P.add(choose);
        this.B_P.add(Confirm);
        this.B_P.add(Cancel);
        this.ProducerF.add(JCB_P);
        this.ProducerF.add(B_P);
        DB.JComboBoxAdd(2,choose);
    }
}

