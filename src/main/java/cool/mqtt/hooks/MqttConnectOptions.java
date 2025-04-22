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
 * A wrapper of the connection parameters actually used by the MQTT&#46;Cool server while
 * establishing the <i>end-to-end</i> connection between the client and the target MQTT broker.
 *
 * <p>The client may specify the following options on its behalf:
 * <ul>
 * <li>{@code username} and {@code password} used for authenticating the client: if provided, they
 * will take precedence over the ones which may have been defined in
 * {@code mqtt_master_connector_conf.xml } or supplied through the {@code MqttBrokerConfig} instance
 * returned by an invocation of {@link MQTTCoolHook#resolveAlias(String)}.</li>
 * <li>{@code clean session} flag, which specifies how to handle the Session Persistence.</li>
 * <li>{@code Will Message}, which allows to publish a message in case of connection issues on the
 * client side; if provided, it will take precedence over the one which may have been defined in
 * {@code mqtt_master_connector_conf.xml} or supplied through the {@code MqttBrokerConfig} instance
 * returned by an invocation of {@link MQTTCoolHook#resolveAlias(String)}.</li>
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
   * @return the {@code Will Message} to be stored by the MQTT broker if any, or {@code null}
   */
  MqttMessage getWillMessage();

  /**
   * Gets the <i>clean session</i> flag specified by the client on connection establishment.
   *
   * @return {@code true} if the client does not want to make the Session persistent
   */
  boolean isCleanSession();

}
