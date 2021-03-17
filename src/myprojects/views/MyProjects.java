package myprojects;
import javax.swing.*;
import java.awt.*;
// import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Properties;
import java.io.*;
import myprojects.easyllite.EasyllitePanel;
import myprojects.easyllite.EasylliteTraversalPolicy;
import java.awt.Graphics;
import java.net.URL;



public class MyProjects extends JFrame implements KeyListener{
//    private static final String Url="jdbc:sqlserver://localhost:49243;DatabaseName=EDWIN;method=Cursor";
    private String Url;
//    private String Url;
    private String host_name="";
    private String sql_database_name="";
    private String host_port="";
    private String sql_instance_name="";
    private static final String user_name="sa";
    private static final String password="123456";
    public PreparedStatement get_users=null;
    public JPasswordField field2;
    int k=0;
    int button_text;
    Connection con=null;
    JButton button1;
    JButton button2,button_1,button_2,button_3,button_4;
    JButton button_5,button_6,button_7,button_8,button_9;
    //JPanel main_panel;
    EasyllitePanel main_panel,panel2;
    Timer timer;
    SplashScreen sp;
    String Current_cashier;
    String Current_User;
    String Cashier_last_name;
    String Current_User_last_name;
    JButton okButton;
    private EasylliteTraversalPolicy new_policy;
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    
    
    Scanner scanner=new Scanner(System.in);
    public int pass_text=0;
    public MyProjects(String title,String a){
       super(title);
        connect();
        try{
         for(UIManager.LookAndFeelInfo  info: UIManager.getInstalledLookAndFeels()){
             if(("Windows").equals(info.getName())){
                 UIManager.setLookAndFeel(info.getClassName());
                 SwingUtilities.updateComponentTreeUI(this);
             }}}
         catch(Exception e){
             
         }
        try{
           
        Connection con=DriverManager.getConnection(Url,user_name,password);
        get_users=con.prepareStatement("SELECT * FROM SYSTEM_USERS WHERE PASSWORD=?");
        
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null,"No database connection","ERROR",JOptionPane.ERROR_MESSAGE);
            quit();
            System.exit(0);
            exception.printStackTrace();
        }
        String run_id="0702181403";
        try{
            InputStream in=new FileInputStream("eclipse.ini");
            Properties properties=new Properties();
            properties.load(in);
            run_id=properties.getProperty("run_system");
            if(!(run_id.equalsIgnoreCase("0702181403"))){
                JOptionPane.showMessageDialog(null,"Cannot run program","ERROR",JOptionPane.ERROR_MESSAGE);
                quit();
                System.exit(0);
            }
        
            else{
        getContentPane().setLayout(null);
        main_panel=new EasyllitePanel();
        main_panel.setLayout(null);
        
        JPanel topPanel=new JPanel();
        topPanel.setLocation(0,0);
        topPanel.setSize(screenSize.width,40);
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(null);
  //      main_panel.add(topPanel);
        
        JLabel topLabel=new JLabel(title);
        topLabel.setLocation(screenSize.width * 1/3,0);
        topLabel.setSize(400,25);
        topLabel.setFont(new Font("MONSERRAT",Font.BOLD,20));
        topLabel.setForeground(Color.GREEN);
        main_panel.add(topLabel);
        
    
        
        JLabel label=new JLabel("Username");
        label.setLocation(800,170);
        label.setSize(200,40);
        label.setFont(new Font("TAHOMA",Font.BOLD,16));
        label.setForeground(new Color(128,128,0));
       main_panel.add(label);
       
       JTextField field=new JTextField(10);
   //     field.setLocation(800,130);
        field.setLocation(910,170);
        field.setSize(400,30);
      //  field.setBackground(new Color(250,235,215));
        field.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.GRAY));
        field.setOpaque(false);
        field.setEditable(false);
        field.setFocusable(false);
       // getContentPane().add(field);
        main_panel.add(field);
        
        
        JLabel label2=new JLabel("Password");
      //  label2.setLocation(1150,230);
        label2.setLocation(800,270);
        label2.setSize(200,40);
        label2.setFont(new Font("TAHOMA",Font.BOLD,16));
        label2.setForeground(new Color(128,128,0));
     //   getContentPane().add(label2);
       main_panel.add(label2);
       
