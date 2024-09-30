package com.example.inicial1.services;

import com.example.inicial1.entities.Persona;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServices implements BaseService <Persona> {

    private PersonaRepository personaRepository;

    public PersonaServices(PersonaRepository personaRepository){
        this.personaRepository= personaRepository;
    }


    @Override
    @Transactional
    public List<Persona> findAll() throws Exception {
        try{
            List<Persona> entities= personaRepository.findAll(); //optener en bases de datos todas las personas que tenemos
            return entities;
        }catch(Exception e){
            throw new Exception(e.getMessage()); //enviar el mensaje de excepcion, es capturado en controlador.
        }
    }

    @Override
    @Transactional
    public Persona findById(Long id) throws Exception {
        try{
            Optional<Persona> entityOptional=personaRepository.findById(id); //no sabemos si en bases de datos encontramos uuna entidad que tenga ese id como clave primaria.
            return entityOptional.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try{
            entity =personaRepository.save(entity); //usa repositorio en save.
            return entity;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try{
            Optional<Persona> entityOptional= personaRepository.findById(id);
            Persona persona= entityOptional.get();
            persona= personaRepository.save(entity);
            return persona;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(personaRepository.existsById(id)){ //usamos el if para ver si existe una persona con id que indicamos.
                personaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
