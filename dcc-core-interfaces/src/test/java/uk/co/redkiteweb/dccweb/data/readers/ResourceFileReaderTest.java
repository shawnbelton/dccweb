package uk.co.redkiteweb.dccweb.data.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ResourceFileReaderTest {

    private ResourceFileReader reader;

    @Before
    public void setup() {
        reader = new ResourceFileReader();
    }

    @Test
    public void testReadLine() {
        reader.setResourceFile("test.txt");
        assertEquals("line1", reader.readLine());
        assertEquals("line2", reader.readLine());
        assertNull(reader.readLine());
    }

}
