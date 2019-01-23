package br.com.cuidebemapp.service.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class Base64Deserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    private Class<?> resultClass;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) throws JsonMappingException {
        this.resultClass = property.getType().getRawClass();
        return this;
    }

    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        String value = parser.getValueAsString();
        

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] decodedValue = Base64Utils.decodeFromString(value);

            return objectMapper.readValue(decodedValue, this.resultClass);
        } catch (IllegalArgumentException | JsonParseException e) {
            String fieldName = parser.getParsingContext().getCurrentName();
            Class<?> wrapperClass = parser.getParsingContext().getCurrentValue().getClass();

            throw new InvalidFormatException(
                parser,
                String.format("Value for '%s' is not a base64 encoded JSON", fieldName),
                value,
                wrapperClass
            );
        }
    }
}
