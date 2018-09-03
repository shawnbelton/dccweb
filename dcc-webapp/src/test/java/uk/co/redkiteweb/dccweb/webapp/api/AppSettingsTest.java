package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;
import uk.co.redkiteweb.dccweb.store.LogStore;
import uk.co.redkiteweb.dccweb.webapp.data.Settings;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 27/11/16.
 */
@RunWith(JUnit4.class)
public class AppSettingsTest {

    private AppSettings appSettings;
    private SettingsService settingsService;

    @Before
    public void setup() {
        settingsService = mock(SettingsService.class);
        appSettings = new AppSettings();
        appSettings.setSettingsService(settingsService);
        appSettings.setLogStore(mock(LogStore.class));
    }

    @Test
    public void testGetSettings() {
        when(settingsService.getSettingValue(anyString(), anyString())).thenReturn("System").thenReturn("port");
        assertNotNull(appSettings.getAppSettings());
    }

    @Test
    public void testSaveSettings() {
        final Settings settings = new Settings();
        appSettings.saveAppSettins(settings);
        verify(settingsService, times(2)).setSettingValue(anyString(), anyString());
    }

}
