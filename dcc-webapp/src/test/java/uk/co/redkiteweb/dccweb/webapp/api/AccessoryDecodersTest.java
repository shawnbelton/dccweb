package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 22/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecodersTest {

    private AccessoryDecoders accessoryDecoders;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;
    private AccessoryService accessoryService;

    @Before
    public void setup() {
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        accessoryDecoderTypeRepository = mock(AccessoryDecoderTypeRepository.class);
        accessoryService = mock(AccessoryService.class);
        accessoryDecoders = new AccessoryDecoders();
        accessoryDecoders.setAccessoryDecoderRepository(accessoryDecoderRepository);
        accessoryDecoders.setAccessoryDecoderTypeRepository(accessoryDecoderTypeRepository);
        accessoryDecoders.setAccessoryService(accessoryService);
    }

    @Test
    public void testAllAccessoryDecoderTypes() {
        when(accessoryDecoderTypeRepository.findAll()).thenReturn(new ArrayList<AccessoryDecoderType>());
        assertNotNull(accessoryDecoders.allAccessoryDecoderTypes());
    }

    @Test
    public void testAllAccessoryDecoders() {
        when(accessoryDecoderRepository.findAll()).thenReturn(new ArrayList<AccessoryDecoder>());
        assertNotNull(accessoryDecoders.allAccessoryDecoders());
    }

    @Test
    public void testSaveAccessoryDecoder() {
        when(accessoryDecoderRepository.findAll()).thenReturn(new ArrayList<AccessoryDecoder>());
        assertNotNull(accessoryDecoders.saveAccessoryDecoder(new AccessoryDecoder()));
        verify(accessoryDecoderRepository, times(1)).save(any(AccessoryDecoder.class));
    }

    @Test
    public void testOperateAccessory() {
        assertTrue(accessoryDecoders.operateAccessory(new AccessoryOperation()));
        verify(accessoryService, times(1)).operateService(any(AccessoryOperation.class));
    }
}
