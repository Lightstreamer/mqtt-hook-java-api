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
 * An abstraction of the <code>SUBSCRIBE</code> Control Packet, sent by the client and passed to the
 * Hook, before being encoded and finally submitted to the target MQTT broker.
 */
public interface MqttSubscription {

  /**
   * Gets the topic filter indicating one or more topics to which the client subscribe.
   *
   * @return the topic filter
   */
  String getTopicFilter();

  /**
   * Gets the maximum Quality Of Service level which the target MQTT broker is allowed to send
   * Application Messages.
   *
   * @return the maximum Quality Of Service level
   */
  QoS getQos();

}
