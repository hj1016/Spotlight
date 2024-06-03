package com.example.spotlight.network.DTO;

import java.io.Serializable;

public class ProposalDTO implements Serializable {
    private String title;
    private String position;
    private String contact;
    private String description;

    public ProposalDTO(String title, String position, String contact, String description) {
        this.title = title;
        this.position = position;
        this.contact = contact;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}