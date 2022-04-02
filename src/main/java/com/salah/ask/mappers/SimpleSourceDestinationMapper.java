package com.salah.ask.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class SimpleSourceDestinationMapper {

    @Mapping(source = "specialty", target = "specialization")
    abstract SimpleDestination sourceToDestination(SimpleSource source);

    abstract SimpleSource destinationToSource(SimpleDestination destination);
}
