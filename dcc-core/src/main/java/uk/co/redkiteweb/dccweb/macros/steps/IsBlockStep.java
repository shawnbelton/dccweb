package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;

/**
 * Created by shawn on 08/05/17.
 */
@Component("isBlock")
@Scope("prototype")
public class IsBlockStep extends AbstractMacroStep {

    private BlockRepository blockRepository;

    @Autowired
    public void setBlockRepository(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public void run() {
        final Block block = blockRepository.findOne(getBlockId());
        getMacroContext().setState(block.getOccupied() == (1 == getValue()));
    }
}
