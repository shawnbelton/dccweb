package uk.co.redkiteweb.dccweb.randommac;

import java.util.Date;
import java.util.Random;

/**
 * Created by shawn on 09/03/17.
 */
public class MacAddress {

    private Random random;
    private int[] macAddress;

    public MacAddress() {
        macAddress = new int[6];
        random = new Random(new Date().getTime());
        buildMacAddress();
    }

    private void buildMacAddress() {
        macAddress[0] = 0xDE;
        macAddress[1] = 0xAD;
        macAddress[2] = 0xBE;
        for (int ind = 3; ind < 6; ind++) {
            macAddress[ind] = random.nextInt() & 0xff;
        }
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        for(int ind = 0; ind < 6; ind++) {
            buffer.append(String.format("%02X", macAddress[ind]));
        }
        return buffer.toString();
    }
}
