import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        new ATM("aftabnabi910",1430);
    }
}

class frame  {
    final int WIDTH = 300;
    final int HEIGHT = 400;

    JFrame f;

    frame(){
        Image img = Toolkit.getDefaultToolkit().getImage("ATM.jpg");
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(400,90,WIDTH,HEIGHT);
        f.setResizable(false);
        f.setIconImage(img);
        f.setTitle("ATM");
        f.setLayout(null);
        f.setContentPane(loginPanel());
        f.setResizable(false);
        f.setVisible(true);
    }

    JPanel loginPanel(){
        JPanel totalGUI = new JPanel();
        totalGUI.setBounds(0,0,WIDTH,HEIGHT);
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.lightGray);

        Font font = new Font("Arial",Font.BOLD,20);

        JLabel un = new JLabel("User Name:");
        un.setBounds(60,50,200,22);
        un.setFont(font);

        JLabel passLabel = new JLabel("PIN:");
        passLabel.setBounds(60,130,200,30);
        passLabel.setFont(font);

        JTextField uName = new JTextField();
        uName.setBounds(60,80,150,30);
        uName.setFont(font);

        JTextField pass = new JTextField();
        pass.setBounds(60,160,150,30);
        pass.setFont(font);
        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyChar() == '\b')){
                    pass.setEditable(true);
                }
                else{
                    pass.setEditable(false);
                }
            }
        });

        JButton logIn = new JButton("Login");
        logIn.setBounds(130,200,80,25);
        logIn.setFocusable(false);
        logIn.setForeground(Color.white);
        logIn.setFont(new Font("Times new Roman",Font.PLAIN,20));
        logIn.setBackground(Color.blue);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = uName.getText();
                int pin = Integer.parseInt(pass.getText());
                if(DataBase.Login(name,pin)){
                    f.dispose();
                    new ATM(name,pin);
                }
                else{
                    uName.setBorder(BorderFactory.createLineBorder(Color.red));
                    pass.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

        JLabel signInText = new JLabel("Don't have an account?");
        signInText.setFont(new Font("Times new Roman",Font.PLAIN,15));
        signInText.setBounds(30,260,150,20);

        JButton signIn = new JButton("SignIn");
        signIn.setBounds(170,260,40,18);
        signIn.setFont(new Font("Times new Roman",Font.PLAIN,15));
        signIn.setBackground(Color.lightGray);
        signIn.setForeground(Color.BLUE);
        signIn.setBorder(null);
        signIn.setHorizontalAlignment(SwingConstants.LEFT);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(SigninPanel());
            }
        });


        totalGUI.add(signIn);
        totalGUI.add(signInText);
        totalGUI.add(logIn);
        totalGUI.add(pass);
        totalGUI.add(passLabel);
        totalGUI.add(uName);
        totalGUI.add(un);
        return totalGUI;
    }

    JPanel SigninPanel(){
        Font font = new Font("Times new Roman",Font.BOLD,20);
        Font font2 = new Font("Times new Roman",Font.PLAIN,20);

        JPanel totalUI = new JPanel(null);
        totalUI.setBounds(0,0,WIDTH,HEIGHT);
        totalUI.setBackground(Color.lightGray);

        JLabel textFN = new JLabel("First Name:");
        textFN.setBounds(35,40,100,30);
        textFN.setFont(font);

        JTextField firstName = new JTextField();
        firstName.setBounds(30,70,220,30);
        firstName.setFont(font2);

        JLabel textLN = new JLabel("Last Name:");
        textLN.setBounds(35,100,100,30);
        textLN.setFont(font);

        JTextField lastName = new JTextField();
        lastName.setBounds(30,130,220,30);
        lastName.setFont(font2);

        JLabel userIDtext = new JLabel("User ID:");
        userIDtext.setBounds(35,160,100,30);
        userIDtext.setFont(font);

        JTextField userID = new JTextField();
        userID.setBounds(30,190,220,30);
        userID.setFont(font2);

        JLabel textPIN = new JLabel("PIN:");
        textPIN.setBounds(35,220,60,30);
        textPIN.setFont(font);

        JTextField pin = new JTextField();
        pin.setBounds(30,250,220,30);
        pin.setFont(font2);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(30,295,100,30);
        cancel.setFont(font);
        cancel.setHorizontalAlignment(SwingConstants.LEFT);
        cancel.setFocusable(false);
        cancel.setBorder(null);
        cancel.setBackground(Color.lightGray);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(loginPanel());
            }
        });

        JButton Continue = new JButton("Continue");
        Continue.setBounds(140,295,110,30);
        Continue.setFont(font);
        Continue.setHorizontalAlignment(SwingConstants.LEFT);
        Continue.setFocusable(false);
        Continue.setBackground(Color.blue);
        Continue.setForeground(Color.white);
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name,userid;
                int PIN;
                name = firstName.getText() + " " + lastName.getText();
                userid = userID.getText();
                PIN = Integer.parseInt(pin.getText());
                f.setContentPane(SigninPanel2(name,userid,PIN));
            }
        });

        totalUI.add(Continue);
        totalUI.add(cancel);
        totalUI.add(textPIN);
        totalUI.add(pin);
        totalUI.add(userID);
        totalUI.add(userIDtext);
        totalUI.add(textFN);
        totalUI.add(textLN);
        totalUI.add(firstName);
        totalUI.add(lastName);
        return totalUI;
    }

    JPanel SigninPanel2(String name, String userid, int pin){
        JPanel totalUI = new JPanel(null);
        totalUI.setBounds(0,0,WIDTH,HEIGHT);
        totalUI.setBackground(Color.lightGray);

        JLabel text = new JLabel("Initial Deposit:");
        text.setBounds(30,70,160,30);
        text.setLayout(null);
        text.setFont(new Font("Roman times",Font.BOLD,20));

        JTextField depositTextFeild = new JTextField();
        depositTextFeild.setBounds(30,105,220,30);
        depositTextFeild.setFont(new Font("Roman times",Font.PLAIN,20));
        depositTextFeild.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = depositTextFeild.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '\b') ){
                    depositTextFeild.setEditable(true);
                } else {
                    depositTextFeild.setEditable(false);
                }
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(30,295,100,30);
        cancel.setFont(new Font("Roman times",Font.PLAIN,20));
        cancel.setHorizontalAlignment(SwingConstants.LEFT);
        cancel.setFocusable(false);
        cancel.setBorder(null);
        cancel.setBackground(Color.lightGray);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(loginPanel());
            }
        });

        JButton Continue = new JButton("Continue");
        Continue.setBounds(140,295,110,30);
        Continue.setFont(new Font("Roman times",Font.PLAIN,20));
        Continue.setHorizontalAlignment(SwingConstants.LEFT);
        Continue.setFocusable(false);
        Continue.setBackground(Color.blue);
        Continue.setForeground(Color.white);
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int balance = Integer.parseInt(depositTextFeild.getText());
                DataBase.Signin(name,userid,pin,balance);
                new ATM(userid,pin);
            }
        });

        totalUI.add(Continue);
        totalUI.add(cancel);
        totalUI.add(depositTextFeild);
        totalUI.add(text);
        return totalUI;
    }

}

