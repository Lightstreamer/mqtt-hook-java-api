1.2.0 build 280 (18 OCt 2018)
----------------------------------------------------------------

**Improvements**

- Increased minimum required Java version to 8.
- Updated link to new `MQTT.Cool Getting Started Guide` URL in the API
  specification.
- Updated `pom.xml` by upgrading `maven-javadoc-plugin` to version 3.0.1.

**Bug Fixes**

- Fixed wrong reference to the default ClientId prefix in documentation of the
`MqttBrokerConfig.getClientIdPrefix()` method.
- Removed unsupported *HTML5* tags from the API specification.

**Compatibility**

Compatible with MQTT.Cool since version 1.0.3 b3



1.1.0 build 175 (9 Jan 2018)
----------------------------

**Improvements**

- Added `SecurityParams` class for providing parameters relative to the setting
up of encrypted connections.
- Added required methods to `MqttBrokerConfig` and `MqttBokerConfigBuilder`
for handling new `SecurityParams` class.
- Updated the API specification to reflect changes made necessary by
`SecurityParams` management.
- Done other minor changes in the API specification to improve readability.
  In particular:
  - Clarified which schemas can be used in the URI returned by
`MqttBrokerConfig.getAddress()`.
  - Improve description of `MqttBrokerConfig.build()`.
- Updated JUnit test cases.

**Bug Fixes**

- Fixed the API specification. In particular:
  - Fixed typos.
  - Fixed conditions which actually throw `IllegalArgumentsException` in
the `MqttBrokerConfigBuilder.willMessage(...)` method.
  - Removed wrong statement in the
`MqttBrokerConfigBuilder.willMessage(...)` method about the behavior of
`MqttBrokerConfig.getWillMessage()`.

**Compatibility**

Compatible with MQTT.Cool since version 1.0.3 b3



1.0.1 build 165 (22 Sep 2017)
-----------------------------

**Improvements**

- Added formal checks while setting the will message trough
`MqttBrokerConfigBuilder`.
- Added JUnit test cases for the concrete classes provided by the API.
- Improved code formatting.

**Compatibility**

Compatible with MQTT.Cool since version 1.0.3 b1



1.0.0 build 159 (2 Aug 2017)
----------------------------

First public release.

**Compatibility**

Compatible with MQTT.Cool since version 1.0.3 b1