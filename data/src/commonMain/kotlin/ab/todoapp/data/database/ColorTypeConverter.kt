package ab.todoapp.data.database

import androidx.room.TypeConverter
import ab.todoapp.domain.model.ColorType

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