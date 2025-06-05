package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	/**
	 * Test para crear un nuevo Owner
	 */
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

	/**
	 * Test para actualizar un Owner existente
	 */
	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "Juan";
		String LAST_NAME = "Perez";
		String ADDRESS = "Av. Siempre Viva";
		String CITY = "Lima";
		String TELEPHONE = "987654321";

		String UP_FIRST_NAME = "Pedro";
		String UP_LAST_NAME = "Gomez";
		String UP_ADDRESS = "Av. Los Álamos";
		String UP_CITY = "Cusco";
		String UP_TELEPHONE = "999888777";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		// Actualizamos los datos
		createdOwner.setFirstName(UP_FIRST_NAME);
		createdOwner.setLastName(UP_LAST_NAME);
		createdOwner.setAddress(UP_ADDRESS);
		createdOwner.setCity(UP_CITY);
		createdOwner.setTelephone(UP_TELEPHONE);

		Owner updatedOwner = null;
		try {
			updatedOwner = ownerService.update(createdOwner);
		} catch (OwnerNotFoundException e) {
			fail("No se encontró el owner para actualizar: " + e.getMessage());
		}

		log.info("OWNER UPDATED: " + updatedOwner);

		assertEquals(UP_FIRST_NAME, updatedOwner.getFirstName());
		assertEquals(UP_LAST_NAME, updatedOwner.getLastName());
		assertEquals(UP_ADDRESS, updatedOwner.getAddress());
		assertEquals(UP_CITY, updatedOwner.getCity());
		assertEquals(UP_TELEPHONE, updatedOwner.getTelephone());
	}


	/**
	 * Test para eliminar un Owner
	 */
	@Test
	public void testDeleteOwner() {

		String FIRST_NAME = "Carlos";
		String LAST_NAME = "Ramirez";
		String ADDRESS = "Calle Falsa 123";
		String CITY = "Arequipa";
		String TELEPHONE = "123123123";

		// Creamos un nuevo Owner para eliminar
		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		Integer ownerId = createdOwner.getId();
		log.info("OWNER TO DELETE: " + createdOwner);

		try {
			ownerService.delete(ownerId);
		} catch (OwnerNotFoundException e) {
			fail("Falló la eliminación: " + e.getMessage());
		}

		// Verificamos que ya no exista
		assertThrows(OwnerNotFoundException.class, () -> {
			ownerService.findById(ownerId);
		});

		log.info("¡Dueño eliminado correctamente!");
	}
}