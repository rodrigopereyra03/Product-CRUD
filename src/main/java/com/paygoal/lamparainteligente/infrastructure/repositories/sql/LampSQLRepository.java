package com.paygoal.lamparainteligente.infrastructure.repositories.sql;

import com.paygoal.lamparainteligente.domain.exceptions.LampNotFoundException;
import com.paygoal.lamparainteligente.domain.models.Lamp;
import com.paygoal.lamparainteligente.infrastructure.repositories.ILampRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LampSQLRepository implements ILampRepository {

    private final ILampSQLRepository sqlRepository;

    public LampSQLRepository(ILampSQLRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }


    @Override
    public List<Lamp> findAll() {
        return sqlRepository.findAll();
    }

    @Override
    public Lamp findById(Long id) {
        Optional<Lamp> lamp = sqlRepository.findById(id);
        if(lamp.isEmpty()){
            throw new LampNotFoundException("Lamp not found with id: "+id);
        }
        return lamp.get();
    }

    @Override
    public Lamp save(Lamp lamp) {
        return sqlRepository.save(lamp);
    }

    @Override
    public void deleteById(Long id) {
        sqlRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return sqlRepository.existsById(id);
    }

    @Override
    public List<Lamp> search(String keyword) {
        return sqlRepository.search(keyword);
    }

    @Override
    public List<Lamp> findAllByOrderByPrice() {
        return sqlRepository.findAllByOrderByPrice();
    }


}
