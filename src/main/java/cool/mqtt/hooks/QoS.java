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
