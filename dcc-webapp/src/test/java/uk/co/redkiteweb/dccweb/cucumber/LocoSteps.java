package uk.co.redkiteweb.dccweb.cucumber;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.webapp.api.Locos;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 20/07/16.
 */
public class LocoSteps extends BaseSteps {

    private Locos locos;

    @Autowired
    public void setLocos(final Locos locos) {
        this.locos = locos;
    }

    @Given("^new loco$")
    public void new_loco() {
    }

    @When("^details are added$")
    public void details_are_added(final DataTable locoInfo) {
        for (DataTableRow locoRow : locoInfo.getGherkinRows()) {
            final Loco loco = new Loco();
            loco.setNumber(locoRow.getCells().get(0));
            loco.setName(locoRow.getCells().get(1));
            locos.saveLoco(loco);
        }
    }

    @Then("^(\\d+) locos are listed$")
    public void locos_are_listed(final int numLocos) {
        assertEquals(numLocos, locos.getAllLocos().size());
    }

}
