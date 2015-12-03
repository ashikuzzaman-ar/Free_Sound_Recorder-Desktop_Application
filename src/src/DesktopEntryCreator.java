package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import javax.swing.JOptionPane;

class DesktopEntryCreator {

    static void createDesktopEntry() {

        try {
            
            File tempFile = new File(".");
            String tempName = tempFile.getAbsolutePath();
            String desktopPath = "";
            int index = 0, slashCount = 0;
            
            while (slashCount != 3) {
                
                if (tempName.charAt(index) == '/') {
                    slashCount++;
                }
                desktopPath += tempName.charAt(index);
                index++;
            }  
            
            tempFile = new File(desktopPath+"/Desktop/Sound Recorder.desktop");
            
            if(!tempFile.exists()){
                
                File curLoc = new File("");
                Formatter format = new Formatter(desktopPath+"/Desktop/Sound Recorder.desktop");
                format.format("%s", 
                        "[Desktop Entry]\n"
                        +"Version=1.0\n"
                        +"Name=Sound Recorder\n"
                        +"Comment=Desktop base sound recorder.\n"
                        +"Exec=java -jar Sound_Recorder.jar\n"
                        +"Path="+curLoc.getAbsolutePath()+"/\n"
                        +"Icon="+curLoc.getAbsolutePath()+"/Icon02.png\n"
                        +"Terminal=false\n"
                        +"Type=Application\n"
                        +"Categories=Utility;Application;Development;\n"
                );
                format.close();
            }
        } catch (FileNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
