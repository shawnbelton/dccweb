package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 28/10/16.
 */
@RunWith(JUnit4.class)
public class CabFunctionComparatorTest {

    private CabFunctionComparator cabFunctionComparator;

    @Before
    public void setup() {
        cabFunctionComparator = new CabFunctionComparator();
    }

    @Test
    public void testSame() {
        final CabFunction cabFunction = getCabFunction(1);
        assertEquals(0, cabFunctionComparator.compare(cabFunction, cabFunction));
    }

    @Test
    public void testLess() {
        assertTrue(cabFunctionComparator.compare(getCabFunction(1), getCabFunction(2))<0);
    }

    @Test
    public void testGreater() {
        assertTrue(cabFunctionComparator.compare(getCabFunction(2), getCabFunction(1))>0);
    }

    @Test
    public void testNullSame() {
        assertEquals(0, cabFunctionComparator.compare(null, null));
    }

    @Test
    public void testNullLess() {
        assertTrue(cabFunctionComparator.compare(null, getCabFunction(1))<0);
    }

    @Test
    public void testNullGreater() {
        assertTrue(cabFunctionComparator.compare(getCabFunction(1), null)>0);
    }

    @Test
    public void testNumberNullSame() {
        assertEquals(0, cabFunctionComparator.compare(getCabFunction(null), getCabFunction(null)));
    }

    @Test
    public void testNumberNullLess() {
        assertTrue(cabFunctionComparator.compare(getCabFunction(null), getCabFunction(1))<0);
    }

    @Test
    public void testNumberNullGreater() {
        assertTrue(cabFunctionComparator.compare(getCabFunction(1), getCabFunction(null))>0);
    }

    private CabFunction getCabFunction(final Integer number) {
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(number);
        return cabFunction;
    }

}
