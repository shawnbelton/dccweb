package uk.co.redkiteweb.dccweb.cucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import uk.co.redkiteweb.dccweb.ApplicationRunner;

/**
 * Created by shawn on 20/07/16.
 */
@ContextConfiguration
@SpringBootTest(classes = ApplicationRunner.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseSteps {

}
