package uk.co.redkiteweb.dccweb.nce.communication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 18/06/16.
 */
@RunWith(JUnit4.class)
public class NceDataTest {

    @Test
    public void testData() {
        final NceData nceData = new NceData();
        nceData.addData(3);
        nceData.addData(1);
        nceData.addData(128);
        assertEquals(3, (int)nceData.readData());
        assertEquals(1, (int)nceData.readData());
        assertEquals(128, (int)nceData.readData());
        assertNull(nceData.readData());
    }

}
