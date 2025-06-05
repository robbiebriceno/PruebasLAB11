package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OwnerController {

    private OwnerService ownerService;
    private OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    /**
     * Get all owners
     *
     * @return
     */
    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerDTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        log.info("owners: " + owners);
        owners.forEach(item -> log.info("Owner >>  {} ", item));

        List<OwnerDTO> ownersDTO = this.mapper.toOwnerDTOList(owners);
        log.info("ownersDTO: " + ownersDTO);
        ownersDTO.forEach(item -> log.info("OwnerDTO >>  {} ", item));

        return ResponseEntity.ok(ownersDTO);
    }

    /**
     * Create owner
     *
     * @param ownerDTO
     * @return
     */
    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO ownerDTO) {
        Owner newOwner = this.mapper.toOwner(ownerDTO);
        OwnerDTO newOwnerDTO = this.mapper.toOwnerDTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerDTO);
    }

    /**
     * Find owner by id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Integer id) {
        OwnerDTO ownerDTO = null;

        try {
            Owner owner = ownerService.findById(id);
            ownerDTO = this.mapper.toOwnerDTO(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerDTO);
    }

    /**
     * Update owner
     *
     * @param ownerDTO
     * @param id
     * @return
     */
    @PutMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerDTO> update(@RequestBody OwnerDTO ownerDTO, @PathVariable Integer id) {
        OwnerDTO updateOwnerDTO = null;

        try {
            Owner updateOwner = ownerService.findById(id);

            updateOwner.setFirstName(ownerDTO.getFirstName());
            updateOwner.setLastName(ownerDTO.getLastName());
            updateOwner.setCity(ownerDTO.getCity());
            updateOwner.setAddress(ownerDTO.getAddress());
            updateOwner.setTelephone(ownerDTO.getTelephone());

            ownerService.update(updateOwner);

            updateOwnerDTO = this.mapper.toOwnerDTO(updateOwner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateOwnerDTO);
    }

    /**
     * Delete owner by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Delete ID :" + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
