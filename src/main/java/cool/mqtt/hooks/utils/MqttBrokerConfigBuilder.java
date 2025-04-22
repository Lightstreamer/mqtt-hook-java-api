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
package cool.mqtt.hooks.utils;

import cool.mqtt.hooks.MqttBrokerConfig;
import cool.mqtt.hooks.MqttMessage;
import cool.mqtt.hooks.QoS;
import cool.mqtt.hooks.SecurityParams;

import java.nio.file.Path;

/**
 * Simple builder class to simplify the making of an {@link MqttBrokerConfig} instance.
 */
public class MqttBrokerConfigBuilder {

  private String address;

  private String username;

  private String password;

  private String willTopic;

  private Integer connectionTimeout;

  private Integer keepAlive;

  private String clientIdPrefix;

  private byte[] willApplicationMessage;

  private QoS willQos;

  private boolean willRetain;

  private String securityProtocol;

  private Path truststorePath;

  private char[] truststorePassword;

  private Path keystorePath;

  private char[] keystorePassword;

  private char[] privateKeyPassword;

  /**
   * Creates an {@code MqttBrokerConfigBuilder} initialized with the specified MQTT broker address.
   *
   * @param address the address of the MQTT broker
   */
  public MqttBrokerConfigBuilder(String address) {
    this.address = address;
  }

  /**
   * Sets the {@code username} on this builder.
   *
   * @param username the username
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
   * @param password the password
   * @return a reference to this object
   * @see MqttBrokerConfig#getPassword()
   */
  public MqttBrokerConfigBuilder password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Sets the <i>Will Message</i> on this builder.
   *
   * @param topic the topic name
   * @param applicationMessage the carried Application Message
   * @param qos the Quality of Service
   * @param retain the retained flag
   * @return a reference to this object
   * @throws IllegalArgumentException in the following cases:
   *         <ul>
   *         <li>the specified {@code qos} is {@code null}</li>
   *         <li>the specified {@code topic} is either {@code null} or empty</li>
   *         </ul>
   * @see MqttMessage
   * @see MqttBrokerConfig#getWillMessage()
   */
  public MqttBrokerConfigBuilder willMessage(String topic, byte[] applicationMessage, QoS qos,
      boolean retain) {

    if (topic == null || (topic != null && topic.length() == 0)) {
      throw new IllegalArgumentException("Invalid topic");
    }

    if (qos == null) {
      throw new IllegalArgumentException("Invalid QoS");
    }

    this.willTopic = topic;
    this.willApplicationMessage = applicationMessage;
    this.willQos = qos;
    this.willRetain = retain;
    return this;
  }

  /**
   * Sets the keep alive interval expressed in seconds on this builder.
   *
   * @param keepAlive the keep alive interval in seconds
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
   * @param connectionTimeout the connection timeout in seconds
   * @return a reference to this object
   * @see MqttBrokerConfig#getConnectionTimeout()
   */
  public MqttBrokerConfigBuilder connectionTimeout(int connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
    return this;
  }

  /**
   * Sets the ClientId prefix (to be used for shared connection) on this builder.
   *
   * @param clientIdPrefix the ClientId prefix
   * @return a reference to this object
   * @see MqttBrokerConfig#getClientIdPrefix()
   */
  public MqttBrokerConfigBuilder clientIdPrefix(String clientIdPrefix) {
    this.clientIdPrefix = clientIdPrefix;
    return this;
  }

  /**
   * Sets the standard name of the security protocol on this builder.
   * 
   * <p>The standard name must be specified according to
   * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#SSLContext">Java Cryptography Architecture Standard Algorithm Name</a>.
   *
   * @param securityProtocol the security protocol name
   * @return a reference to this object
   * @see SecurityParams#getSecurityProtocol()
   */
  public MqttBrokerConfigBuilder securityProtocol(String securityProtocol) {
    this.securityProtocol = securityProtocol;
    return this;
  }

