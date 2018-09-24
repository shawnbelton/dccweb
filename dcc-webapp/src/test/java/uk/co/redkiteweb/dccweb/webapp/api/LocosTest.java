package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.services.LocoService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 27/06/16.
 */
@RunWith(JUnit4.class)
public class LocosTest {


    private Locos locos;
    private LocoService locoService;

    @Before
    public void setUp() {
        locoService = mock(LocoService.class);
        locos = new Locos();
        locos.setLocoService(locoService);
    }

    @Test
    public void testGetAllLocos() {
        locos.getAllLocos();
        verify(locoService, times(1)).getAllLocos();
    }

    @Test
    public void testGetLocoById() {
        locos.getById(1);
        verify(locoService, times(1)).getById(anyInt());
    }

    @Test
    public void testSaveLock() {
        locos.saveLoco(mock(Loco.class));
        verify(locoService, times(1)).saveLoco(any(Loco.class));
    }
}
