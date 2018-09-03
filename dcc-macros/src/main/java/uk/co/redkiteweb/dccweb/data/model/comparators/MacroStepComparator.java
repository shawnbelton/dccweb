package uk.co.redkiteweb.dccweb.data.model.comparators;

import uk.co.redkiteweb.dccweb.data.model.MacroStep;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by shawn on 05/12/16.
 */
public class MacroStepComparator implements Comparator<MacroStep>, Serializable {

    static final long serialVersionUID = 8003;

    @Override
    public int compare(final MacroStep step1,final MacroStep step2) {
        int compare;
        if (step1==null) {
            compare = compareStepOneNull(step2);
        } else {
            compare = compareStepOneNotNull(step1, step2);
        }
        return compare;
    }

    private static int compareStepOneNull(final MacroStep step) {
        int compare;
        if (step==null) {
            compare = 0;
        } else {
            compare = -1;
        }
        return compare;
    }

    private static int compareStepOneNotNull(final MacroStep step1, final MacroStep step2) {
        int compare;
        if (step2==null) {
            compare = 1;
        } else {
            compare = compareNeitherNull(step1.getNumber(), step2.getNumber());
        }
        return compare;
    }

    private static int compareNeitherNull(final Integer step1Number, final Integer step2Number) {
        int compare;
        if (step1Number==null) {
            compare = compareNumberOneNull(step2Number);
        } else {
            compare = compareNumberOneNotNull(step1Number, step2Number);
        }
        return compare;
    }

    private static int compareNumberOneNull(final Integer stepNumber) {
        int compare;
        if (stepNumber==null) {
            compare = 0;
        } else {
            compare = -1;
        }
        return compare;
    }

    private static int compareNumberOneNotNull(final Integer step1Number, final Integer step2Number) {
        int compare;
        if (step2Number==null) {
            compare = 1;
        } else {
            compare = step1Number.compareTo(step2Number);
        }
        return compare;
    }
}
