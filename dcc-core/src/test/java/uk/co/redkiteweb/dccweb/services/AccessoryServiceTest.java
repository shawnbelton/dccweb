package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
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
    private DccInterface dccInterface;
    private AccessoryDecoderRepository accessoryDecoderRepository;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        accessoryService = new AccessoryService();
        accessoryService.setDccInterface(dccInterface);
        accessoryService.setAccessoryDecoderRepository(accessoryDecoderRepository);
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
        accessoryService.operateService(new AccessoryOperation());
        verify(dccInterface, times(1)).sendMessage(any(OperateAccessoryMessage.class));
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
