package com.lucianaugusto.ebanxassignment.event.http.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EventHttpResponseSerializer extends JsonSerializer<EventHttpResponse> {
    @Override
    public void serialize(
            EventHttpResponse response,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();

        if (response.origin() != null) {
            jsonGenerator.writeRaw("\"origin\":");
            serializeEventAccountInfo(response.origin(), jsonGenerator);
        }

        if (response.origin() != null && response.destination() != null) {
            jsonGenerator.writeRaw(", ");
        }

        if (response.destination() != null) {
            jsonGenerator.writeRaw("\"destination\":");
            serializeEventAccountInfo(response.destination(), jsonGenerator);
        }

        jsonGenerator.writeEndObject();
    }

    private void serializeEventAccountInfo(EventAccountInfo info, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(" {");
        jsonGenerator.writeRaw("\"id\":\"" + info.id() + "\"");
        jsonGenerator.writeRaw(", ");
        jsonGenerator.writeRaw("\"balance\":" + info.balance());
        jsonGenerator.writeRaw("}");
    }
}
