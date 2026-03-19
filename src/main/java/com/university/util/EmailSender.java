package com.university.util;

public class EmailSender implements Runnable {
    private String to;
    private String subject;
    private String body;

    public EmailSender(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.setBody(body);
    }

    @Override
    public void run() {
        try {
            // Simulate sending email (e.g., 2 seconds delay)
            Thread.sleep(2000);
            System.out.println("Email sent to " + to + " with subject: " + subject);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}