class DataBase{
    static void Signin(String name, String userid, int pin, int balance){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "insert into accounts (name, id, pin, balance) values (?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, userid);
            pstmt.setInt(3, pin);
            pstmt.setInt(4, balance);
            int count = pstmt.executeUpdate();
            con.close();
            pstmt.close();
        }
        catch (Exception e){System.out.println(e);}
    }

    static boolean Login(String name, int pin){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "select pin from accounts where id=?";
        boolean check= false;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,name);
            ResultSet rs = st.executeQuery();
            rs.next();
            if(pin == rs.getInt(1)){return check=true;}
        }
        catch (Exception e){System.out.println(e);}
        return check;
    }

    static String getName(String name, int pin){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "select name from accounts where id=?";
        boolean check= false;
        String n = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,name);
            ResultSet rs = st.executeQuery();
            rs.next();
            n = rs.getString(1);
        }
        catch (Exception e){System.out.println(e);}
        return n;
    }

    static int getBalance(String userid){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "select balance from accounts where id=?";
        int balance = 0;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,userid);
            ResultSet rs = st.executeQuery();
            rs.next();
            balance = rs.getInt(1);
        }
        catch(Exception e){System.out.println(e);}

        return balance;

    }

    static boolean withdraw(String userid,String amount){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query1 = "select balance from accounts where id=?";
        String query2 = "update accounts set balance=balance-? where id=?";
        int balance = 0;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement st = con.prepareStatement(query1);
            st.setString(1,userid);
            ResultSet rs = st.executeQuery();
            rs.next();
            balance = rs.getInt(1);
        }
        catch(Exception e){System.out.println(e);}

        if(balance>=Integer.parseInt(amount)){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,user, pass);
                PreparedStatement st = con.prepareStatement(query2);
                st.setString(1,amount);
                st.setString(2,userid);
                int rs = st.executeUpdate();
            }
            catch (Exception e){System.out.println(e);}
            return true;
        }
        else{
            return false;
        }
    }

    static void deposit(String userid, String amount){

        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "update accounts set balance=balance+? where id=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user, pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,amount);
            st.setString(2,userid);
            int rs = st.executeUpdate();
        }
        catch (Exception e){System.out.println(e);}
    }

    static void transfer(String userid,String receiver, String amount){
        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";
        String query = "update accounts set balance=balance-? where id=?";
        String query2 = "update accounts set balance=balance+? where id=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user, pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,amount);
            st.setString(2,userid);
            int rs = st.executeUpdate();
            st = con.prepareStatement(query2);
            st.setString(1,amount);
            st.setString(2,receiver);
            rs = st.executeUpdate();
        }
        catch (Exception e){System.out.println(e);}
    }

    static void delete(String userid){
        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pass = "aftab.com";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user, pass);
            PreparedStatement st = con.prepareStatement("delete from accounts where id=?");
            st.setString(1,userid);
            st.executeUpdate();
        }
        catch (Exception e){System.out.println(e);}
    }

}

