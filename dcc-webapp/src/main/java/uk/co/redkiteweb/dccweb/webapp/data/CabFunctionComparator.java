package uk.co.redkiteweb.dccweb.webapp.data;

import java.util.Comparator;

/**
 * Created by shawn on 28/10/16.
 */
public class CabFunctionComparator implements Comparator<CabFunction> {

    @Override
    public int compare(final CabFunction cabFunctionOne, final CabFunction cabFunctionTwo) {
        return cabFunctionOne.getNumber().compareTo(cabFunctionTwo.getNumber());
    }
}
