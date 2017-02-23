package uk.co.redkiteweb.dccweb.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import uk.co.redkiteweb.dccweb.async.AsyncUncaughtHandler;

import java.util.concurrent.Executor;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 23/12/16.
 */
@RunWith(JUnit4.class)
public class AsyncConfigTest {

    private AsyncConfig asyncConfig;

    @Before
    public void setup() {
        asyncConfig = new AsyncConfig();
        asyncConfig.setAsyncUncaughtExceptionHandler(mock(AsyncUncaughtHandler.class));
    }

    @Test
    public void schedulerExecutorTest() {
        assertNotNull(asyncConfig.getSchedulerExecutor());
    }

    @Test
    public void threadPoolExecutorTest() {
        assertNotNull(asyncConfig.getAsyncExecutor());
    }

    @Test
    public void uncaughtHandlerTest() {
        assertNotNull(asyncConfig.getAsyncUncaughtExceptionHandler());
    }

    @Test
    public void configureTaskTest() {
        final ScheduledTaskRegistrar scheduledTaskRegistrar = mock(ScheduledTaskRegistrar.class);
        asyncConfig.configureTasks(scheduledTaskRegistrar);
        verify(scheduledTaskRegistrar, times(1)).setScheduler(any(Executor.class));
    }
}
