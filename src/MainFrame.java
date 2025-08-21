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

// Standard Java imports.
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import samael.huginandmunin.*;

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
     * Constructs the main frame of the application.
     */
    public MainFrame() {

        Debug.writeLine(Debug.DebugLevel.Info, "Initializing MainFrame", "MainFrame");
        Log.writeLine(Log.LogLevel.Info, "Initializing MainFrame", "MainFrame");

        Debug.writeLine(Debug.DebugLevel.Verbose, "Getting title from Config.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Getting title from Config.", "MainFrame");
        setTitle(Config.get("App.Name") + " - " + Config.get("App.Version"));
        
        Debug.writeLine(Debug.DebugLevel.Verbose, "Setting Window size to 300 x 200.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Setting Window size to 300 x 200.", "MainFrame");
        setSize(300, 200);

        Debug.writeLine(Debug.DebugLevel.Verbose, "Setting default close operation to EXIT_ON_CLOSE.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Setting default close operation to EXIT_ON_CLOSE.", "MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Debug.writeLine(Debug.DebugLevel.Verbose, "Setting location to center.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Setting location to center.", "MainFrame");
        setLocationRelativeTo(null); // Center the window

        Debug.writeLine(Debug.DebugLevel.Verbose, "Creating the label", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Creating the label", "MainFrame");

        Debug.writeLine(Debug.DebugLevel.Verbose, "Getting label text from Config.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Getting label text from Config.", "MainFrame");

        JLabel label = new JLabel(Config.get("App.Label.Text"), JLabel.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 24));
        add(label);
    }
}