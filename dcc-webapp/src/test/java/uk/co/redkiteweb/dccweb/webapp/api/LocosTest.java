package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 27/06/16.
 */
@RunWith(JUnit4.class)
public class LocosTest {


    private Locos locos;
    private LocoRepository locoRepository;

    @Before
    public void setUp() {
        locoRepository = mock(LocoRepository.class);
        final LogStore logStore = mock(LogStore.class);
        locos = new Locos();
        locos.setLocoRepository(locoRepository);
        locos.setLogStore(logStore);
    }

    @Test
    public void saveLoco() {
        locos.saveLoco(new Loco());
        verify(locoRepository, times(1)).save(any(Loco.class));
    }

    @Test
    public void getAllLocos() {
        final List<Loco> locoList = new ArrayList<Loco>();
        when(locoRepository.findAll()).thenReturn(locoList);
        assertNotNull(locos.getAllLocos());
    }

    @Test
    public void getById() {
        when(locoRepository.findOne(anyInt())).thenReturn(new Loco());
        assertNotNull(locos.getById(1));
    }

}
