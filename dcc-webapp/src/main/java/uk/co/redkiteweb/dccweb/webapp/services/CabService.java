package uk.co.redkiteweb.dccweb.webapp.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

/**
 * Created by shawn on 13/09/16.
 */
@Service
public class CabService {

    private static final Logger LOGGER = LogManager.getLogger(CabService.class);

    @Async
    public void updateCab(final Cab cab) {
        if (cab.getTrain() != null && cab.getTrain().getDecoder() != null) {
            LOGGER.info(String.format("Updating cab %d", cab.getTrain().getDecoder().getLongAddress()));
        }
    }

}
