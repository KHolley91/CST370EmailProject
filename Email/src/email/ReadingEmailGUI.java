/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author danii
 */
public class ReadingEmailGUI extends javax.swing.JFrame {

    /**
     * Creates new form ReadingEmailGUI
     */
    int number;
    MainGUI main;
    
    public ReadingEmailGUI() {
        initComponents();
    }
    
    public ReadingEmailGUI(int index, MainGUI MGUI){
        initComponents();
        number = index;
        main = MGUI;
        printEmail(index);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Close = new javax.swing.JButton();
        From = new javax.swing.JTextField();
        Subject = new javax.swing.JTextField();
        BodyScroll = new javax.swing.JScrollPane();
        Body = new javax.swing.JTextArea();
        Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Close.setText("Close");
        Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CloseMouseReleased(evt);
            }
        });

        From.setEditable(false);

        Subject.setEditable(false);

        Body.setEditable(false);
        Body.setColumns(20);
        Body.setRows(5);
        BodyScroll.setViewportView(Body);

        Delete.setText("Delete");
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DeleteMouseReleased(evt);
            }
        });
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Close))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BodyScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(Subject)
                            .addComponent(From))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Close)
                    .addComponent(Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BodyScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseMouseReleased
        super.dispose();
    }//GEN-LAST:event_CloseMouseReleased

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        
    }//GEN-LAST:event_DeleteActionPerformed

    private void DeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseReleased
        DeleteEmail DE = new DeleteEmail();
        DE.delete(number);
        super.dispose();
        main.Refresh();
    }//GEN-LAST:event_DeleteMouseReleased
    
    private void printEmail(int index)
    {
        int i;
        i = index;
        String e = "Email";
        String n = "Number";
        
        File file = new File("./Inbox.txt");
        try
        {
            Scanner inputFile = new Scanner(file);

            while(inputFile.hasNext())
            {
                String line = inputFile.nextLine();
                String[] tokens = line.split(" ");

                if(tokens.length == 3 && tokens[0].equals(e) && tokens[1].equals(n) && Integer.parseInt(tokens[2]) == i)
                {
                    line = inputFile.nextLine();
                    line = line.replaceAll("Subject:", "");
                    Subject.setText(line);
                    
                    line = inputFile.nextLine();
                    line = line.replaceAll("From:", "");
                    From.setText(line);
                    
                    String body = null;
                    line = inputFile.nextLine();
                    line = line.replaceAll("Text:", "");
                    body = line;
                    line = inputFile.nextLine();
                    while(!line.equals("---------------------------------"))
                    {  
                        body = body + line;
                        line = inputFile.nextLine();
                    }
                    Body.setText(body);
                }               
            }
        }
        catch (IOException g)
        {
            g.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                new ReadingEmailGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Body;
    private javax.swing.JScrollPane BodyScroll;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField From;
    private javax.swing.JTextField Subject;
    // End of variables declaration//GEN-END:variables
}
