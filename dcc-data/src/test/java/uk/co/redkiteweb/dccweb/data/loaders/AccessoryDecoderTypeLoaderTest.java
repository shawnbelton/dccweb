package uk.co.redkiteweb.dccweb.data.loaders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeOperationReader;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeReader;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeOperationRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 22/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeLoaderTest {

    private AccessoryDecoderTypeLoader accessoryDecoderTypeLoader;
    private AccessoryDecoderTypeReader accessoryDecoderTypeReader;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;
    private AccessoryDecoderTypeOperationReader accessoryDecoderTypeOperationReader;
    private AccessoryDecoderTypeOperationRepository accessoryDecoderTypeOperationRepository;

    @Before
    public void setup() {
        accessoryDecoderTypeReader = mock(AccessoryDecoderTypeReader.class);
        accessoryDecoderTypeRepository = mock(AccessoryDecoderTypeRepository.class);
        accessoryDecoderTypeOperationReader = mock(AccessoryDecoderTypeOperationReader.class);
        accessoryDecoderTypeOperationRepository = mock(AccessoryDecoderTypeOperationRepository.class);
        accessoryDecoderTypeLoader = new AccessoryDecoderTypeLoader();
        accessoryDecoderTypeLoader.setAccessoryDecoderTypeReader(accessoryDecoderTypeReader);
        accessoryDecoderTypeLoader.setAccessoryDecoderTypeRepository(accessoryDecoderTypeRepository);
        accessoryDecoderTypeLoader.setAccessoryDecoderTypeOperationReader(accessoryDecoderTypeOperationReader);
        accessoryDecoderTypeLoader.setAccessoryDecoderTypeOperationRepository(accessoryDecoderTypeOperationRepository);
    }

    @Test
    public void loadTest() {
        when(accessoryDecoderTypeReader.read()).thenReturn(new AccessoryDecoderType()).thenReturn(null);
        when(accessoryDecoderTypeOperationReader.read()).thenReturn(new AccessoryDecoderTypeOperation()).thenReturn(null);
        accessoryDecoderTypeLoader.load();
        verify(accessoryDecoderTypeRepository, times(1)).save(any(AccessoryDecoderType.class));
        verify(accessoryDecoderTypeOperationRepository, times(1)).save(any(AccessoryDecoderTypeOperation.class));
    }

}
