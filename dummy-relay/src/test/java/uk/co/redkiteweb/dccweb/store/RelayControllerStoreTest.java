package uk.co.redkiteweb.dccweb.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import uk.co.redkiteweb.dccweb.model.RelayController;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class RelayControllerStoreTest {

    private RelayControllerStore store;
    private SimpMessagingTemplate messagingTemplate;

    @Before
    public void setup() {
        final RelayController relayConroller = new RelayController();
        messagingTemplate = mock(SimpMessagingTemplate.class);
        store = new RelayControllerStore();
        store.setMessagingTemplate(messagingTemplate);
        store.setRelayController(relayConroller);
    }

    @Test
    public void testGetRelayController() {
        assertNotNull(store.getRelayController());
    }

    @Test
    public void testSetValue() {
        store.setValue(1);
        verify(messagingTemplate, times(1)).convertAndSend(anyString(), eq(1));
    }

}
