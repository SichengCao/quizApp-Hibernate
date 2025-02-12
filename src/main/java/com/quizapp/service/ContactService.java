package com.quizapp.service;

import com.quizapp.model.Contact;
import com.quizapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveMessage(Contact message) {
        contactRepository.save(message);
    }

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public void deleteMessageById(int id) {
        contactRepository.deleteById(id);
    }
}
