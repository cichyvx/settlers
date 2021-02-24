package debuger;

/**
 * the class represents the object that is to store the object we are currently debugging
 * @param <T> Currently debugging object
 */

public class DebugingObject<T extends SettlerDebuger> implements SettlerDebuger{

    private T debuger;

    /**
     *
     * @param debuger object thaw we want do debugging
     */
    public DebugingObject(T debuger){
        this.debuger = debuger;
    }

    @Override
    public String getCornerText() {
        return debuger.getCornerText();
    }
}
