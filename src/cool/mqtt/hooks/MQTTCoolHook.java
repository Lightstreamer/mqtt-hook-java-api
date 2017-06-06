package cool.mqtt.hooks;

import java.io.File;
import java.util.Map;

/**
 * Interface that defines an <i>MQTT&#46;Cool Hook</i>. By implementing this
 * interface it is possible to create a custom pluggable component for extending
 * the authorization and authentication functionalities of MQTT&#46;Cool.
 * <p>
 * Hook allows to intercept and authorize the following requests:
 * <ul>
 * <li>Session opening.</li>
 * <li>Connection creation.</li>
 * <li>Subscribing/unsubscribing to/from topics.</li>
 * <li>Messages publishing.</li>
 * </ul>
 * <p>
 * In addition, Hook could be notified about the following events:
 * <ul>
 * <li>Hook initialization.</li>
 * <li>Session closing.</li>
 * <li>Disconnection.</li>
 * <li>Unsubscription request.</li>
 * </ul>
 * Lastly, Hook can also dynamically extend the set of statically provided MQTT
 * connection settings.
 */
public interface MQTTCoolHook {

    /**
     * Called during the MQTT&#46;Cool initialization process.
     * <p>
     * Any setup logic should be implemented here.
     *
     * @param configDir
     *            the <code>&lt;MQTT&#46;COOL_HOME&gt;/mqtt_connectors</code>
     *            directory
     * @throws HookException
     *             if the the initialization can't complete successfully; in
     *             this case the MQTT&#46;Cool server process will abort
     */
    void init(File configDir) throws HookException;

    /**
     * Gets an {@code MqttBrokerConfig} instance corresponding to the
     * <code>connection alias</code> provided by the client while connecting to
     * MQTT&#46;Cool, if no other static entries have been provided in the
     * <code>mqtt_master_connector_conf.xml</code> file for the given alias.
     *
     * @param connectionAlias
     *            the connection alias provided by a client to address a
     *            specific MQTT broker
     * @return an {@code MqttBrokerConfig} object, or {@code null} if this Hook
     *         can not supply a valid MQTT broker configuration
     * @throws HookException
     *             if this Hook runs against a specific issue while providing an
     *             MQTT broker configuration
     */
    MqttBrokerConfig resolveAlias(String connectionAlias) throws HookException;

    /**
     * Checks whether the client is authorized to open a new session against
     * MQTT&#46;Cool.
     * <p>
     * The requested session is the one carried by the MQTT&#46;Cool connection
     * between the MQTT&#46;Cool JS client and MQTT&#46;Cool, and <strong>is
     * not</strong> related to the MQTT connection between MQTT&#46;Cool and any
     * MQTT broker. Full client context is passed, together with the client
     * principal, when a client certificate is specified. Note that the
     * Lightstreamer server must be configured appropriately to receive the
     * client principal (see <code>use_client_auth</code> and
     * <code>force_client_auth</code> parameters).
     *
     * @param sessionId
     *            the unique identifier of client session
     * @param user
     *            the username of the user trying to opening a session; it can
     *            be {@code null} if no username has been provided
     * @param password
     *            the password of the user trying to opening a session; it can
     *            be {@code null} if no password has been provided
     * @param clientContext
     *            the key-value map which contains the properties of the client
     *            request; available keys are:
     *            <ul>
     *            <li><code>"REMOTE_IP"</code> - string representation of the
     *            remote IP related to the current connection; it may be a proxy
     *            address.
     *            <li><code>"REMOTE_PORT"</code> - string representation of the
     *            remote port related to the current connection.
     *            <li><code>"USER_AGENT"</code> - the user-agent as declared in
     *            the current connection HTTP header.
     *            <li><code>"FORWARDING_INFO"</code> - the comma-separated list
     *            of addresses forwarded by intermediaries, obtained from the
     *            <code>X-Forwarded-For HTTP</code> header, related to the
     *            current connection; intermediate proxies usually set this
     *            header to supply connection routing information. Note that if
     *            the number of forwards to be considered local to the Server
     *            environment has been specified through the
     *            <code>skip_local_forwards</code> configuration element, in
     *            order to better determine the remote address, then these
     *            forwards will not be list.
     *            <li><code>"LOCAL_SERVER"</code> - the name of the specific
     *            server socket that handles the current connection, as
     *            configured through the <code>http_server</code> or
     *            <code>https_server</code> element.
     *            <li><code>"HTTP_HEADERS"</code> - a map object that contains a
     *            name-value pair for each header found in the HTTP request that
     *            originated the call.
     *            </ul>
     * @param clientPrincipal
     *            the identification name reported in the client TLS/SSL
     *            certificate supplied on the socket connection used to issue
     *            the request that originated the call; it can be {@code null}
     *            if client has not authenticated itself or the
     *            authentication has failed
     * @return {@code true} if this Hook authorizes the client to open the
     *         session, {@code false} otherwise
     * @throws HookException
     *             if this Hook runs against a specific issue while performing
     *             authorization checks (for example, while validating the
     *             provided credentials with an external service)
     */
    boolean canOpenSession(String sessionId, String user, String password,
        Map clientContext, String clientPrincipal) throws HookException;

