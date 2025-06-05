package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Owner entity
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // Buscar propietarios por nombre
    List<Owner> findByFirstName(String firstName);

    // Buscar propietarios por apellido
    List<Owner> findByLastName(String lastName);

    @Override
    List<Owner> findAll();

}
