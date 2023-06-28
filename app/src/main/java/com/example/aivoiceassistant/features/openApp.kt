package com.example.aivoiceassistant.features

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast

fun getPackageNameFromAppName(context:Context,appName: String): String? {
//    val context = LocalContext.current
    val packageManager: PackageManager = context.packageManager
    val packages = packageManager.getInstalledPackages(0)

    val packageNames = packages.mapNotNull { packageInfo ->
        packageInfo.packageName
    }
    Log.d("package : ",packageNames.toString())
    val map: Map<String, String> = mapOf(
        "whatsapp" to "com.whatsapp",
        "facebook" to "com.facebook.katana",
        "instagram" to "com.instagram.android",
        "twitter" to "com.twitter.android",
        "snapchat" to "com.snapchat.android",
        "linkedin" to "com.linkedin.android",
        "youtube" to "com.vanced.android.youtube",
        "spotify" to "com.spotify.music",
        "netflix" to "com.netflix.mediaclient",
        "amazon" to "in.amazon.mShop.android.shopping",
        "flipkart" to "com.flipkart.android",
        "google" to "com.google.android.googlequicksearchbox",
        "chrome" to "com.android.chrome",
        "settings" to "com.android.settings",
        "calculator" to "com.google.android.calculator",
        "camera" to "org.codeaurora.snapcam",
        "gallery" to "com.google.android.apps.photos",
        "clock" to "com.google.android.deskclock",
        "calendar" to "com.google.android.calendar",
        "contacts" to "com.google.android.contacts",
        "messages" to "com.google.android.apps.messaging",
        "phone" to "com.google.android.dialer",
        "gmail" to "com.google.android.gm",
        "maps" to "com.google.android.apps.maps",
        "drive" to "com.google.android.apps.docs",
        "files" to "com.google.android.apps.nbu.files",
        "music" to "com.google.android.apps.youtube.music",
        "playstore" to "com.android.vending",
        "paytm" to "net.one97.paytm"
    )
    return map[appName]
//    return null
}

fun openAnotherApp(context: Context,appName:String) {
    val packageName = getPackageNameFromAppName(context,appName)
    Log.d("packageName",packageName.toString())

    var intent = context.packageManager.getLaunchIntentForPackage(packageName.toString())
    if (intent == null) {
        // Bring user to the market or let them choose an app?
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=${packageName.toString()}")
        Toast.makeText(
            context,
            "No App Found",
            Toast.LENGTH_SHORT
        ).show()
    }
    intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}