package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;
import uk.co.redkiteweb.dccweb.events.AccessoryUpdateEvent;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/03/17.
 */
@RunWith(JUnit4.class)
public class AccessoryServiceTest {

    private AccessoryService accessoryService;
    private EventBus eventBus;
    private DccInterface dccInterface;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        accessoryDecoderTypeRepository = mock(AccessoryDecoderTypeRepository.class);
        eventBus = mock(EventBus.class);
        accessoryService = new AccessoryService();
        accessoryService.setDccInterface(dccInterface);
        accessoryService.setAccessoryDecoderRepository(accessoryDecoderRepository);
        accessoryService.setAccessoryDecoderTypeRepository(accessoryDecoderTypeRepository);
        accessoryService.setEventBus(eventBus);
    }

    @Test
    public void testOperateAccessory() {
        final List<AccessoryDecoder> accessories = new ArrayList<AccessoryDecoder>();
        accessories.add(getAccessoryDecoder());
        when(accessoryDecoderRepository.findAccessoryDecodersByAddress(anyInt()))
                .thenReturn(accessories);
        final AccessoryOperation accessoryOperation = new AccessoryOperation();
        accessoryOperation.setAccessoryAddress(123);
        accessoryOperation.setOperationValue(0);
        accessoryService.operateService(accessoryOperation);
        verify(dccInterface, times(1)).sendMessage(any(OperateAccessoryMessage.class));
    }

    @Test
    public void testOperateAccessoryWithMacro() {
        final List<AccessoryDecoder> accessories = new ArrayList<AccessoryDecoder>();
        final AccessoryDecoder accessoryDecoder = getAccessoryDecoder();
        accessoryDecoder.setMacro(new Macro());
        accessories.add(accessoryDecoder);
        when(accessoryDecoderRepository.findAccessoryDecodersByAddress(anyInt()))
                .thenReturn(accessories);
        final AccessoryOperation accessoryOperation = new AccessoryOperation();
        accessoryOperation.setAccessoryAddress(123);
        accessoryOperation.setOperationValue(0);
        accessoryService.operateServiceAsyc(accessoryOperation);
        verify(dccInterface, times(1)).sendMessage(any(OperateAccessoryMessage.class));
        verify(eventBus, times(1)).post(any(AccessoryUpdateEvent.class));
    }

    @Test
    public void testGetAllAccessories() {
        when(accessoryDecoderRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(accessoryService.getAccessoryDecoders());
    }

    @Test
    public void testGetAccessoryDecoderTypes() {
        when(accessoryDecoderTypeRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(accessoryService.getAccessoryDecoderTypes());
    }

    @Test
    public void testSaveNoCurrent() {
        when(accessoryDecoderRepository.findAll()).thenReturn(new ArrayList<>());
        final AccessoryDecoder accessoryDecoder = mock(AccessoryDecoder.class);
        when(accessoryDecoder.getCurrentValue()).thenReturn(null);
        final AccessoryDecoderType accessoryDecoderType = mock(AccessoryDecoderType.class);
        when(accessoryDecoder.getAccessoryDecoderType()).thenReturn(accessoryDecoderType);
        final AccessoryDecoderTypeOperation accessoryDecoderTypeOperation = mock(AccessoryDecoderTypeOperation.class);
        final List<AccessoryDecoderTypeOperation> operations = new ArrayList<AccessoryDecoderTypeOperation>();
        operations.add(accessoryDecoderTypeOperation);
        when(accessoryDecoderType.getDecoderTypeOperations()).thenReturn(operations);
        when(accessoryDecoderType.getDecoderTypeId()).thenReturn(1);
        when(accessoryDecoderTypeRepository.findOne(anyInt())).thenReturn(accessoryDecoderType);
        assertNotNull(accessoryService.saveAccessoryDecoder(accessoryDecoder));
    }

    @Test
    public void testSaveWithCurrent() {
        when(accessoryDecoderRepository.findAll()).thenReturn(new ArrayList<>());
        final AccessoryDecoder accessoryDecoder = mock(AccessoryDecoder.class);
        final AccessoryDecoderType accessoryDecoderType = mock(AccessoryDecoderType.class);
        when(accessoryDecoder.getAccessoryDecoderType()).thenReturn(accessoryDecoderType);
        when(accessoryDecoderType.getDecoderTypeId()).thenReturn(1);
        when(accessoryDecoder.getCurrentValue()).thenReturn(2);
        when(accessoryDecoderTypeRepository.findOne(anyInt())).thenReturn(accessoryDecoderType);
        assertNotNull(accessoryService.saveAccessoryDecoder(accessoryDecoder));
    }

    private AccessoryDecoder getAccessoryDecoder() {
        final AccessoryDecoder accessoryDecoder = new AccessoryDecoder();
        accessoryDecoder.setAccessoryDecoderType(getAccessoryDecoderType());
        return accessoryDecoder;
    }

    private AccessoryDecoderType getAccessoryDecoderType() {
        final AccessoryDecoderType accessoryDecoderType = new AccessoryDecoderType();
        final List<AccessoryDecoderTypeOperation> accessoryOperations = new ArrayList<>();
        accessoryOperations.add(getAccessoryDecoderTypeOperation(1, "Start"));
        accessoryOperations.add(getAccessoryDecoderTypeOperation(0,"Stop"));
        accessoryDecoderType.setDecoderTypeOperations(accessoryOperations);
        return accessoryDecoderType;
    }

    private AccessoryDecoderTypeOperation getAccessoryDecoderTypeOperation(final Integer value, final String operation) {
        final AccessoryDecoderTypeOperation accessoryDecoderTypeOperation = new AccessoryDecoderTypeOperation();
        accessoryDecoderTypeOperation.setDecoderOperationValue(value);
        accessoryDecoderTypeOperation.setDecoderTypeOperation(operation);
        return accessoryDecoderTypeOperation;
    }
}
