package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new OwnerNotFoundException("Owner not found with id: " + id);
        }
        return owner.get();
    }

    public List<Owner> findByFirstName(String firstName) {
        return ownerRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner update(Owner owner) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(owner.getId())) {
            throw new OwnerNotFoundException("Owner not found with id: " + owner.getId());
        }
        return ownerRepository.save(owner);
    }

    public void delete(Integer id) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(id)) {
            throw new OwnerNotFoundException("Owner not found with id: " + id);
        }
        ownerRepository.deleteById(id);
    }

}
