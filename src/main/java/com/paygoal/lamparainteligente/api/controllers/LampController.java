package com.paygoal.lamparainteligente.api.controllers;

import com.paygoal.lamparainteligente.api.dtos.LampDto;
import com.paygoal.lamparainteligente.application.services.InterfaceLampService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LampController {
    private final InterfaceLampService service;

    public LampController(InterfaceLampService service) {
        this.service = service;
    }

    //Metodos HTTP

    //Metodo GET
    @GetMapping(value = "/lamps")
    public ResponseEntity<List<LampDto>> getLamps(){
        //1)Obtener todas las lamparas de la BD
        List<LampDto> lamps = service.getLamps();
        //2)Devolver la lista y enviar coomo respuesta
        return ResponseEntity.status(HttpStatus.OK).body(lamps);
    }

    //POST
    @PostMapping(value = "/lamps")
    public ResponseEntity<LampDto> createLamp(@RequestBody LampDto dto){
        //Redirije hacia el responsable de crear una lampara
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLamp(dto));
    }

    //PUT
    @PutMapping(value = "/lamps")
    public ResponseEntity<LampDto> updateLamp(@RequestBody LampDto lampDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateLamp(lampDto));
    }

    //DELETE
    @DeleteMapping(value = "/lamps/{id}")
    public ResponseEntity<String> deleteLamp (@PathVariable Long id){
        String result = service.deleteLamp(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //Busqueda por ID
    @GetMapping(value = "/lamps/{id}")
    public ResponseEntity<LampDto> getLampById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getLampById(id));
    }

    //Buscqueda por nombre
    @GetMapping(value = "/lamps/getBy")
    public ResponseEntity<List<LampDto>> getLampByName(@RequestParam String keyword){
        List<LampDto> lampDtos = service.getLampByName(keyword);
        if(!lampDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(lampDtos);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Ordenar productos por precio
    @GetMapping(value = "/lamps/getAllByOrder")
    public ResponseEntity<List<LampDto>> getAllLampsByOrdeByrPrice(){
        return  ResponseEntity.status(HttpStatus.OK).body(service.getAllLampsOrderByPrice());

    }

}
