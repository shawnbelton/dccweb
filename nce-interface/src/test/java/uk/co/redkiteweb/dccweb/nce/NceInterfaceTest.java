package uk.co.redkiteweb.dccweb.nce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class NceInterfaceTest {

    private NceInterface nceInterface;

    @Before
    public void setUp() {
        this.nceInterface = new NceInterface();
    }

    @Test
    public void initialise() {
        nceInterface.initialise();
    }
}
