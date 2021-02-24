package gamepanles;

import javax.swing.*;

public abstract class DefaultPanel extends JPanel implements GamePanel2D{

    /**
     * status is used to signal the change of the Panel to another
     * optionalStatus is used to signal changes to the current Panel or other global technical changes
     */
    protected int status, optionalStatus;

    public DefaultPanel(){
        status = 0;
        optionalStatus = 0;
    }

    /**
     * updating the current status of the Panel
     */
    @Override
    public void update() {

    }

    /**
     * drawing Inside the graphics panel
     */
    @Override
    public void draw() {

    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int getOptionalStatus() {
        return optionalStatus;
    }

    protected void setStatus(int status) { this.status = status; }

    protected void setOptionalStatus(int optionalStatus) { this.optionalStatus = optionalStatus; }
}
