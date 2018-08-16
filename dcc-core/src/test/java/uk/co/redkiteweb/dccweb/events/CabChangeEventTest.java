package uk.co.redkiteweb.dccweb.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class CabChangeEventTest {

    private CabChangeEvent event;

    @Before
    public void setup() {
        final Cab cab = mock(Cab.class);
        event = new CabChangeEvent(cab);
    }

    @Test
    public void testUrl() {
        assertEquals("/cab", event.getUrl());
    }

    @Test
    public void testObject() {
        assertNotNull(event.sendObject());
    }
}
