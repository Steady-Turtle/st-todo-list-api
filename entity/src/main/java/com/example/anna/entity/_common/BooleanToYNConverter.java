package com.example.anna.entity._common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * DB data 'Y/N' 과 객체 boolean 컨버터
 * 글로벌 설정
 */
@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, Character> {
    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 'Y' : 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        if(dbData == null) {
            return false;
        }
        return dbData == 'Y';
    }
}
