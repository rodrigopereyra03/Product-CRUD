package com.example.lamparainteligente.application.services.impl;

import com.example.lamparainteligente.api.dtos.LampDto;
import com.example.lamparainteligente.domain.exceptions.LampNotFoundException;
import com.example.lamparainteligente.api.mappers.LampMapper;
import com.example.lamparainteligente.domain.models.Lamp;
import com.example.lamparainteligente.infrastructure.repositories.ILampRepository;
import com.example.lamparainteligente.application.services.InterfaceLampService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LampService implements InterfaceLampService {

    private final ILampRepository repository;

    public LampService(ILampRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LampDto> getLamps() {
        List<Lamp> lamps = repository.findAll();
        return lamps.stream()
                .map(LampMapper::lampToDto)
                .toList();
    }

    @Override
    public LampDto getLampById(Long id) {
        return LampMapper.lampToDto(repository.findById(id));
    }

    @Override
    public LampDto createLamp(LampDto lamp) {
        return LampMapper.lampToDto(repository.save(LampMapper.dtoToLamp(lamp)));
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
            return LampMapper.lampToDto(saved);
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
        return LampMapper.toDtoList(lamps);
    }

    public List<LampDto> getAllLampsOrderByPrice() {
        List<Lamp> lamps = repository.findAllByOrderByPrice();
        return lamps.stream()
                .map(LampMapper::lampToDto)
                .toList();
    }

}
