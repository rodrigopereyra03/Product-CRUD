package com.example.lamparainteligente.infrastructure.repositories;

import com.example.lamparainteligente.domain.models.Lamp;

import java.util.List;

public interface ILampRepository {

    List<Lamp> findAll();

    Lamp findById(Long id);

    Lamp save (Lamp lamp);

    void deleteById(Long id);

    Boolean existsById(Long id);

    List<Lamp> search(String keyword);

}
