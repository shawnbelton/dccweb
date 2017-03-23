package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 21/03/17.
 */
@RunWith(JUnit4.class)
public class NotificationTest {

    private Notification notification;

    @Before
    public void setup() {
        notification = new Notification();
        notification.setNotificationId(1l);
        notification.setType("STATUS");
        notification.setValue("Connected");
        notification.setCreated(new Date(100000));
    }

    @Test
    public void testId() {
        assertEquals(new Long(1), notification.getNotificationId());
    }

    @Test
    public void testType() {
        assertEquals("STATUS", notification.getType());
    }

    @Test
    public void testValue() {
        assertEquals("Connected", notification.getValue());
    }

    @Test
    public void testCreated() {
        assertEquals(100000, notification.getCreated().getTime());
    }
}
