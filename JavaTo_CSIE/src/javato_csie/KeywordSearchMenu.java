package javato_csie;
import java.awt.*;
import java.awt.event.*;

public class KeywordSearchMenu{
    private Frame KF;
    private Panel P1;
    private Panel P2;
    private Panel P3;
    private Label message;
    private Button MoviesGenreBT;
    private Button StarBT;
    private Button StudioNameBT;
    private Button ProducerBT;
    private Button cancel;
    Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    KeywordSearchMenu(DB_JDBC DB,TextArea Layout){
        KF=new Frame("Search");
        KF.setLayout(new FlowLayout(FlowLayout.CENTER));
        KF.setLocation((myScreen.width-280)/2, (myScreen.height-180)/2);
        KF.setSize(280,180);
        P1=new Panel();
        P2=new Panel();
        P3=new Panel();
        P1.setLayout(new FlowLayout(FlowLayout.CENTER));
        P2.setLayout(new GridLayout(2,2));
        P3.setLayout(new FlowLayout(FlowLayout.CENTER));
        MoviesGenreBT=new Button("Search Movies Genre");
        StarBT=new Button("Search MoviesStar");
        StudioNameBT=new Button("Search StudioName");
        ProducerBT=new Button("Search Producer");
        cancel=new Button("Cancel");
        message=new Label("Choose Search method");
        P1.add(message);
        P2.add(MoviesGenreBT);
        P2.add(StarBT);
        P2.add(StudioNameBT);
        P2.add(ProducerBT);
        P3.add(cancel);
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                KF.setVisible(false);
            }
        });
        MoviesGenreBT.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new MoviesGenre(DB,KF,Layout);
                KF.setVisible(false);
            }
        });
        StarBT.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new MovieStar(DB,KF,Layout);
                KF.setVisible(false);
            }
        });
        StudioNameBT.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new StudioName(DB,KF,Layout);
                KF.setVisible(false);
            }
        });
        ProducerBT.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Producer(DB,KF,Layout);
                KF.setVisible(false);
            }
        });
        KF.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                KF.setVisible(false);
            }
        });
        KF.add(P1);
        KF.add(P2);
        KF.add(P3);
        KF.setVisible(true);
    }
}