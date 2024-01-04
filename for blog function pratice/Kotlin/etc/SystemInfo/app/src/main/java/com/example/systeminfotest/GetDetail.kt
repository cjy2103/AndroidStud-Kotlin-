package com.example.systeminfotest

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.NetworkInterface
import java.text.DecimalFormat
import java.util.Arrays
import java.util.Collections
import java.util.Locale


class GetDetail {

    object GetDetails {
        @ColorInt
        fun getThemeColor(context: Context, @AttrRes attributeColor: Int): Int {
            val value = TypedValue()
            context.theme.resolveAttribute(attributeColor, value, true)
            return value.data
        }

        fun GetFromBuildProp(PropKey: String?): String? {
            val p: Process
            var propvalue: String? = ""
            try {
                p = ProcessBuilder("/system/bin/getprop", PropKey).redirectErrorStream(true).start()
                val br = BufferedReader(InputStreamReader(p.inputStream))
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    propvalue = line
                }
                p.destroy()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return propvalue
        }

        fun GetOSNameAdvanced(): String {
            val OSName: String
            OSName = when (Build.VERSION.SDK_INT) {
                21 -> "Lollipop"
                22 -> "Lollipop MR1"
                23 -> "Marshmallow"
                24 -> "Nougat"
                25 -> "Nougat MR1"
                26 -> "Oreo"
                27 -> "Oreo MR1"
                28 -> "Android Pie"
                else -> "Unknown"
            }
            return OSName
        }

        fun GetOSReleaseDate(): String {
            val OSReleaseDate: String
            OSReleaseDate = when (Build.VERSION.SDK_INT) {
                11, 12, 13 -> "February 22, 2011"
                14, 15 -> "October 18, 2011"
                16, 17, 18 -> "July 9, 2012"
                19 -> "October 31, 2013"
                21, 22 -> "November 12, 2014"
                23 -> "October 5, 2015"
                24, 25 -> "August 22, 2016"
                26, 27 -> "August 21, 2017"
                28 -> "August 09, 2018"
                else -> "Unknown"
            }
            return OSReleaseDate
        }

        fun GetOSName(sdk: Int): String {
            val OSName: String
            OSName = when (sdk) {
                11, 12, 13 -> "HoneyComb"
                14, 15 -> "Ice Cream Sandwich"
                16, 17, 18 -> "Jelly Bean"
                19 -> "KitKat"
                21, 22 -> "Lollipop"
                23 -> "Marshmallow"
                24, 25 -> "Nougat"
                26, 27 -> "Oreo"
                28 -> "Pie"
                else -> "Unknown"
            }
            return OSName
        }

