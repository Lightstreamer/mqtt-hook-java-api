package cool.mqtt.hooks;

/**
 * A wrapper of the connection parameters actually used by the PRODUCT_NAME_PLACEHOLDER server while
 * establishing the <i>end-to-end</i> connection between the client and the target MQTT broker.
 * <p>
 * The client may specify the following options on its behalf:
 * <ul>
 * <li>{@code username} and {@code password} used for authenticating the client: if provided, they
 * will take precedence over the ones which may have been defined in
 * <i>mqtt_master_connector_conf.xml</i> or supplied through the {@code MqttBrokerConfig} instance
 * returned by an invocation of {@link MQTTCoolHook#resolveAlias(String)}.
 * </li>
 * <li>{@code clean session} flag, which specifies how to handle the Session Persistence.</li>
 * <li>{@code Will Message}, which allows to publish a message in case of connection issues on the
 * client side; if provided, it will take precedence over the one which may have been defined in
 * <i>mqtt_master_connector_conf.xml</i> or supplied through the {@code MqttBrokerConfig} instance
 * returned by an invocation of {@link MQTTCoolHook#resolveAlias(String)}.
 * </li>
 * </ul>
 */
public interface MqttConnectOptions {

    /**
     * Gets the {@code username} to be used for authenticating with the target MQTT broker.
     * 
     * @return the {@code username}, or {@code null} if no credential is provided
     */
    String getUsername();

    /**
     * Gets the {@code password} to be used for authenticating with the target MQTT broker.
     * 
     * @return the {@code password}, or {@code null} if no credential is provided
     */
    String getPassword();

    /**
     * Gets the connection timeout expressed in seconds.
     * 
     * @return the connection timeout expressed in seconds
     */
    int getConnectionTimeout();

    /**
     * Gets the keep alive interval expressed in seconds.
     * 
     * @return the keep alive interval expressed in seconds
     */
    int getKeepAlive();

    /**
     * Gets the {@code Will Message} to be stored by the MQTT broker.
     * 
     * @return the {@code Will Message} to be stored by the MQTT broker if any, otherwise
     *         {@code null}
     */
    MqttMessage getWillMessage();

    /**
     * Gets the <i>clean session</i> flag specified by the client on connection establishment.
     * 
     * @return {@code true} if the client does not want to make the Session persistent,
     *         {@code false} otherwise
     */
    boolean isCleanSession();
}