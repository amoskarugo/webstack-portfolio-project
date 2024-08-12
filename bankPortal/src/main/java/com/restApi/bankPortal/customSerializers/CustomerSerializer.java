package com.restApi.bankPortal.customSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.restApi.bankPortal.domain.dto.CustomerDto;
import java.io.IOException;

public class CustomerSerializer extends JsonSerializer<CustomerDto> {
    @Override
    public void serialize(CustomerDto data,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("customer_id", data.getCustomer_id());
        jsonGenerator.writeStringField("first_name", data.getFirst_name());
        jsonGenerator.writeStringField("last_name", data.getLast_name());
        jsonGenerator.writeStringField("email", data.getEmail());
    }

    @Override
    public Class<CustomerDto> handledType()
    {
        return CustomerDto.class;
    }
}
