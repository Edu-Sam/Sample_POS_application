package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
public class OptionScreen extends JFrame{
    String user1;
    String user2;
    String Set_User;
    String Set_User_last_name;
    Timer timer;
  //  Trial salesScreen;
    UserDashBoard salesScreen;
    String p;
     UserTransaction user_transaction;
    public OptionScreen(String title){
        super(title);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().setLayout(null);
        
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(Color.YELLOW);
        panel.setLocation(0,0);
        panel.setSize((screenSize.width/4) *3 ,screenSize.height);
        
        getContentPane().add(panel);
        
        JPanel panel2=new JPanel();
        panel2.setLocation((screenSize.width/4)* 3,0);
        panel2.setBorder(BorderFactory.createRaisedBevelBorder());
         panel2.setSize((screenSize.width/4),screenSize.height);
        panel2.setLayout(null);
        JButton button1=new JButton("SALES SCREEN");
        button1.setLocation(0,0);
        button1.setSize((screenSize.width/4),80);
 //       button1.setBackground(Color.BLACK);
        button1.setFont(new Font("TIMES NEW ROMAN",50,25));
 //       button1.setForeground(Color.BLACK);
   //     panel2.setLocation(0,0);
        panel2.add(button1);
        button1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          getContentPane().setVisible(false);
           getContentPane().setEnabled(false);
          
        /*   timer=new Timer();
           timer.schedule(new RemindTask(),3000);*/
           Set_User=user1;
           user_transaction=new UserTransaction();
           user_transaction.set_cashier_name(Set_User);
       //    salesScreen=new Trial("FRONT OFFICE UPDATE 1:  LOCATION:RESTAURANT    TILL NO:1",user_transaction);
           salesScreen=new UserDashBoard("",user_transaction);
           salesScreen.setUndecorated(true);
           create_user_screens();
    /*       salesScreen.putTitle(user_transaction.get_cashier_name(),user_transaction.get_cashier_name());
           salesScreen.setVisible(true);
           salesScreen.setEnabled(false);
           cancel();*/
   //        m.dispose();
        }
    });
        JButton button2=new JButton("PARAMETERS");
        button2.setSize((screenSize.width/4),80);
 //       button2.setBackground(Color.BLACK);
        button2.setFont(new Font("TIMES NEW ROMAN",50,25));
 //       button2.setForeground(Color.BLACK);
        button2.setLocation(0,(screenSize.height-110));
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                call_admin_param();
            }
        });
        panel2.add(button2);
    //    panel2.setSize((screenSize.width/2),screenSize.height);
        
  //      panel2.setBackground(Color.BLACK);
        panel2.setBackground(new Color(0,128,0));
        getContentPane().add(panel2);
        
          
        this.setSize(screenSize.width,screenSize.height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    public void setName(String name,int number){
      user1=name;
 //     user2=name2;  
    }
    public void cancel(){
        dispose();
    }
    
    private void create_user_screens(){
     //    Build_salesman_screen build_salesman=new Build_salesman_screen(salesScreen,this);
        Build_salesman_screen build_salesman=new Build_salesman_screen(salesScreen,this);
         build_salesman.execute();
    }
    class RemindTask extends TimerTask{
        public void run(){
            
            salesScreen.dispose();
            timer.cancel();
            SalesmanScreen salesman=new SalesmanScreen("ECLIPSE ENTEPRISE:[  --SALESMAN--  ]",Set_User);
            salesman.setVisible(true);
        }
    }
    private void call_admin_param(){
        ParametersDialog param_dialog=new ParametersDialog(this,true);
        param_dialog.setUndecorated(true);
        param_dialog.setVisible(true);
    }
    
    class Build_salesman_screen extends SwingWorker<Boolean,Integer>{
    //    private Trial owner;
        private UserDashBoard owner;
        private OptionScreen previous_owner;
        public Build_salesman_screen(/*Trial owner */ UserDashBoard owner ,OptionScreen previous_owner){
           this.owner=owner;
           this.previous_owner=previous_owner;
         /*  Set_User=user1;
           UserTransaction user_transaction=new UserTransaction();
           user_transaction.set_cashier_name(Set_User);*/
    //       owner=new Trial("FRONT OFFICE UPDATE 1:  LOCATION:RESTAURANT    TILL NO:1",user_transaction);
           owner.putTitle(user_transaction.get_cashier_name(),user_transaction.get_cashier_name());
           owner.setVisible(true);
           owner.setEnabled(false);
           owner.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
           previous_owner.dispose();
        }
        
        @Override
        public Boolean doInBackground() throws Exception{
            setProgress(0);
            waitFor(500);
            for(int i=10;i>=0;i--){
                publish(i);
                  
                setProgress((i * 100)/20);
                waitFor(250);
            }
            
            return true;
        }
        
        @Override
        protected void process(List<Integer> chunks){
             owner.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   
    }
        @Override
        protected void done(){
           owner.Cancel();
            SalesmanScreen salesman=new SalesmanScreen("Easyllite BMS: [ --Salesman--]",Set_User);
            salesman.setUndecorated(true);
            salesman.setVisible(true);
            
            
        }
        
        private void waitFor(int millis){
            try{
                Thread.sleep(millis);
            }
            catch(Exception ex){
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }
     
    
    public static void main(String[] args){
        try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        }
        catch(Exception e){
            
        }
        new OptionScreen("FRONT OFFICE UPDATE 1").setVisible(true);
    }
}