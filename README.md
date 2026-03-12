# Wireless Alerts Widget

A minimal Android home screen widget that opens the **Wireless Emergency Alerts** settings page in one tap — no more digging through Settings menus.

---

## 📱 What It Does

- Adds a **1×1 widget** to your home screen
- Tap it → instantly opens your Wireless Emergency Alerts settings
- Works on **stock Android (Pixel)**, **Samsung**, and most other Android devices
- Falls back gracefully if the primary intent isn't supported by your device

---

## 🛠 How to Build & Install

### Requirements
- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer)
- Android device or emulator running **Android 8.0+** (API 26+)

### Steps

1. **Open the project**
   - Launch Android Studio
   - Click `File → Open`
   - Select the `WirelessAlertsWidget` folder

2. **Wait for Gradle sync** to complete (bottom status bar)

3. **Run the app**
   - Connect your Android device via USB (enable USB Debugging in Developer Options)
   - Click the green ▶ Run button
   - The app installs silently — no launcher icon appears (by design)

4. **Add the widget to your home screen**
   - Long-press an empty area on your home screen
   - Tap **Widgets**
   - Search for **"Alerts Settings"** or scroll to find it
   - Drag it to your home screen

5. **Tap the widget** → it opens your Wireless Alerts settings directly!

---

## 📂 Project Structure

```
app/src/main/
├── java/com/example/wirelessalertswidget/
│   ├── AlertsWidgetProvider.kt   ← Widget logic
│   └── LauncherActivity.kt       ← Multi-device fallback intent launcher
├── res/
│   ├── layout/widget_layout.xml  ← Widget UI
│   ├── drawable/
│   │   ├── ic_alert.xml          ← Bell icon
│   │   └── widget_background.xml ← Blue rounded background
│   ├── xml/widget_info.xml       ← Widget metadata (size, etc.)
│   └── values/strings.xml
└── AndroidManifest.xml
```

---

## 🔧 Device Compatibility Notes

| Device / Android Version | Intent Used |
|--------------------------|-------------|
| Pixel / Stock Android 10+ | `Settings.ACTION_WIRELESS_EMERGENCY_ALERTS_SETTINGS` |
| Samsung Galaxy | `CellBroadcastSettings` activity |
| Older Samsung | Samsung Messaging CB settings |
| Fallback | General Wireless Settings |

If your device isn't opening the right page, open an issue or adjust the intent in `LauncherActivity.kt`.

---

## ⚠️ Why It Can't "Toggle" Directly

Android intentionally restricts programmatic access to the Emergency Alerts toggle — it requires carrier/system-level permissions. This widget is the best possible solution without rooting your device.
