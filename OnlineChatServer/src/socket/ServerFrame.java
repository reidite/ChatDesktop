package socket;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

import javax.swing.UIManager.*;



public class ServerFrame extends javax.swing.JFrame {//class giao dien cua server

    public SocketServer server;//cai socketServer de chay ui nay(khac serversocket)
    public Thread serverThread;
    public String filePath = "D:/Data.xml";//file database mac dinh
    public JFileChooser fileChooser;
    
    public ServerFrame() {//khoi tao...
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
    	getContentPane().setBackground(Color.GRAY);
    	setBackground(Color.GRAY);
        initComponents();     
        jTextField3.setEditable(false);
        jTextField3.setBackground(Color.DARK_GRAY);
        
        fileChooser = new JFileChooser();
        jTextArea1.setEditable(false);
    }
    
    public boolean isWin32(){//he diu hanh la co phai win32 ko
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() { // khoi tao button, jtextfield,area...

        jButton1 = new javax.swing.JButton();
        jButton1.setForeground(Color.LIGHT_GRAY);
        jButton1.setBackground(Color.BLACK);
        jButton1.setText("Start");
        jButton1.setToolTipText("Start");
        jButton1.setSelectedIcon(new ImageIcon("/img/press-play-button.png"));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setBackground(Color.DARK_GRAY);
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setBackground(Color.BLACK);
        jTextField3 = new javax.swing.JTextField();
        jLabel3.setLabelFor(jTextField3);
        jButton2 = new javax.swing.JButton();
        jButton2.setForeground(Color.LIGHT_GRAY);
        jButton2.setBackground(Color.BLACK);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Online Chat Server");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Database File : ");

        jButton2.setText("Browse...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jTextField3, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addGap(266)
        					.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGap(260)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel3)
        				.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jButton1)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        server = new SocketServer(this);//nhan nut start thi tao 1 server moi
        jButton1.setEnabled(false); jButton2.setEnabled(false);//khong cho bam nut brower nua
    }//GEN-LAST:event_jButton1ActionPerformed

    public void RetryStart(int port){//ham ket noi lai
        if(server != null){ server.stop(); }
        server = new SocketServer(this, port);
    }
    //button2 la nut brower
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fileChooser.showDialog(this, "Select Database");
        File file = fileChooser.getSelectedFile();//mo hoi thoai chon file
        
        if(file != null){
            filePath = file.getPath();
            if(this.isWin32()){ filePath = filePath.replace("\\", "/"); }//tuy hdh ma chon \\ hay / de cach
            jTextField3.setText(filePath);//hien duong dan len man hinh
            jButton1.setEnabled(true);//gio moi cho chon start server
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){
            System.out.println("Look & Feel Exception");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
