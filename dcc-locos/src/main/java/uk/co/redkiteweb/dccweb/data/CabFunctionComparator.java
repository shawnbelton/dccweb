package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by shawn on 28/10/16.
 */
public class CabFunctionComparator implements Comparator<CabFunction>, Serializable {

    static final long serialVersionUID = 8002;

    @Override
    public int compare(final CabFunction cabFunctionOne, final CabFunction cabFunctionTwo) {
        int compare;
        if (cabFunctionOne==null) {
            compare = compareFunctionOneNull(cabFunctionTwo);
        } else {
            compare = compareFunctionOneNotNull(cabFunctionOne, cabFunctionTwo);
        }
        return compare;
    }

    private static int compareFunctionOneNotNull(final CabFunction cabFunctionOne,final CabFunction cabFunctionTwo) {
        int compare;
        if (cabFunctionTwo==null) {
            compare = 1;
        } else {
            compare = compareNeitherNull(cabFunctionOne.getNumber(), cabFunctionTwo.getNumber());
        }
        return compare;
    }

    private static int compareNeitherNull(final Integer numberOne,final Integer numberTwo) {
        int compare;
        if (numberOne==null) {
            compare = compareNumberOneNull(numberTwo);
        } else {
            compare = compareNumberOneNotNull(numberOne, numberTwo);
        }
        return compare;
    }

    private static int compareNumberOneNotNull(final Integer numberOne,final Integer numberTwo) {
        int compare;
        if (numberTwo==null) {
            compare = 1;
        } else {
            compare = numberOne.compareTo(numberTwo);
        }
        return compare;
    }

    private static int compareNumberOneNull(final Integer numberTwo) {
        int compare;
        if (numberTwo==null) {
            compare = 0;
        } else {
            compare = -1;
        }
        return compare;
    }

    private static int compareFunctionOneNull(final CabFunction cabFunctionTwo) {
        int compare;
        if (cabFunctionTwo==null) {
            compare = 0;
        } else {
            compare = -1;
        }
        return compare;
    }
}
