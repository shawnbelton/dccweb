package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 09/01/17.
 */
@Entity
public class LinkedMacro implements Serializable {
    private static final long serialVersionUID = 9007;

    private Integer linkedMacroId;
    private Integer decoderId;
    private Integer macroId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getLinkedMacroId() {
        return linkedMacroId;
    }

    public void setLinkedMacroId(Integer linkedMacroId) {
        this.linkedMacroId = linkedMacroId;
    }

    @Column(name = "decoder_id")
    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(Integer decoderId) {
        this.decoderId = decoderId;
    }

    public Integer getMacroId() {
        return macroId;
    }

    public void setMacroId(final Integer macroId) {
        this.macroId = macroId;
    }
}
