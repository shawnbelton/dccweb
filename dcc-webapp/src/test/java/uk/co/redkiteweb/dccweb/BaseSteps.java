package uk.co.redkiteweb.dccweb;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by shawn on 20/07/16.
 */
@ContextConfiguration(classes = DccWebApplication.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest("server.port:8095")
@WebAppConfiguration
public class BaseSteps {

}
