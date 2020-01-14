package uk.co.redkiteweb.dccweb.macros.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.macros.IStep;

import java.util.concurrent.TimeUnit;

/**
 * Created by shawn on 05/12/16.
 */
@Component("delay")
@Scope("prototype")
public class Wait extends AbstractMacroStep implements IStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(Wait.class);

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(Math.round(this.getMacroStep().getDelay() * 1000));
        } catch (InterruptedException exception) {
            LOGGER.info("Wait has been interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
