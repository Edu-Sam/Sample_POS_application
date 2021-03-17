
package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class SalesScreen extends JFrame implements ActionListener{
    JButton page;
    JPanel pagePanel,pa;
    JButton itemButton;
    
    public SalesScreen(String title){
        super(title);
  
        JPanel panel2=new JPanel(new BorderLayout(2,2));
       String header[]={"ITEM","UNIT PRICE","QTY","PRICE","TOTAL"};
       DefaultTableModel model=new DefaultTableModel(header,50);
       JTable myTable=new JTable(model){
        @Override
        public boolean isCellEditable(int args0,int args1){
           return false;
       }
    };
       JScrollPane pane=new JScrollPane(myTable);
       panel2.add(BorderLayout.CENTER,pane);
       panel2.setBackground(new Color(0,0,30));
       panel2.setLocation(0,0);
       panel2.setSize(925,500);
       
       JPanel panel1=new JPanel();
        panel1.setBorder(BorderFactory.createRaisedBevelBorder());
        panel1.setLayout(null);
        JButton button1=new JButton("PAY");
        button1.setSize(300,50);
        button1.setBackground(Color.BLACK);
        button1.setFont(new Font("TIMES NEW ROMAN",50,25));
        button1.setForeground(Color.BLACK);
        button1.setLocation(5,10);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
   //     panel2.setLocation(0,0);
        panel1.add(button1);
        JButton button2=new JButton("CLEAR SCREEN");
        button2.setSize(300,50);
        button2.setBackground(Color.BLACK);
        button2.setFont(new Font("TIMES NEW ROMAN",50,25));
        button2.setForeground(Color.BLACK);
        button2.setLocation(5,110);
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        panel1.add(button2);
        
        JButton button3=new JButton("QUIT");
        button3.setLocation(5,210);
        button3.setSize(300,50);
        button3.setBackground(Color.BLACK);
        button3.setFont(new Font("TIMES NEW ROMAN",50,25));
        button3.setForeground(Color.BLACK);
        button3.setBorder(BorderFactory.createRaisedBevelBorder());
        panel1.add(button3);
        
        JButton button4=new JButton("RETRIEVE ORDER");
        button4.setLocation(5,310);
        button4.setSize(300,50);
        button4.setBackground(Color.BLACK);
        button4.setFont(new Font("TIMES NEW ROMAN",50,25));
        button4.setForeground(Color.BLACK);
        button4.setBorder(BorderFactory.createRaisedBevelBorder());
        panel1.add(button4);
        
        JButton button5=new JButton("LOCK SCREEN");
        button5.setLocation(5,410);
        button5.setSize(300,50);
        button5.setBackground(Color.BLACK);
        button5.setFont(new Font("TIMES NEW ROMAN",50,25));
        button5.setForeground(Color.BLACK);
        button5.setBorder(BorderFactory.createRaisedBevelBorder());
        panel1.add(button5);
        panel1.setSize(575,500);
        panel1.setLocation(925,0);
        panel1.setBackground(Color.YELLOW);
        JPanel overallPanel=new JPanel();
        getContentPane().add(panel1);
        getContentPane().add(panel2);
        
        pa=new JPanel();
        pa.setLayout(null);
        pa.setBorder(BorderFactory.createRaisedBevelBorder());
    //    pa.setLocation(0,800);
        page=new JButton("PAGE 1");
        page.setLocation(0,500);
        page.setSize(100,40);
        page.setBackground(Color.BLACK);
        page.setForeground(Color.BLACK);
        page.setFont(new Font("TIMES NEW ROMAN",50,25));
        page.setBorder(BorderFactory.createRaisedBevelBorder());
        page.addActionListener(this);
        pa.add(page);
        itemButton=new JButton("FOOD");
        itemButton.setLocation(0,540);
        itemButton.setSize(200,80);
        itemButton.setBackground(Color.BLACK);
        itemButton.setForeground(Color.GREEN);
        itemButton.setFont(new Font("TIMES NEW ROMAN",50,25));
   //     itemButton.setVisible(false);
        pa.add(itemButton);
        
        
  //      JTabbedPane tabbedpane=new JTabbedPane();
  //      JPanel tabbedPanel=new JPanel();
  //      tabbedpane.addTab("PAGE1",null,tabbedPanel,"FIRST PANEL");
        pa.setBackground(Color.PINK);
 //       tabbedpane.setLocation(5,300);
 //       pa.add(tabbedpane);
        JScrollPane o=new JScrollPane(pa);
        getContentPane().add(BorderLayout.CENTER,o);
        JTextField field=new JTextField();
        field.setSize(200,40);
        field.setForeground(Color.WHITE);
        
        JLabel label1=new JLabel("TOTAL:");
        label1.setFont(new Font("TIMES NEW ROMAN",50,40));
        label1.setForeground(Color.GREEN);
        label1.setSize(200,30);
        
        
         JLabel label2=new JLabel("SH");
        label2.setFont(new Font("TIMES NEW ROMAN",50,40));
     //   label2.setLocation(480,510);
        label2.setForeground(Color.GREEN);
        label2.setSize(200,30);
   //     pa.add(label2);
        
        JTextField field2=new JTextField();
        field2.setSize(100,40);
        field2.setForeground(Color.WHITE);
        
        JLabel label3=new JLabel("CT");
        label3.setFont(new Font("TIMES NEW ROMAN",50,40));
        label3.setForeground(Color.GREEN);
        label3.setSize(200,30);
        
        Box Box1=Box.createHorizontalBox();
        Box1.add(label1);
        Box1.add(label2);
        Box1.add(field);
        Box1.add(label3);
        Box1.add(field2);
        panel2.add(BorderLayout.SOUTH,Box1);
        
        Box box2=Box.createHorizontalBox();
        JLabel newLabel=new JLabel("CURRENT USER:");
        newLabel.setFont(new Font("TIMES NEW ROMAN",50,40));
        newLabel.setForeground(Color.GREEN);
        newLabel.setSize(200,30);
        
        JLabel newLabel2=new JLabel("SYSTEM ADMIN");
        newLabel2.setFont(new Font("TIMES NEW ROMAN",50,40));
        newLabel2.setForeground(Color.GREEN);
        newLabel2.setSize(200,30);
        box2.add(newLabel);
        box2.add(newLabel2);
        panel2.add(BorderLayout.NORTH,box2);
        
         
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
        public void actionPerformed(ActionEvent e){
            
          if(e.getSource()==page){
          
       
          }
        }

    
   public static void main(String[] args){
       try{
           for(UIManager.LookAndFeelInfo info:    UIManager.getInstalledLookAndFeels()){
               if(("Windows").equals(info.getName())){
                   UIManager.setLookAndFeel(info.getClassName());
               }
           }
       }
       catch(Exception e){
           
       }
      new SalesScreen("SALES SCREEN").setVisible(true);
       
   }
    
}
