package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class LocoServiceTest {

    private LocoService locoService;
    private LocoRepository locoRepository;
    private LogStore logStore;

    @Before
    public void setup() {
        locoRepository = mock(LocoRepository.class);
        logStore = mock(LogStore.class);
        locoService = new LocoService();
        locoService.setLocoRepository(locoRepository);
        locoService.setLogStore(logStore);
    }

    @Test
    public void saveLoco() {
        locoService.saveLoco(new Loco());
        verify(locoRepository, times(1)).save(any(Loco.class));
    }

    @Test
    public void getAllLocos() {
        final List<Loco> locoList = new ArrayList<Loco>();
        when(locoRepository.findAll()).thenReturn(locoList);
        assertNotNull(locoService.getAllLocos());
    }

    @Test
    public void getById() {
        when(locoRepository.findOne(anyInt())).thenReturn(new Loco());
        assertNotNull(locoService.getById(1));
    }

}
