package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 24/06/16.
 */
@RestController
public class Trains {

    private TrainRepository trainRepository;
    private DecoderRepository decoderRepository;

    @Autowired
    public void setTrainRepository(final TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
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

    @RequestMapping(value = "/trains/assign/decoder", method = RequestMethod.POST)
    public @ResponseBody List<Train> assignDecoder(@RequestBody final Train train) {
        trainRepository.save(train);
        return getAllTrains();
    }

    @RequestMapping(value = "/trains/byId", method = RequestMethod.POST)
    public @ResponseBody Train getById(@RequestBody final Integer trainId) {
        return trainRepository.findOne(trainId);
    }
}
