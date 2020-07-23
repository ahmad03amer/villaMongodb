package com.exalt.villa.dto.mapper;

import com.exalt.villa.dto.VillaDto;
import com.exalt.villa.model.Villa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VillaMapper {
    VillaMapper INSTANCE = Mappers.getMapper( VillaMapper.class );

    VillaDto villaToDto(Villa villa);
    Villa dtoToVilla(VillaDto villaDto);
}
