package uk.co.redkiteweb.dccweb.cucumber;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;
import uk.co.redkiteweb.dccweb.webapp.api.Decoders;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 25/07/16.
 */
public class DecoderSteps extends BaseSteps {

    private Decoder decoder;
    private Decoders decoders;
    private DecoderRegister decoderRegister;

    @Autowired
    public void setDecoders(final Decoders decoders) {
        this.decoders = decoders;
    }

    @Autowired
    public void setDecoderRegister(final DecoderRegister decoderRegister) {
        this.decoderRegister = decoderRegister;
    }

    @Given("^a train on the program track fitted with a decoder with CV Values$")
    public void a_train_on_the_program_track_fitted_with_a_decoder_with_CV_Values(final DataTable cvValues) {
        decoderRegister.initialise();
        for (DataTableRow cvValue : cvValues.getGherkinRows()) {
            decoderRegister.setCV(Integer.parseInt(cvValue.getCells().get(0)),
                    Integer.parseInt(cvValue.getCells().get(1)));
        }
    }

    @When("^decoder is read$")
    public void decoder_is_read() {
        decoder = decoders.readDecoder();
    }

    @Then("^decoder manufacture is '(.*)'$")
    public void decoder_manufacture_is(final String manufacture) throws Throwable {
        assertEquals(manufacture, decoder.getDccManufacturer().getManufacturer());
    }

    @Then("^version is (\\d+)$")
    public void version_is(final Integer version) throws Throwable {
        assertEquals(version, decoder.getVersion());
    }

}
