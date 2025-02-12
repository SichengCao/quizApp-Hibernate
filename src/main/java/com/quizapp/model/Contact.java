package com.quizapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    private String subject;
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "time", updatable = false)
    private LocalDateTime time;

    @PrePersist
    protected void onCreate() {
        this.time = LocalDateTime.now();
    }

    public Contact() {}

    public Contact(String subject, String email, String message) {
        this.subject = subject;
        this.email = email;
        this.message = message;
    }

    // Getters and Setters
    public int getContactId() { return contactId; }
    public void setContactId(int contactId) { this.contactId = contactId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTime() { return time; }
}