class ATM{
    final int WIDTH = 450, HEIGHT = 450;
    String userID ; int pin;
    JFrame frame;

    ATM(String userID, int pin){
        this.userID = userID;
        this.pin = pin;
        Image img = Toolkit.getDefaultToolkit().getImage("ATM.jpg");
        frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(img);
        frame.setContentPane(withDraw());
        frame.setResizable(false);
        frame.setTitle("My ATM");

        frame.setVisible(true);

    }

    JPanel homePage(){
        Font font = new Font("Times New Roman",Font.BOLD,20);
        JPanel homePage = new JPanel(null);
        homePage.setBounds(0,0,WIDTH,HEIGHT);
        homePage.setBackground(Color.blue);

        JLabel currentBalanceText = new JLabel("Current Balance:");
        currentBalanceText.setBounds(130,80,200,30);
        currentBalanceText.setFont(new Font("Arial", Font.BOLD,25));
        currentBalanceText.setForeground(Color.white);

        JLabel balance = new JLabel(Integer.toString(DataBase.getBalance(userID)));
        balance.setBounds(0,120,WIDTH,45);
        balance.setFont(new Font("Arial", Font.BOLD,45));
        balance.setForeground(Color.black);
        balance.setHorizontalAlignment(0);
//        balance.setBorder(BorderFactory.createLineBorder(Color.black,2));

        JLabel service = new JLabel("Please Select a service:");
        service.setBounds(120,180,250,40);
        service.setFont(new Font("Arial", Font.BOLD,20));
        service.setForeground(Color.white);

        JButton exit = new JButton("Exit");
        exit.setBackground(Color.red);
        exit.setBounds(50,350,130,30);
        exit.setFocusable(false);
        exit.setForeground(Color.white);
        exit.setFont(font);
        exit.setBorder(BorderFactory.createLineBorder(Color.red));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new frame();
            }
        });

        JButton withDraw = new JButton("Withdraw");
        withDraw.setBackground(Color.white);
        withDraw.setBounds(50,230,130,30);
        withDraw.setFocusable(false);
        withDraw.setForeground(Color.blue);
        withDraw.setFont(font);
        withDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(withDraw());
