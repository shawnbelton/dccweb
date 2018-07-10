package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.Macro;

public class MacroRunEvent implements RunMacroEvent {

    private final Macro macro;

    public MacroRunEvent(final Macro macro) {
        this.macro = macro;
    }

    @Override
    public Macro getMacro() {
        return macro;
    }
}
