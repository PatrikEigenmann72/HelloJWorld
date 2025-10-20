/* -------------------------------------------------------------------------------
 * App.java -  The App class serves as the entry point for the application. When
 * the user launches the program—whether by double-clicking an icon or running it
 * from the command line—the system needs a defined starting point. This class
 * provides that by containing the main method.
 *
 * The main method is where execution begins, initializing necessary components and
 * setting the application into motion. It ensures that the first frame, user
 * interface, and core functionality are properly instantiated.
 *
 * In a structured software environment, every project needs a single place where
 * the system knows to start. This class fulfills that role, keeping the application
 * streamlined and predictable across multiple use cases.
 * -------------------------------------------------------------------------------
 * Author:  Patrik Eigemann
 * eMail:   p.eigenmann72@gmail.com
 * GitHub:  www.github.com/PatrikEigemann72/HelloJWorld
 * -------------------------------------------------------------------------------
 * Change Log:
 * Mon 2025-05-26 File created.                                     Version: 00.01
 * Thu 2025-08-21 Reorganized project structure and namespaces.     Version: 00.02
 * Fri 2025-08-22 Improved logging and debugging capabilities.      Version: 00.03
 * Wed 2025-09-17 Updated Config from huginandmunin to chronicle.   Version: 00.04
 * Thu 2025-09-18 Implemented samael.scribe.ResourceLoader.         Version: 00.05
 * Thu 2025-09-18 Logged more sophisticated messages.               Version: 00.06
 * Mon 2025-10-20 Restructured ResourceLoader import path.          Version: 00.07
 * ------------------------------------------------------------------------------- */
import hellojworld.gui.*;
import javax.swing.SwingUtilities;
import samael.huginandmunin.*;
import samael.chronicle.Config;
import samael.necronomicon.ResourceLoader;

/**
 * App.java -  The App class serves as the entry point for the application. When
 * the user launches the program—whether by double-clicking an icon or running it
 * from the command line—the system needs a defined starting point. This class
 * provides that by containing the main method.
 *
 * The main method is where execution begins, initializing necessary components and
 * setting the application into motion. It ensures that the first frame, user
 * interface, and core functionality are properly instantiated.
 *
 * In a structured software environment, every project needs a single place where
 * the system knows to start. This class fulfills that role, keeping the application
 * streamlined and predictable across multiple use cases.
 */
public class App {

    /**
     * The main method acts as the starting point of the application. When the program
     * is launched, execution begins here, ensuring that all necessary components are
     * initialized and the user interface is displayed.
     *
     * This method is responsible for setting the application in motion. It ensures
     * that the primary frame is created and rendered within the correct thread
     * context to maintain responsiveness and stability.
     *
     * In structured software design, having a clearly defined entry point allows for
     * predictable execution, making the application easy to manage and extend.
     *
     * @param args Command-line arguments passed during application startup.
     *             These can be used for configuration or debugging but are
     *             typically not required in standard executions.
     */
    public static void main(String[] args) {

        String msg1;

        Debug.init(args);
        Log.init(Config.getString("App.LogName"));

        Debug.setBitmask(Debug.DebugLevel.All.value);
        Log.setBitmask(Log.LogLevel.All.value);

        msg1 = "Starting " + Config.getString("App.Name") + " " + Config.getString("App.Version");
        Debug.writeLine(Debug.DebugLevel.Info, msg1, "App");
        Log.writeLine(Log.LogLevel.Info, msg1, "App");
        
        // Using SwingUtilities.invokeLater to start a
        // Swing application as background task.
        SwingUtilities.invokeLater(() -> {
            String msg2 = "Launching main frame.";
            Debug.writeLine(Debug.DebugLevel.Info, msg2, "App");
            Log.writeLine(Log.LogLevel.Info, msg2, "App");

            MainFrame mf = new MainFrame();
            
            msg2 = "Loading application icon: " + Config.getString("App.IconName");
            Debug.writeLine(Debug.DebugLevel.Info, msg2, "App");
            Log.writeLine(Log.LogLevel.Info, msg2, "App");
            mf.setIconImage(ResourceLoader.loadIcon(Config.getString("App.IconName")));
            
            msg2 = "Making main frame visible.";
            Debug.writeLine(Debug.DebugLevel.Info, msg2, "App");
            Log.writeLine(Log.LogLevel.Info, msg2, "App");
            mf.setVisible(true);
        });
    }
}