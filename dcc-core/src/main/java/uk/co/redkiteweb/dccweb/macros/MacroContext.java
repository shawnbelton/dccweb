package uk.co.redkiteweb.dccweb.macros;

/**
 * Created by shawn on 05/05/17.
 */
public class MacroContext {

    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(final boolean state) {
        this.state = state;
    }

    public MacroContext() {
        this.state = false;
    }
}
