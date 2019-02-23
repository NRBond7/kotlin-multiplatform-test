package lib

import android.content.Context
import com.russhwolf.settings.PlatformSettings

fun generateSettingsFactory(context: Context) = PlatformSettings.Factory(context)