package uk.co.redkiteweb.dccweb.decoders.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CVDefinitionTest {

    private CVDefinition cvDefinition;

    @Before
    public void setup() {
        cvDefinition = newCVDefinition("1");
    }

    @Test
    public void testNumber() {
        assertEquals("1", cvDefinition.getNumber());
    }

    @Test
    public void testValues() {
        assertNotNull(cvDefinition.getValues());
    }

    @Test
    public void testHashCode() {
        assertNotNull(cvDefinition.hashCode());
    }

    @Test
    public void testEquality() {
        assertFalse(cvDefinition.equals(null));
        assertTrue(cvDefinition.equals(cvDefinition));
        assertFalse(cvDefinition.equals(newCVDefinition("2")));
        final Collection<CVValue> diffList1 = new HashSet<>();
        diffList1.add(newCVValue("Name", "Number1"));
        final CVDefinition cvDefDiff1 = newCVDefinition("1");
        cvDefDiff1.setValues(diffList1);
        assertFalse(cvDefinition.equals(cvDefDiff1));
        final Collection<CVValue> diffList2 = new HashSet<>();
        diffList2.add(newCVValue("Name", "Number"));
        diffList2.add(newCVValue("Name", "Number2"));
        final CVDefinition cvDefDiff2 = newCVDefinition("1");
        cvDefDiff2.setValues(diffList2);
        assertFalse(cvDefinition.equals(cvDefDiff2));
    }

    private static CVDefinition newCVDefinition(final String number) {
        final CVDefinition newCVDefiniation = new CVDefinition();
        newCVDefiniation.setNumber(number);
        final Collection<CVValue> cvValues = new HashSet<>();
        cvValues.add(newCVValue("Name", "Number"));
        newCVDefiniation.setValues(cvValues);
        return newCVDefiniation;
    }

    private static CVValue newCVValue(final String name, final String number) {
        final CVValue newValue = new CVValue();
        newValue.setName(name);
        newValue.setCvNumber(number);
        newValue.setType(CVValue.Type.VALUE);
        return newValue;
    }
}
