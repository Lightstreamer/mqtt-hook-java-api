package cool.mqtt.hooks;

/**
 * An encapsulation of the MQTT broker configuration corresponding to the
 * connection alias provided by a client on connection establishment with
 * MQTT&#46;Cool.
 * <p>
 * The Hook is expected to supply an instance of {@code MqttBrokerConfig}
 * (through an invocation of {@link MQTTCoolHook#resolveAlias(String)}) if no
 * static entries have been provided in the
 * <tt>mqtt_master_connector_conf.xml</tt> file for a given connection alias.
 * <p>
 * The configuration is made up of:
 * <ul>
 * <li>A mandatory address of the MQTT broker.</li>
 * <li>Optional connection timeout and keep alive interval values.</li>
 * <li>An optional ClientId prefix.</li>
 * <li>Optional {@code username} and {@code password}, if the MQTT broker
 * requires an authenticated access,</li>
 * <li>An optional {@code Will Message}.</li>
 * </ul>
 * 
 * @see MQTTCoolHook#resolveAlias(String)
 */
public interface MqttBrokerConfig {
    /**
     * Gets the address of the MQTT broker to connect to.
     * <p>
     * The address must be specified in one of the following URI forms:
     * <ul>
     * <li>{@code tcp://<host>:<port>}</li>
     * <li>{@code mqtt://<host>:<port>}</li>
     * </ul>
     * otherwise MQTT&#46;Cool will invalidate it and the client will be
     * notified with an a error.
     *
     * @return the address of the MQTT broker
     */
    String getAddress();

    /**
     * Gets the ClientId prefix to be used for shared connections.
     * <p>
     * If the clients want to share a single connection, MQTT&#46;Cool
     * concatenates the ClientId prefix with a randomly generated suffix in
     * order to make a unique ClientId for opening the physical connection to
     * the MQTT broker. Uniqueness of the ClientId is mandatory as multiple
     * shared connections may exist for the same MQTT broker.
     * <p>
     * In case of blank return value (that is, an empty, whitespace or
     * {@code null} string), the default ClientId prefix
     * {@code "CLIENT_ID_PREFIX_PLACEHOLDER"} will be used.
     *
     * @return the ClientId prefix, or a blank value (as defined above) if the
     *         default ClientId prefix is accepted
     */
    String getClientIdPrefix();

    /**
     * Gets the connection timeout expressed in seconds.
     * <p>
     * In case of a {@code null} return value, a default value of {@code 5}
     * seconds will be used.
     *
     * @return the connection timeout expressed in seconds, or {@code null} if
     *         the default value is accepted
     */
    Integer getConnectionTimeout();

    /**
     * Gets the keep alive interval expressed in seconds.
     * <p>
     * In case of a {@code null} return value, a default value of {@code 30}
     * seconds will be used.
     *
     * @return the keep alive interval expressed in seconds, or {@code null} if
     *         the default value is accepted
     */
    Integer getKeepAlive();

    /**
     * Gets the {@code username} for authenticating with the MQTT broker.
     * <p>
     * If the MQTT broker does not require authentication, this method should
     * return a {@code null} value. Empty or blank values will be provided <i>as
     * is</i> in the <tt>CONNECT</tt> Control Packet built to connect to the
     * broker.
     * <p>
     * Note that the {@code username} which may be provided by the client will
     * take precedence over the one returned by this method.
     *
     * @return the {@code username} or {@code null} if no credential is provided
     * @see MqttConnectOptions#getUsername()
     */
    String getUsername();

    /**
     * Gets the {@code password} for authenticating with the MQTT broker.
     * <p>
     * As for {@link #getUsername()}, this method should returns {@code null}
     * value in the case of unauthenticated access. Empty or blank values will
     * be provided <i>as is</i> in the <tt>CONNECT</tt> Control Packet built to
     * connect to the broker.
     * <p>
     * Note that the {@code password} which may be provided by the client will
     * take precedence over the one returned by this method.
     *
     * @return the {@code password}, or {@code null} if no credential is
     *         provided
     * @see MqttConnectOptions#getPassword()
     */
    String getPassword();

    /**
     * Gets the {@code Will Message} to be stored by the MQTT broker.
     * <p>
     * Note that the {@code Will Message} which may be provided by the client
     * will take precedence over the one returned by this method.
     *
     * @return the {@code Will Message} to be stored by the MQTT broker if any,
     *         otherwise {@code null}
     * @see MqttConnectOptions#getWillMessage()
     */
    MqttMessage getWillMessage();

}
