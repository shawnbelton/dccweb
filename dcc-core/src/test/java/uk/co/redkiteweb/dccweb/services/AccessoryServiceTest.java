package uk.co.redkiteweb.dccweb.services;

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
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/03/17.
 */
@RunWith(JUnit4.class)
public class AccessoryServiceTest {

    private AccessoryService accessoryService;
    private MacroService macroService;
    private DccInterface dccInterface;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private NotificationService notificationService;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        notificationService = mock(NotificationService.class);
        macroService = mock(MacroService.class);
        accessoryService = new AccessoryService();
        accessoryService.setDccInterface(dccInterface);
        accessoryService.setAccessoryDecoderRepository(accessoryDecoderRepository);
        accessoryService.setNotificationService(notificationService);
        accessoryService.setMacroService(macroService);
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
        accessoryService.operateService(accessoryOperation);
        verify(dccInterface, times(1)).sendMessage(any(OperateAccessoryMessage.class));
        verify(macroService, times(1)).runMacro(any(Macro.class));
    }

    private AccessoryDecoder getAccessoryDecoder() {
        final AccessoryDecoder accessoryDecoder = new AccessoryDecoder();
        accessoryDecoder.setAccessoryDecoderType(getAccessoryDecoderType());
        return accessoryDecoder;
    }

    private AccessoryDecoderType getAccessoryDecoderType() {
        final AccessoryDecoderType accessoryDecoderType = new AccessoryDecoderType();
        final List<AccessoryDecoderTypeOperation> accessoryOperations = new ArrayList<AccessoryDecoderTypeOperation>();
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
