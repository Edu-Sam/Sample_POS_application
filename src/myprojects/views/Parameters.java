/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Parameters extends JDialog {
    public Parameters(JDialog owner_dialog,boolean modal){
        super(owner_dialog,modal);
        getContentPane().setLayout(null);
        JPanel topPanel=new JPanel();
        topPanel.setLocation(0,0);
        topPanel.setSize(680,25);
        topPanel.setBackground(new Color(100,149,237));
        getContentPane().add(topPanel);
        
        JLabel system_param_label=new JLabel("SYSTEM PARAMTERS");
        system_param_label.setLocation(5,5);
        system_param_label.setSize(200,15);
        system_param_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
        system_param_label.setForeground(Color.BLACK);
        topPanel.add(system_param_label);
        
        JPanel middle_panel=new JPanel();
        middle_panel.setLayout(null);
        middle_panel.setLocation(0,26);
        middle_panel.setSize(680,40);
        middle_panel.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(middle_panel);
        JLabel system_parameters_label=new JLabel("System parameters");
        system_parameters_label.setLocation(0,0);
        system_parameters_label.setSize(400,50);
        system_parameters_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,23));
        system_parameters_label.setForeground(Color.BLACK);
        middle_panel.add(system_parameters_label);
        
        JPanel option_panel=new JPanel();
 //       option_panel.setBorder(BorderFactory.createRaisedBevelBorder());
        option_panel.setLayout(null);
        option_panel.setLocation(0,90);
        option_panel.setSize(680,450);
        getContentPane().add(option_panel);
        JTabbedPane tabbed_pane=new JTabbedPane();
        tabbed_pane.setLocation(0,0);
        tabbed_pane.setSize(680,450);
        option_panel.add(tabbed_pane);
        JPanel panel_1=new JPanel();
        panel_1.setLayout(null);
        panel_1.setLocation(0,0);
        panel_1.setSize(680,450);
        tabbed_pane.addTab("  PAGE 1",null,panel_1,"");
        
        JCheckBox print_receipts_box=new JCheckBox();
        print_receipts_box.setLocation(5,5);
        print_receipts_box.setSize(30,20);
        panel_1.add(print_receipts_box);
        
        JLabel print_receipts_label=new JLabel("Do you want to print receipts");
        print_receipts_label.setLocation(35,10);
        print_receipts_label.setSize(200,13);
        print_receipts_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        print_receipts_label.setForeground(Color.BLACK);
        panel_1.add(print_receipts_label);
        
        JCheckBox preview_receipt_box=new JCheckBox();
        preview_receipt_box.setLocation(5,25);
        preview_receipt_box.setSize(30,20);
        panel_1.add(preview_receipt_box);
        
        JLabel preview_receipt_label=new JLabel("Do you want to preview the receipt");
        preview_receipt_label.setLocation(35,30);
        preview_receipt_label.setSize(200,13);
        preview_receipt_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        preview_receipt_label.setEnabled(false);
        panel_1.add(preview_receipt_label);
        
        JCheckBox cut_receipt_box=new JCheckBox();
        cut_receipt_box.setLocation(5,45);
        cut_receipt_box.setSize(30,20);
        panel_1.add(cut_receipt_box);
        
        JLabel cut_receipt_label=new JLabel("Dont Automatically cut the receipt-Used for ESD");
        cut_receipt_label.setLocation(35,50);
        cut_receipt_label.setSize(300,13);
        cut_receipt_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        cut_receipt_label.setEnabled(false);
        panel_1.add(cut_receipt_label);
        
        JCheckBox show_prices_box=new JCheckBox();
     //("Dont show prices when printing orders");
        show_prices_box.setLocation(350,5);
        show_prices_box.setSize(30,20);
        panel_1.add(show_prices_box);
        
        JLabel show_prices_label=new JLabel("Dont show prices when printing orders");
        show_prices_label.setLocation(380,10);
        show_prices_label.setSize(300,13);
        show_prices_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        show_prices_label.setEnabled(false);
        panel_1.add(show_prices_label);
        
        JCheckBox reprint_document_box=new JCheckBox();
        reprint_document_box.setLocation(350,25);
        reprint_document_box.setSize(30,20);
        panel_1.add(reprint_document_box);
        
        JLabel reprint_document_label=new JLabel("Dont Allow reprint of documents");
        reprint_document_label.setLocation(380,30);
        reprint_document_label.setSize(200,13);
        reprint_document_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        reprint_document_label.setEnabled(false);
        panel_1.add(reprint_document_label);
        
        JCheckBox long_receipt_box=new JCheckBox();
        long_receipt_box.setLocation(480,80);
        long_receipt_box.setSize(20,20);
        panel_1.add(long_receipt_box);
        
        JLabel long_receipt_label=new JLabel("Use the long receipt type format");
        long_receipt_label.setLocation(500,85);
        long_receipt_label.setSize(200,13);
        long_receipt_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        long_receipt_label.setEnabled(false);
        panel_1.add(long_receipt_label);
        
        JLabel seconds_between_label=new JLabel("Seconds between receipts reprint");
        seconds_between_label.setLocation(5,130);
        seconds_between_label.setSize(200,14);
        seconds_between_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        panel_1.add(seconds_between_label);
        
        JTextField seconds_between_text=new JTextField();
        seconds_between_text.setLocation(250,130);
        seconds_between_text.setSize(200,25);
        seconds_between_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        panel_1.add(seconds_between_text);
        
        JLabel message_1_label=new JLabel("Receipt message 1");
        message_1_label.setLocation(5,170);
        message_1_label.setSize(200,13);
        message_1_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_1_label);
        
        JTextField message_1_text=new JTextField();
        message_1_text.setLocation(110,165);
        message_1_text.setSize(340,25);
        message_1_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_1_text);
        
        JLabel message_2_label=new JLabel("Receipt message 2");
        message_2_label.setLocation(5,200);
        message_2_label.setSize(200,13);
        message_2_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_2_label);
        
        JTextField message_2_text=new JTextField();
        message_2_text.setLocation(110,195);
        message_2_text.setSize(340,25);
        message_2_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_2_text);
        
        JLabel message_3_label=new JLabel("Receipt message 3");
        message_3_label.setLocation(5,230);
        message_3_label.setSize(200,13);
        message_3_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_3_label);
        
        JTextField message_3_text=new JTextField();
        message_3_text.setLocation(110,225);
        message_3_text.setSize(340,25);
        message_3_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_3_text);
                
        JLabel message_4_label=new JLabel("Receipt message 4");
        message_4_label.setLocation(5,260);
        message_4_label.setSize(200,13);
        message_4_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_4_label);
        
        JTextField message_4_text=new JTextField();
        message_4_text.setLocation(110,255);
        message_4_text.setSize(340,25);
        message_4_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_4_text);
        
        JLabel message_5_label=new JLabel("Receipt message 5");
        message_5_label.setLocation(5,290);
        message_5_label.setSize(200,13);
        message_5_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_5_label);
        
        JTextField message_5_text=new JTextField();
        message_5_text.setLocation(110,285);
        message_5_text.setSize(340,25);
        message_5_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_5_text);
        
        JLabel message_6_label=new JLabel("Receipt message 6");
        message_6_label.setLocation(5,320);
        message_6_label.setSize(200,13);
        message_6_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_1.add(message_6_label);
        
        JTextField message_6_text=new JTextField();
        message_6_text.setLocation(110,315);
        message_6_text.setSize(340,25);
        message_6_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_1.add(message_6_text);
        
        JPanel panel_2=new JPanel();
        panel_2.setLayout(null);
        panel_2.setLocation(0,0);
        panel_2.setSize(680,450);
        tabbed_pane.addTab("  PAGE 2",null,panel_2,"");
        
        JCheckBox costing_information_box=new JCheckBox();
        costing_information_box.setLocation(20,140);
        costing_information_box.setSize(20,20);
        costing_information_box.setEnabled(false);
        panel_2.add(costing_information_box);
        
        JLabel costing_information_label=new JLabel("Disable display of costing information on the screen");
        costing_information_label.setLocation(43,143);
        costing_information_label.setSize(300,14);
        costing_information_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        costing_information_label.setEnabled(false);
        panel_2.add(costing_information_label);
        
        JCheckBox real_processing_box=new JCheckBox();
        real_processing_box.setLocation(20,160);
        real_processing_box.setSize(20,20);
        real_processing_box.setEnabled(false);
        panel_2.add(real_processing_box);
        
        JLabel real_processing_label=new JLabel("Disable real time processing of AVW cost");
        real_processing_label.setLocation(43,163);
        real_processing_label.setSize(300,14);
        real_processing_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        real_processing_label.setEnabled(false);
        panel_2.add(real_processing_label);
        
        JCheckBox multiple_prices_box=new JCheckBox();
        multiple_prices_box.setLocation(20,180);
        multiple_prices_box.setSize(20,20);
        multiple_prices_box.setEnabled(false);
        panel_2.add(multiple_prices_box);
        
        JLabel multiple_prices_label=new JLabel("Disable multiple prices");
        multiple_prices_label.setLocation(43,183);
        multiple_prices_label.setSize(300,14);
        multiple_prices_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        multiple_prices_label.setEnabled(false);
        panel_2.add(multiple_prices_label);
        
        JCheckBox disable_quantities_box=new JCheckBox();
        disable_quantities_box.setLocation(20,200);
        disable_quantities_box.setSize(20,20);
        disable_quantities_box.setEnabled(false);
        panel_2.add(disable_quantities_box);
        
        JLabel disable_quantities_label=new JLabel("Disable quantities in stock grid on the screen");
        disable_quantities_label.setLocation(43,203);
        disable_quantities_label.setSize(300,14);
        disable_quantities_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        disable_quantities_label.setEnabled(false);
        panel_2.add(disable_quantities_label);
                
                
        
        JPanel panel_3=new JPanel();
        panel_3.setLayout(null);
        panel_3.setLocation(0,0);
        panel_3.setSize(680,450);
        tabbed_pane.addTab("  PAGE 3",null,panel_3,"");
        
        JCheckBox using_pole_display_box=new JCheckBox();
        using_pole_display_box.setLocation(5,30);
        using_pole_display_box.setSize(20,20);
        panel_3.add(using_pole_display_box);
        
        JLabel using_pole_display_label=new JLabel("Are you using the pole display");
        using_pole_display_label.setLocation(28,33);
        using_pole_display_label.setSize(300,15);
        using_pole_display_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        using_pole_display_label.setEnabled(false);
        panel_3.add(using_pole_display_label);
        
        JLabel pole_display_port_label=new JLabel("Pole display port");
        pole_display_port_label.setLocation(10,60);
        pole_display_port_label.setSize(300,16);
        pole_display_port_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_3.add(pole_display_port_label);
        
        JComboBox pole_display_port_text=new JComboBox();
        pole_display_port_text.setLocation(120,55);
        pole_display_port_text.setSize(140,25);
        pole_display_port_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,17));
        panel_3.add(pole_display_port_text);
        
        JLabel baud_rate_parameters_label=new JLabel("Pole display baud rate parameters");
        baud_rate_parameters_label.setLocation(300,34);
        baud_rate_parameters_label.setSize(300,13);
        baud_rate_parameters_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_3.add(baud_rate_parameters_label);
        
        JTextField baud_rate_parameters_text=new JTextField("9600,N,8,1");
        baud_rate_parameters_text.setLocation(300,55);
        baud_rate_parameters_text.setSize(180,25);
        baud_rate_parameters_text.setFont(new Font("SERIF",Font.PLAIN,15));
        baud_rate_parameters_text.setEnabled(false);
        panel_3.add(baud_rate_parameters_text);
        
        JLabel init_string_label=new JLabel("Pole display init string");
        init_string_label.setLocation(10,100);
        init_string_label.setSize(200,13);
        init_string_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,12));
        panel_3.add(init_string_label);
        
        JTextField init_string_text=new JTextField();
        init_string_text.setLocation(10,120);
        init_string_text.setSize(300,25);
        init_string_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        init_string_text.setEnabled(false);
        panel_3.add(init_string_text);
        
        JCheckBox user_select_account_box=new JCheckBox();
        user_select_account_box.setLocation(15,155);
        user_select_account_box.setSize(20,20);
        user_select_account_box.setEnabled(false);
        panel_3.add(user_select_account_box);
        
        JLabel user_select_account_label=new JLabel("User must select account when selling on cash");
        user_select_account_label.setLocation(40,160);
        user_select_account_label.setSize(250,13);
        user_select_account_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,12));
        panel_3.add(user_select_account_label);
        
        JLabel Lcd_message1_label=new JLabel("LCD MESSAGE 1");
        Lcd_message1_label.setLocation(10,200);
        Lcd_message1_label.setSize(200,15);
        Lcd_message1_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_3.add(Lcd_message1_label);
        
        JTextField Lcd_message1_text=new JTextField("WELCOME TO");
        Lcd_message1_text.setLocation(130,195);
        Lcd_message1_text.setSize(300,25);
        Lcd_message1_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        Lcd_message1_text.setEnabled(false);
        panel_3.add(Lcd_message1_text);
        
        JLabel Lcd_message2_label=new JLabel("LCD MESSAGE 2");
        Lcd_message2_label.setLocation(10,230);
        Lcd_message2_label.setSize(200,15);
        Lcd_message2_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_3.add(Lcd_message2_label);
        
        JTextField Lcd_message2_text=new JTextField("CONNECTIONS LTD");
        Lcd_message2_text.setLocation(130,225);
        Lcd_message2_text.setSize(300,25);
        Lcd_message2_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        Lcd_message2_text.setEnabled(false);
        panel_3.add(Lcd_message2_text);
        
        JLabel Lcd_message3_label=new JLabel("LCD MESSAGE 3");
        Lcd_message3_label.setLocation(10,260);
        Lcd_message3_label.setSize(200,15);
        Lcd_message3_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_3.add(Lcd_message3_label);
        
        JTextField Lcd_message3_text=new JTextField("THANK YOU FOR");
        Lcd_message3_text.setLocation(130,255);
        Lcd_message3_text.setSize(300,25);
        Lcd_message3_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        Lcd_message3_text.setEnabled(false);
        panel_3.add(Lcd_message3_text);
        
        JLabel Lcd_message4_label=new JLabel("LCD MESSAGE 4");
        Lcd_message4_label.setLocation(10,290);
        Lcd_message4_label.setSize(200,15);
        Lcd_message4_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        panel_3.add(Lcd_message4_label);
        
        JTextField Lcd_message4_text=new JTextField("YOUR BUSINESS");
        Lcd_message4_text.setLocation(130,285);
        Lcd_message4_text.setSize(300,25);
        Lcd_message4_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        Lcd_message4_text.setEnabled(false);
        panel_3.add(Lcd_message4_text);
        
        
        JPanel panel_4=new JPanel();
        panel_4.setLayout(null);
        panel_4.setLocation(0,0);
        panel_4.setSize(680,450);
        panel_4.setBackground(Color.WHITE);
        tabbed_pane.addTab("  PAGE 4",null,panel_4,"");
        
        JLabel current_location_label=new JLabel("Current Location");
        current_location_label.setLocation(10,15);
        current_location_label.setSize(200,14);
        current_location_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        current_location_label.setForeground(Color.BLACK);
        panel_4.add(current_location_label);
        
        JComboBox current_location_text=new JComboBox();
        current_location_text.setLocation(120,10);
        current_location_text.setSize(300,25);
        current_location_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        current_location_text.setEnabled(false);
        panel_4.add(current_location_text);
        
        JLabel current_till_label=new JLabel("Till no");
        current_till_label.setLocation(10,50);
        current_till_label.setSize(200,14);
        current_till_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        panel_4.add(current_till_label);
        
        JComboBox current_till_text=new JComboBox();
        current_till_text.setLocation(120,45);
        current_till_text.setSize(300,25);
        current_till_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        current_till_text.setEnabled(false);
        panel_4.add(current_till_text);
        
        JPanel panel_5=new JPanel();
        panel_5.setLocation(0,0);
        panel_5.setSize(680,450);
        panel_5.setBackground(Color.WHITE);
        panel_5.setLayout(null);
        tabbed_pane.addTab("  PAGE 5  ",null,panel_5,"");
        
        JPanel post_cash_panel=new JPanel();
        post_cash_panel.setLocation(5,10);
        post_cash_panel.setSize(600,100);
        post_cash_panel.setLayout(null);
  //      post_cash_panel.setBorder(BorderFactory.createEtchedBorder());
        post_cash_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(1),"Post Cash Sales to the following account"));
  //      post_cash_panel.setBorder(BorderFactory.createTitledBorder("Post Cash Sales to the following account"));
        panel_5.add(post_cash_panel);
        
        JLabel account_label=new JLabel("Account");
        account_label.setLocation(5,30);
        account_label.setSize(200,15);
        account_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        post_cash_panel.add(account_label);
        
        JTextField current_sales_account_no=new JTextField();
        current_sales_account_no.setLocation(60,25);
        current_sales_account_no.setSize(100,25);
        current_sales_account_no.setFont(new Font("SERIF",Font.PLAIN,16));
        current_sales_account_no.setEnabled(false);
        post_cash_panel.add(current_sales_account_no);
        
        JTextField current_sales_account_text=new JTextField();
        current_sales_account_text.setLocation(180,25);
        current_sales_account_text.setSize(350,25);
        current_sales_account_text.setFont(new Font("ARIAL",Font.PLAIN,16));
        current_sales_account_text.setEnabled(false);
        post_cash_panel.add(current_sales_account_text);
        
        JPanel map_transaction_panel=new JPanel();
        map_transaction_panel.setLocation(5,120);
        map_transaction_panel.setSize(600,300);
        map_transaction_panel.setLayout(null);
        map_transaction_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Map transaction types to voucher types as follows"));
        panel_5.add(map_transaction_panel);
        
        JLabel cash_label=new JLabel("Cash");
        cash_label.setLocation(15,25);
        cash_label.setSize(200,15);
        cash_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        map_transaction_panel.add(cash_label);
        
        JTextField cash_account_no=new JTextField();
        cash_account_no.setLocation(130,20);
        cash_account_no.setSize(100,25);
        cash_account_no.setFont(new Font("SERIF",Font.PLAIN,15));
        cash_account_no.setEnabled(false);
        map_transaction_panel.add(cash_account_no);
        
        JTextField cash_account_nm=new JTextField();
        cash_account_nm.setLocation(250,20);
        cash_account_nm.setSize(300,25);
        cash_account_nm.setFont(new Font("ARIAL",Font.PLAIN,15));
        cash_account_nm.setEnabled(false);
        map_transaction_panel.add(cash_account_nm);
        
        JLabel cheque_label=new JLabel("Cheque");
        cheque_label.setLocation(15,55);
        cheque_label.setSize(200,15);
        cheque_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        map_transaction_panel.add(cheque_label);
        
        JTextField cheque_account_no=new JTextField();
        cheque_account_no.setLocation(130,50);
        cheque_account_no.setSize(100,25);
        cheque_account_no.setFont(new Font("SERIF",Font.PLAIN,15));
        cheque_account_no.setEnabled(false);
        map_transaction_panel.add(cheque_account_no);
        
        JTextField cheque_account_nm=new JTextField();
        cheque_account_nm.setLocation(250,50);
        cheque_account_nm.setSize(300,25);
        cheque_account_nm.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        cheque_account_nm.setEnabled(false);
        map_transaction_panel.add(cheque_account_nm);
        
        JLabel credit_card_label=new JLabel("Credit Card");
        credit_card_label.setLocation(15,85);
        credit_card_label.setSize(200,15);
        credit_card_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        map_transaction_panel.add(credit_card_label);
        
        JTextField credit_card_no=new JTextField();
        credit_card_no.setLocation(130,80);
        credit_card_no.setSize(100,25);
        credit_card_no.setFont(new Font("SRIF",Font.PLAIN,15));
        credit_card_no.setEnabled(false);
        map_transaction_panel.add(credit_card_no);
        
        JTextField credit_card_nm=new JTextField();
        credit_card_nm.setLocation(250,80);
        credit_card_nm.setSize(300,25);
        credit_card_nm.setFont(new Font("SERIF",Font.PLAIN,15));
        credit_card_nm.setEnabled(false);
        map_transaction_panel.add(credit_card_nm);
        
        JPanel bottom_panel=new JPanel();
        bottom_panel.setLayout(null);
        bottom_panel.setLocation(0,540);
        bottom_panel.setSize(680,58);
        bottom_panel.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(bottom_panel);
        
        JButton top_button=new JButton("Top");
        top_button.setLocation(5,10);
        top_button.setSize(70,25);
        top_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        top_button.setEnabled(false);
        bottom_panel.add(top_button);
        
        JButton prev_button=new JButton("Prev");
        prev_button.setLocation(75,10);
        prev_button.setSize(70,25);
        prev_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        prev_button.setEnabled(false);
        bottom_panel.add(prev_button);
        
        JButton next_button=new JButton("Next");
        next_button.setLocation(145,10);
        next_button.setSize(70,25);
        next_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        next_button.setEnabled(false);
        bottom_panel.add(next_button);
        
        JButton bottom_button=new JButton("Bottom");
        bottom_button.setLocation(215,10);
        bottom_button.setSize(70,25);
        bottom_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,12));
        bottom_button.setEnabled(false);
        bottom_panel.add(bottom_button);
        
        JButton Add_button=new JButton("Add");
        Add_button.setLocation(310,10);
        Add_button.setSize(70,25);
        Add_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        bottom_panel.add(Add_button);
        
        JButton edit_button=new JButton("Edit");
        edit_button.setLocation(380,10);
        edit_button.setSize(70,25);
        edit_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        bottom_panel.add(edit_button);
        
        JButton Delete_button=new JButton("Delete");
        Delete_button.setLocation(450,10);
        Delete_button.setSize(70,25);
        Delete_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        bottom_panel.add(Delete_button);
        
        JButton exit_button=new JButton("Exit");
        exit_button.setLocation(520,10);
        exit_button.setSize(70,25);
        exit_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        exit_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                quit();
            }
        });
        bottom_panel.add(exit_button);
        
        
        setSize(680,600);
        setLocationRelativeTo(null);
    }
    private void quit(){
        this.dispose();
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
        Parameters parameters=new Parameters(null,false);
        parameters.setUndecorated(true);
        parameters.setVisible(true);
    }
    
}
