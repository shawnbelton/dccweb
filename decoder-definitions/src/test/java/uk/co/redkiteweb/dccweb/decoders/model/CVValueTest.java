package uk.co.redkiteweb.dccweb.decoders.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CVValueTest {

    private CVValue cvValue;

    private static CVValue newCVValue(final String id,
                                      final String cvNumber,
                                      final String name,
                                      final Integer bit,
                                      final Integer high,
                                      final Integer low,
                                      final Integer mask,
                                      final Integer readMask,
                                      final CVValue.Type type) {
        final CVValue newValue = new CVValue();
        newValue.setId(id);
        newValue.setCvNumber(cvNumber);
        newValue.setName(name);
        newValue.getBit().add(bit);
        newValue.setHigh(high);
        newValue.setLow(low);
        newValue.setMask(mask);
        newValue.setReadMask(readMask);
        newValue.setType(type);
        final Collection<CVValueOption> options = new HashSet<>();
        options.add(newCVOption("Name", 1));
        newValue.setOptions(options);
        return newValue;
    }

    @Test
    public void testId() {
        assertEquals("ID", cvValue.getId());
    }

    @Test
    public void testCvNumber() {
        assertEquals("Number", cvValue.getCvNumber());
    }

    @Test
    public void testName() {
        assertEquals("Name", cvValue.getName());
    }

    @Test
    public void testBit() {
        assertEquals(Integer.valueOf(1), cvValue.getBit().iterator().next());
    }

    @Test
    public void testHigh() {
        assertEquals(Integer.valueOf(2), cvValue.getHigh());
    }

    @Test
    public void testLow() {
        assertEquals(Integer.valueOf(3), cvValue.getLow());
    }

    @Test
    public void testMask() {
        assertEquals(Integer.valueOf(4), cvValue.getMask());
    }

    @Before
    public void setup() {
        cvValue = newCVValue("ID", "Number", "Name",
                1, 2, 3, 4, 3, CVValue.Type.VALUE);
    }

    @Test
    public void testType() {
        assertEquals(CVValue.Type.VALUE, cvValue.getType());
    }

    @Test
    public void testHashCode() {
        assertNotNull(cvValue.hashCode());
    }

    @Test
    public void testReadMask() {
        assertEquals(Integer.valueOf(3), cvValue.getReadMask());
    }

    @Test
    public void testEquality() {
        assertFalse(cvValue.equals(null));
        assertTrue(cvValue.equals(cvValue));
        assertFalse(cvValue.equals(newCVValue("Id", "Number", "Name",
                1, 2, 3, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "number", "Name",
                1, 2, 3, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "name",
                1, 2, 3, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "Name",
                9, 2, 3, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "Name",
                1, 9, 3, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "Name",
                1, 2, 9, 4, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "Name",
                1, 2, 3, 9, null, CVValue.Type.VALUE)));
        assertFalse(cvValue.equals(newCVValue("ID", "Number", "Name",
                1, 2, 3, 4, null, CVValue.Type.OPTION)));
        final CVValue listDifferent1 = newCVValue("ID", "Number", "Name",
                1, 2, 3, 4, null, CVValue.Type.VALUE);
        final Collection<CVValueOption> diffList1 = new HashSet<>();
        diffList1.add(newCVOption("Name", 2));
        listDifferent1.setOptions(diffList1);
        assertFalse(cvValue.equals(listDifferent1));
        final CVValue listDifferent2 = newCVValue("ID", "Number", "Name",
                1, 2, 3, 4, null, CVValue.Type.VALUE);
        final Collection<CVValueOption> diffList2 = new HashSet<>();
        diffList2.add(newCVOption("Name", 1));
        diffList2.add(newCVOption("Name", 2));
        listDifferent2.setOptions(diffList2);
        assertFalse(cvValue.equals(listDifferent2));

    }

    private static CVValueOption newCVOption(final String name, final Integer value) {
        final CVValueOption option = new CVValueOption();
        option.setValue(value);
        option.setName(name);
        return option;
    }
}
