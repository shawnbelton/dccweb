package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 24/10/16.
 */
@Entity
public class DecoderFunction implements Serializable {
    private static final long serialVersionUID = 9004;

    private Integer functionId;
    private Integer decoderId;
    private Integer number;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(final Integer functionId) {
        this.functionId = functionId;
    }

    @Column(name = "decoder_id")
    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(final Integer decoderId) {
        this.decoderId = decoderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
