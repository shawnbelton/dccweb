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
    private Macro macro;

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

    @ManyToOne
    public Macro getMacro() {
        return macro;
    }

    public void setMacro(Macro macro) {
        this.macro = macro;
    }
}
