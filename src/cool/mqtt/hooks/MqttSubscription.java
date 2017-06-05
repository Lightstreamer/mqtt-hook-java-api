package cool.mqtt.hooks;

/**
 * An abstraction of the <i>MQTT SUBSCRIBE Packet</i>, sent by the client and passed to the Hook,
 * before being encoded and finally submitted to the target MQTT broker.
 */
public interface MqttSubscription {

    /**
     * Gets the topic filter indicating one or more topics to which the client subscribe.
     * 
     * @return the topic filter
     */
    String getTopicFilter();

    /**
     * Gets the maximum <i>Quality Of Service</i> level which the target MQTT broker is allowed to
     * send <i>Application Messages</i>.
     * 
     * @return the maximum <i>QoS</i> level
     */
    QoS getQos();

}