package ui;

import ui.ChatFrame;
import socket.History;
import socket.Message;
import socket.SocketClient;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import oracle.jrockit.jfr.JFR;

public class LoginFrame extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SocketClient client;
    public int port;
    public String serverAddr, username, password;
    //public Thread clientThread;
    public DefaultListModel model;
    public File file;
    File location =  new File(new File("").getAbsolutePath());
    /*public String historyFile = location + "/History.xml"; //file history mac dinh
    public HistoryFrame historyFrame;
    public History hist;*/
    public ChatFrame chat_man;//=======================>
    
    public LoginFrame() {
    	setAlwaysOnTop(true);
    	try {
    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	    // If Nimbus is not available, you can set the GUI to another look and feel.
    	}
        initComponents();
        this.setTitle("JatQ");
        this.setSize(500, 150);
        this.setResizable(false); //====================================>
        model = new DefaultListModel();//=====================================>
        model.addElement("All");
        getContentPane().setLayout(new MigLayout("", "[59px][157px][81px][80.00px][1.00px][80.00px]", "[19px][27px][25px]"));
        getContentPane().add(serverLabel, "cell 0 0,alignx right,aligny top");
        getContentPane().add(serverTextField, "cell 1 0,growx,aligny top");
        getContentPane().add(usernameLabel, "cell 2 0,alignx right,aligny center");
        getContentPane().add(usernameTextField, "cell 3 0 3 1,growx,aligny top");
        getContentPane().add(portLabel, "cell 0 1,alignx right,aligny top");
        getContentPane().add(portTextField, "cell 1 1,growx,aligny bottom");
        getContentPane().add(passwordLabel, "cell 2 1,alignx right,aligny center");
        getContentPane().add(passwordField, "cell 3 1 3 1,growx,aligny center");
        getContentPane().add(connectBnt, "cell 1 2,growx,aligny top");
        getContentPane().add(loginBnt, "cell 3 2,growx,aligny top");
        getContentPane().add(signupBnt, "cell 5 2,growx,aligny top");
        
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.sendToServer(new Message("message", username, ".bye", "SERVER","nani", -1)); client.clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });

    }
    
    public boolean isWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverLabel = new javax.swing.JLabel();
        serverLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        serverTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        portTextField = new javax.swing.JTextField();
        connectBnt = new javax.swing.JButton();
        connectBnt.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
        connectBnt.setFont(new Font("Ubuntu", Font.BOLD, 16));
        usernameTextField = new javax.swing.JTextField();
        usernameTextField.setText("");
        passwordLabel = new javax.swing.JLabel();
        passwordLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        usernameLabel = new javax.swing.JLabel();
        usernameLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        signupBnt = new javax.swing.JButton();
        signupBnt.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
        signupBnt.setFont(new Font("Ubuntu", Font.BOLD, 16));
        passwordField = new javax.swing.JPasswordField();
        passwordField.setToolTipText("");
        loginBnt = new javax.swing.JButton();
        loginBnt.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
        loginBnt.setFont(new Font("Ubuntu", Font.BOLD, 16));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverLabel.setText("Server : ");

        serverTextField.setText("localhost");

        portLabel.setText("Port : ");

        portTextField.setText("37011");

        connectBnt.setText("Connect");
        connectBnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        usernameTextField.setEnabled(false);

        passwordLabel.setText("Password :");

        usernameLabel.setText("Username :");

        signupBnt.setText("SignUp");
        signupBnt.setEnabled(false);
        signupBnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        passwordField.setEnabled(false);

        loginBnt.setText("Login");
        loginBnt.setEnabled(false);
        loginBnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        serverAddr = serverTextField.getText(); 
        port = Integer.parseInt(portTextField.getText());
        
        if(!serverAddr.isEmpty() && !portTextField.getText().isEmpty()){
            try{
                client = new SocketClient(this);
                client.clientThread = new Thread(client);
                client.clientThread.start();
                client.sendToServer(new Message("test", "testUser", "testContent", "SERVER","nani", -1));
            }
            catch(Exception ex){
                //jTextArea1.append("[Application > Me] : Server not found\n");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        username = usernameTextField.getText();
        password = passwordField.getText();
        
        if(!username.isEmpty() && !password.isEmpty()){
            client.sendToServer(new Message("login", username, password, "SERVER","nani", -1));
        }
        else {
        	final JPanel panel = new JPanel();
        	JOptionPane.showMessageDialog(panel, "Username and password must be provided!", "Login Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        username = usernameTextField.getText();
        password = passwordField.getText();
        
        if(!username.isEmpty() && !password.isEmpty()){
            client.sendToServer(new Message("signup", username, password, "SERVER","nani", -1));
        }
        else {
        	final JPanel panel = new JPanel();
        	JOptionPane.showMessageDialog(panel, "Username and password must be provided!", "Signup Failed!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton connectBnt;
    public javax.swing.JButton loginBnt;
    public javax.swing.JButton signupBnt;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JLabel portLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel usernameLabel;
    public javax.swing.JPasswordField passwordField;
    public javax.swing.JTextField serverTextField;
    public javax.swing.JTextField portTextField;
    public javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
