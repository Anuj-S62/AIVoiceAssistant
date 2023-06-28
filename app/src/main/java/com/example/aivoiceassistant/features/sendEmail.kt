package com.example.aivoiceassistant.features
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun sendEmial(context: Context,mailID:String){
    val emailIntent = Intent().apply {
        action = Intent.ACTION_SENDTO
        data = Uri.parse("mailto:$mailID")
    }
    emailIntent.setPackage("com.google.android.gm")
    if (emailIntent.resolveActivity(context.packageManager) != null) {
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(emailIntent)
    } else {
        Toast.makeText(
            context,
            "No app available to send email!!",
            Toast.LENGTH_SHORT
        ).show()
    }

}
