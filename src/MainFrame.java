/* -------------------------------------------------------------------------------
 * MainFrame.java - This class serves as the primary window for the application.
 * It provides a structured layout for different sections, ensuring a clear separation
 * of functionalities such as controls, camera feed, audio analysis, and event logging.
 * 
 * This class is responsible for initializing and managing the main user interface. It
 * defines how visual components are arranged within the frame, ensuring an intuitive
 * experience for users interacting with the Ghost Box application.
 * 
 * In software design, the main frame acts as the central hub, housing all core functionalities
 * while maintaining scalability for future enhancements. This ensures flexibility
 * in adapting to additional features and improved processing capabilities.
 * -------------------------------------------------------------------------------
 * Author:  Patrik Eigemann
 * eMail:   p.eigenmann72@gmail.com
 * GitHub:  www.github.com/PatrikEigemann72/HelloJWorld
 * -------------------------------------------------------------------------------
 * Change Log:
 * Mon 2025-05-26 File created.                                     Version: 00.01
 * ------------------------------------------------------------------------------- */

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import samael.huginandmunin.Config;

/**
 * This class serves as the primary window for the application.
 * It provides a structured layout for different sections, ensuring a clear separation
 * of functionalities such as controls, camera feed, audio analysis, and event logging.
 * 
 * This class is responsible for initializing and managing the main user interface. It
 * defines how visual components are arranged within the frame, ensuring an intuitive
 * experience for users interacting with the Ghost Box application.
 * 
 * In software design, the main frame acts as the central hub, housing all core functionalities
 * while maintaining scalability for future enhancements. This ensures flexibility
 * in adapting to additional features and improved processing capabilities.
 */
//@Version(namespace = "HelloWorld", component = "MainFrame", major = 0, minor = 1)
public class MainFrame extends JFrame /*implements ITrackable*/ {
    
    /**
     * 
     */
    public MainFrame() {
        setTitle(Config.get("App.Name") + " - " + Config.get("App.Version"));
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        JLabel label = new JLabel("Hello, World!", JLabel.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 24));
        add(label);
    }
}