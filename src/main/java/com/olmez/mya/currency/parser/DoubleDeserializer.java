package com.olmez.mya.currency.parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.olmez.mya.model.CurrencyRate;

/**
 * Prevents values (Double) from being printed in scientific notation like
 * "7.514674232048524E-4" in JSON files.
 * <p>
 * It is used with @JsonSerialize(using = DoubleDeserializer.class) notation on
 * the related class or field. {@link CurrencyRate}.
 */
public class DoubleDeserializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator generator, SerializerProvider serializers)
            throws IOException {
        BigDecimal bdValue = BigDecimal.valueOf(value).setScale(5, RoundingMode.HALF_UP);
        generator.writeNumber(bdValue.toPlainString());
    }

}