package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by shawn on 03/04/17.
 */
@Entity
public class Block implements Serializable {

    private static final long serialVersionUID = 9010;

    private String blockId;
    private String blockName;
    private Boolean occupied;
    private Macro macro;

    @Id
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(final String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(final String blockName) {
        this.blockName = blockName;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(final Boolean occupied) {
        this.occupied = occupied;
    }

    @ManyToOne
    public Macro getMacro() {
        return macro;
    }

    public void setMacro(Macro macro) {
        this.macro = macro;
    }
}
