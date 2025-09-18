/* ------------------------------------------------------------------------------------------------
 * ResourceLoader.java - This class is intended to handle the loading of various
 * ------------------------------------------------------------------------------------------------
 * Author:  Patrik Eigemann
 * eMail:   p.eigenmann72@gmail.com
 * GitHub:  www.github.com/PatrikEigemann72/HelloJWorld
 * ------------------------------------------------------------------------------------------------
 * Change Log:
 * Wed 2024-06-19 File created.                                                     Version: 00.01
 * Wed 2024-06-19 Method debug and colores defined.                                 Version: 00.02
 * Thu 2024-06-20 Implemented loadResource() method.                                Version: 00.03
 * Thu 2024-06-20 loadIcon implemented.                                             Version: 00.04
 * Thu 2024-06-20 loadPicture implemented.                                          Version: 00.05
 * Thu 2024-06-20 loadText implemented.                                             Version: 00.06
 * Thu 2024-06-20 loadBin implemented.                                              Version: 00.07
 * Thu 2024-06-20 loadAudio implemented.                                            Version: 00.08
 * Thu 2024-06-20 loadMarkdown implemented.                                         Version: 00.09
 * Thu 2024-06-20 loadHtml implemented.                                             Version: 00.10
 * Thu 2024-06-20 loadCss implemented.                                              Version: 00.11
 * ------------------------------------------------------------------------------------------------ */
package samael.scribe;

// Standard Java imports would go here.
import java.io.InputStream;             // For returning the resource stream
import java.io.FileInputStream;         // For fallback loading from filesystem
import java.io.IOException;             // For handling IO exceptions

/**
 * This class is intended to handle the loading of various
 */
public class ResourceLoader {

    //#region loadResource()
    /**
     * Loads a resource from the application's classpath or local resources folder.
     * <p>
     * This method attempts to retrieve a specific resource—such as an image, audio file,
     * or binary blob—using the class loader. If the resource is not found inside the JAR,
     * it falls back to the local {@code resources/} directory. All diagnostic output is
     * routed through in-class debug messages to avoid external dependencies.
     *
     * @param resource the full classpath-relative path to the resource (e.g. {@code resources/icons/home.png})
     * @return an {@link InputStream} of the resource, or {@code null} if loading fails
     */
    protected static InputStream loadResource(String resource) {
        debug("Info", "Attempting to load resource: " + resource);

        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(resource);
        if (stream != null) {
            debug("Info", "Loaded from classpath: " + resource);
            return stream;
        }

        java.nio.file.Path fallback = java.nio.file.Paths.get(resource);
        if (java.nio.file.Files.exists(fallback)) {
            try {
                debug("Warning", "Classpath failed. Attempting filesystem fallback: " + fallback);
                return new java.io.FileInputStream(fallback.toFile());
            } catch (Exception e) {
                debug("Error", "Failed to open fallback file: " + fallback);
                debug("Error", e.toString());
                return null;
            }
        }

        debug("Error", "Resource not found in classpath or filesystem: " + resource);
        return null;
    }
    //#endregion

