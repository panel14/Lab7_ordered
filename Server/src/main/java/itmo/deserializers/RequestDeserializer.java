package itmo.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itmo.interfaces.SimpleRequest;
import itmo.requests.Request;
import itmo.utility.CommandArguments;

import java.io.IOException;
import java.util.Iterator;

public class RequestDeserializer extends StdDeserializer<Request> {

    public RequestDeserializer(){
        this(null);
    }

    public RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Request deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = deserializationContext.readTree(jsonParser);
        Iterator<JsonNode> iterator = jsonNode.elements();
        String typeString = iterator.next().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addDeserializer(CommandArguments.class, new CommandArgumentsDeserializer()));

        Class<? extends SimpleRequest> requestType = objectMapper.readValue(typeString, Class.class);
        String requestString = iterator.next().toString();
        SimpleRequest simpleRequest = objectMapper.readValue(requestString, requestType);

        Request request = new Request(simpleRequest);
        return request;
    }
}
