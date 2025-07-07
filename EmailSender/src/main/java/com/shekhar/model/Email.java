package com.shekhar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity 
@Table(name = "emails") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Email {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String recipient;

    @Column(nullable = false)
    private String subject;

    @Lob 
    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime sentAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", recipient=" + recipient + ", subject=" + subject + ", body=" + body + ", sentAt="
				+ sentAt + "]";
	} 
    
    
    
}
