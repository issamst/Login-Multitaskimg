/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.img;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.rmi.Naming;

/**
 * @author fchic
 */
public class GUI extends ClientTools {

    private static boolean all_set = false;
    private static boolean image_set = false;
    private static boolean filter_set = false;
    private static boolean server_set = false;
    static BufferedImage res_image;
    static BufferedImage og_image;
    static int[][] imagegray;
    static int[][] resimagegray;
    public static int width;
    public static int height;
    static String log = "";
    static String selected_server = "";
    static String selected_filter = "";

    public GUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        og_image_panel = new CustomJPanel();
        jLabel1 = new javax.swing.JLabel();
        res_image_panel = new CustomJPanel();
        jLabel2 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        insert = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        send = new javax.swing.JButton();
        filterBox = new javax.swing.JComboBox<>();
        serverStyleBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        og_image_panel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout og_image_panelLayout = new javax.swing.GroupLayout(og_image_panel);
        og_image_panel.setLayout(og_image_panelLayout);
        og_image_panelLayout.setHorizontalGroup(
                og_image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 337, Short.MAX_VALUE)
        );
        og_image_panelLayout.setVerticalGroup(
                og_image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 293, Short.MAX_VALUE)
        );

        jLabel1.setForeground(Color.BLACK);
        jLabel1.setText("Original Image");

        res_image_panel.setBackground(new java.awt.Color(153, 153, 153));
        res_image_panel.setPreferredSize(new java.awt.Dimension(337, 293));

        javax.swing.GroupLayout res_image_panelLayout = new javax.swing.GroupLayout(res_image_panel);
        res_image_panel.setLayout(res_image_panelLayout);
        res_image_panelLayout.setHorizontalGroup(
                res_image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 358, Short.MAX_VALUE)
        );
        res_image_panelLayout.setVerticalGroup(
                res_image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 293, Short.MAX_VALUE)
        );

        jLabel2.setForeground(Color.BLACK);
        jLabel2.setText("Resulted Image");
        jLabel2.setMaximumSize(new java.awt.Dimension(79, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(79, 16));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        jTextArea.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea.setForeground(Color.green);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        insert.setText("Insert Image");
        insert.setMaximumSize(new java.awt.Dimension(57, 25));
        insert.setMinimumSize(new java.awt.Dimension(57, 25));
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt, jTextArea, og_image_panel);
            }
        });

        jPanel3.setBackground(new java.awt.Color(60, 63, 65));

        send.setText("Send Image");
        send.setMaximumSize(new java.awt.Dimension(57, 25));
        send.setMinimumSize(new java.awt.Dimension(57, 25));
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    sendActionPerformed(evt, jTextArea, res_image_panel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        filterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"---", "Filtre de Moyenne", "Gradient de Sobel", "Gradient de Sobel t", "Perturbation", "Laplacien 8-connexe", "OutLine", "Vertical Edge", "Horizontal Edge"}));
        filterBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBoxActionPerformed(evt);
            }
        });

        serverStyleBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"---", "Socket Server", "RMI Server"}));
        serverStyleBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverStyleBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(serverStyleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(serverStyleBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(og_image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addComponent(res_image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(225, 225, 225))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(162, 162, 162)
                                                .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(201, 201, 201))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(104, 104, 104))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(og_image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(res_image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selected = (String) filterBox.getSelectedItem();
        if (selected.equals("---")) {
            filter_set = false;
            all_set = false;
        } else {
            filter_set = true;
            selected_filter = selected;
            if (server_set && image_set) {
                all_set = true;
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void serverStyleBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        String selected = (String) serverStyleBox.getSelectedItem();

        if (selected.equals("---")) {
            server_set = false;
            all_set = false;
        } else {
            server_set = true;
            selected_server = selected;
            if (filter_set && image_set) {
                all_set = true;
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_saveActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            SaveImage(res_image, fileToSave.getAbsolutePath());
            ;
        }
    }//GEN-LAST:event_saveActionPerformed

    private void insertActionPerformed(java.awt.event.ActionEvent evt, JTextArea jTextArea, CustomJPanel image_frame) {//GEN-FIRST:event_insertActionPerformed

        // insert Image Size
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        // if the user selected a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File f = jfc.getSelectedFile().getAbsoluteFile();
                og_image = ImageIO.read(f);
                int[][] img = ImageToMatrix(jfc.getSelectedFile());
                imagegray = ImageToGray(img);

                image_set = true;
                log += "\n Image Selected.\n";
                width = imagegray.length;
                height = imagegray[0].length;
                image_frame.setImage(og_image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            image_set = false;
            all_set = false;
            log += "\n Error : File not selected.";
        }
        jTextArea.setText(log);

    }//GEN-LAST:event_insertActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt, JTextArea jTextArea, CustomJPanel image_frame) throws IOException {//GEN-FIRST:event_sendActionPerformed
        if (all_set) {

            if (selected_server.equals("Socket Server")) {// Send Image Size
                int port = switch (GUI.selected_filter) {
                    case "Filtre de Moyenne" -> 4999;
                    case "Gradient de Sobel" -> 5999;
                    case "Gradient de Sobel t" -> 6999;
                    case "Perturbation" -> 7999;
                    case "Laplacien 8-connexe" -> 8999;
                    case "OutLine" -> 9999;
                    case "Vertical Edge" -> 10999;
                    case "Horizontal Edge" -> 11999;
                    default -> 0;
                };

                System.out.println(port);

                Socket client_socket = new Socket("localhost", port);

                DataOutputStream client_out = new DataOutputStream(client_socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(new DataInputStream(client_socket.getInputStream()));

                Thread sendT = new SendHandler(client_socket, client_out, dataInputStream);
                sendT.start();
            } else {
                int port = switch (GUI.selected_filter) {
                    case "Filtre de Moyenne" -> 1;
                    case "Gradient de Sobel" -> 2;
                    case "Gradient de Sobel t" -> 3;
                    case "Perturbation" -> 4;
                    case "Laplacien 8-connexe" -> 5;
                    case "OutLine" -> 6;
                    case "Vertical Edge" -> 7;
                    case "Horizontal Edge" -> 8;
                    default -> 0;
                };
                Thread sendT = new RMISendHandler(port);
                sendT.start();
            }

        } else {
            log += ("Please Fill Requirements before trying to send.\n");
            jTextArea.setText(log);
        }

    }//GEN-LAST:event_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filterBox;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTextArea jTextArea;
    private CustomJPanel og_image_panel;
    static CustomJPanel res_image_panel;
    private javax.swing.JButton save;
    private javax.swing.JButton send;
    private javax.swing.JComboBox<String> serverStyleBox;
    // End of variables declaration//GEN-END:variables
}

class RMISendHandler extends Thread {

    private final int kernel;

    public RMISendHandler(int kernel) {
        this.kernel = kernel;
    }

    public void run() {
        try {
            GUI.log += "Sending Image to server " + this.kernel + "...\n";
            GUI.jTextArea.setText(GUI.log);

            Filters stub = (Filters) Naming.lookup("rmi://localhost:5022/Filters");

            // Convert BufferedImage -> byteArray
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(GUI.og_image, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            // Sending image to be processed by the RMI Server
            byte[] mydata = stub.filterImage(bytes, this.kernel);

            GUI.log += "Image Received..\n";
            GUI.jTextArea.setText(GUI.log);

            // Convert byteArray -> BufferedImage
            InputStream is = new ByteArrayInputStream(mydata);
            GUI.res_image = ImageIO.read(is);

            // Display image in the Panel
            GUI.res_image_panel.setImage(GUI.res_image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SendHandler extends Thread {
    private Socket client_socket;
    private DataOutputStream dos;
    private DataInputStream dis;


    public SendHandler(Socket client, DataOutputStream client_out, DataInputStream dataInputStream) {
        this.client_socket = client;
        this.dos = client_out;
        this.dis = dataInputStream;
    }

    @Override
    public void run() {

        try {
            this.dos.writeUTF(GUI.width + "-" + GUI.height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int prog = 0, disp = 0;

        String text1 = ClientTools.printProgress("Sending :    ", prog);
        GUI.jTextArea.setText(GUI.log);

        for (int i = 0; i < GUI.width; i++) {
            disp++;
            if (disp >= GUI.width / 20) {
                disp = 0;
                prog++;
                GUI.log = ClientTools.removeLastLine(GUI.log);
                GUI.log += ClientTools.printProgress("Sending :    ", prog);
                GUI.jTextArea.setText(GUI.log);
            }
            for (int j = 0; j < GUI.height; j++) {
                try {
                    this.dos.writeUTF(String.valueOf(GUI.imagegray[i][j]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        GUI.log += "\nDone !\n";
        GUI.jTextArea.setText(GUI.log);
        GUI.log += "Waiting for Server Result.. \n";

        disp = 0;
        prog = 0;
        GUI.log += "Receiving : ";
        GUI.jTextArea.setText(GUI.log);

        GUI.resimagegray = new int[GUI.width][GUI.height];
        for (int i = 0; i < GUI.width; i++) {
            disp++;
            if (disp >= GUI.height / 20) {
                disp = 0;
                prog++;
                GUI.log = ClientTools.removeLastLine(GUI.log);
                GUI.log += ClientTools.printProgress("Receiving : ", prog);
                GUI.jTextArea.setText(GUI.log);
            }
            for (int j = 0; j < GUI.height; j++) {
                try {
                    GUI.resimagegray[i][j] = Integer.parseInt(this.dis.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        GUI.log += "\nImage Received successfully !\n";
        GUI.jTextArea.setText(GUI.log);

        try {
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.client_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.res_image = GUI.GrayToBufferedImage(GUI.resimagegray);

        GUI.res_image_panel.setImage(GUI.res_image);

    }
}

class CustomJPanel extends JPanel {

    BufferedImage image = null;

    public CustomJPanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
}