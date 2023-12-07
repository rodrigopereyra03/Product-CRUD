package com.paygoal.lamparainteligente.api.mappers;

import com.paygoal.lamparainteligente.api.dtos.LampDto;
import com.paygoal.lamparainteligente.domain.models.Lamp;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ILampMapper {
    Lamp LampDtoToLamp(LampDto dto);


    LampDto lampToLampDto(Lamp lamp);

    List<LampDto> toDtoList(List<Lamp> lamps);
}
