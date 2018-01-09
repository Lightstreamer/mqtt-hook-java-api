package cool.mqtt.hooks.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import cool.mqtt.hooks.MqttBrokerConfig;
import cool.mqtt.hooks.MqttMessage;
import cool.mqtt.hooks.QoS;
import cool.mqtt.hooks.SecurityParams;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MqttBrokerConfigBuilderTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private MqttBrokerConfigBuilder builder;

  @Before
  public void setup() {
    builder = new MqttBrokerConfigBuilder("mqtts://localhost:8883");
  }

  @Test
  public void shouldBuildConfigWithDefaultValues() {
    MqttBrokerConfig config = builder.build();
    assertThat(config.getAddress(), is("mqtts://localhost:8883"));
    assertThat(config.getClientIdPrefix(), nullValue());
    assertThat(config.getConnectionTimeout(), nullValue());
    assertThat(config.getKeepAlive(), nullValue());
    assertThat(config.getPassword(), nullValue());
    assertThat(config.getUsername(), nullValue());
    assertThat(config.getPassword(), nullValue());
    assertThat(config.getWillMessage(), nullValue());
    assertThat(config.getSecurityParams(), nullValue());
  }

  @Test
  public void shouldBuildConfigWithCustomValues() {
    MqttBrokerConfig config = builder
      .username("username")
      .password("password")
      .clientIdPrefix("client_id_prefix")
      .connectionTimeout(5)
      .keepAlive(10)
      .willMessage("topic", new byte[] {1, 2, 3}, QoS.AT_MOST_ONCE, true)
      .securityProtocol("TLSV1.1")
      .truststorePath(Paths.get("truststore.jks"))
      .truststorePassword("truststorepassword".toCharArray())
      .keystorePath(Paths.get("keystore.jks"))
      .keystorePassword("keystorepassword".toCharArray())
      .privateKeyPassword("privatekeypassword".toCharArray())
      .build();

    assertThat(config.getAddress(), is("mqtts://localhost:8883"));
    assertThat(config.getClientIdPrefix(), is("client_id_prefix"));
    assertThat(config.getConnectionTimeout(), is(5));
    assertThat(config.getKeepAlive(), is(10));
    assertThat(config.getPassword(), is("password"));
    assertThat(config.getUsername(), is("username"));

    MqttMessage willMessage = config.getWillMessage();
    assertThat(willMessage, notNullValue());
    assertThat(willMessage.getTopicName(), is("topic"));
    assertThat(willMessage.getQos(), is(QoS.AT_MOST_ONCE));
    assertThat(willMessage.getApplicationMessage(), is(new byte[] {1, 2, 3}));

    SecurityParams securityParams = config.getSecurityParams();
    assertThat(securityParams, notNullValue());
    assertThat(securityParams.getTruststorePath(), is(Paths.get("truststore.jks")));
    assertThat(securityParams.getTruststorePassword(), is("truststorepassword".toCharArray()));
    assertThat(securityParams.getKeystorePath(), is(Paths.get("keystore.jks")));
    assertThat(securityParams.getKeystorePassword(), is("keystorepassword".toCharArray()));
    assertThat(securityParams.getPrivateKeyPassword(), is("privatekeypassword".toCharArray()));
  }

  @Test
  public void shouldReturnSecurityParamsWhenProvidingSecurityProtocol() {
    MqttBrokerConfig config = builder.securityProtocol("TLSV1.2").build();
    SecurityParams secParams = config.getSecurityParams();
    assertThat(secParams, notNullValue());
    assertThat(secParams.getSecurityProtocol(), is("TLSV1.2"));
  }

  @Test
  public void shouldReturnSecurityParamsWhenProvidingTruststore() {
    MqttBrokerConfig config = builder.truststorePath(Paths.get("truststore.jks")).build();
    SecurityParams secParams = config.getSecurityParams();
    assertThat(secParams, notNullValue());
    assertThat(secParams.getTruststorePath(), is(Paths.get("truststore.jks")));
  }

  @Test
  public void shouldReturnSecurityParamsWhenProvidingKeystore() {
    MqttBrokerConfig config = builder.keystorePath(Paths.get("keystore.jks")).build();
    SecurityParams secParams = config.getSecurityParams();
    assertThat(secParams, notNullValue());
    assertThat(secParams.getKeystorePath(), is(Paths.get("keystore.jks")));
  }

  @Test
  public void shouldNotReturnSecurityParams() {
    MqttBrokerConfig config = builder.securityProtocol("").build();
    assertThat(config.getSecurityParams(), nullValue());

    config = builder.keystorePassword("keystorepassword".toCharArray()).build();
    assertThat(config.getSecurityParams(), nullValue());

    config = builder.privateKeyPassword("privatekeypassword".toCharArray()).build();
    assertThat(config.getSecurityParams(), nullValue());
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidNullTopic() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid topic"));

    builder.willMessage(null, new byte[] {1, 2, 3}, QoS.AT_MOST_ONCE, true);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidEmptyTopic() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid topic"));

    builder.willMessage("", new byte[] {1, 2, 3}, QoS.AT_MOST_ONCE, true);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionForInvalidQoS() {
    // Set expected exception.
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(is("Invalid QoS"));

    builder.willMessage("topic", new byte[] {1, 2, 3}, null, false);
  }

}
