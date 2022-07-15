package com.fake.Restaurant.domain.convert;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertLocalDatetimeToSQL implements AttributeConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(String attribute) {
        return LocalDateTime.parse(attribute,DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String convertToEntityAttribute(LocalDateTime dbData) {
        return dbData.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
