package cool.mqtt.hooks;

import java.nio.file.Path;

/**
 * A wrapper of the parameters used to support the setting up of encrypted connections between the
 * MQTT&#46;Cool server and the target MQTT broker.
 */
public interface SecurityParams {

  /**
   * The standard name of the security protocol to be used, as specified in the
   * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#SSLContext">Java
   * Cryptography Architecture Standard Algorithm Name</a>.
   *
   *<p>If either {@code null} or empty string is returned, the {@code TLSv1.2} protocol will be
   * used.
   *
   * @return the standard name of the security protocol, or {@code null} or empty string for the
   *         default value
   */
  String getSecurityProtocol();

  /**
   * The absolute path to the <i>JKS truststore</i>, which contains the MQTT broker certificate to
   * trust.
   *
   * <p>If {@code null} is returned, a default truststore will be determined according to the
   * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/JSSERefGuide.html">JSSE Reference Guide</a>.
   * 
   * @return the absolute path to the <i>JKS truststore</i>, or {@code null}
   */
  Path getTruststorePath();

  /**
   * The password for the <i>JKS truststore</i>.
   *
   * <p>
   * The password will be used only if a truststore is provided by {@link #getTruststorePath()}.
   *
   * @return the password for the <i>JSK truststore</i>
   */
  char[] getTruststorePassword();

  /**
   * The absolute path to the <i>JKS keystore</i>, which contains the client certificate and the
   * private key to be used in the case the target MQTT broker requires client authentication.
   *
   * <p>If {@code null} is returned, a default keystore will be determined according to the
   * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/JSSERefGuide.html">JSSE Reference Guide</a>.
   *
   * @return the absolute path to the <i>JKS keystore</i>, or {@code null}
   */
  Path getKeystorePath();

  /**
   * The password for the <i>JKS keystore</i>.
   *
   * <p>The password will be used only if a keystore is provided by {@link #getKeystorePath()}.
   *
   * @return the password for the <i>JKS keystore</i>
   */
  char[] getKeystorePassword();

  /**
   * The password for the private key stored into the <i>JSK keystore</i>.
   *
   * <p>The password will be used only if a keystore is provided by {@link #getKeystorePath()}.
   *
   * @return the password for the private key stored into the <i>JSK keystore</i>, or {@code null}
   */
  char[] getPrivateKeyPassword();

}
