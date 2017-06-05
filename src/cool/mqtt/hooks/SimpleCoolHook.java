package cool.mqtt.hooks;

import java.io.File;
import java.util.Map;

/**
 * Simple skeletal implementation of the {@code MQTTCoolHook} interface, meant as a base class to be
 * extended in order to minimize the effort required to provide a full implementation of a custom
 * Hook.
 * <p>
 * All methods provide a trivial implementation, therefore subclasses must override those methods
 * for which a specific behaviour is really needed.
 */
public class SimpleCoolHook implements MQTTCoolHook {

    /**
     * This implementation is void.
     */
    @Override
    public void init(File configDir) throws HookException {

    }

    /**
     * This implementation always return {@code null}
     */
    @Override
    public MqttBrokerConfig resolveAlias(String alias) throws HookException {
        return null;
    }

    /**
     * This implementation always return {@code true}
     */
    @Override
    public boolean canOpenSession(String sessionId, String user, String password,
        @SuppressWarnings("rawtypes") Map clientContext,
        String clientPrincipal) throws HookException {

        return true;
    }

    /**
     * This implementation is void.
     */
    @Override
    public void onSessionClose(String sessionId) {
    }

    /**
     * This implementation always return {@code true}
     */
    @Override
    public boolean canConnect(String sessionId, String clientId, String brokerAddress,
        MqttConnectOptions connectOptions) throws HookException {

        return true;
    }

    /**
     * This implementation is void.
     */
    @Override
    public void onDisconnection(String sessionId, String clientId, String brokerAddress) {

    }

    /**
     * This implementation always return {@code true}
     */
    @Override
    public boolean canPublish(String sessionId, String clientId, String brokerAddress,
        MqttMessage message) throws HookException {

        return true;
    }

    /**
     * This implementation always return {@code true}
     */
    @Override
    public boolean canSubscribe(String sessionId, String clientId, String brokerAddress,
        MqttSubscription subscription) throws HookException {

        return true;
    }

    /**
     * This implementation is void.
     */
    @Override
    public void onUnsubscribe(String sessionId, String clientId, String brokerAddress,
        String topicFilter) {
    }
}
