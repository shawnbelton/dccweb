package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 08/07/16.
 */
@RunWith(JUnit4.class)
public class MessageResponseTest {

    private MessageResponse messageResponse;

    @Before
    public void setUp() {
        messageResponse = new MessageResponse();
        messageResponse.setStatus(MessageResponse.MessageStatus.OK);
        messageResponse.put("TEST", new Integer(1));
    }

    @Test
    public void testStatus() {
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
    }

    @Test
    public void testGetData() {
        assertEquals(1, messageResponse.get("TEST"));
    }

    @Test
    public void testGetNotExisting() {
        assertNull(messageResponse.get("MISSING"));
    }
}
