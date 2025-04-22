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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class SimpleCoolHookTest {

  private SimpleCoolHook simpleCoolHook;

  @Before
  public void setUp() throws Exception {
    simpleCoolHook = new SimpleCoolHook();
  }

  @Test
  public void shouldNotResoleAliass() throws HookException {
    assertThat(simpleCoolHook.resolveAlias("anyAlias"), nullValue());
  }

  @Test
  public void shouldAllowSessionOpen() throws HookException {
    // For simplicity's sake, just pass simple values as clientContext and clientPrincipal
    assertThat(simpleCoolHook.canOpenSession("sessionId", "user", "password", new HashMap<>(), ""),
        is(true));
  }

  @Test
  public void shoulwAllowConnection() throws HookException {
    // For simplicity's sake, just pass null as MqttConnectionOptions
    assertThat(simpleCoolHook.canConnect("sessionId", "clientId", "tcp://localhost:1883", null),
        is(true));
  }

  @Test
  public void shoulwAllowPublishing() throws HookException {
    // For simplicity's sake, just pass null as MqttMessage
    assertThat(simpleCoolHook.canPublish("sessionId", "clientId", "tcp://localhost:1883", null),
        is(true));
  }

  @Test
  public void shoulwAllowSubscription() throws HookException {
    // For simplicity's sake, just pass null as MqttSubscription
    assertThat(simpleCoolHook.canSubscribe("sessionId", "clientId", "tcp://localhost:1883", null),
        is(true));
  }

}
