package uk.co.redkiteweb.dccweb.nce.communication;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by shawn on 17/06/16.
 */
public class NceData {

    private Queue<Integer> nceData;

    public NceData() {
        nceData = new PriorityQueue<Integer>();
    }

    public void addData(final int data) {
        nceData.add(data);
    }

    public Integer readData() {
        return nceData.poll();
    }
}
