package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
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
        trains = new Trains();
        trains.setTrainRepository(trainRepository);
    }

    @Test
    public void saveTrain() {
        trains.createTrain(new Train());
        verify(trainRepository, times(1)).save(any(Train.class));
    }

    @Test
    public void getAllTrains() {
        final List<Train> trainList = new ArrayList<Train>();
        when(trainRepository.findAll()).thenReturn(trainList);
        assertNotNull(trains.getAllTrains());
    }

}
