package com.example.kakaomap.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.example.kakaomap.BuildConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashKey {
    fun migrateSignatures(context: Context) {
        if (BuildConfig.DEBUG) {
            var packageInfo: PackageInfo? = null
            try {
                packageInfo = context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
            assert(packageInfo != null)
            val signatureInfo = packageInfo!!.signingInfo.apkContentsSigners
            for (signature in signatureInfo) {
                try {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                } catch (e: NoSuchAlgorithmException) {
                    Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
                }
            }
        }
    }
}