//                balance.setText(Integer.toString(DataBase.getBalance(userID)));
            }
        });

        JButton deposit = new JButton("Deposit");
        deposit.setBackground(Color.white);
        deposit.setBounds(250,230,130,30);
        deposit.setFocusable(false);
        deposit.setForeground(Color.blue);
        deposit.setFont(font);
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(deposit());
            }
        });

        JButton transfer = new JButton("Transfer");
        transfer.setBackground(Color.white);
        transfer.setBounds(50,290,130,30);
        transfer.setFocusable(false);
        transfer.setForeground(Color.blue);
        transfer.setFont(font);
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(transfer());
            }
        });

        JButton delete = new JButton("Delete");
        delete.setBackground(Color.white);
        delete.setBounds(250,350,130,30);
        delete.setFocusable(false);
        delete.setForeground(Color.blue);
        delete.setFont(font);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.delete(userID);
                frame.dispose();
                new frame();
            }
        });

        homePage.add(delete);
        homePage.add(transfer);
        homePage.add(deposit);
        homePage.add(withDraw);
        homePage.add(exit);
        homePage.add(service);
        homePage.add(balance);
        homePage.add(currentBalanceText);
        homePage.add(helloMsg());
        return homePage;
    }

    JLabel helloMsg(){
        JLabel helloMsg = new JLabel("Hi, "+DataBase.getName(userID,pin));
        helloMsg.setBounds(10,10,150,15);
        helloMsg.setFont(new Font("Roman time",Font.BOLD,13));
        helloMsg.setForeground(Color.white);

        return helloMsg;
    }

    JButton back(){
        JButton back = new JButton("Back");
        back.setFont(new Font("New Times Raman",Font.BOLD,25));
        back.setBounds(50,350,130,30);
        back.setBorder(null);
        back.setBackground(Color.lightGray);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(homePage());
            }
        });
        return back;
    }

    JButton next(){
        JButton next = new JButton("Continue");
        next.setFont(new Font("New Times Raman",Font.BOLD,25));
        next.setBackground(Color.lightGray);
        next.setForeground(Color.blue);
        next.setBounds(250,350,130,30);
        next.setBorder(null);
        return next;
    }

    JPanel withDraw(){
        JPanel totalUI = new JPanel(null);
        totalUI.setBounds(0,0,WIDTH,HEIGHT);
        totalUI.setBackground(Color.lightGray);

        JLabel MSG = new JLabel("Enter the Amount:");
        MSG.setForeground(Color.BLACK);
        MSG.setFont(new Font("Arial",Font.BOLD,17));
        MSG.setBounds(80,100,160,20);

        JTextField amountField = new JTextField();
        amountField.setBounds(80,120,220,30);
        amountField.setFont(new Font("Roman times",Font.PLAIN,20));
        amountField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountField.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '\b') ){
                    amountField.setEditable(true);
                } else {
                    amountField.setEditable(false);
                }
            }
        });

        JButton next = next();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(amountField.getText(), "")){
                    boolean successful = DataBase.withdraw(userID,amountField.getText());
                    if (!successful){
//                        JLabel errorMSG = new JLabel("insufficient balance");
//                        errorMSG.setForeground(Color.red);
//                        errorMSG.setFont(new Font("Arial",Font.BOLD,15));
//                        errorMSG.setBounds(80,155,150,20);
//                        totalUI.add(errorMSG);
                        amountField.setText("");
                    }
                    else{
                        frame.setContentPane(homePage());
                    }
                }
            }
        });

        totalUI.add(MSG);
//        totalUI.add(errorMSG);
        totalUI.add(amountField);
        totalUI.add(helloMsg());
        totalUI.add(back());
        totalUI.add(next);
        return totalUI;
    }

    JPanel deposit(){
        JPanel totalUI = new JPanel(null);
        totalUI.setBounds(0,0,WIDTH,HEIGHT);
        totalUI.setBackground(Color.lightGray);

        JLabel MSG = new JLabel("Enter the Amount:");
        MSG.setForeground(Color.BLACK);
        MSG.setFont(new Font("Arial",Font.BOLD,17));
        MSG.setBounds(80,100,160,20);

        JTextField amountField = new JTextField();
        amountField.setBounds(80,120,220,30);
        amountField.setFont(new Font("Roman times",Font.PLAIN,20));
        amountField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountField.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '\b') ){
                    amountField.setEditable(true);
                } else {
                    amountField.setEditable(false);
                }
            }
        });

        JButton next = next();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(amountField.getText(), "")){
                    DataBase.deposit(userID,amountField.getText());
                    frame.setContentPane(homePage());
                }
            }
        });

        totalUI.add(MSG);
        totalUI.add(amountField);
        totalUI.add(helloMsg());
        totalUI.add(back());
        totalUI.add(next);
        return totalUI;
    }

    JPanel transfer(){
        JPanel totalUI = new JPanel(null);
        totalUI.setBounds(0,0,WIDTH,HEIGHT);
        totalUI.setBackground(Color.lightGray);

        JLabel amountMSG = new JLabel("Enter the Amount:");
        amountMSG.setForeground(Color.BLACK);
        amountMSG.setFont(new Font("Arial",Font.BOLD,17));
        amountMSG.setBounds(80,100,160,20);

        JLabel MSG = new JLabel("Enter the userID:");
        MSG.setForeground(Color.BLACK);
        MSG.setFont(new Font("Arial",Font.BOLD,17));
        MSG.setBounds(80,175,160,20);

        JTextField amountField = new JTextField();
        amountField.setBounds(80,120,220,30);
        amountField.setFont(new Font("Roman times",Font.PLAIN,20));
        amountField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountField.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '\b') ){
                    amountField.setEditable(true);
                } else {
                    amountField.setEditable(false);
                }
            }
        });

        JTextField name = new JTextField();
        name.setBounds(80,200,220,30);
        name.setFont(new Font("Roman times",Font.PLAIN,20));

        JButton next = next();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(amountField.getText(), "")){
                    DataBase.transfer(userID , name.getText() , amountField.getText());
                    frame.setContentPane(homePage());
                }
            }
        });

        totalUI.add(amountMSG);
        totalUI.add(MSG);
        totalUI.add(amountField);
        totalUI.add(name);
        totalUI.add(helloMsg());
        totalUI.add(back());
        totalUI.add(next);
        return totalUI;
    }
}
