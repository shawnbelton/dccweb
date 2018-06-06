package uk.co.redkiteweb.dccweb.nce.communication;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shawn on 17/06/16.
 */
public class NceData {

    private final Queue<Integer> nceDataQueue;
    private int expectedValues;

    public NceData() {
        nceDataQueue = new LinkedList<>();
    }

    public int getExpectedValues() {
        return expectedValues;
    }

    public void setExpectedValues(int expectedValues) {
        this.expectedValues = expectedValues;
    }

    public void addData(final int data) {
        nceDataQueue.add(data);
    }

    public Integer readData() {
        return nceDataQueue.poll();
    }

    public boolean isEmpty() {
        return nceDataQueue.isEmpty();
    }

    public int size() {
        return nceDataQueue.size();
    }
}
