package com.surmin.phonebook.controller;

import com.surmin.phonebook.model.PhoneBookEntry;
import com.surmin.phonebook.service.PhoneBookService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneBook")
public class PhoneBookController {

    private final PhoneBookService phoneBookService;

    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping
    public Collection<PhoneBookEntry> findAll() {
        return phoneBookService.findAll();
    }

    @GetMapping("/{id}")
    public PhoneBookEntry findOne(@PathVariable("id") long id) {
        return phoneBookService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneBookEntry save(@RequestBody PhoneBookEntry phoneBookEntry) {
        return phoneBookService.save(phoneBookEntry);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneBookEntry update(@RequestBody PhoneBookEntry phoneBookEntry) {
        return phoneBookService.update(phoneBookEntry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        phoneBookService.delete(id);
    }

    @GetMapping("/actualize")
    public void actualize() {
        phoneBookService.actualize();
    }

}
