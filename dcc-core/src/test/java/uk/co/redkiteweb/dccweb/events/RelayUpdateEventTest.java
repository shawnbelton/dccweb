package uk.co.redkiteweb.dccweb.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class RelayUpdateEventTest {

    private RelayUpdateEvent event;

    @Before
    public void setup() {
        final RelayController relay = mock(RelayController.class);
        when(relay.getIpAddress()).thenReturn("ipaddress");
        when(relay.getValue()).thenReturn(10);
        event = new RelayUpdateEvent(relay);
    }

    @Test
    public void testUrl() {
        assertEquals("/relays", event.getUrl());
    }

    @Test
    public void testObject() {
        assertNotNull(event.sendObject());
    }

    @Test
    public void testUpdateUrl() {
        assertEquals("http://ipaddres/setrelay/10", event.getUpdateUrl());
    }
}
