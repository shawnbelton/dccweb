package uk.co.redkiteweb.dccweb.services;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class LocoServiceTest {

    private LocoService locoService;
    private LocoRepository locoRepository;

    @Before
    public void setup() {
        locoRepository = mock(LocoRepository.class);
        final LogStore logStore = mock(LogStore.class);
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
        final List<Loco> locoList = new ArrayList<>();
        when(locoRepository.findAll()).thenReturn(locoList);
        TestCase.assertNotNull(locoService.getAllLocos());
    }

    @Test
    public void getById() {
        when(locoRepository.findById(anyInt())).thenReturn(Optional.of(new Loco()));
        assertNotNull(locoService.getById(1));
    }

}
