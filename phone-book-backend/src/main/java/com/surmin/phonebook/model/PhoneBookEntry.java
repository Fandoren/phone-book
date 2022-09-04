package com.surmin.phonebook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_book")
public class PhoneBookEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE   )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "update")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update;

    @Column(name = "actualized")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime actualized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }

    public LocalDateTime getActualized() {
        return actualized;
    }

    public void setActualized(LocalDateTime actualized) {
        this.actualized = actualized;
    }

}
