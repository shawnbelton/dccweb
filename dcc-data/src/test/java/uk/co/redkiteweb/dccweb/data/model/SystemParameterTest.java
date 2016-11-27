package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 25/11/16.
 */
@RunWith(JUnit4.class)
public class SystemParameterTest {

    private SystemParameter systemParameter;

    @Before
    public void setup() {
        systemParameter = new SystemParameter();
        systemParameter.setSettingId(1);
        systemParameter.setName("Name");
        systemParameter.setValue("Value");
    }

    @Test
    public void testSettingId() {
        assertEquals(new Integer(1), systemParameter.getSettingId());
    }

    @Test
    public void testName() {
        assertEquals("Name", systemParameter.getName());
    }

    @Test
    public void testValue() {
        assertEquals("Value", systemParameter.getValue());
    }
}
