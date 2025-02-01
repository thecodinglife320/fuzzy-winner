package com.ad.ff2

open class SmartDevice protected constructor(val name: String, val category: String) {

   var deviceStatus = "online"

   open val deviceType = "unknown"

   constructor(name: String, category: String, statusCode: Int) : this(name, category) {
      deviceStatus = when (statusCode) {
         0 -> "offline"
         1 -> "online"
         else -> "unknown"
      }
   }

   open fun turnOn() {
      deviceStatus = "on"
   }

   open fun turnOff() {
      deviceStatus = "off"
   }

   open fun printDeviceInfo() {
      println("Device name: $name, category: $category, type: $deviceType")
   }
}

class SmartTvDevice(tvName: String, deviceCategory: String) :
   SmartDevice(name = tvName, category = deviceCategory) {

   override val deviceType = "Smart TV"

   private var speakerVolume by RangeRegulator(2, 0, 100)

   private var channelNumber by RangeRegulator(1, 0, 200)

   fun increaseSpeakerVolume() {
      speakerVolume++
   }

   fun nextChannel() {
      channelNumber++
   }

   fun decreaseVolume() {
      speakerVolume--
   }

   fun previousChannel() {
      channelNumber--
   }

   override fun turnOn() {
      super.turnOn()
      println(
         "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
               "set to $channelNumber."
      )
   }

   override fun turnOff() {
      super.turnOff()
      println("$name turned off")
   }

   override fun printDeviceInfo() {
      super.printDeviceInfo()
      println("Speaker Volume: $speakerVolume, Channel Number: $channelNumber status: $deviceStatus")
   }
}

class SmartLightDevice(lightName: String, deviceCategory: String) :
   SmartDevice(name = lightName, category = deviceCategory) {

   override val deviceType: String
      get() = "Smart Light"

   private var brightnessLevel by RangeRegulator(0, 0, 100)

   fun increaseBrightness() {
      brightnessLevel++
   }

   fun decreaseBrightness() {
      brightnessLevel--
   }

   override fun turnOn() {
      super.turnOn()
      brightnessLevel = 2
      println("$name turned on. The brightness level is $brightnessLevel.")
   }

   override fun turnOff() {
      super.turnOff()
      brightnessLevel = 0
      println("Smart Light turned off")
   }
}

class SmartHome(
   val smartTvDevice: SmartTvDevice,
   private val smartLightDevice: SmartLightDevice
) {

   private var deviceTurnOnCount = 0

   fun turnOnTv() {
      if (smartTvDevice.deviceStatus == "off") {
         deviceTurnOnCount++
         smartTvDevice.turnOn()
      }
   }

   private fun turnOffTv() {
      if (smartTvDevice.deviceStatus == "on") {
         deviceTurnOnCount--
         smartTvDevice.turnOff()
      }
   }

   fun increaseTvVolume() {
      if (smartTvDevice.deviceStatus=="on") {
         smartTvDevice.increaseSpeakerVolume()
      }
   }

   fun changeTvChannelToNext() {
      if (smartTvDevice.deviceStatus=="on") {
         smartTvDevice.nextChannel()
      }
   }

   fun turnOnLight() {
      if (smartLightDevice.deviceStatus == "off") {
         deviceTurnOnCount++
         smartLightDevice.turnOn()
      }
   }

   private fun turnOffLight() {
      if (smartLightDevice.deviceStatus == "on") {
         deviceTurnOnCount--
         smartLightDevice.turnOff()
      }
   }

   fun increaseLightBrightness() {
      smartLightDevice.increaseBrightness()
   }

   fun turnOffAllDevices() {
      turnOffTv()
      turnOffLight()
   }

}

fun main() {
   val smartTv = SmartTvDevice("Android TV", "Entertainment")
   val smartLight = SmartLightDevice("Google Light", "Utility")
   val smartHome = SmartHome(smartTv, smartLight)

   smartHome.smartTvDevice.printDeviceInfo()
   smartHome.turnOnTv()
   smartHome.increaseTvVolume()
   smartHome.changeTvChannelToNext()
   smartHome.smartTvDevice.printDeviceInfo()
}