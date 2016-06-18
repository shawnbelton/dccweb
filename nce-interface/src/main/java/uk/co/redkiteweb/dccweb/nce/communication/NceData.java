package uk.co.redkiteweb.dccweb.nce.communication;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by shawn on 17/06/16.
 */
public class NceData {

    private Queue<Integer> requestData;
    private Queue<Integer> responseData;

    public NceData() {
        requestData = new PriorityQueue<Integer>();
        responseData = new PriorityQueue<Integer>();
    }

    public void addRequestData(final int data) {
        requestData.add(data);
    }

    public void addResponseData(final int data) {
        responseData.add(data);
    }

    public Integer readRequestData() {
        return requestData.poll();
    }

    public Integer readResponseData() {
        return responseData.poll();
    }
}
