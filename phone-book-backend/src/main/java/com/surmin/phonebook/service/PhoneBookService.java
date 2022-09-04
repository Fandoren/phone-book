package com.surmin.phonebook.service;

import com.surmin.phonebook.exception.EntityNotFoundException;
import com.surmin.phonebook.model.PhoneBookEntry;
import com.surmin.phonebook.repostirory.PhoneBookEntryRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PhoneBookService {

    private static final String ENTITY_WAS_NOT_FOUND = "Phone book entity wasn't found";
    private static final String PHONE_FORMAT_IS_INVALID = "Phone format is invalid";
    private final String ID_SHOULD_NOT_BE_SPECIFIED = "Id should not be manually specified";
    private final String ID_NOT_SPECIFIED = "Ids should be specified";
    private final String ENTITY_NOT_PROVIDED =  "Phone book entity wasn't provided";

    private final PhoneBookEntryRepository phoneBookEntryRepository;

    public PhoneBookService(PhoneBookEntryRepository phoneBookEntryRepository) {
        this.phoneBookEntryRepository = phoneBookEntryRepository;
    }

    public Collection<PhoneBookEntry> findAll() {
        return phoneBookEntryRepository.findAll();
    }

    public PhoneBookEntry findOne(long id) {
        Optional<PhoneBookEntry> entryOptional = phoneBookEntryRepository.findById(id);
        if(entryOptional.isEmpty()) {
            throw new EntityNotFoundException(ENTITY_WAS_NOT_FOUND);
        }
        return entryOptional.get();
    }

    public PhoneBookEntry save(PhoneBookEntry phoneBookEntry) {
        if(phoneBookEntry == null) {
            throw new EntityNotFoundException(ENTITY_NOT_PROVIDED);
        }
        if(phoneBookEntry.getId() != null) {
            throw new IllegalArgumentException(ID_SHOULD_NOT_BE_SPECIFIED);
        }
        String phoneRegex = "^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$";
        if(!phoneBookEntry.getPhone().matches(phoneRegex)) {
            throw new IllegalArgumentException(PHONE_FORMAT_IS_INVALID);
        }
        phoneBookEntry.setUpdate(LocalDateTime.now());
        phoneBookEntry.setActualized(LocalDateTime.now());
        return phoneBookEntryRepository.save(phoneBookEntry);
    }

    public PhoneBookEntry update(PhoneBookEntry phoneBookEntry) {
        if(phoneBookEntry == null) {
            throw new EntityNotFoundException(ENTITY_NOT_PROVIDED);
        }
        String phoneRegex = "(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))";
        if(!phoneBookEntry.getPhone().matches(phoneRegex)) {
            throw new IllegalArgumentException(PHONE_FORMAT_IS_INVALID);
        }
        if(phoneBookEntry.getId() == null) {
            throw new EntityNotFoundException(ID_NOT_SPECIFIED);
        }
        phoneBookEntryRepository.findById(phoneBookEntry.getId())
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_WAS_NOT_FOUND));
        phoneBookEntry.setUpdate(LocalDateTime.now());
        phoneBookEntry.setActualized(LocalDateTime.now());
        return phoneBookEntryRepository.save(phoneBookEntry);
    }

    public void delete(long id) {
        Optional<PhoneBookEntry> entryOptional = phoneBookEntryRepository.findById(id);
        entryOptional.ifPresent(phoneBookEntryRepository::delete);
    }

    @Async
    public void actualize() {
        phoneBookEntryRepository.actualizeAllRecords(LocalDateTime.now());
    }
}
