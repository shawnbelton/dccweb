package uk.co.redkiteweb.dccweb.webapp.data;

import uk.co.redkiteweb.dccweb.data.model.Train;

import java.io.Serializable;

/**
 * Created by shawn on 12/09/16.
 */
public class Cab implements Serializable {

    static final long serialVersionUID = 8000;

    private Train train;
    private int speed;
    private String direction;
    private String steps;

    public Train getTrain() {
        return train;
    }

    public void setTrain(final Train train) {
        this.train = train;
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
}
