package uk.co.redkiteweb.dccweb.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.lang.reflect.Method;

/**
 * Created by shawn on 23/12/16.
 */
@Component
public class AsyncUncaughtHandler implements AsyncUncaughtExceptionHandler {

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Override
    public void handleUncaughtException(final Throwable throwable,final Method method,final Object... objects) {
        logStore.log("ERROR", throwable.getMessage());
    }
}
