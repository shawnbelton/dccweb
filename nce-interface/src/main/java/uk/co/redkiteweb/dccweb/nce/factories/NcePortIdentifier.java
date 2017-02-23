package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;

/**
 * Created by shawn on 19/06/16.
 */
@Component
public class NcePortIdentifier {

    private SettingsService settingsService;

    @Autowired
    public void setSettingsService(final SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    public CommPortIdentifier getInstance() throws NoSuchPortException {
        final String connectionName = settingsService.getSettingValue("SerialPort", "/dev/ttyUSB0");
        System.setProperty("gnu.io.rxtx.SerialPorts", connectionName);
        return CommPortIdentifier.getPortIdentifier(connectionName);
    }

}
