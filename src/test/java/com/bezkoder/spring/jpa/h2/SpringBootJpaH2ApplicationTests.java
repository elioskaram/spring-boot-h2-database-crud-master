package com.bezkoder.spring.jpa.h2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class SpringBootJpaH2ApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootJpaH2ApplicationTests.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/tutorials"))
				.andExpect(status().isOk());
	}

	@Test
	void testCreateTutorial() throws Exception {
		Tutorial newTutorial = new Tutorial("New Title", "New Description", false);
		mockMvc.perform(post("/api/tutorials")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newTutorial)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title", is("New Title")));
	}

	@Test
	void testGetSpecificTutorial() throws Exception {
		// Assuming there's a tutorial with ID 1
		mockMvc.perform(get("/api/tutorials/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	void testUpdateTutorial() throws Exception {
		Tutorial updatedTutorial = new Tutorial("Updated Title", "Updated Description", true);
		// Assuming there's a tutorial with ID 1 to update
		mockMvc.perform(put("/api/tutorials/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedTutorial)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Updated Title")));
	}

	@Test
	void testDeleteSpecificTutorial() throws Exception {
		// Assuming there's a tutorial with ID 1 to delete
		mockMvc.perform(delete("/api/tutorials/1"))
				.andExpect(status().isNoContent());
	}

	@Test
	void contextLoads() {
		logger.info("Context loads test running");
	}

}