  /**
   * Sets the absolute path to the <i>JKS truststore</i> on this builder.
   *
   * @param truststorePath the absolute to the <i>JKS truststore</i>
   * @return a reference to this object
   * @see SecurityParams#getTruststorePath()
   */
  public MqttBrokerConfigBuilder truststorePath(Path truststorePath) {
    this.truststorePath = truststorePath;
    return this;
  }

  /**
   * Sets the password for the <i>JKS truststore</i> on this builder.
   *
   * @param truststorePassword the password for the <i>JKS truststore</i>
   * @return a reference to this object
   * @see SecurityParams#getTruststorePassword()
   */
  public MqttBrokerConfigBuilder truststorePassword(char[] truststorePassword) {
    this.truststorePassword = truststorePassword;
    return this;
  }

  /**
   * Sets the absolute path to the <i>JKS keystore</i> on this builder.
   * 
   * @param keystorePath the absolute path to the <i>JKS keystore</i>
   * @return a reference to this object
   * @see SecurityParams#getKeystorePath()
   */
  public MqttBrokerConfigBuilder keystorePath(Path keystorePath) {
    this.keystorePath = keystorePath;
    return this;
  }

  /**
   * Sets the password for the <i>JKS keystore</i> on this builder.
   *
   * @param keystorePassword the password for the <i>JKS keystore</i>.
   * @return a reference to this object
   * @see SecurityParams#getKeystorePassword()
   */
  public MqttBrokerConfigBuilder keystorePassword(char[] keystorePassword) {
    this.keystorePassword = keystorePassword;
    return this;
  }

  /**
   * Sets the password for the private key (stored into the <i>JSK keystore</i>) on this builder.
   *
   * @param privateKeyPassword the password for the private key stored into the <i>JSK keystore</i>
   * @return a reference to this object
   * @see SecurityParams#getPrivateKeyPassword()
   */
  public MqttBrokerConfigBuilder privateKeyPassword(char[] privateKeyPassword) {
    this.privateKeyPassword = privateKeyPassword;
    return this;
  }

  /**
   * Returns a new {@code MqttBrokerConfig} instance initialized with all parameters provided to
   * this builder.
   * 
   * <p>Note that the returned {@code MqttBrokerConfig} contains a <i>Will Message</i> only if a
   * valid topic has been explicitly provided through
   * {@link #willMessage(String, byte[], QoS, boolean)}.
   * 
   * <p>Similarly, the {@code MqttBrokerConfig} instance contains a {@code SecurityParams} object
   * only if at least one of the following parameters has been explicitly provided:
   * <ul>
   * <li>the security protocol (through {@link #securityProtocol(String)})</li>
   * <li>the truststore path (through {@link #truststorePath(Path)})</li>
   * <li>the keystore path (through {@link #keystorePath(Path)})</li>
   * </ul>
   * 
   * @return an {@code MqttBrokerConfig} instance
   */
  public MqttBrokerConfig build() {
    return new MqttBrokerConfig() {

      @Override
      public MqttMessage getWillMessage() {
        MqttMessage willMessage = null;
        if (willTopic != null) {
          return new MqttMessage() {

            @Override
            public boolean isRetained() {
              return willRetain;
            }

            @Override
            public boolean isDuplicate() {
              return false;
            }

            @Override
            public String getTopicName() {
              return willTopic;
            }

            @Override
            public QoS getQos() {
              return willQos;
            }

            @Override
            public byte[] getApplicationMessage() {
              return willApplicationMessage;
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

      @Override
      public SecurityParams getSecurityParams() {
        if ((securityProtocol != null && securityProtocol.length() != 0) || truststorePath != null
            || keystorePath != null) {
          return new SecurityParams() {

            @Override
            public String getSecurityProtocol() {
              return securityProtocol;
            }

            @Override
            public Path getTruststorePath() {
              return truststorePath;
            }

            @Override
            public char[] getTruststorePassword() {
              return truststorePassword;
            }

            @Override
            public Path getKeystorePath() {
              return keystorePath;
            }

            @Override
            public char[] getKeystorePassword() {
              return keystorePassword;
            }

            @Override
            public char[] getPrivateKeyPassword() {
              return privateKeyPassword;
            }
          };
        }
        return null;
      }
    };
  }

}
