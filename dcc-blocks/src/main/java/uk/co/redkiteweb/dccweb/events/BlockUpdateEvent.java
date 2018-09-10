package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.Block;

public class BlockUpdateEvent implements RunMacroEvent, SendEvent {

    private final Block block;

    public BlockUpdateEvent(final Block block) {
        this.block = block;
    }

    @Override
    public Integer getMacroId() {
        return block.getMacroId();
    }

    @Override
    public String getUrl() {
        return "/blocks";
    }

    @Override
    public Object sendObject() {
        return block;
    }
}
