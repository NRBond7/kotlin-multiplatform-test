package lib

import com.russhwolf.settings.PlatformSettings
import com.russhwolf.settings.Settings


fun generateSettingsFactory(): Settings.Factory = PlatformSettings.Factory()