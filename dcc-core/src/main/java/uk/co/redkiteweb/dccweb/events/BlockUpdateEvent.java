package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.model.Macro;

public class BlockUpdateEvent implements RunMacroEvent {

    private final Block block;

    public BlockUpdateEvent(final Block block) {
        this.block = block;
    }

    @Override
    public Macro getMacro() {
        return block.getMacro();
    }
}
