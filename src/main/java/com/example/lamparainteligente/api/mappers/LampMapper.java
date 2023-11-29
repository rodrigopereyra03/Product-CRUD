package com.example.lamparainteligente.api.mappers;

import com.example.lamparainteligente.api.dtos.LampDto;
import com.example.lamparainteligente.domain.models.Lamp;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class LampMapper {
    //Esta clase permite pasar los datos desde una entidad hacia un dto o visceversa
    public Lamp dtoToLamp(LampDto dto){
        Lamp lamp = new Lamp();
        lamp.setName(dto.getName());
        lamp.setDescription(dto.getDescription());
        lamp.setPrice(dto.getPrice());
        lamp.setAmount(dto.getAmount());
        return lamp;
    }

    public LampDto lampToDto(Lamp lamp){
        LampDto dto = new LampDto();
        dto.setName(lamp.getName());
        dto.setDescription(lamp.getDescription());
        dto.setPrice(lamp.getPrice());
        dto.setAmount(lamp.getAmount());
        dto.setId(lamp.getId());
        return dto;
    }

    public List<LampDto> toDtoList(List<Lamp> lamps){
        return lamps.stream().map(LampMapper::lampToDto).toList();
    }
}