    /**
     * Called to notify the Hook that a session opened against MQTT&#46;Cool has
     * been closed.
     *
     * @param sessionId
     *            the unique identifier of the client session
     */
    void onSessionClose(String sessionId);

    /**
     * Checks whether the client is authorized to connect to the MQTT broker
     * hosted at the specified address.
     * <p>
     * For a <i>shared connection</i>, this method is invoked on each connection
     * request made by every <i>joining</i> client, even if a single MQTT
     * connection is actually held to the target MQTT broker.
     * 
     * @param sessionId
     *            the unique identifier of the client session
     * @param clientId
     *            the client identifier as sent by the client, namely:
     *            <ul>
     *            <li>For a <i>dedicated connection</i>, it corresponds to the
     *            actual client identifier being used to connect to the target
     *            MQTT broker.
     *            </li>
     *            <li>
     *            For a <i>shared connection</i>, it is an empty ("") string, as
     *            the actual client identifier is determined through the
     *            connection parameters specified in the
     *            <code>mqtt_master_connector_conf.xml</code> file, or by the
     *            {@code MqttBrokerConfig} instance provided by the
     *            {@link #resolveAlias(String)} method.
     *            </li>
     *            </ul>
     * @param brokerAddress
     *            the address of the MQTT broker to connect to
     * @param connectOptions
     *            the set of options being used to connect to the target MQTT
     *            broker
     * @return {@code true} if this Hook authorizes the client to connect to the
     *         target MQTT broker, {@code false} otherwise
     * @throws HookException
     *             if this Hook runs against a specific issue while performing
     *             authorization checks
     */
    boolean canConnect(String sessionId, String clientId, String brokerAddress,
        MqttConnectOptions connectOptions) throws HookException;

    /**
     * Called to notify the Hook that a client has been disconnected from the
     * specified MQTT broker.
     * <p>
     * Note that the method is also invoked in case of session interruption (for
     * example, due to any network issue) while the client is currently
     * connected: if it is the case, both {@code onSessionClose} and
     * {@code onDisconnection} will be triggered.
     *
     * @param sessionId
     *            the unique identifier of the client session
     * @param clientId
     *            the client identifier as sent by the client (and detailed in
     *            {@link #canConnect})
     * @param brokerAddress
     *            the address of the MQTT broker disconnected from
     */
    void onDisconnection(String sessionId, String clientId,
        String brokerAddress);

    /**
     * Checks whether the client is authorized to publish the given message to
     * the specified MQTT broker.
     *
     * @param sessionId
     *            the unique identifier of the client session
     * @param clientId
     *            the client identifier as sent by the client (and detailed in
     *            {@link #canConnect})
     * @param brokerAddress
     *            the address of the MQTT broker connected to
     * @param message
     *            the message being requested to be published to the specified
     *            MQTT broker
     * @return {@code true} if this Hook authorizes the client to publish the
     *         given message, {@code false} otherwise
     * @throws HookException
     *             if this Hook runs against a specific issue while performing
     *             authorization checks
     */
    boolean canPublish(String sessionId, String clientId, String brokerAddress,
        MqttMessage message) throws HookException;

    /**
     * Checks whether the client is authorized to send the given subscription to
     * the specified MQTT broker.
     *
     * @param sessionId
     *            the unique identifier of the client session
     * @param clientId
     *            the client identifier as sent by the client (and detailed in
     *            {@link #canConnect})
     * @param brokerAddress
     *            the address of the MQTT broker connected to
     * @param subscription
     *            the subscription being requested to be sent to the specified
     *            MQTT broker
     * @return {@code true} if this Hook authorizes the client to send the given
     *         subscription, {@code false} otherwise
     * @throws HookException
     *             if this Hook runs against a specific issue while performing
     *             authorization checks
     */
    boolean canSubscribe(String sessionId, String clientId,
        String brokerAddress,
        MqttSubscription subscription) throws HookException;

    /**
     * Called to notify the Hook that a client, connected to the specified MQTT
     * broker, has been unsubscribed from the given topic filter.
     *
     * @param sessionId
     *            the unique identifier of the client session
     * @param clientId
     *            the client identifier as sent by the client (and detailed in
     *            {@link #canConnect})
     * @param brokerAddress
     *            the address of the MQTT broker connected to
     * @param topicFilter
     *            the topic filter unsubscribed from
     */
    void onUnsubscribe(String sessionId, String clientId, String brokerAddress,
        String topicFilter);

}