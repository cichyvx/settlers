package debuger;

/**
 * the interface is to be implemented in every object,
 * that should show specific information about itself in debugging mode
 */

public interface SettlerDebuger {
    /**
     *
     * @return the text to be displayed
     */
    String getCornerText();
}
