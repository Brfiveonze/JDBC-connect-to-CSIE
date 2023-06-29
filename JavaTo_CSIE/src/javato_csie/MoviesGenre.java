package javato_csie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;

public class MoviesGenre{
    private Frame MovieGenreF;
    private Panel JCB_P;
    private Panel B_P;
    private JComboBox choose;
    private Button Confirm;
    private Button Cancel;
    Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    MoviesGenre(DB_JDBC DB,Frame F,TextArea Layout){
        this.MovieGenreF=new Frame("Search Movies Genre");
        this.JCB_P=new Panel();
        this.B_P=new Panel();
        this.Confirm=new Button("Confirm");
        this.Cancel=new Button("Cancel");
        this.choose = new JComboBox();
        this.MovieGenreF.setSize(150,150);
        this.MovieGenreF.setVisible(true);
        this.MovieGenreF.setLocation((myScreen.width-100)/2,(myScreen.height-200)/2);
        this.MovieGenreF.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.MovieGenreF.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                MovieGenreF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MovieGenreF.setVisible(false);
                F.setVisible(true);
            }
        });
        this.Confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DB.ChooseGenre((String)choose.getSelectedItem(),Layout);
                MovieGenreF.setVisible(false);
            }
        });
        this.JCB_P.add(choose);
        this.B_P.add(Confirm);
        this.B_P.add(Cancel);
        this.MovieGenreF.add(JCB_P);
        this.MovieGenreF.add(B_P);
        DB.JComboBoxAdd(0,choose);
    }
}
