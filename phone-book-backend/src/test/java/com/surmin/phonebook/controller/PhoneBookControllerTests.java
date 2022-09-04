package com.surmin.phonebook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surmin.phonebook.model.PhoneBookEntry;
import com.surmin.phonebook.service.PhoneBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PhoneBookController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhoneBookControllerTests {

	private static final String PHONEBOOK_PATH = "/phoneBook";
	private static final Logger LOGGER = LogManager.getLogger(PhoneBookControllerTests.class);

	@MockBean
	private PhoneBookService phoneBookService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findAllEntriesTest() throws Exception {
		LOGGER.info("GET URL {}", PHONEBOOK_PATH);

		this.mockMvc.perform(get(PHONEBOOK_PATH))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	public void postEntryTest() throws Exception {
		LOGGER.info("POST URL {}", PHONEBOOK_PATH);

		PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
		phoneBookEntry.setName("Username");
		phoneBookEntry.setPhone("+79999999999");

		this.mockMvc.perform(post(PHONEBOOK_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(phoneBookEntry)))
				.andExpect(status().isCreated());
	}

	@Test
	public void putEntryTest() throws Exception {
		LOGGER.info("PUT URL {}", PHONEBOOK_PATH);

		PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
		phoneBookEntry.setId(1L);
		phoneBookEntry.setName("Username");
		phoneBookEntry.setPhone("+79999999999");

		this.mockMvc.perform(put(PHONEBOOK_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(phoneBookEntry)))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteEntryTest() throws Exception {
		LOGGER.info("DELETE URL {}", PHONEBOOK_PATH);

		this.mockMvc.perform(delete(PHONEBOOK_PATH + "/1"))
				.andExpect(status().isOk());
	}

}
