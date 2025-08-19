// --------------------------------------------------------------------------------------------
// File: Samael/HuginAndMunin/Log.java
// This file is part of the Samael.HuginAndMunin library and provides a logging utility.
// It includes methods for writing log messages on the levels of errors and exceptions,
// warnings, and informational or verbose messages. With this utility, developers can
// persist application behavior to a log file for later inspection. The log messages are
// timestamped and categorized for clarity.
// --------------------------------------------------------------------------------------------
// Author:          Patrik Eigenmann
// eMail:           p.eigenmann72@gmail.com
// GitHub:          https://github.com/PatrikEigenmann72/HelloJWorld
// --------------------------------------------------------------------------------------------
// Change Log:
// Tue 2025-08-19 Initial Java implementation based on Debug.java.           Version: 00.01
// Tue 2025-08-19 Replaced try-with-resources with explicit close().         Version: 00.02
// --------------------------------------------------------------------------------------------
package samael.huginandmunin;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Log {

    public enum LogLevel {
        None(0),
        @SuppressWarnings("PointlessBitwiseExpression")
        Error(1 << 0),
        Warning(1 << 1),
        Info(1 << 2),
        Verbose(1 << 3),
        All(Error.value | Warning.value | Info.value | Verbose.value);

        public final int value;
        LogLevel(int value) { this.value = value; }
    }

    private static int bitmask = LogLevel.All.value;
    private static String logFileName;

    public static void init(String fileName) {
        String home = System.getProperty("user.home");
        File logDir = new File(home, "Documents\\Logs");
        if (!logDir.exists() && !logDir.mkdirs()) {
            System.err.println("Failed to create log directory: " + logDir.getAbsolutePath());
            return;
        }

        logFileName = new File(logDir, fileName).getAbsolutePath();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(logFileName, false));
            // Overwrite file with empty start
        } catch (IOException ex) {
            System.err.println("Failed to initialize log file: " + ex.getMessage());
        } finally {
            close(writer);
        }
    }

    public static void setBitmask(int bitmaskIn) {
        bitmask = bitmaskIn;
    }

    public static void writeLine(LogLevel level, String message, String component) {
        if ((bitmask & level.value) == 0 || logFileName == null) return;

        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        String line = String.format("%s [%s] [%s] %s", timestamp, level.name(), component, message);

        PrintWriter writer = null;
        try {
            writer = open();
            writer.println(line);
        } catch (IOException ex) {
            System.err.println("Log write failed: " + ex.getMessage());
        } finally {
            close(writer);
        }
    }

    public static void writeException(Exception ex) {
        if ((bitmask & LogLevel.Error.value) == 0 || logFileName == null) return;

        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        PrintWriter writer = null;
        try {
            writer = open();
            writer.println(timestamp + " [Exception] " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            for (StackTraceElement elem : ex.getStackTrace()) {
                writer.println("  at " + elem.toString());
            }
        } catch (IOException e) {
            System.err.println("Exception log failed: " + e.getMessage());
        } finally {
            close(writer);
        }
    }

    private static PrintWriter open() throws IOException {
        return new PrintWriter(new FileWriter(logFileName, true), true);
    }

    private static void close(PrintWriter writer) {
        if (writer != null) {
            writer.close();
        }
    }
}