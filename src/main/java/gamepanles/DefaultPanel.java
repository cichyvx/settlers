package gamepanles;

import javax.swing.*;

public class DefaultPanel extends JPanel implements GamePanel2D{

    protected int status, optionalStatus;

    public DefaultPanel(){
        status = 0;
        optionalStatus = 0;
    }

    @Override
    public void update() {

    }

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
