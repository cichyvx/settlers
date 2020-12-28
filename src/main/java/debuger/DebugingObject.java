package debuger;

public class DebugingObject<T extends SettlerDebuger> implements SettlerDebuger{

    private T debuger;

    public DebugingObject(T debuger){
        this.debuger = debuger;
    }

    @Override
    public String getCornerText() {
        return debuger.getCornerText();
    }
}
