package map.structures;

import debuger.SettlerDebuger;

public abstract class SettlerStructure implements Structure2D, SettlerDebuger {

    @Override
    public String getCornerText() {
        StringBuilder debugText = new StringBuilder();
        debugText.append(toString());

        return debugText.toString();
    }

}
