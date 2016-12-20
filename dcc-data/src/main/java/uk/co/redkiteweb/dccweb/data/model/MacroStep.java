package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 02/12/16.
 */
@Entity
public class MacroStep implements Serializable {
    private static final long serialVersionUID = 9005;

    private Integer stepId;
    private Integer macroId;
    private Integer number;
    private String type;
    private Float delay;
    private Integer trainId;
    private Integer functionNumber;
    private String functionStatus;
    private Integer speed;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(final Integer stepId) {
        this.stepId = stepId;
    }

    @Column(name = "macro_id")
    public Integer getMacroId() {
        return macroId;
    }

    public void setMacroId(final Integer macroId) {
        this.macroId = macroId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Float getDelay() {
        return delay;
    }

    public void setDelay(final Float delay) {
        this.delay = delay;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(final Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getFunctionNumber() {
        return functionNumber;
    }

    public void setFunctionNumber(final Integer functionNumber) {
        this.functionNumber = functionNumber;
    }

    public String getFunctionStatus() {
        return functionStatus;
    }

    public void setFunctionStatus(final String functionStatus) {
        this.functionStatus = functionStatus;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(final Integer speed) {
        this.speed = speed;
    }
}