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

import java.io.File;
import java.util.Map;

/**
 * Simple skeletal implementation of the {@code MQTTCoolHook} interface, meant as a base class to be
 * extended in order to minimize the effort required to provide a full implementation of a custom
 * Hook.
 *
 * <p>All methods provide a trivial implementation, therefore subclasses must override those methods
 * for which a specific behavior is really needed.
 */
public class SimpleCoolHook implements MQTTCoolHook {

  /**
   * This implementation is void.
   */
  @Override
  public void init(File configDir) throws HookException {

  }

  /**
   * This implementation always returns {@code null}.
   */
  @Override
  public MqttBrokerConfig resolveAlias(String alias) throws HookException {
    return null;
  }

  /**
   * This implementation always returns {@code true}.
   */
  @Override
  public boolean canOpenSession(String sessionId, String user, String password,
      @SuppressWarnings("rawtypes") Map clientContext, String clientPrincipal)
      throws HookException {

    return true;
  }

  /**
   * This implementation is void.
   */
  @Override
  public void onSessionClose(String sessionId) {}

  /**
   * This implementation always returns {@code true}.
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
   * This implementation always returns {@code true}.
   */
  @Override
  public boolean canPublish(String sessionId, String clientId, String brokerAddress,
      MqttMessage message) throws HookException {

    return true;
  }

  /**
   * This implementation always returns {@code true}.
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
      String topicFilter) {}

}
