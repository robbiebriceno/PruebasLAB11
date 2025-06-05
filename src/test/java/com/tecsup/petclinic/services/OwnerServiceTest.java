package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testCreateOwner() {
		Owner owner = new Owner("Carlos", "Torres", "Av. Arequipa", "Lima", "999999999");
		Owner created = ownerService.create(owner);

		assertNotNull(created.getId());
		assertEquals("Carlos", created.getFirstName());
		assertEquals("Torres", created.getLastName());
	}

	@Test
	public void testFindOwnerById() throws OwnerNotFoundException {
		Owner owner = new Owner("Lucía", "Ramirez", "Calle 123", "Cusco", "987654321");
		Owner created = ownerService.create(owner);

		Owner found = ownerService.findById(created.getId());

		assertNotNull(found);
		assertEquals("Lucía", found.getFirstName());
		assertEquals("Ramirez", found.getLastName());
	}

	@Test
	public void testUpdateOwner() throws OwnerNotFoundException {
		Owner owner = new Owner("Ana", "Garcia", "Av. Grau", "Arequipa", "111222333");
		Owner created = ownerService.create(owner);

		created.setFirstName("Ana María");
		created.setCity("Trujillo");

		Owner updated = ownerService.update(created);

		assertEquals("Ana María", updated.getFirstName());
		assertEquals("Trujillo", updated.getCity());
	}

	@Test
	public void testDeleteOwner() throws OwnerNotFoundException {
		Owner owner = new Owner("Luis", "Flores", "Jr. Piura", "Tacna", "555666777");
		Owner created = ownerService.create(owner);

		ownerService.delete(created.getId());

		assertThrows(OwnerNotFoundException.class, () -> {
			ownerService.findById(created.getId());
		});
	}

	@Test
	public void testFindAllOwners() {
		List<Owner> owners = ownerService.findAll();

		assertNotNull(owners);
		assertTrue(owners.size() > 0);
	}
}
