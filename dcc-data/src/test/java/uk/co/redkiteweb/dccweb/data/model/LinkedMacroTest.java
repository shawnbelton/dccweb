package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 10/01/17.
 */
@RunWith(JUnit4.class)
public class LinkedMacroTest {

    private LinkedMacro linkedMacro;

    @Before
    public void setup() {
        linkedMacro = new LinkedMacro();
        linkedMacro.setDecoderId(1);
        linkedMacro.setLinkedMacroId(1);
        linkedMacro.setMacroId(1);
    }

    @Test
    public void testId() {
        assertEquals(new Integer(1), linkedMacro.getLinkedMacroId());
    }

    @Test
    public void testDecoderId() {
        assertEquals(new Integer(1), linkedMacro.getDecoderId());
    }

    @Test
    public void testLinkedMacroId() {
        assertNotNull(linkedMacro.getMacroId());
    }
}
