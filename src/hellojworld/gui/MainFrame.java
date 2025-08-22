/* ---------------------------------------------------------------------------------------
 * MainFrame.java - This class serves as the primary window for the application. It
 * provides a structured layout for different sections, ensuring a clear separation of
 * functionalities such as controls, camera feed, audio analysis, and event logging.
 * 
 * This class is responsible for initializing and managing the main user interface. It
 * defines how visual components are arranged within the frame, ensuring an intuitive
 * experience for users interacting with the Ghost Box application.
 * 
 * In software design, the main frame acts as the central hub, housing all core
 * functionalities while maintaining scalability for future enhancements. This ensures
 * flexibility in adapting to additional features and improved processing capabilities.
 * ---------------------------------------------------------------------------------------
 * Author:  Patrik Eigemann
 * eMail:   p.eigenmann72@gmail.com
 * GitHub:  www.github.com/PatrikEigemann72/HelloJWorld
 * ---------------------------------------------------------------------------------------
 * Change Log:
 * Mon 2025-05-26 File created.                                             Version: 00.01
 * Thu 2025-08-21 samael.huginandmunin package imported.                    Version: 00.02
 * Thu 2025-08-21 Debug class added.                                        Version: 00.03
 * Thu 2025-08-21 Log class added.                                          Version: 00.04
 * Thu 2025-08-21 Config class added.                                       Version: 00.05
 * Fri 2025-08-22 Functionality Config.get(key) changed to specific types.  Version: 00.06
 * Fri 2025-08-22 Ctrl+Q to exit application added.                         Version: 00.07
 * --------------------------------------------------------------------------------------- */
package hellojworld.gui;

// Standard Java swing/awtimports.
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

// Importing samael.huginandmunin package
import samael.huginandmunin.*;

/**
 * This class serves as the primary window for the application. It
 * provides a structured layout for different sections, ensuring a clear separation of
 * functionalities such as controls, camera feed, audio analysis, and event logging.
 * 
 * This class is responsible for initializing and managing the main user interface. It
 * defines how visual components are arranged within the frame, ensuring an intuitive
 * experience for users interacting with the Ghost Box application.
 * 
 * In software design, the main frame acts as the central hub, housing all core
 * functionalities while maintaining scalability for future enhancements. This ensures
 * flexibility in adapting to additional features and improved processing capabilities.
 */
public class MainFrame extends JFrame {

    /**
     * Construction of the class MainFrame. Other than C#, in Java we create the whole
     * Gui in the same class. Java doesn't support partial classes like C#. So the
     * abstraction level in Java comes down to seperate methods. If I create the whole
     * Gui in the constructor, or outsource it to a initializeGui() (Designer Style),
     * is actually potayto - potahto. I am pretty much from the old guard of Software
     * engineers and to rip everything appart and abstract it to a level where it is
     * unreadable any more, makes no sense to me.
     */
    public MainFrame() {

        Debug.writeLine(Debug.DebugLevel.Info, "Initializing MainFrame", "MainFrame");
        Log.writeLine(Log.LogLevel.Info, "Initializing MainFrame", "MainFrame");

        Debug.writeLine(Debug.DebugLevel.Verbose, "Getting title from Config.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Getting title from Config.", "MainFrame");
        setTitle(Config.getString("App.Name") + " - " + Config.getString("App.Version"));

        Debug.writeLine(Debug.DebugLevel.Verbose, "Setting Window size to 300 x 150.", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Setting Window size to 300 x 150.", "MainFrame");
        setSize(Config.getInt("App.Width"), Config.getInt("App.Height"));

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

        JLabel label = new JLabel(Config.getString("App.Label.Text"), JLabel.CENTER);
        label.setFont(new Font(Config.getString("App.Label.Font"), Font.PLAIN, 24));
        add(label);

        Debug.writeLine(Debug.DebugLevel.Verbose, "Binding Ctrl+Q to exit action", "MainFrame");
        Log.writeLine(Log.LogLevel.Verbose, "Binding Ctrl+Q to exit action", "MainFrame");

        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK), "exitApp");
        getRootPane().getActionMap().put("exitApp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debug.writeLine(Debug.DebugLevel.Info, "Ctrl+Q pressed. Exiting application.", "MainFrame");
                Log.writeLine(Log.LogLevel.Info, "Ctrl+Q pressed. Exiting application.", "MainFrame");
                System.exit(0);
            }
        });
    }
}