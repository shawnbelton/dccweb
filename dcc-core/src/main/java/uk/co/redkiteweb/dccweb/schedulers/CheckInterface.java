package uk.co.redkiteweb.dccweb.schedulers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;
import uk.co.redkiteweb.dccweb.services.NotificationService;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class CheckInterface {

    private static final Logger LOGGER = LogManager.getLogger(CheckInterface.class);

    private DccInterface dccInterface;
    private NotificationService notificationService;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkInterface() {
        LOGGER.info("Checking Interface.");
        final DccInterfaceStatus.Status status = dccInterface.getInterfaceStatus().getStatus();
        dccInterface.checkInterface();
        if (dccInterface.getInterfaceStatus().getStatus() != status) {
            notificationService.createNotification("STATUS", "");
        }
    }
}
