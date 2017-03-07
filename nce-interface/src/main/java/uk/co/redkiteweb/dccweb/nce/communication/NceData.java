package uk.co.redkiteweb.dccweb.nce.communication;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shawn on 17/06/16.
 */
public class NceData {

    private final Queue<Integer> nceData;
    private int expectedValues;

    public NceData() {
        nceData = new LinkedList<Integer>();
    }

    public int getExpectedValues() {
        return expectedValues;
    }

    public void setExpectedValues(int expectedValues) {
        this.expectedValues = expectedValues;
    }

    public void addData(final int data) {
        nceData.add(data);
    }

    public Integer readData() {
        return nceData.poll();
    }

    public boolean isEmpty() {
        return nceData.isEmpty();
    }

    public int size() {
        return nceData.size();
    }
}
