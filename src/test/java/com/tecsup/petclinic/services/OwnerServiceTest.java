package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
    private OwnerService ownerService;

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "pashita";
		String LAST_NAME = "ruiz";
		String ADDRESS = "Lima";
		String CITY = "Lima";
		String TELEPHONE = "942 574 612";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		log.info("OWNER CREATED: " + createdOwner);

		assertNotNull(createdOwner.getId());
		assertEquals(FIRST_NAME, createdOwner.getFirstName());
		assertEquals(LAST_NAME, createdOwner.getLastName());
		assertEquals(ADDRESS, createdOwner.getAddress());
		assertEquals(CITY, createdOwner.getCity());
		assertEquals(TELEPHONE, createdOwner.getTelephone());
	}
	@Test
	public void testFindOwnerById() {
		Integer ID = 1;
		String FIRST_NAME_EXPECTED = "pashita";

		Owner owner = null;
		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(FIRST_NAME_EXPECTED, owner.getFirstName());
	}


	@Test
	public void testDeleteOwner() throws OwnerNotFoundException {
		// Primero creamos un owner para luego eliminarlo
		String FIRST_NAME = "pashita";
		String LAST_NAME = "ruiz";
		String ADDRESS = "Lima";
		String CITY = "Lima";
		String TELEPHONE = "942 574 612";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		// Obtenemos el ID del owner creado
		Integer ownerId = createdOwner.getId();

		// Eliminamos el owner
		ownerService.delete(ownerId);

		// Verificamos que ya no exista en la base de datos
		assertThrows(OwnerNotFoundException.class, () -> {
			ownerService.findById(ownerId);
		});

		log.info("Owner eliminado correctamente - No se encontró el owner con ID: " + ownerId);
	}

}
