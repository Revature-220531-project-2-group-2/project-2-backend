package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.data.MessagesRepository;
import com.revature.models.Message;

@Service
public class MessageService {

	private MessagesRepository msgRepo;
	
	public MessageService(MessagesRepository msgRepo) {
		this.msgRepo = msgRepo;
	}
	public Message save(Message msg) {
		return msgRepo.save(msg);
	
	}
}
