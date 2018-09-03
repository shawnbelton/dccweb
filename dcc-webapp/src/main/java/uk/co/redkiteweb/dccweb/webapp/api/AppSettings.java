package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;
import uk.co.redkiteweb.dccweb.store.LogStore;
import uk.co.redkiteweb.dccweb.webapp.data.Settings;

/**
 * Created by shawn on 26/11/16.
 */
@RestController
@RequestMapping("/api/application")
public class AppSettings {

    private SettingsService settingsService;
    private LogStore logStore;

    @Autowired
    public void setSettingsService(final SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public @ResponseBody Settings getAppSettings() {
        final Settings settings = new Settings();
        settings.setDccSystem(settingsService.getSettingValue("InterfaceType", "Demo"));
        settings.setSerialPort(settingsService.getSettingValue("SerialPort", "/dev/ttyUSB0"));
        return settings;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public @ResponseBody Settings saveAppSettins(@RequestBody final Settings settings) {
        settingsService.setSettingValue("InterfaceType", settings.getDccSystem());
        settingsService.setSettingValue("SerialPort", settings.getSerialPort());
        logStore.log("info", "Application Settings Saved.");
        return settings;
    }
}
