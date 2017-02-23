package uk.co.redkiteweb.dccweb.data.loaders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;
import uk.co.redkiteweb.dccweb.data.readers.DccManufacturerReader;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 30/06/16.
 */
@RunWith(JUnit4.class)
public class DccManufacturerLoaderTest {

    private DccManufacturerLoader dccManufacturerLoader;
    private DccManufacturerReader dccManufacturerReader;
    private DccManufacturerRepository dccManufacturerRepository;

    @Before
    public void setUp() {
        dccManufacturerReader = mock(DccManufacturerReader.class);
        dccManufacturerRepository = mock(DccManufacturerRepository.class);
        dccManufacturerLoader = new DccManufacturerLoader();
        dccManufacturerLoader.setDccManufacturerReader(dccManufacturerReader);
        dccManufacturerLoader.setDccManufacturerRepository(dccManufacturerRepository);
    }

    @Test
    public void loadTest() {
        when(dccManufacturerReader.read()).thenReturn(new DccManufacturer()).thenReturn(null);
        dccManufacturerLoader.load();
        verify(dccManufacturerRepository, times(1)).save(any(DccManufacturer.class));
    }
}
