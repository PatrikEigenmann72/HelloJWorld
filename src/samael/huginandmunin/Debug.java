// --------------------------------------------------------------------------------------------
// File: Samael/HuginAndMunin/Debug.java
// This file is part of the Samael.HuginAndMunin library and provides a debugging utility.
// It includes methods for displaying debug messages on the levels of errors and exceptions,
// warnings, and informational or verbose messages. With this utility, developers can easily
// track their application's behavior during development and debugging phases. The debug
// messaging will be shut off in release builds to avoid performance overhead and cluttering
// the console output. The debug messages are color-coded for better visibility in the console.
// --------------------------------------------------------------------------------------------
// Author:          Patrik Eigenmann
// eMail:           p.eigenmann72@gmail.com
// GitHub:          https://github.com/PatrikEigenmann72/HelloJWorld
// --------------------------------------------------------------------------------------------
// Change Log:
// Mon 2025-08-18 Initial Java port from C# version.                          Version: 00.01
// --------------------------------------------------------------------------------------------
package samael.huginandmunin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This file is part of the Samael.HuginAndMunin library and provides a debugging utility.
 * It includes methods for displaying debug messages on the levels of errors and exceptions,
 * warnings, and informational or verbose messages. With this utility, developers can easily
 * track their application's behavior during development and debugging phases. The debug
 * messaging will be shut off in release builds to avoid performance overhead and cluttering
 * the console output. The debug messages are color-coded for better visibility in the console.
 */
public final class Debug {

    /** The enum DebugLevel is used to categorize the severity of debug messages. */
    public enum DebugLevel {
        None(0),                                                            // No debug messages will be shown.
        @SuppressWarnings("PointlessBitwiseExpression")
        Error(1 << 0),                                                      // Error messages will be shown.
        Warning(1 << 1),                                                    // Warning messages will be shown.
        Info(1 << 2),                                                       // Informational messages will be shown.
        Verbose(1 << 3),                                                    // Verbose messages will be shown.
        All(Error.value | Warning.value | Info.value | Verbose.value);      // All messages will be shown.

        /** The integer value associated with each debug level. */
        public final int value;

        /** Construction for the enum class. */
        DebugLevel(int value) {
            this.value = value;
        }
    }

    /** The bitmask representing the active debug levels. */
    private static int bitmask = DebugLevel.All.value;

    /** Flag indicating whether debugging is enabled. */
    private static boolean debugOn = false;

    /** Enables debug mode explicitly. */
    private static void enable() {
        debugOn = true;
    }

    /** Returns whether debug mode is currently enabled. */
    private static boolean isDebugOn() {
        return debugOn;
    }

    /**
     * Sets the bitmask representing the active debug levels.
     * 
     * @param bitmaskIn The new bitmask value to set.
     */
    public static void setBitmask(int bitmaskIn) {
        bitmask = bitmaskIn;
    }

    /**
     * Initializes the debug utility with command-line arguments.
     * 
     * @param args The command-line arguments.
     */
    public static void init(String[] args) {
    for (String arg : args) {
        if ("-debug".equalsIgnoreCase(arg)) {
            enable();
            break;
            }
        }
    }

    /** Writes a debug message to the console if debug mode is active and the level is enabled. */
    public static void writeLine(DebugLevel level, String message, String component) {
        if (isDebugOn() && (bitmask & level.value) != 0) {
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
            String prefix = String.format("%s [%s] [%s] ", timestamp, level.name(), component);
            System.out.println(prefix + message);
        }
    }

    /** Writes an exception stack trace to the error output if debug mode is active and Error level is enabled. */
    public static void writeException(Exception ex) {
        if (isDebugOn() && (bitmask & DebugLevel.Error.value) != 0) {
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
            System.err.println(timestamp + " [Exception] " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

/*
# not needed section. I will put all the methods I don't need here.

private static String colorize(DebugLevel level, String message) {
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_GRAY = "\u001B[90m";

    return switch (level) {
        case Error   -> ANSI_RED    + message + ANSI_RESET;
        case Warning -> ANSI_YELLOW + message + ANSI_RESET;
        case Info    -> ANSI_CYAN   + message + ANSI_RESET;
        case Verbose -> ANSI_GRAY   + message + ANSI_RESET;
        default      -> message;
    };
}
*/
}