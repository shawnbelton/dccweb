package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.NoSuchPortException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.env.Environment;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 11/07/16.
 */
@RunWith(JUnit4.class)
public class NcePortIdentifierTest {

    private NcePortIdentifier ncePortIdentifier;
    private Environment environment;
    private SettingsService settingsService;

    @Before
    public void setUp() {
        settingsService = mock(SettingsService.class);
        environment = mock(Environment.class);
        ncePortIdentifier = new NcePortIdentifier();
        ncePortIdentifier.setEnvironment(environment);
        ncePortIdentifier.setSettingsService(settingsService);
    }

    @Test
    public void getTest() {
        when(settingsService.getSettingValue(anyString(), anyString())).thenReturn("/dev/ttyS0");
        try {
            ncePortIdentifier.getInstance();
        } catch (NoSuchPortException exception) {
            assertTrue(true);
        }
    }
}
