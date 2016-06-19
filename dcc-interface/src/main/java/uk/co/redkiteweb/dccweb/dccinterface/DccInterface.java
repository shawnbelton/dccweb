package uk.co.redkiteweb.dccweb.dccinterface;

/**
 * Created by shawn on 15/06/16.
 */
public interface DccInterface {

    DccInterfaceStatus getInterfaceStatus();
    void initialise();
    void checkInterface();
    void shutdown();

}
