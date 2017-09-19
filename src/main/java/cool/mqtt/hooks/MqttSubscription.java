package cool.mqtt.hooks;

/**
 * An abstraction of the <code>SUBSCRIBE</code> Control Packet, sent by the
 * client and passed to the Hook, before being encoded and finally submitted to
 * the target MQTT broker.
 */
public interface MqttSubscription {

  /**
   * Gets the topic filter indicating one or more topics to which the client
   * subscribe.
   * 
   * @return the topic filter
   */
  String getTopicFilter();

  /**
   * Gets the maximum Quality Of Service level which the target MQTT broker is
   * allowed to send Application Messages.
   * 
   * @return the maximum Quality Of Service level
   */
  QoS getQos();

}