package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class DummyControllerTest {

    private DummyController controller;
    private RelayControllerStore store;

    @Before
    public void setup() {
        store = mock(RelayControllerStore.class);
        controller = new DummyController();
        controller.setRelayControllerStore(store);
    }

    @Test
    public void testSetRelayValue() {
        controller.setRelayValue(10);
        verify(store, times(1)).setValue(eq(10));
    }
}
