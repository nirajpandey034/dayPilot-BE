package com.kasamoke.DayPilot.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserModelSerializer extends JsonSerializer<UserModel> {

    @Override
    public void serialize(UserModel userModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", userModel.getId().toString());
        jsonGenerator.writeStringField("name", userModel.getName());
        jsonGenerator.writeStringField("email", userModel.getEmail());
        jsonGenerator.writeEndObject();
    }
}
