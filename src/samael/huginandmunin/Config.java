/* ---------------------------------------------------------------------------------------------
 * Config.java -  The Config class is a singleton class, so it is only one time instantiated
 * within the application. I decided to create an in memory key=value storage for security
 * reasons. Config files can have sensitive data like passwords and API keys. If these sensitive
 * data are stored in plain text, they could be easily accessed by unauthorized users. By using
 * an in-memory storage solution, we can minimize the risk of exposing sensitive information.
 * Still handle with care.
 * ---------------------------------------------------------------------------------------------
 * Author:  Patrik Eigemann
 * eMail:   p.eigenmann72@gmail.com
 * GitHub:  www.github.com/PatrikEigemann72/HelloJWorld
 * ---------------------------------------------------------------------------------------------
 * Change Log:
 * Mon 2025-08-18 File created.                                                 Version: 00.01
 * Mon 2025-08-18 Refactored to use Lazy Holder Pattern.                        Version: 00.02
 * Mon 2025-08-18 Added static proxy methods for get/set.                       Version: 00.03
 * Mon 2025-08-18 Renamed instance methods to avoid shadowing.                  Version: 00.04
 * Mon 2025-08-18 Updated constructor to use setSetting with namespaced keys.   Version: 00.05
 * --------------------------------------------------------------------------------------------- */
package samael.huginandmunin;

// Standard Java imports. These imports are needed to have the Config class working properly.
import java.util.HashMap;
import java.util.Map;

/**
 * Config is a lazily initialized, thread-safe singleton with static access methods.
 */
public final class Config {

    /** Inner static class responsible for holding the singleton instance. */
    private static class Holder {
        private static final Config INSTANCE = new Config();
    }

    /** Returns the singleton instance of Config. */
    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    /** In-memory key=value store */
    private final Map<String, String> settings;

    /** Private constructor to prevent external instantiation */
    private Config() {
        settings = new HashMap<>();
        setSetting("App.Name", "HelloJWorld");
        setSetting("App.Version", "00.01");
        setSetting("App.Author", "Patrik Eigemann");
        setSetting("App.Label.Text", "Hello Java World!");
        setSetting("App.LogName", "HelloJWorld.log");
    }

    /** Static proxy for getting a config value */
    public static String get(String key) {
        return getInstance().getSetting(key);
    }

    /** Static proxy for setting a config value */
    public static void set(String key, String value) {
        getInstance().setSetting(key, value);
    }

    /** Instance method for getting a config value (not intended for direct use) */
    private String getSetting(String key) {
        return settings.get(key);
    }

    /** Instance method for setting a config value (not intended for direct use) */
    private void setSetting(String key, String value) {
        settings.put(key, value);
    }
}