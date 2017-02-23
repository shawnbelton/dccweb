package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 27/06/16.
 */
@RunWith(JUnit4.class)
public class TrainsTest {


    private Trains trains;
    private TrainRepository trainRepository;

    @Before
    public void setUp() {
        trainRepository = mock(TrainRepository.class);
        final LogStore logStore = mock(LogStore.class);
        trains = new Trains();
        trains.setTrainRepository(trainRepository);
        trains.setLogStore(logStore);
    }

    @Test
    public void saveTrain() {
        trains.saveTrain(new Train());
        verify(trainRepository, times(1)).save(any(Train.class));
    }

    @Test
    public void getAllTrains() {
        final List<Train> trainList = new ArrayList<Train>();
        when(trainRepository.findAll()).thenReturn(trainList);
        assertNotNull(trains.getAllTrains());
    }

    @Test
    public void getById() {
        when(trainRepository.findOne(anyInt())).thenReturn(new Train());
        assertNotNull(trains.getById(1));
    }

}
