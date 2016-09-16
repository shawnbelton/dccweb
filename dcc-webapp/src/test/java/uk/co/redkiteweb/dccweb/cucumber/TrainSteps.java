package uk.co.redkiteweb.dccweb.cucumber;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.BaseSteps;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.api.Trains;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 20/07/16.
 */
public class TrainSteps extends BaseSteps {

    private Trains trains;

    @Autowired
    public void setTrains(final Trains trains) {
        this.trains = trains;
    }

    @Given("^new train$")
    public void new_train() {
    }

    @When("^details are added$")
    public void details_are_added(final DataTable trainInfo) {
        for (DataTableRow trainRow : trainInfo.getGherkinRows()) {
            final Train train = new Train();
            train.setNumber(trainRow.getCells().get(0));
            train.setName(trainRow.getCells().get(1));
            trains.saveTrain(train);
        }
    }

    @Then("^(\\d+) locos are listed$")
    public void locos_are_listed(final int numTrains) {
        assertEquals(numTrains, trains.getAllTrains().size());
    }


}
