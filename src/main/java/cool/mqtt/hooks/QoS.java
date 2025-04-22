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
 * The <i>Quality of Service</i>.
 * 
 * <p>{@code QoS} is an enum representing the 3 Quality of Service levels according to which the
 * MQTT broker delivers Application Messages.
 */
public enum QoS {

  /**
   * The singleton instance for <i>At most once</i> level. This has the numeric value of {@code 0}.
   */
  AT_MOST_ONCE(0),

  /**
   * The singleton instance for <i>At least once</i> level. This has the numeric value of {@code 1}.
   */
  AT_LEAST_ONCE(1),

  /**
   * The singleton instance for <i>Exactly once</i> level. This has the numeric value of {@code 2}.
   */
  EXACTLY_ONCE(2);

  private int value;

  QoS(int value) {
    this.value = value;
  }

  /**
   * Gets the Quality of Service level {@code int} value.
   *
   * @return the Quality of Service level, from 0 ({@code AT_MOST_ONCE}) to 2 ({@code EXACTLY_ONCE}
   *         once)
   */
  public int getValue() {
    return value;
  }

}
