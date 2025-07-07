package com.shekhar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shekhar.service.EmailService;

@RestController
@RequestMapping("/api")
public class EmailController {

	private final EmailService emailService;

	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	public static class EmailRequest {
		private String to;
		private String subject;
		private String body;

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
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
	}

	@PostMapping("/send-email")
	public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest emailRequest) {
		Map<String, String> response = new HashMap<>();

		if (emailRequest.getTo() == null || emailRequest.getTo().isEmpty() || emailRequest.getSubject() == null
				|| emailRequest.getSubject().isEmpty() || emailRequest.getBody() == null
				|| emailRequest.getBody().isEmpty()) {
			response.put("message", "All fields (to, subject, body) are required.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		boolean sent = emailService.sendAndSaveEmail(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getBody());

		if (sent) {
			response.put("message", "Email sent successfully!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put("message", "Failed to send email. Check server logs for details.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
