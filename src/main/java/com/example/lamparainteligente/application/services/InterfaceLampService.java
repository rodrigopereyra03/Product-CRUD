package com.example.lamparainteligente.application.services;

import com.example.lamparainteligente.api.dtos.LampDto;

import java.util.List;

public interface InterfaceLampService {

    public List<LampDto> getLamps();

    public LampDto getLampById(Long id);

    public LampDto createLamp(LampDto lamp);

    public LampDto updateLamp(LampDto lamp);

    public String deleteLamp(Long id);

    public List<LampDto> getLampByName(String keyword);
}
