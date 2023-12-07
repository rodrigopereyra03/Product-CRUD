package com.paygoal.lamparainteligente.application.services.impl;

import com.paygoal.lamparainteligente.api.dtos.LampDto;
import com.paygoal.lamparainteligente.api.mappers.ILampMapper;
import com.paygoal.lamparainteligente.api.mappers.ILampMapperImpl;
import com.paygoal.lamparainteligente.domain.exceptions.LampNotFoundException;
import com.paygoal.lamparainteligente.domain.models.Lamp;
import com.paygoal.lamparainteligente.infrastructure.repositories.ILampRepository;
import com.paygoal.lamparainteligente.application.services.InterfaceLampService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LampService implements InterfaceLampService {

    private final ILampRepository repository;
    private final ILampMapper mapper;

    public LampService(ILampRepository repository, ILampMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LampDto> getLamps() {
        List<Lamp> lamps = repository.findAll();
        return lamps.stream()
                .map(mapper::lampToLampDto)
                .toList();
    }

    @Override
    public LampDto getLampById(Long id) {
        return mapper.lampToLampDto(repository.findById(id));
    }

    @Override
    public LampDto createLamp(LampDto lamp) {
        return mapper.lampToLampDto(repository.save(mapper.LampDtoToLamp(lamp)));
    }

    @Override
    public LampDto updateLamp(LampDto lamp) {
        Optional<Lamp> lampCreated = Optional.ofNullable(repository.findById(lamp.getId()));

        if(lampCreated.isPresent()){
            Lamp entity = lampCreated.get();
            if(lamp.getName()!=null){
                entity.setName(lamp.getName());
            }
            if(lamp.getDescription()!=null){
                entity.setDescription(lamp.getDescription());
            }
            if(lamp.getPrice()!=0){
                entity.setPrice(lamp.getPrice());
            }
            if(lamp.getAmount()!=0){
                entity.setAmount(lamp.getAmount());
            }
            Lamp saved =repository.save(entity);
            return mapper.lampToLampDto(saved);
        }else{
            throw new LampNotFoundException("Lamp not found with id: +");
        }
    }

    @Override
    public String deleteLamp(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "The Lamp has been successfully deleted";
        }else {
            throw new LampNotFoundException("Lamp not found with id: " + id);
        }
    }

    @Override
    public List<LampDto> getLampByName(String keyword) {
        List<Lamp> lamps = repository.search(keyword.toLowerCase());
        return mapper.toDtoList(lamps);
    }

    @Override
    public List<LampDto> getAllLampsOrderByPrice() {
        List<Lamp> lamps = repository.findAllByOrderByPrice();
        return lamps.stream()
                .map(mapper::lampToLampDto)
                .toList();
    }

}
