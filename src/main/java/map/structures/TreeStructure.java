package map.structures;

public class TreeStructure extends SettlerStructure {

    @Override
    public String getCornerText() {
        StringBuilder debugText = new StringBuilder();
        debugText.append(toString());

        return debugText.toString();
    }

    @Override
    public String toString() {
        return "TREE";
    }
}
