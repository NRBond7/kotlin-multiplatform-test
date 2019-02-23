package lib

import com.russhwolf.settings.*
import kotlin.properties.ReadWriteProperty

abstract class SettingsRepository(factory: Settings.Factory, settingsKey: String) {

    val settings = factory.create(settingsKey)

    abstract val mySettings: List<SettingConfig<*>>

    fun clear() = settings.clear()
}

sealed class SettingConfig<T>(
        private val settings: Settings,
        val key: String,
        defaultValue: T,
        delegate: Settings.(String, T) -> ReadWriteProperty<Any?, T>,
        private val toType: String.() -> T
) {
    private var value: T by settings.delegate(key, defaultValue)

    fun remove() {
        settings -= key
    }

    fun exists(): Boolean = key in settings
    fun get(): T = value
    fun set(value: String): Boolean {
        return try {
            this.value = value.toType()
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun toString() = key
}

sealed class NullableSettingConfig<T : Any>(
        settings: Settings,
        key: String,
        delegate: Settings.(String) -> ReadWriteProperty<Any?, T?>,
        toType: String.() -> T
) : SettingConfig<T?>(settings, key, null, { it, _ -> delegate(it) }, toType)

class StringSettingConfig(settings: Settings, key: String, defaultValue: String = "") :
        SettingConfig<String>(settings, key, defaultValue, Settings::string, { this })

class IntSettingConfig(settings: Settings, key: String, defaultValue: Int = 0) :
        SettingConfig<Int>(settings, key, defaultValue, Settings::int, String::toInt)

class LongSettingConfig(settings: Settings, key: String, defaultValue: Long = 0) :
        SettingConfig<Long>(settings, key, defaultValue, Settings::long, String::toLong)

class FloatSettingConfig(settings: Settings, key: String, defaultValue: Float = 0f) :
        SettingConfig<Float>(settings, key, defaultValue, Settings::float, String::toFloat)

class DoubleSettingConfig(settings: Settings, key: String, defaultValue: Double = 0.0) :
        SettingConfig<Double>(settings, key, defaultValue, Settings::double, String::toDouble)

class BooleanSettingConfig(settings: Settings, key: String, defaultValue: Boolean = false) :
        SettingConfig<Boolean>(settings, key, defaultValue, Settings::boolean, String::toBoolean)

class NullableStringSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<String>(settings, key, Settings::nullableString, { this })

class NullableIntSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<Int>(settings, key, Settings::nullableInt, String::toInt)

class NullableLongSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<Long>(settings, key, Settings::nullableLong, String::toLong)

class NullableFloatSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<Float>(settings, key, Settings::nullableFloat, String::toFloat)

class NullableDoubleSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<Double>(settings, key, Settings::nullableDouble, String::toDouble)

class NullableBooleanSettingConfig(settings: Settings, key: String) :
        NullableSettingConfig<Boolean>(settings, key, Settings::nullableBoolean, String::toBoolean)