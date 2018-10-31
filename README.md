# MQTT.Cool Hook Java API

The MQTT.Cool Hook is a custom pluggable component which provides a powerful extension mechanism to integrate your own authentication and authorization functionalities into the MQTT.Cool server.

The MQTT.Cool Hook Java API lets you easily develop any Hook, which will be then packaged and deployed into the MQTT.Cool installation.
Once loaded inside the running server process, it will be able to intercept specific events originated from the client side in order to apply fine-grained custom authorization checks as per your own security needs.

# The MQTT.Cool SDK for Java Hooks
The API is part of the _MQTT.Cool SDK for Java Hooks_, which also offers the full Javadoc API Specification and other additional information. See the [online](https://mqtt.cool/download/latest-hook-sdk) SDK section for more information.

# Documentation
Take a look at Section "The MQTT.Cool Hook" of the [_MQTT.Cool Getting Started Guide_](https://mqtt.cool/docs/server/guides/MQTT.Cool+Getting+Started+Guide.html) for the core concepts of Hook development.

# Using the API

Since the API is available from the Maven Central Repository, to setup your development environment add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>cool.mqtt</groupId>
    <artifactId>mqtt.cool-hook-java-api</artifactId>
    <version>1.2.0</version>
</dependency>
```