package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by shawn on 16/02/17.
 */
@Entity
public class AccessoryDecoderTypeOperation implements Serializable {
    private static final long serialVersionUID = 9009;

    private Integer decoderTypeOperationId;
    private Integer decoderTypeId;
    private String  decoderTypeOperation;
    private Integer decoderOperationValue;

    @Id
    public Integer getDecoderTypeOperationId() {
        return decoderTypeOperationId;
    }

    public void setDecoderTypeOperationId(Integer decoderTypeOperationId) {
        this.decoderTypeOperationId = decoderTypeOperationId;
    }

    @Column(name = "decoder_type_id")
    public Integer getDecoderTypeId() {
        return decoderTypeId;
    }

    public void setDecoderTypeId(Integer decoderTypeId) {
        this.decoderTypeId = decoderTypeId;
    }

    public String getDecoderTypeOperation() {
        return decoderTypeOperation;
    }

    public void setDecoderTypeOperation(String decoderTypeOperation) {
        this.decoderTypeOperation = decoderTypeOperation;
    }

    public Integer getDecoderOperationValue() {
        return decoderOperationValue;
    }

    public void setDecoderOperationValue(Integer decoderOperationValue) {
        this.decoderOperationValue = decoderOperationValue;
    }
}
