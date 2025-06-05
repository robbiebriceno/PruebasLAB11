package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

/**
 *
 * @author jgomezm
 *
 */
public interface OwnerService {

    /**
     * Crea un nuevo owner
     *
     * @param owner
     * @return owner creado
     */
    Owner create(Owner owner);

    /**
     * Actualiza un owner existente
     *
     * @param owner
     * @return owner actualizado
     */
    Owner update(Owner owner);

    /**
     * Elimina un owner por id
     *
     * @param id
     * @throws OwnerNotFoundException si no existe
     */
    void delete(Integer id) throws OwnerNotFoundException;

    /**
     * Busca un owner por id
     *
     * @param id
     * @return owner encontrado
     * @throws OwnerNotFoundException si no existe
     */
    Owner findById(Integer id) throws OwnerNotFoundException;

    /**
     * Busca owners por apellido (lastName)
     *
     * @param lastName
     * @return lista de owners con ese apellido
     */
    List<Owner> findByLastName(String lastName);

    List<Owner> findAll();

    /**
     * Devuelve todos los owners
     * @return lista de owners
     */
}
