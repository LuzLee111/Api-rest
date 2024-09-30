package com.example.inicial1.controllers;

import com.example.inicial1.dtos.PersonaDto;
import com.example.inicial1.entities.Persona;
import com.example.inicial1.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins="*") //cualquier origen puede acceder a nuestro datos de ...
//@RequestMapping("/personas")
@RequestMapping(path="api/v1/personas")


public class PersonaController {
 //  @Autowired
//PersonaServices servicio;

    private PersonaServices personaService;
    public PersonaController(PersonaServices personaService){  //dejamos hecho la inyección de la depedencias
        this.personaService= personaService;
    }

    @GetMapping("") //indica el uri específico de este método.
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.findAll());//statua de la respuesta, osea si sale bien o no, sale bien es ok.
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
            //retornar el response entity por si algo sale mal, vamos a tener un status que no encuentra
        }
    }



    @GetMapping("/{id}") //get mapping que recibe id
    public ResponseEntity<?> getOne(@PathVariable Long id){ //variable dentro de path
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Busqué esta persona por Id:" + id);
            //tambien puede ser .body(personaServices.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }





    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Persona entity){

        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Nombre :" + entity.getNombre());
        System.out.println("Nombre :" + entity.getApellido());



        try{
            return ResponseEntity.status(HttpStatus.OK).body("Grabé los datos anteriores");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity){
        System.out.println("EL ID LO TOMO DE LA URL");
        System.out.println("Nombre :" + entity.getId());
        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Nombre :" + entity.getNombre());
        System.out.println("Apellido :" + entity.getApellido());
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Actualicé los datos anteriores");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminé el registro" + id); //no tiene contenido.
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}