package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/03/17.
 */
@RunWith(JUnit4.class)
public class AccessoryServiceTest {

    private AccessoryService accessoryService;
    private DccInterface dccInterface;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        accessoryService = new AccessoryService();
        accessoryService.setDccInterface(dccInterface);
    }

    @Test
    public void testOperateAccessory() {
        accessoryService.operateService(new AccessoryOperation());
        verify(dccInterface, times(1)).sendMessage(any(OperateAccessoryMessage.class));
    }
}
