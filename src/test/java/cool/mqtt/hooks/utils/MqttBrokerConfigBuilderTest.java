package cool.mqtt.hooks.utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import cool.mqtt.hooks.MqttBrokerConfig;
import cool.mqtt.hooks.MqttMessage;
import cool.mqtt.hooks.QoS;
import cool.mqtt.hooks.utils.MqttBrokerConfigBuilder;

public class MqttBrokerConfigBuilderTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private MqttBrokerConfigBuilder builder;

  @Before
  public void setup() {
    builder = new MqttBrokerConfigBuilder("tcp://localhost:1883");
  }

  @Test
  public void shouldBuildConfigWithDefaultValues() {
    MqttBrokerConfig config = builder.build();
    assertThat(config.getAddress(), is("tcp://localhost:1883"));
    assertThat(config.getClientIdPrefix(), nullValue());
    assertThat(config.getConnectionTimeout(), nullValue());
    assertThat(config.getKeepAlive(), nullValue());
    assertThat(config.getPassword(), nullValue());
    assertThat(config.getUsername(), nullValue());
    assertThat(config.getPassword(), nullValue());
    assertThat(config.getWillMessage(), nullValue());
  }

  @Test
  public void shouldBuildConfigWithCustomValues() {
    MqttBrokerConfig config = builder
      .username("username")
      .password("password")
      .clientIdPrefix("client_id_prefix")
      .connectionTimeout(5)
      .keepAlive(10)
      .willMessage("topic", new byte[] { 1, 2, 3 }, QoS.AT_MOST_ONCE,
        true)
      .build();

    assertThat(config.getAddress(), is("tcp://localhost:1883"));
    assertThat(config.getClientIdPrefix(), is("client_id_prefix"));
    assertThat(config.getConnectionTimeout(), is(5));
    assertThat(config.getKeepAlive(), is(10));
    assertThat(config.getPassword(), is("password"));
    assertThat(config.getUsername(), is("username"));

    MqttMessage willMessage = config.getWillMessage();
    assertThat(willMessage, notNullValue());
    assertThat(willMessage.getTopicName(), is("topic"));
    assertThat(willMessage.getQos(), is(QoS.AT_MOST_ONCE));
    assertThat(willMessage.getApplicationMessage(),
      is(new byte[] { 1, 2, 3 }));
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidNullTopic() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid topic"));

    builder.willMessage(null, new byte[] { 1, 2, 3 }, QoS.AT_MOST_ONCE,
      true);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidEmptyTopic() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid topic"));

    builder.willMessage("", new byte[] { 1, 2, 3 }, QoS.AT_MOST_ONCE,
      true);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidQoS() {
    // Set expected exception.
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid QoS"));

    builder.willMessage("topic", new byte[] { 1, 2, 3 }, null, false);
  }

}
