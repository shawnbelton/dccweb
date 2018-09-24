package uk.co.redkiteweb.dccweb.data.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.SystemParameter;
import uk.co.redkiteweb.dccweb.data.repositories.SystemParameterRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 25/11/16.
 */
@RunWith(JUnit4.class)
public class SettingsServiceTest {

    private SettingsService settingsService;
    private SystemParameterRepository systemParameterRepository;

    @Before
    public void setup() {
        systemParameterRepository = mock(SystemParameterRepository.class);
        settingsService = new SettingsService();
        settingsService.setSystemParameterRepository(systemParameterRepository);
    }

    @Test
    public void testGetSettingNotSet() {
        assertEquals("Test", settingsService.getSettingValue("Param", "Test"));
        verify(systemParameterRepository, times(1)).save(any(SystemParameter.class));
    }

    @Test
    public void testGetSettingFromCache() {
        settingsService.getSettingValue("Param", "Test");
        assertEquals("Test", settingsService.getSettingValue("Param", "Test"));
        verify(systemParameterRepository, times(1)).save(any(SystemParameter.class));
    }

    @Test
    public void testGetSettingInDB() {
        final SystemParameter systemParameter = new SystemParameter();
        systemParameter.setName("Param");
        systemParameter.setValue("Value");
        systemParameter.setSettingId(1);
        when(systemParameterRepository.findByName(eq("Param"))).thenReturn(systemParameter);
        assertEquals("Value", settingsService.getSettingValue("Param", "Test"));
        verify(systemParameterRepository, never()).save(any(SystemParameter.class));
    }

    @Test
    public void testGetAllSettings() {
        final List<SystemParameter> settingsList = new ArrayList<>();
        when(systemParameterRepository.findAll()).thenReturn(settingsList);
        assertNotNull(settingsService.getAllSettings());
    }

    @Test
    public void testNewSaveSetting() {
        when(systemParameterRepository.findByName(anyString())).thenReturn(null);
        settingsService.setSettingValue("TEST", "VALUE");
        verify(systemParameterRepository, times(1)).save(any(SystemParameter.class));
    }

    @Test
    public void testUpdateSetting() {
        when(systemParameterRepository.findByName(anyString())).thenReturn(new SystemParameter());
        settingsService.setSettingValue("TEST", "VALUE");
        verify(systemParameterRepository, times(1)).save(any(SystemParameter.class));
    }
}
