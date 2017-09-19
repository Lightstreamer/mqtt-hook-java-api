package cool.mqtt.hooks.utils;

import cool.mqtt.hooks.MqttBrokerConfig;
import cool.mqtt.hooks.MqttMessage;
import cool.mqtt.hooks.QoS;

/**
 * Simple builder class to simplify the making of an {@link MqttBrokerConfig}
 * instance.
 */
public class MqttBrokerConfigBuilder {

  private String address;

  private String username;

  private String password;

  private String will_topic;

  private Integer connectionTimeout;

  private Integer keepAlive;

  private String clientIdPrefix;

  private byte[] will_applcationMessage;

  private QoS will_qos;

  private boolean will_retain;

  /**
   * Creates a {@code MqttBrokerConfigBuilder} initialized with the specified
   * MQTT broker address
   * 
   * @param address
   *          the address of the MQTT broker
   */
  public MqttBrokerConfigBuilder(String address) {
    this.address = address;
  }

  /**
   * Sets the {@code username} on this builder.
   * 
   * @param username
   *          the username
   * @return a reference to this object
   * @see MqttBrokerConfig#getUsername()
   */
  public MqttBrokerConfigBuilder username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Sets the {@code password} on this builder.
   * 
   * @param password
   *          the password
   * @return a reference to this object
   * @see MqttBrokerConfig#getPassword()
   */
  public MqttBrokerConfigBuilder password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Sets the <i>Will Message</i> on this builder.
   * <p>
   * If the provided {@code topic} is {@code null} or a empty string, invoking
   * {@link MqttBrokerConfig#getWillMessage()} on the
   * {@code MqttBrokerConfig}s
   * instance built through this builder will return {@code null}.
   * <p>
   * 
   * @param topic
   *          the topic name
   * @param applicationMessage
   *          the carried Application Message
   * @param qos
   *          the Quality of Service
   * @param retain
   *          the retained flag
   * @return a reference to this object
   * @throws IllegalArgumentException
   *           if the specified {@code qos} is {@code null} and
   *           {@code topic} is a valid topic name
   * @see MqttMessage
   * @see MqttBrokerConfig#getWillMessage()
   */
  public MqttBrokerConfigBuilder willMessage(String topic,
    byte[] applicationMessage, QoS qos, boolean retain) {

    if (topic == null || (topic != null && topic.length() == 0)) {
      throw new IllegalArgumentException("Invalid topic");
    }

    if (qos == null) {
      throw new IllegalArgumentException("Invalid QoS");
    }

    this.will_topic = topic;
    this.will_applcationMessage = applicationMessage;
    this.will_qos = qos;
    this.will_retain = retain;
    return this;
  }

  /**
   * Sets the keep alive interval expressed in seconds on this builder.
   * 
   * @param keepAlive
   *          the keep alive interval in seconds
   * @return a reference to this object
   * @see MqttBrokerConfig#getKeepAlive()
   */
  public MqttBrokerConfigBuilder keepAlive(int keepAlive) {
    this.keepAlive = keepAlive;
    return this;
  }

  /**
   * Sets the connection timeout expressed in seconds on this builder.
   * 
   * @param connectionTimeout
   *          the connection timeout in seconds
   * @return a reference to this object
   * @see MqttBrokerConfig#getConnectionTimeout()
   */
  public MqttBrokerConfigBuilder connectionTimeout(int connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
    return this;
  }

  /**
   * Sets the ClientId prefix to be used for shared connection on this
   * builder.
   * 
   * @param clientIdPrefix
   *          the ClientId prefix
   * @return a reference to this object
   * @see MqttBrokerConfig#getClientIdPrefix()
   */
  public MqttBrokerConfigBuilder clientIdPrefix(String clientIdPrefix) {
    this.clientIdPrefix = clientIdPrefix;
    return this;
  }

  /**
   * Returns a new {@code MqttBrokerConfig} instance initialized with all
   * parameters provided to this builder.
   * 
   * @return a {@code MqttBrokerConfig} instance
   */
  public MqttBrokerConfig build() {
    return new MqttBrokerConfig() {

      @Override
      public MqttMessage getWillMessage() {
        MqttMessage willMessage = null;
        if (will_topic != null) {
          return new MqttMessage() {

            @Override
            public boolean isRetained() {
              return will_retain;
            }

            @Override
            public boolean isDuplicate() {
              return false;
            }

            @Override
            public String getTopicName() {
              return will_topic;
            }

            @Override
            public QoS getQos() {
              return will_qos;
            }

            @Override
            public byte[] getApplicationMessage() {
              return will_applcationMessage;
            }
          };
        }
        return willMessage;
      }

      @Override
      public String getUsername() {
        return username;
      }

      @Override
      public String getPassword() {
        return password;
      }

      @Override
      public Integer getKeepAlive() {
        return keepAlive;
      }

      @Override
      public Integer getConnectionTimeout() {
        return connectionTimeout;
      }

      @Override
      public String getClientIdPrefix() {
        return clientIdPrefix;
      }

      @Override
      public String getAddress() {
        return address;
      }
    };
  }

}
