package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 22/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecodersTest {

    private AccessoryDecoders accessoryDecoders;
    private AccessoryService accessoryService;

    @Before
    public void setup() {
        accessoryService = mock(AccessoryService.class);
        accessoryDecoders = new AccessoryDecoders();
        accessoryDecoders.setAccessoryService(accessoryService);
    }

    @Test
    public void testAllAccessoryDecoderTypes() {
        when(accessoryService.getAccessoryDecoderTypes()).thenReturn(new ArrayList<>());
        assertNotNull(accessoryDecoders.allAccessoryDecoderTypes());
    }

    @Test
    public void testAllAccessoryDecoders() {
        when(accessoryService.getAccessoryDecoders()).thenReturn(new ArrayList<>());
        assertNotNull(accessoryDecoders.allAccessoryDecoders());
    }

    @Test
    public void testSaveAccessoryDecoder() {
        when(accessoryService.saveAccessoryDecoder(any(AccessoryDecoder.class))).thenReturn(new ArrayList<>());
        assertNotNull(accessoryDecoders.saveAccessoryDecoder(new AccessoryDecoder()));
    }

    @Test
    public void testOperateAccessory() {
        assertNotNull(accessoryDecoders.operateAccessory(new AccessoryOperation()));
        verify(accessoryService, times(1)).operateService(any(AccessoryOperation.class));
    }
}
