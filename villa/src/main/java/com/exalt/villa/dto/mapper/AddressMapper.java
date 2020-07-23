package com.exalt.villa.dto.mapper;

import com.exalt.villa.dto.AddressDto;
import com.exalt.villa.model.Address;
import org.mapstruct.factory.Mappers;

public interface AddressMapper {
    VillaMapper INSTANCE = Mappers.getMapper( VillaMapper.class );

    AddressDto villaToDto(Address address);
    Address dtoToVilla(AddressDto addressDto);
}
