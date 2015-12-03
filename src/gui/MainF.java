package gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

public class MainF extends javax.swing.JFrame {

    private AudioFormat format;
    private DataLine.Info info;
    private TargetDataLine targetLine;
    private AudioInputStream audioStream;
    private File file;
    private Thread thread;
    private String fileName;
    private String fileDirectory;

    public MainF() {

        initComponents();
        bFinishRecording.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        pTime = new javax.swing.JDesktopPane();
        lIcon = new javax.swing.JLabel();
        bFinishRecording = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        bStartRecording = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sound Recorder");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Icon02.png"))); // NOI18N

        javax.swing.GroupLayout pTimeLayout = new javax.swing.GroupLayout(pTime);
        pTime.setLayout(pTimeLayout);
        pTimeLayout.setHorizontalGroup(
            pTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimeLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTimeLayout.setVerticalGroup(
            pTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTime.setLayer(lIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        bFinishRecording.setText("Finish and Save");
        bFinishRecording.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFinishRecordingActionPerformed(evt);
            }
        });

        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        bStartRecording.setText("Start Recording");
        bStartRecording.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartRecordingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bFinishRecording, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bStartRecording, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bFinishRecording, bStartRecording});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bStartRecording)
                .addGap(38, 38, 38)
                .addComponent(bFinishRecording)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed

        bFinishRecordingActionPerformed(evt);
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bStartRecordingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartRecordingActionPerformed

        this.fileName = JOptionPane.showInputDialog("Please Select a name to Save This Recording");

        if ("".equals(this.fileName)) {

            JOptionPane.showMessageDialog(null, "No file name selected.\n"
                    + "Select a new file name and start again."
            );

            this.bStartRecordingActionPerformed(evt);
        } else {

            try {

                this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
                this.info = new DataLine.Info(TargetDataLine.class, format);

                if (!AudioSystem.isLineSupported(info)) {

                    JOptionPane.showMessageDialog(null, "Your Audio system is not supported !");
                }

                this.targetLine = (TargetDataLine) AudioSystem.getLine(info);

                this.targetLine.open();
                this.targetLine.start();

                File tempFile = new File(".");
                String tempName = tempFile.getAbsolutePath();
                /**
                 * For windows temp fileName is file directory...
                 * So this.fileDirectory = tempFilename
                 * Else this.fileDirectory = "";
                 */
                this.fileDirectory = "";
                
                /**
                 * For windows index slash count and loping is not necessary....
                 */
                
                
                int index = 0, slashCount = 0;
                while (slashCount != 3 && index<tempName.length()) {
                    if (tempName.charAt(index) == '/') {
                        slashCount++;
                    }
                    this.fileDirectory += tempName.charAt(index);
                    index++;
                }
                        
                
                
                
                this.thread = new Thread() {

                    @Override
                    public void run() {

                        audioStream = new AudioInputStream(targetLine);
                        file = new File(fileDirectory + fileName + ".wav");

                        try {

                            AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, file);
                        } catch (IOException ex) {

                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }

                };

                this.thread.start();
                this.bFinishRecording.setVisible(true);
                this.bStartRecording.setVisible(false);
            } catch (HeadlessException | LineUnavailableException e) {

                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_bStartRecordingActionPerformed


    private void bFinishRecordingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFinishRecordingActionPerformed

        try {

            this.targetLine.stop();
            this.targetLine.close();
            this.bStartRecording.setVisible(true);
            this.bFinishRecording.setVisible(false);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "File Successfully Saved as " + fileDirectory + fileName + ".wav");
    }//GEN-LAST:event_bFinishRecordingActionPerformed

    public static void launcher() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bExit;
    private javax.swing.JButton bFinishRecording;
    private javax.swing.JButton bStartRecording;
    private javax.swing.JLabel lIcon;
    private javax.swing.JPanel p1;
    private javax.swing.JDesktopPane pTime;
    // End of variables declaration//GEN-END:variables
}
