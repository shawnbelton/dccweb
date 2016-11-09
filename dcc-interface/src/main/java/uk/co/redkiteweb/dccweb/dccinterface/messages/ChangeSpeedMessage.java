package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 20/09/16.
 */
public class ChangeSpeedMessage implements Message {

    public enum SpeedSteps {
        STEPS_28,
        STEPS_128
    }

    public enum Direction {
        RSTOP,
        FSTOP,
        UP,
        DOWN
    }

    private SpeedSteps speedSteps;
    private int speed;
    private Direction direction;
    private int address;
    private boolean addressMode;

    public SpeedSteps getSpeedSteps() {
        return speedSteps;
    }

    public void setSpeedSteps(final SpeedSteps speedSteps) {
        this.speedSteps = speedSteps;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(final int address) {
        this.address = address;
    }

    public boolean isAddressMode() {
        return addressMode;
    }

    public void setAddressMode(final boolean addressMode) {
        this.addressMode = addressMode;
    }
}
