package com.example.wirelessalertswidget

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Try intents in order of preference
        val intents = listOf(
            // Android 10+ standard
            Intent(Settings.ACTION_WIRELESS_EMERGENCY_ALERTS_SETTINGS),
            // Samsung / older devices
            Intent().apply {
                setClassName("com.android.phone", "com.android.phone.settings.CellBroadcastSettings")
            },
            // Another Samsung variant
            Intent().apply {
                setClassName("com.samsung.android.messaging", "com.samsung.android.messaging.ui.setting.CbSettingActivity")
            },
            // Generic cell broadcast
            Intent().apply {
                action = "android.provider.Telephony.ACTION_EMERGENCY_ALERTS_SETTINGS"
            },
            // Ultimate fallback - general wireless settings
            Intent(Settings.ACTION_WIRELESS_SETTINGS)
        )

        var opened = false
        for (intent in intents) {
            try {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                opened = true
                break
            } catch (e: ActivityNotFoundException) {
                continue
            } catch (e: SecurityException) {
                continue
            }
        }

        if (!opened) {
            Toast.makeText(
                this,
                "Could not open Wireless Alerts settings on this device. Try: Settings → Apps → Emergency Alerts",
                Toast.LENGTH_LONG
            ).show()
        }

        finish()
    }
}
