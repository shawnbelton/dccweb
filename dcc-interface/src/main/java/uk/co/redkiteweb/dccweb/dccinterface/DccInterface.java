package uk.co.redkiteweb.dccweb.dccinterface;

/**
 * Created by shawn on 15/06/16.
 */
public interface DccInterface {

    DccInterfaceStatus.Status getInterfaceStatus();
    void initialise();
    void checkInterface();

}