        val processor: String
            get() {
                var Final = ""
                try {
                    val sb = StringBuilder()
                    if (File("/proc/cpuinfo").exists()) {
                        try {
                            val br = BufferedReader(FileReader(File("/proc/cpuinfo")))
                            var aLine: String
                            while (br.readLine().also { aLine = it } != null) {
                                val _append = aLine + "ndeviceinfo"
                                sb.append(_append)
                            }
                            br.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        val cpuinfo =
                            sb.toString().split(":".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray()
                        for (a in cpuinfo.indices) {
                            if (cpuinfo[a].lowercase(Locale.getDefault()).contains("processor")) {
                                val getlastindex = cpuinfo[a + 1].indexOf("ndeviceinfo")
                                Final = cpuinfo[a + 1].substring(1, getlastindex)
                                break
                            }
                        }
                        if (Final == "0" || Final == "") {
                            for (a in cpuinfo.indices) {
                                if (cpuinfo[a].contains("model name")) {
                                    val getlastindex = cpuinfo[a + 1].indexOf("ndeviceinfo")
                                    Final = cpuinfo[a + 1].substring(1, getlastindex)
                                    break
                                }
                            }
                        }
                        if (Final == "" || Final == "0") {
                            Final = "Unknown"
                        }
                    } else {
                        Final = "Unknown"
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                return Final
            }
        val processorHardware: String
            get() {
                var Final = ""
                try {
                    val sb = StringBuilder()
                    if (File("/proc/cpuinfo").exists()) {
                        try {
                            val br = BufferedReader(FileReader(File("/proc/cpuinfo")))
                            var aLine: String
                            while (br.readLine().also { aLine = it } != null) {
                                val _append = aLine + "ndeviceinfo"
                                sb.append(_append)
                            }
                            br.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        val cpuinfo =
                            sb.toString().split(":".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray()
                        for (a in cpuinfo.indices) {
                            if (cpuinfo[a].lowercase(Locale.getDefault()).contains("hardware")) {
                                val getlastindex = cpuinfo[a + 1].indexOf("ndeviceinfo")
                                Final = cpuinfo[a + 1].substring(1, getlastindex)
                                break
                            } else {
                                Final = "Unknown"
                            }
                        }
                    } else {
                        Final = "Unknown"
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                return Final
            }
        val cPUGoverner: String
            get() {
                var aLine = ""
                if (File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor").exists()) {
                    try {
                        val br =
                            BufferedReader(FileReader(File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor")))
                        aLine = br.readLine()
                        br.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                return aLine
            }
//
//        fun getTime(millis: Long): String {
//            require(millis >= 0) { "Duration must be greater than zero!" }
//            return java.lang.String.format(
//                Locale.US,
//                "%02d:%02d:%02d",
//                TimeUnit.MILLISECONDS.toHours(millis),
//                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
//                    TimeUnit.MILLISECONDS.toHours(millis)
//                ),
//                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
//                    TimeUnit.MILLISECONDS.toMinutes(millis)
//                )
//            )
//        }

        fun getDisplaySize(activity: Activity): String {
            var x = 0.0
            var y = 0.0
            val mWidthPixels: Int
            val mHeightPixels: Int
            try {
                val windowManager = activity.windowManager
                val display = windowManager.defaultDisplay
                val displayMetrics = DisplayMetrics()
                display.getMetrics(displayMetrics)
                val realSize = Point()
                Display::class.java.getMethod("getRealSize", Point::class.java)
                    .invoke(display, realSize)
                mWidthPixels = realSize.x
                mHeightPixels = realSize.y
                val dm = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(dm)
                x = Math.pow((mWidthPixels / dm.xdpi).toDouble(), 2.0)
                y = Math.pow((mHeightPixels / dm.ydpi).toDouble(), 2.0)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return String.format(Locale.US, "%.2f", Math.sqrt(x + y))
        }

        fun PhoneType(gettype: Int): String {
            var Type = ""
            when (gettype) {
                TelephonyManager.PHONE_TYPE_CDMA -> Type = "CDMA"
                TelephonyManager.PHONE_TYPE_GSM -> Type = "GSM"
                TelephonyManager.PHONE_TYPE_NONE -> Type = "None"
            }
            return Type
        }

        fun NetworkType(gettype: Int): String {
            val Type: String
            Type = when (gettype) {
                TelephonyManager.NETWORK_TYPE_CDMA -> "CDMA"
                TelephonyManager.NETWORK_TYPE_EDGE -> "EDGE"
                TelephonyManager.NETWORK_TYPE_GPRS -> "GPRS"
                TelephonyManager.NETWORK_TYPE_GSM -> "GSM"
                TelephonyManager.NETWORK_TYPE_HSDPA -> "HSDPA"
                TelephonyManager.NETWORK_TYPE_HSPA -> "HSPA"
                TelephonyManager.NETWORK_TYPE_HSPAP -> "HSPAP"
                TelephonyManager.NETWORK_TYPE_HSUPA -> "HSUPA"
                TelephonyManager.NETWORK_TYPE_LTE -> "LTE"
                TelephonyManager.NETWORK_TYPE_UMTS -> "UMTS"
                else -> "Not Available"
            }
            return Type
        }

        val isRooted: Boolean
            get() {
                val buildTags = Build.TAGS
                return buildTags != null && buildTags.contains("test-keys") || canExecuteCommand("/system/xbin/which su") || canExecuteCommand(
                    "/system/bin/which su"
                ) || canExecuteCommand("which su")
            }

        private fun canExecuteCommand(command: String): Boolean {
            return try {
                val exitValue = Runtime.getRuntime().exec(command).waitFor()
                exitValue == 0
            } catch (e: Exception) {
                false
            }
        }

        val wifiMacAddress: String
            get() {
                try {
                    val interfaceName = "wlan0"
                    val interfaces: List<NetworkInterface> =
                        Collections.list(NetworkInterface.getNetworkInterfaces())
                    for (intf in interfaces) {
                        if (!intf.name.equals(interfaceName, ignoreCase = true)) {
                            continue
                        }
                        val mac = intf.hardwareAddress ?: return ""
                        val buf = StringBuilder()
                        for (aMac in mac) {
                            buf.append(String.format("%02X:", aMac))
                        }
                        if (buf.length > 0) {
                            buf.deleteCharAt(buf.length - 1)
                        }
                        return buf.toString()
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                return ""
            }

        @SuppressLint("HardwareIds")
        fun getBluetoothMac(context: Context): String {
            var result = ""
            try {
                result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Settings.Secure.getString(
                        context.contentResolver,
                        "bluetooth_address"
                    )
                } else {
                    val bta = BluetoothAdapter.getDefaultAdapter()
                    if (bta != null) bta.address else ""
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return result
        }

        fun getBatteryCapacity(context: Context?): Int {
            var batteryCapacity = 0.0
            val POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile"
            try {
                val mPowerProfile = Class.forName(POWER_PROFILE_CLASS).getConstructor(
                    Context::class.java
                ).newInstance(context)
                batteryCapacity = Class.forName(POWER_PROFILE_CLASS).getMethod(
                    "getAveragePower",
                    String::class.java
                ).invoke(mPowerProfile, "battery.capacity") as Double
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return batteryCapacity.toInt()
        }

        fun getStorageDirectories(context: Context): Array<String> {
            val storageDirectories: Array<String>
            val rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE")
            val results: MutableList<String> = ArrayList()
            val externalDirs = context.getExternalFilesDirs(null)
            for (file in externalDirs) {
                val path = file.path.split("/Android".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()[0]
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Environment.isExternalStorageRemovable(
                        file
                    )
                    || rawSecondaryStoragesStr != null && rawSecondaryStoragesStr.contains(path)
                ) {
                    results.add(path)
                }
            }
            storageDirectories = results.toTypedArray<String>()
            return storageDirectories
        }

        fun getAndroidVersion(sdk: Int): Double {
            val Version: Double
            Version = when (sdk) {
                10 -> 2.3
                11 -> 3.0
                12 -> 3.1
                13 -> 3.2
                14, 15 -> 4.0
                16 -> 4.1
                17 -> 4.2
                18 -> 4.3
                19 -> 4.4
                21 -> 5.0
                22 -> 5.1
                23 -> 6.0
                24 -> 7.0
                25 -> 7.1
                26 -> 8.0
                27 -> 8.1
                28 -> 9.0
                else -> 0.0
            }
            return Version
        }

        fun GetSELinuxMode(): String {
            val output = StringBuilder()
            val p: Process
            try {
                p = Runtime.getRuntime().exec("getenforce")
                p.waitFor()
                val reader = BufferedReader(InputStreamReader(p.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    output.append(line)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return "Not Supported"
            }
            val response = output.toString()
            return if ("Enforcing" == response) {
                "Enforcing"
            } else if ("Permissive" == response) {
                "Permissive"
            } else {
                "Unable to determine"
            }
        }

        fun GetSensorType(type: Int): String {
            val stype: String
            stype = when (type) {
                1 -> "ACCELEROMETER"
                2 -> "MAGNETIC FIELD"
                3 -> "ORIENTATION"
                4 -> "GYROSCOPE"
                5 -> "LIGHT"
                6 -> "PRESSURE"
                7 -> "TEMPERATURE"
                8 -> "PROXIMITY"
                9 -> "GRAVITY"
                10 -> "LINEAR ACCELERATION"
                11 -> "ROTATION VECTOR"
                12 -> "RELATIVE HUMIDITY"
                13 -> "AMBIENT TEMPERATURE"
                14 -> "MAGNETIC FIELD UNCALIBRATED"
                15 -> "GAME ROTATION VECTOR"
                16 -> "GYROSCOPE UNCALIBRATED"
                17 -> "SIGNIFICANT MOTION"
                18 -> "STEP DETECTOR"
                19 -> "STEP COUNTER"
                20 -> "GEOMAGNETIC ROTATION VECTOR"
                21 -> "HEART_RATE"
                22 -> "TILT DETECTOR"
                23 -> "WAKE GESTURE"
                24 -> "GLANCE_GESTURE"
                25 -> "PICK_UP_GESTURE"
                26 -> "WRIST_TILT_GESTURE"
                27 -> "DEVICE_ORIENTATION "
                28 -> "POSE 6DOF"
                29 -> "STATIONARY DETECT"
                30 -> "MOTION DETECT"
                31 -> "HEART BEAT"
                32 -> "DYNAMIC_SENSOR_META"
                33 -> "ADDITIONAL_INFO"
                34 -> "LOW LATENCY OFFBODY DETECT"
                35 -> "ACCELEROMETER UNCALIBRATED"
                else -> "Unknown"
            }
            return stype
        }

//        fun getDarkColor(context: Context, color: Int): Int {
//            val colorThemeColor =
//                Arrays.asList<String>(*context.resources.getStringArray(R.array.accent_colors))
//            val colorThemeColorDark =
//                Arrays.asList<String>(*context.resources.getStringArray(R.array.accent_colors_700))
//            val getHex = String.format(
//                "#%02x%02x%02x",
//                Color.red(color),
//                Color.green(color),
//                Color.blue(color)
//            )
//            return Color.parseColor(colorThemeColorDark[colorThemeColor.indexOf(getHex)])
//        }

//        fun getDarkColor2(context: Context, color: Int): Int {
//            val colorThemeColor =
//                Arrays.asList<String>(*context.resources.getStringArray(R.array.accent_colors))
//            val colorThemeColor2 =
//                Arrays.asList<String>(*context.resources.getStringArray(R.array.accent_colors_2))
//            val getHex = String.format(
//                "#%02x%02x%02x",
//                Color.red(color),
//                Color.green(color),
//                Color.blue(color)
//            )
//            return Color.parseColor(colorThemeColor2[colorThemeColor.indexOf(getHex)])
//        }

        fun copy(src: File?, dst: File?) {
            try {
                FileInputStream(src).use { `in` ->
                    FileOutputStream(dst).use { out ->
                        // Transfer bytes from in to out
                        val buf = ByteArray(1024)
                        var len: Int
                        while (`in`.read(buf).also { len = it } > 0) {
                            out.write(buf, 0, len)
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun getKeyName(name: String): String {
            val keyName: String
            keyName = when (name) {
                "android.colorCorrection.availableAberrationModes" -> "Aberration Modes"
                "android.control.aeAvailableAntibandingModes" -> "Antibanding Modes"
                "android.control.aeAvailableModes" -> "Auto Exposure Modes"
                "android.control.aeAvailableTargetFpsRanges" -> "Target FPS Ranges"
                "android.control.aeCompensationRange" -> "Compensation Range"
                "android.control.aeCompensationStep" -> "Compensation Step"
                "android.control.aeLockAvailable" -> "Auto Exposure Lock"
                "android.control.afAvailableModes" -> "AutoFocus Modes"
                "android.control.availableEffects" -> "Effects"
                "android.control.availableModes" -> "Available Modes"
                "android.control.availableSceneModes" -> "Scene Modes"
                "android.control.availableVideoStabilizationModes" -> "Video Stabilization Modes"
                "android.control.awbAvailableModes" -> "Auto White Balance Modes"
                "android.control.awbLockAvailable" -> "Auto White Balance Lock"
                "android.control.maxRegionsAe" -> "Max Auto Exposure Regions"
                "android.control.maxRegionsAf" -> "Max Auto Focus Regions"
                "android.control.maxRegionsAwb" -> "Max Auto White Balance Regions"
                "android.edge.availableEdgeModes" -> "Edge Modes"
                "android.flash.info.available" -> "Flash Available"
                "android.hotPixel.availableHotPixelModes" -> "Hot Pixel Modes"
                "android.info.supportedHardwareLevel" -> "Hardware Level"
                "android.jpeg.availableThumbnailSizes" -> "Thumbnail Sizes"
                "android.lens.facing" -> "Lens Placement"
                "android.lens.info.availableApertures" -> "Apertures"
                "android.lens.info.availableFilterDensities" -> "Filter Densities"
                "android.lens.info.availableFocalLengths" -> "Focal Lengths"
                "android.lens.info.availableOpticalStabilization" -> "Optical Stabilization"
                "android.lens.info.focusDistanceCalibration" -> "Focus Distance Calibration"
                "android.lens.info.hyperfocalDistance" -> "Hyperfocal Distance"
                "android.lens.info.minimumFocusDistance" -> "Minimum Focus Distance"
                "android.noiseReduction.availableNoiseReductionModes" -> "Noise Reduction Modes"
                "android.request.availableCapabilities" -> "Camera Capabilities"
                "android.request.maxNumInputStreams" -> "Maximum Input Streams"
                "android.request.maxNumOutputProc" -> "Maximum Output Streams"
                "android.request.maxNumOutputProcStalling" -> "Maximum Output Streams Stalling"
                "android.request.maxNumOutputRaw" -> "Maximum RAW Output Streams"
                "android.request.partialResultCount" -> "Partial Results"
                "android.request.pipelineMaxDepth" -> "Maximum Pipeline Depth"
                "android.scaler.availableMaxDigitalZoom" -> "Maximum Digital Zoom"
                "android.scaler.croppingType" -> "Cropping Type"
                "android.scaler.streamConfigurationMap" -> "Supported Resolutions"
                "android.sensor.availableTestPatternModes" -> "Test Pattern Modes"
                "android.sensor.blackLevelPattern" -> "Black Level Pattern"
                "android.sensor.info.activeArraySize" -> "Active Array Size"
                "android.sensor.info.colorFilterArrangement" -> "Color Filter Arrangement"
                "android.sensor.info.exposureTimeRange" -> "Exposure Time Range"
                "android.sensor.info.maxFrameDuration" -> "Maximum Frame Duration"
                "android.sensor.info.physicalSize" -> "Sensor Size"
                "android.sensor.info.pixelArraySize" -> "Pixel Array Size"
                "android.sensor.info.preCorrectionActiveArraySize" -> "Pre Correction Active Array Size"
                "android.sensor.info.sensitivityRange" -> "Sensitivity Range"
                "android.sensor.info.timestampSource" -> "Timestamp Source"
                "android.sensor.info.whiteLevel" -> "White Level"
                "android.sensor.maxAnalogSensitivity" -> "Maximum Analog Sensitivity"
                "android.sensor.orientation" -> "Orientation"
                "android.shading.availableModes" -> "Shading Modes"
                "android.statistics.info.availableFaceDetectModes" -> "Face Detection Modes"
                "android.statistics.info.availableHotPixelMapModes" -> "Hot Pixel Map Modes"
                "android.statistics.info.availableLensShadingMapModes" -> "Lens Shading Map Modes"
                "android.statistics.info.maxFaceCount" -> "Maximum Faces Detectable"
                "android.sync.maxLatency" -> "Maximum Latency"
                "android.tonemap.availableToneMapModes" -> "Tone Map Modes"
                "android.tonemap.maxCurvePoints" -> "Maximum Curve Points"
                else -> name
            }
            return keyName
        }

//        fun getCameraMP(size: Array<Size>): String {
//            val first: Size = size[0]
//            return if (size.size > 1) {
//                val second: Size = size[size.size - 1]
//                if (first.getWidth() > second.getWidth()) {
//                    getMP(first, 1)
//                } else {
//                    getMP(second, 1)
//                }
//            } else {
//                getMP(first, 1)
//            }
//        }

//        fun getCameraResolution(size: Array<Size>): String {
//            val first: Size = size[0]
//            return if (size.size > 1) {
//                val second: Size = size[size.size - 1]
//                if (first.getWidth() > second.getWidth()) {
//                    first.getWidth() + "x" + first.getHeight()
//                } else {
//                    second.getWidth() + "x" + second.getHeight()
//                }
//            } else {
//                first.getWidth() + "x" + first.getHeight()
//            }
//        }

//        fun getMP(size: Size, decimalPlaces: Int): String {
//            val mp: Float = size.getWidth() * size.getHeight() / 1000000f
//            return if (decimalPlaces == 1) {
//                String.format(Locale.US, "%.1f", mp) + " MP"
//            } else if (decimalPlaces == 2) {
//                String.format(Locale.US, "%.2f", mp) + " MP"
//            } else {
//                String.format(Locale.US, "%.2f", mp) + " MP"
//            }
//        }

        fun getFormattedTemp(zoneValue: String): String {
            var finalTemp: Double
            val `val` = zoneValue.trim { it <= ' ' }.toInt()
            finalTemp = if (`val` >= 10000) {
                `val` / 1000.0
            } else if (`val` >= 1000) {
                `val` / 100.0
            } else if (`val` > 100) {
                `val` / 10.0
            } else {
                `val`.toDouble()
            }
            finalTemp = Math.abs(finalTemp)
            return DecimalFormat("##.#").format(finalTemp) + " \u2103"
        }
    }

}