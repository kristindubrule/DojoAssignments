package com.codingdojo.events.services;

import com.codingdojo.events.repositories.MessageRepository;
import org.springframework.stereotype.Service;
import com.codingdojo.events.models.*;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}
