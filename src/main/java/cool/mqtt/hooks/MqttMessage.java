/*
 * Copyright (C) 2019 Lightstreamer Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cool.mqtt.hooks;

/**
 * An abstraction of the {@code PUBLISH} Control Packet, sent by a client and then passed to the
 * Hook, before being encoded and finally delivered to the target MQTT broker.
 */
public interface MqttMessage {

  /**
   * Gets the name of the topic to which this message is being published.
   *
   * @return the topic name
   */
  String getTopicName();

  /**
   * Gets the <i>Application Message</i> carried by this message.
   *
   * <p>The MQTT Protocol Specifications defines the Application Message as the payload of the
   * {@code PUBLISH} Control Packet.
   *
   * @return the byte array containing the <i>Application Message</i> info
   */
  byte[] getApplicationMessage();

  /**
   * Gets the Quality of Service level with which this message has been delivered.
   * 
   * @return the Quality of Service level
   */
  QoS getQos();

  /**
   * Gets the <i>retained</i> flag of this message.
   *
   * <p>The retain flag specifies whether the MQTT broker must store this message, in order to be
   * immediately delivered to future clients as they subscribe to matching topics.
   *
   * @return {@code true} if the <i>retained</i> flag is set
   */
  boolean isRetained();

  /**
   * Gets the <i>duplicate</i> flag of this message.
   *
   * <p>Only for messages with {@code QoS level > 0}, the duplicate flag indicates that this message
   * might have been re-sent because not acknowledged in an earlier attempt.
   *
   * <p>In case of {@link QoS#AT_MOST_ONCE} level, this method always returns {@code false}.
   *
   * @return {@code true} if the <i>duplicate</i> flag is set
   */
  boolean isDuplicate();

}
