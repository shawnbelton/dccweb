package uk.co.redkiteweb.dccweb.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class WebSocketsConfigTest {

    private WebSocketsConfig config;

    @Before
    public void setup() {
        config = new WebSocketsConfig();
    }

    @Test
    public void testRegisterStompEndpoints() {
        final StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);
        final StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
        when(registry.addEndpoint(anyString())).thenReturn(registration);
        when(registration.setAllowedOrigins(anyString())).thenReturn(registration);
        config.registerStompEndpoints(registry);
        verify(registration, times(1)).withSockJS();
    }

    @Test
    public void testConfigureMessageBroker() {
        final MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);
        when(registry.setApplicationDestinationPrefixes(anyString())).thenReturn(registry);
        config.configureMessageBroker(registry);
        verify(registry, times(1)).enableSimpleBroker(anyString());
    }

}
