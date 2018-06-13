package uk.co.redkiteweb.dccweb.model;

import java.io.Serializable;

public class RegisterDetails implements Serializable {

    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }
}
