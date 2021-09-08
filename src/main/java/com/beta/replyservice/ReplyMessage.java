package com.beta.replyservice;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReplyMessage {

	private final String message;

	/**
	 * Method accepts a string and operator and perform the operation on string and returns result string
	 *
	 * @param message
	 * @param operation
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String Process(String message, char operation) throws NoSuchAlgorithmException {
		switch (operation) {
			case '1': return new StringBuilder(message).reverse().toString();
			case '2': byte[] bytesOfMessage = message.getBytes(StandardCharsets.UTF_8);
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bytes = md.digest(bytesOfMessage);
				StringBuilder sb = new StringBuilder();
				for (byte b : bytes) {
					sb.append(String.format("%02x", b));
				}
				return sb.toString();
			default:
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Invalid input");
		}
	}

	/**
	 * Method accepts the payload string and separates operators from data and calls process.
	 * Returns the resulting string after performing operations specified in payload string.
	 * @param message
	 * @return
	 */
	public String Parse(String message) {
		try {
			String[] parts = message.split("-");
			String operations = String.valueOf(parts[0]);
			if (parts.length != 2 || operations.length() != 2) {
				throw new Exception("Invalid input");
			}
			String result = parts[1];
			result = this.Process(result, operations.charAt(0));
			result = this.Process(result, operations.charAt(1));
			return result;
		} catch (Exception exception) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Invalid input");
		}
	}

	public ReplyMessage(String message) {
		this.message = this.Parse(message);
	}

	public String getMessage() {
		return message;
	}
}
