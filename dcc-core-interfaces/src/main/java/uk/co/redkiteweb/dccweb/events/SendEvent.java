package uk.co.redkiteweb.dccweb.events;

public interface SendEvent {
    String getUrl();
    Object sendObject();
}