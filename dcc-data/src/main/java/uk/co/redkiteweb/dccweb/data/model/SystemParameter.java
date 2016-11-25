package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by shawn on 25/11/16.
 */
@Entity
public class SystemParameter implements Serializable {

    private static final long serialVersionUID = 9100;

    private Integer settingId;
    private String name;
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(final Integer settingId) {
        this.settingId = settingId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
