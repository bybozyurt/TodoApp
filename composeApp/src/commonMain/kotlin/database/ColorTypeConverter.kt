package database

import androidx.room.TypeConverter
import domain.model.ColorType

class ColorTypeConverter {

    @TypeConverter
    fun fromColorType(colorType: ColorType): String {
        return colorType.name
    }

    @TypeConverter
    fun toColorType(value: String): ColorType {
        return ColorType.valueOf(value)
    }
}