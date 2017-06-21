# MQTT.Cool Hook Java API

The MQTT.Cool Hook is a custom pluggable component which provides a powerful extension mechanism to integrate your own authentication and authorization functionalities into the MQTT.Cool server.

The MQTT.Cool Hook Java API lets you easily develop any Hook, which will be then packaged and deployed into the MQTT.Cool installation.
Once loaded inside the running server process, it will be able to intercept specific events originated from the client side in order to apply fine-grained custom authorization checks as per your own security needs.

# The MQTT.Cool Hook SDK
The API is part of the _MQTT.Cool Hook SDK_, which also offers the full Javadoc API Specification and other additional information. See the `<MQTT.COOL_HOME>/DOCS-SDKs/sdk_hook` folder of your local MQTT.Cool installation.

# Documentation
Take a look at Section `The MQTT.Cool Hook` of the [_MQTT.Cool Getting Started Guide_](http://www.lightstreamer.com/mqtt.cool/MQTT.Cool%20Getting%20Started%20Guide.pdf) for the core concepts of Hook development.

# Using the API

Since the API is available from the Maven Central Repository, to setup your development environment add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>cool.mqtt.hook</groupId>
    <artifactId>mqtt.cool-hook-api</artifactId>
    <version>1.0.0</version>
</dependency>
```