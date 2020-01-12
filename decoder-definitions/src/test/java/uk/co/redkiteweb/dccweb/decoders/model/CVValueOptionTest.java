package uk.co.redkiteweb.dccweb.decoders.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CVValueOptionTest {

    private CVValueOption cvValueOption;

    @Before
    public void setup() {
        cvValueOption = new CVValueOption();
        cvValueOption.setName("Name");
        cvValueOption.setValue(101);
    }

    @Test
    public void testName() {
        assertEquals("Name", cvValueOption.getName());
    }

    @Test
    public void testValue() {
        assertEquals(Integer.valueOf(101), cvValueOption.getValue());
    }

    @Test
    public void testHashCode() {
        assertEquals(75033307, cvValueOption.hashCode());
    }

    @Test
    public void testEquality() {
        assertFalse(cvValueOption.equals(null));
        assertTrue(cvValueOption.equals(cvValueOption));
        assertFalse(cvValueOption.equals(createValueOption("Name", 12)));
        assertFalse(cvValueOption.equals(createValueOption("emaN", 101)));
    }

    private static CVValueOption createValueOption(final String name, final Integer value) {
        final CVValueOption newOption = new CVValueOption();
        newOption.setName(name);
        newOption.setValue(value);
        return newOption;
    }
}
