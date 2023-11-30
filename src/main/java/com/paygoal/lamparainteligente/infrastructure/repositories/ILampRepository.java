package com.paygoal.lamparainteligente.infrastructure.repositories;

import com.paygoal.lamparainteligente.domain.models.Lamp;

import java.util.List;

public interface ILampRepository {

    List<Lamp> findAll();

    Lamp findById(Long id);

    Lamp save (Lamp lamp);

    void deleteById(Long id);

    Boolean existsById(Long id);

    List<Lamp> search(String keyword);

    List<Lamp> findAllByOrderByPrice();

}
