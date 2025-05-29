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
	public void testDeleteOwner() {
		// ID de un dueño que ya existe en tu base de datos
		Integer EXISTING_OWNER_ID = 3; // Cambia este valor por un ID real de tu DB

		try {
			// Verificamos que el dueño existe antes de eliminarlo
			Owner owner = ownerService.findById(EXISTING_OWNER_ID);
			log.info("Dueño a eliminar: " + owner);

			// Eliminamos el dueño
			ownerService.delete(EXISTING_OWNER_ID);

			// Verificamos que ya no exista
			assertThrows(OwnerNotFoundException.class, () -> {
				ownerService.findById(EXISTING_OWNER_ID);
			});

			log.info("¡Dueño eliminado correctamente!");

		} catch (OwnerNotFoundException e) {
			fail("El dueño con ID " + EXISTING_OWNER_ID + " no existe en la base de datos");
		}
	}
}
