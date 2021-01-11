package map.structures;

import debuger.SettlerDebuger;

public class TreeStructure implements Structure2D, SettlerDebuger {

    @Override
    public String toString() {
        return "TREE";
    }

    @Override
    public String getCornerText() {
        StringBuilder debugText = new StringBuilder();
        debugText.append(toString());

        return debugText.toString();
    }
}
