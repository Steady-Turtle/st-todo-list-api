package com.example.anna.common._common;

import org.modelmapper.ModelMapper;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;


public class AnnaModelMapper {
    private final ModelMapper modelMapper;

    public AnnaModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D> D convert(Object source, Class<D> classLiteral) {
        return modelMapper.map(source, classLiteral);
    }

    public <D, E> List<D> convert(Collection<E> sourceList, Type type) {
        return modelMapper.map(sourceList, type);
    }
}
