package com.paygoal.lamparainteligente.application.services;

import com.paygoal.lamparainteligente.api.dtos.LampDto;
import com.paygoal.lamparainteligente.api.mappers.ILampMapper;
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
import static org.mockito.Mockito.*;

public class LampServicesTest {

    @Mock
    private LampSQLRepository repository;

    @Mock
    private ILampMapper mapper;

    @InjectMocks
    private LampService service;

    private Lamp lamp;
    private LampDto lampDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        lamp = new Lamp();
        lampDto = new LampDto();
        mapper = mock(ILampMapper.class);
        service = new LampService(repository, mapper);
    }

    @Test
    void getLamps(){
        when(repository.findAll()).thenReturn(Collections.singletonList(lamp));
        assertNotNull(service.getLamps());
    }
    @Test
    void getLampById(){
        Long id = 1L;
        Lamp lamp = new Lamp();
        LampDto lampDto = new LampDto();


        when(repository.findById(id)).thenReturn(lamp);


        when(mapper.lampToLampDto(lamp)).thenReturn(lampDto);

        // Ejecuta el método
        LampDto result = service.getLampById(id);

        // Assertions
        assertNotNull(result);
        assertEquals(lampDto, result);


        verify(mapper, times(1)).lampToLampDto(lamp);
    }

    @Test
    void testCreateLamp(){
        // Datos de prueba
        LampDto lampDto = new LampDto();
        Lamp lamp = new Lamp();
        Lamp savedLamp = new Lamp();
        LampDto savedLampDto = new LampDto();

        // Configura el comportamiento del mapper
        when(mapper.LampDtoToLamp(lampDto)).thenReturn(lamp);
        when(mapper.lampToLampDto(savedLamp)).thenReturn(savedLampDto);

        // Configura el comportamiento del repositorio
        when(repository.save(lamp)).thenReturn(savedLamp);

        // Ejecuta el método
        LampDto result = service.createLamp(lampDto);

        // Assertions
        assertNotNull(result);
        assertEquals(savedLampDto, result);

        // Verifica que el método del mapper se haya llamado una vez
        verify(mapper, times(1)).LampDtoToLamp(lampDto);
        verify(mapper, times(1)).lampToLampDto(savedLamp);
        // Verifica que el método save del repositorio se haya llamado una vez
        verify(repository, times(1)).save(lamp);
    }

    @Test
    void testUpdateLamp(){
        // Datos de prueba
        Long id = 1L;
        LampDto lampDto = new LampDto(id, "Updated Lamp", "Updated Description", 20.0, 5);
        Lamp existingLamp = new Lamp();
        Lamp updatedLamp = new Lamp();
        LampDto updatedLampDto = new LampDto();

        // Configura el comportamiento del mapper
        when(mapper.lampToLampDto(updatedLamp)).thenReturn(updatedLampDto);

        // Configura el comportamiento del repositorio
        when(repository.findById(id)).thenReturn(existingLamp);
        when(repository.save(any())).thenReturn(updatedLamp);

        // Ejecuta el método
        LampDto result = service.updateLamp(lampDto);

        // Assertions
        assertNotNull(result);
        assertEquals(updatedLampDto, result);

        // Verifica que el método findById del repositorio se haya llamado una vez
        verify(repository, times(1)).findById(id);
        // Verifica que el método save del repositorio se haya llamado una vez
        verify(repository, times(1)).save(any());
        // Verifica que el método lampToLampDto del mapper se haya llamado una vez
        verify(mapper, times(1)).lampToLampDto(updatedLamp);
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

    @Test
    void testGetAllLampsOrderByPrice(){
        // Datos de prueba
        List<Lamp> mockedLamps = Arrays.asList(
                new Lamp(),
                new Lamp()
                // ... más lámparas ...
        );

        List<LampDto> expectedLampDtos = Arrays.asList(
                new LampDto(),
                new LampDto()
                // ... más lámparas ...
        );

        // Configura el comportamiento del repositorio
        when(repository.findAllByOrderByPrice()).thenReturn(mockedLamps);

        // Configura el comportamiento del mapper
        when(mapper.lampToLampDto(any())).thenAnswer(invocation -> {
            Lamp lamp = invocation.getArgument(0);
            // Simula la lógica de mapeo
            return new LampDto(/* mapped details based on lamp */);
        });

        // Ejecuta el método
        List<LampDto> result = service.getAllLampsOrderByPrice();

        // Assertions
        assertNotNull(result);
        assertEquals(expectedLampDtos.size(), result.size());

        // Verifica que los detalles de cada LampDto sean iguales
        for (int i = 0; i < expectedLampDtos.size(); i++) {
            assertEquals(expectedLampDtos.get(i), result.get(i));
        }

        // Verifica que el método findAllByOrderByPrice del repositorio se haya llamado una vez
        verify(repository, times(1)).findAllByOrderByPrice();
        // Verifica que el método lampToLampDto del mapper se haya llamado por cada lámpara
        verify(mapper, times(mockedLamps.size())).lampToLampDto(any());

    }
}