    //#region Specific resource loaders
    /**
     * Loads an icon image from the {@code resources/icons/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/icons/}
     * to the given icon name. It then delegates to {@code loadResource()} to retrieve
     * the image as an {@link java.awt.Image} object. All diagnostic output is routed
     * through in-class debug messages to avoid external dependencies.
     *
     * @param icon the filename of the icon (e.g. {@code home.png})
     * @return the loaded {@code Image} object, or {@code null} if loading fails
     */
    public static java.awt.Image loadIcon(String icon) {
        String resource = "resources/icons/" + icon;
        debug("Info", "Preparing to load icon: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing icon resource: " + resource);
                return null;
            }
            return javax.imageio.ImageIO.read(stream);
        } catch (Exception e) {
            debug("Error", "Failed to load icon: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads a picture image from the {@code resources/pictures/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/pictures/}
     * to the given picture name. It then delegates to {@code loadResource()} to retrieve
     * the image as an {@link java.awt.Image} object. All diagnostic output is routed
     * through in-class debug messages to avoid external dependencies.
     *
     * @param picture the filename of the picture (e.g. {@code background.jpg})
     * @return the loaded {@code Image} object, or {@code null} if loading fails
     */
    public static java.awt.Image loadPicture(String picture) {
        String resource = "resources/pictures/" + picture;
        debug("Info", "Preparing to load picture: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing picture resource: " + resource);
                return null;
            }
            return javax.imageio.ImageIO.read(stream);
        } catch (Exception e) {
            debug("Error", "Failed to load picture: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads a UTF-8 encoded text file from the {@code resources/text/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/text/}
     * to the given filename. It then delegates to {@code loadResource()} to retrieve
     * the file as an {@link InputStream}, and reads its contents as a {@link String}.
     * All diagnostic output is routed through in-class debug messages to avoid external dependencies.
     *
     * @param file the filename of the text file (e.g. {@code license.txt})
     * @return the loaded {@code String} content, or {@code null} if loading fails
     */
    public static String loadText(String file) {
        String resource = "resources/text/" + file;
        debug("Info", "Preparing to load text: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing text resource: " + resource);
                return null;
            }

            return new String(stream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            debug("Error", "Failed to load text: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads a binary file from the {@code resources/bin/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/bin/}
     * to the given filename. It then delegates to {@code loadResource()} to retrieve
     * the file as an {@link InputStream}, and reads its contents as a {@code byte[]} array.
     * All diagnostic output is routed through in-class debug messages to avoid external dependencies.
     *
     * @param file the filename of the binary file (e.g. {@code data.bin})
     * @return the loaded {@code byte[]} content, or {@code null} if loading fails
     */
    public static byte[] loadBin(String file) {
        String resource = "resources/bin/" + file;
        debug("Info", "Preparing to load binary: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing binary resource: " + resource);
                return null;
            }

            return stream.readAllBytes();
        } catch (Exception e) {
            debug("Error", "Failed to load binary: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads an audio resource from the {@code resources/audio/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/audio/}
     * to the given audio filename. It then delegates to {@code loadResource()} to retrieve
     * the audio file as an {@link InputStream}. All diagnostic output is routed through
     * in-class debug messages to avoid external dependencies.
     *
     * @param audio the filename of the audio file (e.g. {@code startup.wav})
     * @return the loaded {@code InputStream} of the audio file, or {@code null} if loading fails
     */
    public static InputStream loadAudio(String audio) {
        String resource = "resources/audio/" + audio;
        debug("Info", "Preparing to load audio: " + resource);

        InputStream stream = loadResource(resource);
        if (stream == null) {
            debug("Error", "Missing audio resource: " + resource);
            return null;
        }

        return stream;
    }

    /**
     * Loads a Markdown file from the {@code resources/markdown/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/markdown/}
     * to the given filename. It then delegates to {@code loadResource()} to retrieve
     * the file as an {@link InputStream}, and reads its contents as a {@link String}.
     * All diagnostic output is routed through in-class debug messages to avoid external dependencies.
     *
     * @param file the filename of the Markdown file (e.g. {@code readme.md})
     * @return the loaded {@code String} content, or {@code null} if loading fails
     */
    public static String loadMarkdown(String file) {
        String resource = "resources/markdown/" + file;
        debug("Info", "Preparing to load markdown: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing markdown resource: " + resource);
                return null;
            }

            return new String(stream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            debug("Error", "Failed to load markdown: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads an HTML file from the {@code resources/html/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/html/}
     * to the given filename. It then delegates to {@code loadResource()} to retrieve
     * the file as an {@link InputStream}, and reads its contents as a {@link String}.
     * All diagnostic output is routed through in-class debug messages to avoid external dependencies.
     *
     * @param file the filename of the HTML file (e.g. {@code index.html})
     * @return the loaded {@code String} content, or {@code null} if loading fails
     */
    public static String loadHtml(String file) {
        String resource = "resources/html/" + file;
        debug("Info", "Preparing to load HTML: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing HTML resource: " + resource);
                return null;
            }

            return new String(stream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            debug("Error", "Failed to load HTML: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }

    /**
     * Loads a CSS file from the {@code resources/css/} subfolder.
     * <p>
     * This method constructs the full resource path by prepending {@code resources/css/}
     * to the given filename. It then delegates to {@code loadResource()} to retrieve
     * the file as an {@link InputStream}, and reads its contents as a {@link String}.
     * All diagnostic output is routed through in-class debug messages to avoid external dependencies.
     *
     * @param file the filename of the CSS file (e.g. {@code style.css})
     * @return the loaded {@code String} content, or {@code null} if loading fails
     */
    public static String loadCss(String file) {
        String resource = "resources/css/" + file;
        debug("Info", "Preparing to load CSS: " + resource);

        try (InputStream stream = loadResource(resource)) {
            if (stream == null) {
                debug("Error", "Missing CSS resource: " + resource);
                return null;
            }

            return new String(stream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            debug("Error", "Failed to load CSS: " + resource);
            debug("Error", e.toString());
            return null;
        }
    }
    //#endregion

    //#region Debug ANSI color codes and method
    /**
     * ANSI escape code for red text.
     * Used to highlight [Error] messages in the in-class debug section.
     */
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * ANSI escape code for yellow text.
     * Used to highlight [Warning] messages in the in-class debug section.
     */
    private static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * ANSI escape code for blue text.
     * Used to highlight [Info] messages in the in-class debug section.
     */
    private static final String ANSI_CYAN = "\u001B[36m";

    /**
     * ANSI escape code for resetting text color.
     * Always used at the end of a debug message to restore default terminal color.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Emulates samael.huginandmunin.Debug output without dependency.
     * Outputs timestamped messages with severity tags.
     *
     * @param level   Severity level: "Info", "Warning", "Error"
     * @param message The message to log
     */
    private static void debug(String level, String message) {
        String timestamp = java.time.LocalTime.now()
            .truncatedTo(java.time.temporal.ChronoUnit.MILLIS)
            .toString();

        String color = switch (level) {
            case "Error"   -> ANSI_RED;
            case "Warning" -> ANSI_YELLOW;
            case "Info"    -> ANSI_CYAN;
            default        -> ANSI_RESET;
        };

        System.out.println(String.format(
            "%s%s [%s] [ResourceLoader] %s%s",
            color,
            timestamp,
            level,
            message,
            ANSI_RESET
        ));
    }
    //#endregion
}
