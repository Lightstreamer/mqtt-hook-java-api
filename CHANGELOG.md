## 1.1.0 build 175 (9 Jan 2018) ##

### Improvements

Add `SecurityParams` class for providing parameters relative to the setting
up of encrypted connections.

Add required methods to `MqttBrokerConfig` and `MqttBokerConfigBuilder` 
for handling new `SecurityParams` class.

Update API Specifications to reflect changes made necessary by
`SecurityParams` management.

Do other minor changes in API Specifications to improve readability.
In particular:
* Clarify which schemas can be used in the URI returned by
`MqttBrokerConfig.getAddress()`.
* Improve description of `MqttBrokerConfig.build()`.

Update JUnit test cases.

### Bug Fixes

Fix API Specifications. In particular:
* Fix typos.
* Fix conditions which actually throw `IllegalArgumentsException` in 
the `MqttBrokerConfigBuilder.willMessage(...)` method.
* Remove wrong statement in the
`MqttBrokerConfigBuilder.willMessage(...)` method about the behavior of
`MqttBrokerConfig.getWillMessage()`.

### MQTT.Cool Compatibility Notes ###

Compatible with MQTT.Cool since version 1.0.3 b3



## 1.0.1 build 165 (22 Sep 2017) ##

### Improvements

Add formal checks while setting the will message trough
`MqttBrokerConfigBuilder`. 

Add JUnit test cases for the concrete classes provided by the API.

Improve code formatting.

### MQTT.Cool Compatibility Notes ###

Compatible with MQTT.Cool since version 1.0.3 b1



## 1.0.0 build 159 (2 Aug 2017) ##

First public release.

### MQTT.Cool Compatibility Notes ###

Compatible with MQTT.Cool since version 1.0.3 b1

