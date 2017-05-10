package uk.co.redkiteweb.dccweb.macros.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.macros.factory.IStep;

import java.util.concurrent.TimeUnit;

/**
 * Created by shawn on 05/12/16.
 */
@Component("delay")
@Scope("prototype")
public class Wait extends AbstractMacroStep implements IStep {

    private static final Logger LOGGER = LogManager.getLogger(Wait.class);

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(Math.round(this.delay() * 1000));
        } catch (InterruptedException exception) {
            LOGGER.info("Wait has been interrupted.");
        }
    }
}
