package javato_csie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;

//1.2.4.(12.13.14.15).
public class MainWin{
        //Login windows
        private DB_JDBC Loginin;
        private Frame Login;
        private Button BtChick;
        private Button BtCancel;
        private TextField TfUser;
        private TextField TfPassword;
        private Label user;
        private Label password;
        //Search of windows
        private Frame win1;
        private Panel WorkBar1;
        private Panel WorkBar2;
        private Panel LayoutPanel;
        private TextArea Layout;
        private Button ShowMoviesInformation;
        private Button Conditionsearch;
        private TextField SearchMT;
        private Button SearchMovie;
        private JComboBox CB;
        //
        KeywordSearchMenu KSW;
        Dimension myScreen=Toolkit.getDefaultToolkit().getScreenSize();
    MainWin(){
        Login=new Frame("Login");
        BtChick=new Button("Login");
        BtCancel=new Button("Cancel");
        TfUser=new TextField("cbb108036",10);
        TfPassword=new TextField("",10);
        user=new Label("User");
        password=new Label("Password");
        CB=new JComboBox();
        this.TfPassword.setEchoChar('*');
        this.Login.setLayout(new GridLayout(3,2));
        this.Login.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.BtChick.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Loginin=new DB_JDBC(TfUser.getText(),TfPassword.getText());
                if(Loginin.isCon){
                    Loginin.JComboBoxAdd(-1,CB);
                    Login.setVisible(false);
                    win1.setVisible(true);
                }
                else{
                    System.out.println("Error!\n");
                }
            }
        });
        this.BtCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Exit");
                System.exit(0);
            }
        });
        this.Login.setLocation((myScreen.width-500)/2,(myScreen.height-400)/2);
        this.Login.setSize(300,150);
        this.Login.add(user);
        this.Login.add(TfUser);
        this.Login.add(password);
        this.Login.add(TfPassword);
        this.Login.add(BtCancel);
        this.Login.add(BtChick);
        this.Login.setVisible(true);
        //
        win1=new Frame("Search");
        SearchMT=new TextField("",20);
        SearchMovie=new Button("Search Movie");
        WorkBar1=new Panel();
        WorkBar2=new Panel();
        LayoutPanel=new Panel();
        Layout=new TextArea("",45,120,TextArea.SCROLLBARS_BOTH);
        Layout.setFont(new Font("monospaced", Font.PLAIN, 12));
        ShowMoviesInformation=new Button("Show All Movies Information");
        Conditionsearch=new Button("Condition Search");
        this.win1.setSize(900,800);
        this.win1.setLocation((myScreen.width-900)/2,(myScreen.height-800)/2);
        this.win1.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.win1.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.ShowMoviesInformation.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Loginin.select(Layout,(String)CB.getSelectedItem());
            }
        });
        this.SearchMovie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Loginin.SearchMovies(SearchMT.getText(),Layout);
            }
        });
        this.Conditionsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                KSW=new KeywordSearchMenu(Loginin,Layout);
            }
        });
        WorkBar1.add(CB);
        WorkBar1.add(ShowMoviesInformation);
        WorkBar2.add(SearchMT);
        WorkBar2.add(SearchMovie);
        WorkBar2.add(Conditionsearch);
        LayoutPanel.add(Layout);
        win1.add(WorkBar1);
        win1.add(WorkBar2);
        win1.add(LayoutPanel);
    }
}