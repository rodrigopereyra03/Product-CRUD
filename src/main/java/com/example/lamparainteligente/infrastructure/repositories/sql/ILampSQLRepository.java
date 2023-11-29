package com.example.lamparainteligente.infrastructure.repositories.sql;

import com.example.lamparainteligente.domain.models.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILampSQLRepository extends JpaRepository<Lamp, Long> {

    @Query(value = "SELECT l FROM Lamp l WHERE lower(l.name) LIKE :keyword%")
    List<Lamp> search(@Param("keyword")String keyword);
}
