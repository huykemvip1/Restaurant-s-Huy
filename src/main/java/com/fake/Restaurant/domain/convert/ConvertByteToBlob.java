package com.fake.Restaurant.domain.convert;

import javax.persistence.AttributeConverter;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

public class ConvertByteToBlob implements AttributeConverter<byte[], Blob> {

    @Override
    public Blob convertToDatabaseColumn(byte[] attribute) {
        try {
            return new SerialBlob(attribute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] convertToEntityAttribute(Blob dbData) {
        try {
            return dbData.getBytes(1L, (int) dbData.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