        field2=new JPasswordField(10);
      //  field2.setLocation(800,230);
        field2.setLocation(910,270);
        field2.setSize(400,40);
        field2.setFont(new Font("SERIF",Font.PLAIN,12));
        field2.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.GRAY));
        field2.requestFocusInWindow();
        field2.setOpaque(false);
      //  getContentPane().add(field2);
        main_panel.add(field2);
        
        okButton=new JButton("OK");
        okButton.setLocation(850,440);
        okButton.setSize(180,40);
        okButton.setFont(new Font("Serif",Font.BOLD,14));
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
             pass_text=Integer.parseInt(field2.getText());
            get_user_details(pass_text);}
                catch(Exception ex){
                    JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+ex.getMessage(),
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            
            }
        });
        //okButton.setBackground(Color.BLUE);
        
       // getContentPane().add(okButton);
        main_panel.add(okButton);
        
        JButton cancelButton=new JButton("QUIT");
        cancelButton.setLocation(1090,440);
        cancelButton.setSize(180,40);
        cancelButton.setFont(new Font("Serif",Font.BOLD,14));
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               int option=JOptionPane.showConfirmDialog(null,"ARE YOUR SURE YOU WANT TO QUIT?",
                        "QUIT",JOptionPane.YES_NO_OPTION);
              if(option==0){
              quit();
              }
              
            }
        });
     //   getContentPane().add(cancelButton);
       main_panel.add(cancelButton);
        
        JPanel panel=new JPanel();
        JLabel label4=new JLabel("LOGIN FORM");
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("Serif",60,60));
 //       label4.setLocation(10,10);
        label4.setSize(100,100);
        panel.add(label4);
        panel.setLocation(0,0);
        panel.setBackground(new Color(233,150,122));
 //       panel.setBackground(new Color(102,205,170));
        panel.setSize(1500,120);
        panel.setBorder(BorderFactory.createEtchedBorder());
   
       
   
        
        button_1=new JButton("1");
        button_1.setFont(new Font("Serif",Font.BOLD,20));
        button_1.setLocation(30,170);
        button_1.setBackground(Color.WHITE);
        button_1.setSize(120,70);
        button_1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_1.getText());
            }
        });
        main_panel.add(button_1);
        button_2=new JButton("2");
        button_2.setFont(new Font("Serif",Font.BOLD,20));
        button_2.setLocation(200,170);
        button_2.setBackground(Color.WHITE);
        button_2.setSize(120,70);
        button_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_2.getText());
            }
        });
        main_panel.add(button_2);
        button_3=new JButton("3");
        button_3.setFont(new Font("Serif",Font.BOLD,20));
        button_3.setLocation(370,170);
        button_3.setBackground(Color.WHITE);
        button_3.setSize(120,70);
        button_3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_3.getText());
            }
        });
        main_panel.add(button_3);
        button_4=new JButton("4");
        button_4.setFont(new Font("Serif",Font.BOLD,20));
        button_4.setLocation(540,170);
        button_4.setSize(120,70);
        button_4.setBackground(Color.WHITE);
        button_4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_4.getText());
            }
        });
        main_panel.add(button_4);
        button_5=new JButton("5");
        button_5.setFont(new Font("Serif",Font.BOLD,20));
        button_5.setLocation(30,280);
        button_5.setSize(120,70);
        button_5.setBackground(Color.WHITE);
        button_5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_5.getText());
            }
        });
        main_panel.add(button_5);
        button_6=new JButton("6");
        button_6.setFont(new Font("Serif",Font.BOLD,20));
        button_6.setLocation(200,280);
        button_6.setBackground(Color.WHITE);
        button_6.setSize(120,70);
        button_6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_6.getText());
            }
        });
        main_panel.add(button_6);
        button_7=new JButton("7");
        button_7.setFont(new Font("Serif",Font.BOLD,20));
        button_7.setLocation(370,280);
        button_7.setBackground(Color.WHITE);
        button_7.setSize(120,70);
        button_7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_7.getText());
            }
        });
        main_panel.add(button_7);
        button_8=new JButton("8");
        button_8.setFont(new Font("Serif",Font.BOLD,20));
        button_8.setLocation(540,280);
        button_8.setSize(120,70);
        button_8.setBackground(Color.WHITE);
        button_8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_8.getText());
            }
        });
        main_panel.add(button_8);
        button_9=new JButton("9");
        button_9.setFont(new Font("Serif",Font.BOLD,20));
        button_9.setLocation(30,400);
        button_9.setBackground(Color.WHITE);
        button_9.setSize(120,70);
        button_9.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button_9.getText());
            }
        });
        main_panel.add(button_9);
        button2=new JButton("0");
        button2.setFont(new Font("Serif",Font.BOLD,20));
        button2.setLocation(200,400);
        button2.setBackground(Color.WHITE);
        button2.setSize(120,70);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText(field2.getText()+button2.getText());
            }
        });
        main_panel.add(button2);
        
        JButton button3=new JButton("C");
        button3.setFont(new Font("Serif",Font.BOLD,20));
        button3.setLocation(370,400);
        button3.setBackground(Color.WHITE);
        button3.setSize(120,70);
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                field2.setText("");
            }
        });
        main_panel.add(button3);
        
            JButton button4=new JButton("KEYBOARD");
        button4.setFont(new Font("Serif",Font.BOLD,14));
        button4.setBackground(Color.WHITE);
        button4.setLocation(540,400);
        button4.setSize(120,70);
        main_panel.add(button4);
        
        Vector<Component> order=new Vector<Component>(3);
        order.add(field2);
        order.add(okButton);
        order.add(cancelButton);
        new_policy=new EasylliteTraversalPolicy(order);
        this.setFocusTraversalPolicy(new_policy);
  //      this.setSize(screenSize.width,600);
        this.setSize(screenSize.width,screenSize.height);
        this.getRootPane().setDefaultButton(okButton);
        main_panel.setLocation(0,0);
        main_panel.setSize(screenSize.width,screenSize.height);
     //   main_panel.setSize(screenSize.width,600);
        getContentPane().add(main_panel); 
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
            }
        catch(IOException ex){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+ex.getMessage(),
                            "ERROR",JOptionPane.ERROR_MESSAGE);
      //      ex.printStackTrace();
        }
      
       
 
        
    }
 
    public void putUser(){
     Current_User=this.Current_cashier;
     Current_User_last_name=this.Cashier_last_name;
    }
    public List<Cashier> get_user_details(int pass){
        List<Cashier> user_details=null;
          ResultSet set=null;
         try{
             get_users.setInt(1,pass);
             set=get_users.executeQuery();
             user_details=new ArrayList<Cashier>();
             while(set.next()){
                 user_details.add(new Cashier(set.getString("CASHIER_NAME"),
                         set.getString("LAST_NAME"),
                         set.getInt("PASSWORD")));
   
             }
             
             if(user_details.isEmpty()){
              JOptionPane.showMessageDialog(null,"INCORRECT PASSWORD","ERROR",
                         JOptionPane.ERROR_MESSAGE);   
             }
             else if(field2.getPassword()==null){
                 JOptionPane.showMessageDialog(null,"INCORRECT PASSWORD","ERROR",
                         JOptionPane.ERROR_MESSAGE);
             }
             
             else{
   //   this.dispose();
   //     Current_cashier=new Cashier();
        Current_cashier=user_details.get(0).getUserName();
        Cashier_last_name=user_details.get(0).getLastName();
        putUser();
        main_panel.setVisible(false);
        main_panel.setEnabled(false);
        getContentPane().setBackground(Color.WHITE);
        timer=new Timer(); 
        timer.schedule(new RemindTask(),5000);
        sp=new SplashScreen();
        sp.setVisible(true);
   //   OptionScreen optionscreen=new OptionScreen();
   //   optionscreen.setVisible(true);
       }
              }
         catch(SQLException sqlexception){
             sqlexception.printStackTrace();
         }
         finally{
             try{
               set.close();
             }
             catch(SQLException exception){
                 exception.printStackTrace();
             }}
      return user_details;
    }
    private void quit(){
        this.dispose();
    }
    class RemindTask extends TimerTask{
        public void run(){
     sp.dispose();
     quit();
     timer.cancel();
     OptionScreen optionscreen=new OptionScreen("FRONT OFFICE UPDATE 1");
     optionscreen.setName(Current_User,1);
     optionscreen.setVisible(true);
        }
    }
    public void keyPressed(KeyEvent e){
        key_focus_listener(e);         
    }
    
    public void keyReleased(KeyEvent e){
        key_focus_listener(e);         
    }
    
    public void keyTyped(KeyEvent e){
        key_focus_listener(e);
    }
    
    private void key_focus_listener(KeyEvent e){
         if(e.getKeyCode()==KeyEvent.VK_ENTER){
              try{
             pass_text=Integer.parseInt(field2.getText());
            get_user_details(pass_text);}
                catch(Exception ex){
                    JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+ex.getMessage(),
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
         }
         
         else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
             int option=JOptionPane.showConfirmDialog(null,"ARE YOUR SURE YOU WANT TO QUIT?",
                        "QUIT",JOptionPane.YES_NO_OPTION);
              if(option==0){
              quit();
              }
         }
    }
    
    private void connect(){
        try{
            InputStream in=new FileInputStream("eclipse.ini");
            Properties properties=new Properties();
            properties.load(in);
            host_name=properties.getProperty("host_name");
            host_port=properties.getProperty("host_port");
            sql_instance_name=properties.getProperty("sql_instance_name");
            sql_database_name=properties.getProperty("sql_database_name");
       //     Url="jdbc:sqlserver://"+ host_name + ":" + host_port + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            Url="jdbc:sqlserver://"+ host_name + "\\" + sql_instance_name + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            System.out.println("The url is " + Url);
            
            
            }
            catch(IOException ex){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database" + host_name + host_port + sql_database_name,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            quit();
            System.exit(0);
     
        }
    }
    
  public static void main(String[] args) {
      
        try{
         for(UIManager.LookAndFeelInfo  info: UIManager.getInstalledLookAndFeels()){
             if(("Windows").equals(info.getName())){
                 UIManager.setLookAndFeel(info.getClassName());
             }}}
         catch(Exception e){
             
         }
        MyProjects project=new MyProjects("Easyllite BMS: [ --CASHIER POINT--]","");
        project.setUndecorated(true);
        project.setVisible(true);
    //    new MyProjects("Easyllite BMS: [ --CASHIER POINT-- ]","").setVisible(true);
      }
}


