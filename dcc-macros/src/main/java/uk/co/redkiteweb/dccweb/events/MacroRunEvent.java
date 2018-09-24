package uk.co.redkiteweb.dccweb.events;

public class MacroRunEvent implements RunMacroEvent {

    private final Integer macroId;

    public MacroRunEvent(final Integer macroId) {
        this.macroId = macroId;
    }

    @Override
    public Integer getMacroId() {
        return macroId;
    }
}
