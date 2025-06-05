package com.tecsup.petclinic.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.OwnerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de integración para OwnerController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test: Crear un Owner
     */
    @Test
    public void testCreateOwner() throws Exception {
        OwnerDTO owner = new OwnerDTO(null, "Juan", "Pérez", "Calle Falsa 123", "Lima", "999999999");

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(owner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Pérez"));
    }

    /**
     * Test: Obtener todos los Owners
     */
    @Test
    public void testGetAllOwners() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    /**
     * Test: Obtener un Owner por ID
     */
    @Test
    public void testGetOwnerById() throws Exception {
        int ownerId = 1;

        mockMvc.perform(get("/owners/{id}", ownerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ownerId));
    }
}
