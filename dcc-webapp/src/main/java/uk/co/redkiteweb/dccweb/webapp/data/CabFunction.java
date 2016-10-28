package uk.co.redkiteweb.dccweb.webapp.data;

import java.io.Serializable;

/**
 * Created by shawn on 28/10/16.
 */
public class CabFunction implements Serializable {

    static final long serialVersionUID = 8001;

    private Integer number;
    private String name;
    private Boolean state;

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

    public Boolean getState() {
        return state;
    }

    public void setState(final Boolean state) {
        this.state = state;
    }
}
