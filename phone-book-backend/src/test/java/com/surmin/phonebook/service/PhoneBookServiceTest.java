package com.surmin.phonebook.service;

import com.surmin.phonebook.PhoneBookApplication;
import com.surmin.phonebook.exception.EntityNotFoundException;
import com.surmin.phonebook.model.PhoneBookEntry;
import com.surmin.phonebook.repostirory.PhoneBookEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = PhoneBookApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PhoneBookServiceTest {

    @Autowired
    private PhoneBookService phoneBookService;
    @MockBean
    private PhoneBookEntryRepository phoneBookEntryRepository;

    @Test
    public void findOneThrowsEntityNotFoundExceptionIfEntityNotFound() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> phoneBookService.findOne(123456));
    }

    @Test
    public void saveThrowsIllegalArgumentExceptionIfEntryIdSpecified() {
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
        phoneBookEntry.setId(1L);
        phoneBookEntry.setName("Username");
        phoneBookEntry.setPhone("+79999999988");

        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneBookService.save(phoneBookEntry));
    }

    @Test
    public void saveThrowsIllegalArgumentExceptionIfPhoneInWrongFormat() {
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
        phoneBookEntry.setId(1L);
        phoneBookEntry.setName("Username");
        phoneBookEntry.setPhone("+12345678910");

        Assertions.assertThrows(IllegalArgumentException.class, () -> phoneBookService.save(phoneBookEntry));
    }

    @Test
    public void saveThrowsEntityNotFoundExceptionIfPhoneBookEntryIsNull() {
        PhoneBookEntry phoneBookEntry = null;

        Assertions.assertThrows(EntityNotFoundException.class, () -> phoneBookService.save(phoneBookEntry));
    }

    @Test
    public void updateThrowsEntityNotFoundExceptionIfEntryIdNotSpecified() {
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
        phoneBookEntry.setName("Username");
        phoneBookEntry.setPhone("+79999999988");

        Assertions.assertThrows(EntityNotFoundException.class, () -> phoneBookService.update(phoneBookEntry));
    }
}
