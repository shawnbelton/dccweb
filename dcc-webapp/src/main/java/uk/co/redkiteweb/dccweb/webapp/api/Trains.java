package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 24/06/16.
 */
@RestController
public class Trains {

    private TrainRepository trainRepository;
    private LogStore logStore;

    @Autowired
    public void setTrainRepository(final TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping("/trains")
    public @ResponseBody List<Train> getAllTrains() {
        return (List<Train>)trainRepository.findAll();
    }

    @RequestMapping(value = "/trains/save", method = RequestMethod.POST)
    public @ResponseBody List<Train> saveTrain(@RequestBody final Train train) {
        trainRepository.save(train);
        logStore.log("info", String.format("Saved train with number %s", train.getNumber()));
        return getAllTrains();
    }

    @RequestMapping(value = "/trains/byId", method = RequestMethod.POST)
    public @ResponseBody Train getById(@RequestBody final Integer trainId) {
        return trainRepository.findOne(trainId);
    }
}
