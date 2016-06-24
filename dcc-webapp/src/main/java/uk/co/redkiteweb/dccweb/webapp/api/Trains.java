package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;

import java.util.List;

/**
 * Created by shawn on 24/06/16.
 */
@RestController
public class Trains {

    private TrainRepository trainRepository;

    @Autowired
    public void setTrainRepository(final TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @RequestMapping("/trains")
    public List<Train> getAllTrains() {
        return (List<Train>)trainRepository.findAll();
    }

}
