package uk.co.redkiteweb.dccweb.data;

import uk.co.redkiteweb.dccweb.data.model.Loco;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by shawn on 12/09/16.
 */
public class Cab implements Serializable {

    static final long serialVersionUID = 8000;

    private Loco loco;
    private int speed;
    private String direction;
    private String steps;
    private Set<CabFunction> cabFunctions;

    public Loco getLoco() {
        return loco;
    }

    public void setLoco(final Loco loco) {
        this.loco = loco;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(final String steps) {
        this.steps = steps;
    }

    public Set<CabFunction> getCabFunctions() {
        return cabFunctions;
    }

    public void setCabFunctions(final Set<CabFunction> cabFunctions) {
        this.cabFunctions = cabFunctions;
    }
}
