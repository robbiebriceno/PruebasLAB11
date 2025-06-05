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

		// Datos de prueba
		String FIRST_NAME = "Pashita";
		String LAST_NAME = "Ruiz";
		String ADDRESS = "Lima";
		String CITY = "Lima";
		String TELEPHONE = "942 574 612";

		// Crear objeto y guardar
		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		log.info(">> Owner creado: {}", createdOwner);

		// Verificación
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

		// Datos originales
		Owner owner = new Owner("Juan", "Perez", "Av. Siempre Viva", "Lima", "987654321");
		Owner createdOwner = ownerService.create(owner);

		// Datos actualizados
		createdOwner.setFirstName("Pedro");
		createdOwner.setLastName("Gomez");
		createdOwner.setAddress("Av. Los Álamos");
		createdOwner.setCity("Cusco");
		createdOwner.setTelephone("999888777");

		// Actualizar en la BD
		Owner updatedOwner = ownerService.update(createdOwner);

		log.info(">> Owner actualizado: {}", updatedOwner);

		// Verificación
		assertEquals("Pedro", updatedOwner.getFirstName());
		assertEquals("Gomez", updatedOwner.getLastName());
		assertEquals("Av. Los Álamos", updatedOwner.getAddress());
		assertEquals("Cusco", updatedOwner.getCity());
		assertEquals("999888777", updatedOwner.getTelephone());
	}

	/**
	 * Test para eliminar un Owner
	 */
	@Test
	public void testDeleteOwner() {

		// Datos de prueba
		Owner owner = new Owner("Carlos", "Ramirez", "Calle Falsa 123", "Arequipa", "123123123");
		Owner createdOwner = ownerService.create(owner);
		Integer ownerId = createdOwner.getId();

		log.info(">> Owner a eliminar: {}", createdOwner);

		// Intentar eliminar
		assertDoesNotThrow(() -> ownerService.delete(ownerId));

		// Verificar que no exista más
		assertThrows(OwnerNotFoundException.class, () -> ownerService.findById(ownerId));

		log.info(">> ¡Dueño eliminado correctamente!");
	}
}
