package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

/**
 *
 * @author jgomezm
 *
 */
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Crea un nuevo owner
     * @param owner
     * @return owner creado
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Actualiza un owner existente
     * @param owner
     * @return owner actualizado
     */
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Elimina un owner por id
     * @param id
     * @throws OwnerNotFoundException si no existe
     */
    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    /**
     * Busca un owner por id
     * @param id
     * @return owner encontrado
     * @throws OwnerNotFoundException si no existe
     */
    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent())
            throw new OwnerNotFoundException("Record not found...!");

        return owner.get();
    }

    /**
     * Busca owners por apellido (lastName)
     * @param lastName
     * @return lista de owners con ese apellido
     */
    @Override
    public List<Owner> findByLastName(String lastName) {
        List<Owner> owners = ownerRepository.findByLastName(lastName);

        owners.forEach(owner -> log.info("" + owner));

        return owners;
    }

    /**
     * Devuelve todos los owners
     * @return lista de owners
     */
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}

