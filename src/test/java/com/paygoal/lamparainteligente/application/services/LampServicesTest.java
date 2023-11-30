package com.paygoal.lamparainteligente.application.services;

import com.paygoal.lamparainteligente.api.dtos.LampDto;
import com.paygoal.lamparainteligente.application.services.impl.LampService;
import com.paygoal.lamparainteligente.domain.exceptions.LampNotFoundException;
import com.paygoal.lamparainteligente.domain.models.Lamp;
import com.paygoal.lamparainteligente.infrastructure.repositories.sql.LampSQLRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LampServicesTest {

    @Mock
    private LampSQLRepository repository;

    @InjectMocks
    private LampService service;

    private Lamp lamp;
    private LampDto lampDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        lamp = new Lamp();
        lampDto = new LampDto();
        service = new LampService(repository);
    }

    @Test
    void getLamps(){
        when(repository.findAll()).thenReturn(Collections.singletonList(lamp));
        assertNotNull(service.getLamps());
    }
    @Test
    void getLampById(){
        Long id = 1L;
        lamp.setId(id);
        when(repository.findById(id)).thenReturn(lamp);
        assertNotNull(service.getLampById(id));
    }

    @Test
    void testCreateLamp(){
        when(repository.save(any(Lamp.class))).thenReturn(new Lamp());
        assertNotNull(service.createLamp(lampDto));
    }

    @Test
    void testUpdateLamp(){
        Lamp existingLamp =new Lamp();
        existingLamp.setId(1L);
        existingLamp.setName("OldName");
        existingLamp.setDescription("OldDescription");
        existingLamp.setPrice(50.0);
        existingLamp.setAmount(2);
        when(repository.findById(1L)).thenReturn(existingLamp);
        when(repository.save(existingLamp)).thenReturn(existingLamp);

        lampDto.setId(1L);
        lampDto.setName("NewName");
        lampDto.setDescription("NewDescription");
        lampDto.setPrice(123.45);
        lampDto.setAmount(1234);
        LampDto result =service.updateLamp(lampDto);

        assertEquals(lampDto.getName(), result.getName());
        assertEquals(lampDto.getDescription(), result.getDescription());
        assertEquals(lampDto.getPrice(), result.getPrice());
        assertEquals(1234,result.getAmount());
        verify(repository, times(1)).save(any(Lamp.class));
    }

    @Test
    void testUpdateLampWithNotExistingLamp(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);
        assertThrows(LampNotFoundException.class,()->service.updateLamp(lampDto));
    }

    @Test
    void testDeleteLamp(){
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        String result =service.deleteLamp(id);
        assertEquals("The Lamp has been successfully deleted",result);
        verify(repository,times(1)).deleteById(id);
    }
    @Test
    void testDeleteLampWhenLampNotExists(){
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);
        assertThrows(LampNotFoundException.class,()->service.deleteLamp(id));
    }

    @Test
    void testGetByName(){
        String name = "Lampara";
        lamp.setName(name);
        List<Lamp> lamps = Arrays.asList(new Lamp());
        when(repository.search(name)).thenReturn(lamps);
        List<LampDto> lampDtos =service.getLampByName(name);
        assertNotNull(lampDtos);
    }
}
