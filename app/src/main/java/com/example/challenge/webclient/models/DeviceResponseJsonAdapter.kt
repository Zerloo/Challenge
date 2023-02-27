import com.example.challenge.webclient.models.DeviceResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException

class DeviceResponseJsonAdapter : JsonAdapter<DeviceResponse>() {

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): DeviceResponse {
        var id: String? = null
        var name: String? = null
        var type: String? = null
        var mac: String? = null
        var favorite: String? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "id" -> id = reader.nextString()
                "name" -> name = reader.nextString()
                "type" -> type = reader.nextString()
                "mac" -> mac = reader.nextString()
                "favorite" -> favorite = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return DeviceResponse(id, name, type, mac, favorite)
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: DeviceResponse?) {
        writer.beginObject()
        writer.name("id").value(value?.id)
        writer.name("name").value(value?.name)
        writer.name("type").value(value?.type)
        writer.name("mac").value(value?.mac)
        writer.name("favorite").value(value?.favorite)
        writer.endObject()
    }
}
