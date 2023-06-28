package com.example.aivoiceassistant.features

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun makeCall(context: Context, number:String) {
    if((number.length==11 && number[0]!='0') || number.length>11 || number.length<10){
        Toast.makeText(
            context,
            "Invalid phone number",
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    val phoneNum = number// the number for testing
    var i = Intent(Intent.ACTION_DIAL)
    i.data = Uri.parse("tel:$phoneNum")
    if (i.resolveActivity(context.packageManager) != null) { //the
        i?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }
//    val TELEPHONE_SCHEMA = "tel:"
//    val PRESERVED_CHARACTER = "+"
//    val HK_COUNTRY_CODE = "852"
//    val HK_OBSERVATORY_PHONE_NUMBER = "1878200"
//
//// Step 1: Define the phone call uri / Step 2: Define the phone call uri
//                val phoneCallUri =
//                    Uri.parse(TELEPHONE_SCHEMA + PRESERVED_CHARACTER + HK_COUNTRY_CODE + HK_OBSERVATORY_PHONE_NUMBER)
//
//                // Step 3: Set Intent action to `ACTION_CALL`
//                val phoneCallIntent = Intent(Intent.ACTION_CALL).also {
//                    // Step 4. Set phone call uri to Intent data
//                    it.setData(phoneCallUri)
//                }
//
//                // Step 5: Pass the Intent to System to start any <Activity> which can accept `ACTION_CALL`
//    phoneCallIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                context.startActivity(phoneCallIntent)
}