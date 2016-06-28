package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public @ResponseBody List<Train> getAllTrains() {
        return (List<Train>)trainRepository.findAll();
    }

    @RequestMapping(value = "/trains/create", method = RequestMethod.POST)
    public @ResponseBody List<Train> createTrain(@RequestBody final Train train) {
        trainRepository.save(train);
        return getAllTrains();
    }

    @RequestMapping(value = "/trains/byId", method = RequestMethod.POST)
    public @ResponseBody Train getById(@RequestBody final Integer trainId) {
        return trainRepository.findOne(trainId);
    }
}
