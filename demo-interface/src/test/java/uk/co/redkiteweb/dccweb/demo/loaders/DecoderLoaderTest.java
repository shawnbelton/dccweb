package uk.co.redkiteweb.dccweb.demo.loaders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.demo.reader.CVValue;
import uk.co.redkiteweb.dccweb.demo.reader.DecoderDefaultReader;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 26/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderLoaderTest {

    private DecoderLoader decoderLoader;
    private DecoderRegister decoderRegister;
    private DecoderDefaultReader decoderDefaultReader;

    @Before
    public void setUp() {
        decoderRegister = mock(DecoderRegister.class);
        decoderDefaultReader = mock(DecoderDefaultReader.class);
        decoderLoader = new DecoderLoader();
        decoderLoader.setDecoderRegister(decoderRegister);
        decoderLoader.setDecoderDefaultReader(decoderDefaultReader);
    }

    @Test
    public void loadTest() {
        final CVValue cvValue = new CVValue();
        cvValue.setNumber(1);
        cvValue.setValue(1);
        when(decoderDefaultReader.read()).thenReturn(cvValue).thenReturn(null);
        decoderLoader.load();
        verify(decoderRegister, times(1)).initialise();
        verify(decoderRegister, times(1)).setCV(anyInt(), anyInt());
    }
}
