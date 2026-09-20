package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.config.CommandoFitConfig
import java.net.URLEncoder

object GymIntents {

    fun dialPhone(context: Context, phoneNumber: String = CommandoFitConfig.PHONE_DIAL) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open phone dialer: $phoneNumber", Toast.LENGTH_LONG).show()
        }
    }

    fun openDirections(context: Context, query: String = CommandoFitConfig.FULL_ADDRESS) {
        try {
            // Try geo URI first
            val encodedQuery = Uri.encode(query)
            val gmmIntentUri = Uri.parse("geo:0,0?q=$encodedQuery")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                setPackage("com.google.android.apps.maps")
            }
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            } else {
                // Fallback to browser Google Maps
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedQuery"))
                context.startActivity(webIntent)
            }
        } catch (e: Exception) {
            try {
                val encodedQuery = Uri.encode(query)
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedQuery"))
                context.startActivity(webIntent)
            } catch (fallbackError: Exception) {
                Toast.makeText(context, "Unable to open maps: $query", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun openWhatsApp(
        context: Context,
        phone: String = CommandoFitConfig.WHATSAPP_PHONE_NUMBER,
        message: String = CommandoFitConfig.WHATSAPP_DEFAULT_MESSAGE
    ) {
        if (!CommandoFitConfig.IS_WHATSAPP_CONFIRMED) {
            Toast.makeText(context, "WhatsApp availability is not yet confirmed by gym management.", Toast.LENGTH_LONG).show()
            return
        }
        try {
            val encodedMsg = URLEncoder.encode(message, "UTF-8")
            val url = "https://api.whatsapp.com/send?phone=$phone&text=$encodedMsg"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp not installed or unable to launch.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWebUrl(context: Context, url: String) {
        try {
            if (url.startsWith("[Add") || url.isEmpty()) {
                Toast.makeText(context, "Link placeholder: $url", Toast.LENGTH_SHORT).show()
                return
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open link: $url", Toast.LENGTH_SHORT).show()
        }
    }
}
