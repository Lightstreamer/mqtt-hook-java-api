# Changelog

## [1.3.0] (2020-11-11)

*Compatible with MQTT.Cool since version 1.0.3 b3*

**Improvements**

- Migrated the build to _Gradle_.
- Bump junit from 4.11 to 4.13.1 ([#1](https://github.com/Lightstreamer/mqtt-hook-java-api/pull/1)).
- Changed license to Apache 2.0.

## [1.2.2 build 291] (2020-10-01)

*Compatible with MQTT.Cool since version 1.0.3 b3*

**Bug Fixes**

- Removed undefined results appearing when using the search box from the API specification pages.

## [1.2.1 build 290] (2020-06-25)

*Compatible with MQTT.Cool since version 1.0.3 b3*

**Improvements**

- Changed format of the `CHANGELOG.md` file.

## [1.2.0 build 281] (2018-10-18)

*Compatible with MQTT.Cool since version 1.0.3 b3*

**Improvements**

- Increased minimum required Java version to 8.
- Updated link to new `MQTT.Cool Getting Started Guide` URL in the API
  specification.
- Updated `pom.xml` by upgrading `maven-javadoc-plugin` to version 3.0.1.

**Bug Fixes**

- Fixed wrong reference to the default ClientId prefix in documentation of the
`MqttBrokerConfig.getClientIdPrefix()` method.
- Removed unsupported *HTML5* tags from the API specification.

## [1.1.0 build 175] (2018-01-09)

*Compatible with MQTT.Cool since version 1.0.3 b3*

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

## [1.0.1 build 165] (2018-22-09)

*Compatible with MQTT.Cool since version 1.0.3 b1*

**Improvements**

- Added formal checks while setting the will message trough
`MqttBrokerConfigBuilder`.
- Added JUnit test cases for the concrete classes provided by the API.
- Improved code formatting.

## [1.0.0 build 159] (2019-08-02)

*Compatible with MQTT.Cool since version 1.0.3 b1*

- First public release.
