package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderReaderTest {

    private DecoderReader decoderReader;

    @Before
    public void setUp() {
        decoderReader = new DecoderReader();
    }

    @Test
    public void programTrackReadTest() {
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

}
