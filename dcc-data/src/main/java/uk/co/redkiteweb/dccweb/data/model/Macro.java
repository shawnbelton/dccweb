package uk.co.redkiteweb.dccweb.data.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by shawn on 02/12/16.
 */
@Entity
public class Macro implements Serializable {
    private static final long serialVersionUID = 9006;

    private Integer macroId;
    private String name;
    private List<MacroStep> steps;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getMacroId() {
        return macroId;
    }

    public void setMacroId(final Integer macroId) {
        this.macroId = macroId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "macro_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<MacroStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<MacroStep> steps) {
        this.steps = steps;
    }
}
