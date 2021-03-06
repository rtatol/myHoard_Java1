package com.blstream.myhoard.biz.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.blstream.myhoard.biz.model.UserDTO;

public class UserSerializer extends JsonSerializer<UserDTO> {

    @Override
    public void serialize(UserDTO userDTO, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        jg.writeString(userDTO.getId());
    }

}
