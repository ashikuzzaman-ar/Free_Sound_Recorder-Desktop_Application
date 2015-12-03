package src;

import gui.MainF;

/**
 *
 * @author ashik
 */
public class Main {
    
    public static void main(String[] args) {
        
        /**
         * For windows OS Desktop entry creator is not necessary .....
         */
        DesktopEntryCreator.createDesktopEntry();
        MainF.launcher();
    }
}
