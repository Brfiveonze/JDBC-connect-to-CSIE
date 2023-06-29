package javato_csie;
import java.sql.*;
import java.awt.*;
import javax.swing.JComboBox;

public class DB_JDBC {
    private String url="jdbc:mysql://web.csie2.nptu.edu.tw:3306/cbb108036_db2021_5_11";
    private String User="";
    private String Password="";
    public boolean isCon=false;
    Connection c;
    Statement stm;
    ResultSet rs;
    DB_JDBC(String User,String Password){
        isCon=Connect(User,Password);
    }
    public boolean Connect(String User,String Password){
        this.User=User;
        this.Password=Password;
        try{
            c=DriverManager.getConnection(this.url,this.User,this.Password);
            stm=c.createStatement();
            System.out.println(c);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public void select(TextArea TA,String s){
        
        try{
            String AllData="",oread="ORDER BY ",search="select * from Movies M,MovieExec ME where M.producerCID=ME.certID ";
            switch(s){
                case "Title":
                    AllData = String.format("%1$-40s", "Title") + String.format("%1$-4s", "Year") + "\t" + String.format("%1$-3s", "length") + "\t" + String.format("%1$-13s", "genre") + String.format("%1$-22s", "studioName") + String.format("%1$-20s", "Producer") + "\n";
                    oread+="title";
                break;
                case "Year":
                    AllData = String.format("%1$-4s", "Year") + "\t" + String.format("%1$-40s", "Title") + String.format("%1$-3s", "length") + "\t" + String.format("%1$-13s", "genre") + String.format("%1$-22s", "studioName") + String.format("%1$-20s", "Producer") + "\n";
                    oread+="year";
                break;
                case "Genre":
                    AllData = String.format("%1$-13s", "genre") + String.format("%1$-40s", "Title") + String.format("%1$-4s", "Year") + "\t" + String.format("%1$-3s", "length") + "\t" + String.format("%1$-22s", "studioName") + String.format("%1$-20s", "Producer") + "\n";
                    oread+="genre";
                break;
                case "StudioName":
                    AllData = String.format("%1$-22s", "studioName") + String.format("%1$-40s", "Title") + String.format("%1$-4s", "Year") + "\t" + String.format("%1$-3s", "length") + "\t" + String.format("%1$-13s", "genre") + "\t" + String.format("%1$-20s", "Producer") + "\n";
                    oread+="studioName";
                break;
            }
            rs=stm.executeQuery(search+oread);
            for(int i=0;i<110;i++){
                AllData=AllData+"-";
            }
            AllData=AllData+"\n";
            while(rs.next()){
                String title,year,length,genre,studioName,name;
                title=rs.getString("M.title");
                year=rs.getString("M.year");
                length=rs.getString("M.length");
                genre=rs.getString("M.genre");
                studioName=rs.getString("M.studioName");
                name=rs.getString("ME.name");
                switch(s){
                    case "Title":
                        AllData = AllData + String.format("%1$-40s", title) + year + "\t" + length + "\t" + String.format("%1$-13s",genre) + String.format("%1$-22s",studioName) + name + "\n";
                    break;
                    case "Year":
                        AllData = AllData + year + "\t" + String.format("%1$-40s", title) + length + "\t" + String.format("%1$-13s",genre) + String.format("%1$-22s",studioName) + name + "\n";
                    break;
                    case "Genre":
                        AllData = AllData + String.format("%1$-13s",genre) + String.format("%1$-40s", title) + year + "\t" + length + "\t" + String.format("%1$-22s",studioName) + name + "\n";
                    break;
                    case "StudioName":
                        AllData = AllData + String.format("%1$-22s",studioName) + String.format("%1$-40s", title) + year + "\t" + length + "\t" + String.format("%1$-13s",genre) + name + "\n";
                    break;
                }
            }
            TA.setText(AllData);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void SearchMovies(String s,TextArea Layout){
        String All="";
        boolean NoSearch=true;
        if(!s.equals("")){
            All="以下有包含關鍵字 '" + s + "' 的結果\n\n";
        }
        else{
            All="未輸入關鍵字\n\n";
        }
        try{
            rs=stm.executeQuery("select * from Movies where title like '%" + s + "%' order by title");
            while(rs.next()){
                All=All + rs.getString("title") + "\n";
                NoSearch=false;
            }
            if(NoSearch){
                Layout.setText(All+"沒有符合的結果");
            }
            else{
                Layout.setText(All);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void JComboBoxAdd(int Code,JComboBox JCB){
        String s="",sh="";
        switch(Code){
            case -1:
                JCB.addItem("Title");
                JCB.addItem("Year");
                JCB.addItem("Genre");
                JCB.addItem("StudioName");
                return;
            case 0:
                s="select * from Movies group by genre order by genre";
                sh="genre";
                break;
            case 1:
                s="select * from MovieStar order by name";
                sh="name";
                break;
            case 2:
                s="select * from MovieExec order by name";
                sh="name";
                break;
            case 3:
                s="select * from Movies group by studioName order by studioName";
                sh="StudioName";
        }
        try{
            rs=stm.executeQuery(s);
            while(rs.next()){
                JCB.addItem(rs.getString(sh));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //////////各按鍵方法
    public void ChooseGenre(String s,TextArea TA){
        String Temp="";
        try{
            Temp="曾出品過 \"" + s + "\" 類型電影的電影公司及數目\n";
            rs=stm.executeQuery("select studioName,count(title) from Movies where genre='" + s + "'" + " group by studioName");
            while(rs.next()){
                String studioName=rs.getString("studioName");
                String count=rs.getString("count(title)");
                Temp=Temp+String.format("%1$-25s", studioName)+ "出品個數:" +count+"\n";
            }
            Temp=Temp+"\n演過 \""+ s + "\"類型的明星與所演出的電影數目\n";
            rs=stm.executeQuery("select starName,count(title) from StarsIn S,(select title from Movies where genre='"+ s + "') m where S.movieTitle=m.title group by starName");
            while(rs.next()){
                String studioName=rs.getString("starName");
                String count=rs.getString("count(title)");
                Temp=Temp+String.format("%1$-25s", studioName)+ "\t演出電影次數:" +count+"\n";
            }
            TA.setText(Temp);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ChooseMovieStar(String s,TextArea TA){
        String Temp="",topStudioName="";
        int count=0;
        try{
            Temp= s + "所參與的電影\n";
            rs=stm.executeQuery("select * from StarsIn where starName='" + s + "'");
            while(rs.next()){
                String movieTitle=rs.getString("movieTitle");
                Temp=Temp+String.format("%1$-25s", movieTitle) + "\n";
            }
            Temp=Temp+"\n曾為以下電影公司出演作品\n";
            rs=stm.executeQuery("select *,count(M.title) from (select * from StarsIn where starName='" + s + "') SI,Movies M where SI.movieTitle=M.title group by studioName");
            while(rs.next()){
                String studioName=rs.getString("studioName");
                if(!(count==-1)){
                    if(rs.getInt("count(M.title)")>count){
                        count=rs.getInt("count(M.title)");
                        topStudioName=studioName;
                    }
                    else if(rs.getInt("count(M.title)")==count)
                        count=-1;
                }
                Temp=Temp+studioName+"\n";
            }
            if(count==-1){
                Temp=Temp+"幫兩間以上公司演出最多作品\n\n曾與下列明星(們)合作過\n";
                count=0;
            }
            else{
                Temp=Temp+ "幫'" +topStudioName +"'公司演出最多作品\n\n曾與下列明星(們)合作過\n";
                count=0;
            }
            rs=stm.executeQuery("select S.starName,count(S.movieTitle) from StarsIn S,(select movieTitle from StarsIn where starName='" 
                    + s + "') mt where mt.movieTitle=S.movieTitle group by S.starName");
            while(rs.next()){
                String starName=rs.getString("S.starName");
                if(!(count==-1)){
                    if(rs.getInt("count(S.movieTitle)")>count){
                        count=rs.getInt("count(S.movieTitle)");
                        topStudioName=starName;
                    }
                    else if(rs.getInt("count(S.movieTitle)")==count)
                        count=-1;
                }
                Temp=Temp+starName+"\n";
            }
            if(count==-1){
                Temp=Temp+"超過1位以上的明星合作過最多次\n";
                count=0;
            }
            else{
                Temp=Temp+ "其中與" +topStudioName +"'合作過最多次\n";
                count=0;
            }
            TA.setText(Temp);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ChooseProducer(String s,TextArea TA){
        String temp=s+"製作過的電影\n";
        try{
            rs=stm.executeQuery("select * from MovieExec ME,Movies M where ME.certID=M.producerCID and ME.name='" + s + "'");
            while(rs.next()){
                temp=temp+rs.getString("M.title")+"\n";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        TA.setText(temp);
    }
    public void CooseStudio(String s,TextArea TA){
        String temp="";
        float TimeTotal=0;
        int n=0;
        try{
            rs=stm.executeQuery("select *,sum(length) from Movies where studioName='" + s + "'");
            while(rs.next()){
                TimeTotal=rs.getInt("sum(length)")/60;
            }
            temp="電影公司 '" + s + "' 所出品的電影總時數為:" + TimeTotal + "(小時)\n\n在 \"" + s + "\" 出品過電影的製作人\n";
            rs=stm.executeQuery("select * from (select * from Movies where studioName='" + s + "') M,MovieExec ME where ME.certID=M.producerCID group by ME.name");
            TimeTotal=0;
            while(rs.next()){
                temp=temp + rs.getString("ME.name") + "\n";
            }
            rs=stm.executeQuery("select avg(M.length) from (select * from Movies where studioName='" + s + "') M,MovieExec ME where ME.certID=M.producerCID");
            while(rs.next()){
                TimeTotal=rs.getFloat("avg(M.length)");
            }
            temp=temp+"\n平均片長為:" + TimeTotal + "分鐘\n";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        TA.setText(temp);
    }
}