package uk.co.redkiteweb.dccweb.macros;

/**
 * Created by shawn on 05/05/17.
 */
public class MacroContextImpl implements MacroContext {

    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(final boolean state) {
        this.state = state;
    }

    public MacroContextImpl() {
        this.state = false;
    }
}
