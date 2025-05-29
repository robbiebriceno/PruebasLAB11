package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;


	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "maryurit";
		String LAST_NAME = "nalvarte";
		String ADDRESS = "Lima";
		String CITY = "Lima";
		String TELEPHONE = "90873243";

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
	public void testUpdateOwner() throws OwnerNotFoundException {

		String FIRST_NAME = "Carlos";
		String LAST_NAME = "Lopez";
		String ADDRESS = "Av. Brasil 456";
		String CITY = "Arequipa";
		String TELEPHONE = "999888777";

		String UP_FIRST_NAME = "Carlos Alberto";
		String UP_LAST_NAME = "Lopez Quispe";
		String UP_ADDRESS = "Av. Arequipa 789";
		String UP_CITY = "Cusco";
		String UP_TELEPHONE = "777888999";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);

		// ------------ Create ---------------
		log.info("> " + owner);
		Owner ownerCreated = this.ownerService.create(owner);
		log.info(">> " + ownerCreated);

		// ------------ Update ---------------
		ownerCreated.setFirstName(UP_FIRST_NAME);
		ownerCreated.setLastName(UP_LAST_NAME);
		ownerCreated.setAddress(UP_ADDRESS);
		ownerCreated.setCity(UP_CITY);
		ownerCreated.setTelephone(UP_TELEPHONE);

		// Ejecutar y asignar a la variable correcta
		Owner ownerUpdated = this.ownerService.update(ownerCreated);
		log.info(">>>> " + ownerUpdated);

		// Validaciones
		assertEquals(UP_FIRST_NAME, ownerUpdated.getFirstName());
		assertEquals(UP_LAST_NAME, ownerUpdated.getLastName());
		assertEquals(UP_ADDRESS, ownerUpdated.getAddress());
		assertEquals(UP_CITY, ownerUpdated.getCity());
		assertEquals(UP_TELEPHONE, ownerUpdated.getTelephone());
	}
}

