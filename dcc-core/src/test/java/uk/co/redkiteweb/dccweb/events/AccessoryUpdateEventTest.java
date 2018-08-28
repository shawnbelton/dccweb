package uk.co.redkiteweb.dccweb.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.Macro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AccessoryUpdateEventTest {

    private AccessoryUpdateEvent event;

    @Before
    public void setup() {
        final AccessoryDecoder accessoryDecoder = mock(AccessoryDecoder.class);
        when(accessoryDecoder.getMacro()).thenReturn(mock(Macro.class));
        event = new AccessoryUpdateEvent(accessoryDecoder);
    }

    @Test
    public void testUrl() {
        assertEquals("/accessory", event.getUrl());
    }

    @Test
    public void testMacro() {
        assertNotNull(event.getMacro());
    }

    @Test
    public void testObject() {
        assertNotNull(event.sendObject());
    }
}
