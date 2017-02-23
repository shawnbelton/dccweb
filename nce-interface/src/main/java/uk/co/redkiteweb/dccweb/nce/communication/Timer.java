package uk.co.redkiteweb.dccweb.nce.communication;

import java.util.Date;

/**
 * Created by shawn on 18/01/17.
 */
class Timer {

    private long startTime;
    private final long timeout;

    public Timer(final long timeout) {
        this.timeout = timeout;
    }

    public void start() {
        this.startTime = new Date().getTime();
    }

    public boolean hasTimedOut() {
        return getRunningTime() > timeout;
    }

    public long getRunningTime() {
        return new Date().getTime() - startTime;
    }
}
