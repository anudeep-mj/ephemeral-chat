package com.myfitnesspal.demo.service;

import com.myfitnesspal.demo.dto.MessageDTO;
import com.myfitnesspal.demo.model.MessageEntity;
import com.myfitnesspal.demo.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Map<String, Long> storeMessage(MessageDTO messageDTO) {
        String username = messageDTO.getUsername();
        String text = messageDTO.getText();
        Integer expiry = messageDTO.getTimeout();
        if (expiry == null) {
            expiry = 60;
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(text);
        messageEntity.setUserName(username);

        Date expirationTime = DateUtils.addMinutes(new Date(), expiry);
        messageEntity.setExpirationTime(expirationTime);

        messageRepository.save(messageEntity);
        Map<String, Long> msgResult = new HashMap<>();
        msgResult.put("id", messageEntity.getId());
        return msgResult;
    }

    public MessageDTO getMessageForId(Long messageId) {
        MessageDTO messageDTO = new MessageDTO();
        MessageEntity messageEntity = messageRepository.getOne(messageId);
        if (messageEntity != null) {
            messageDTO.setUsername(messageEntity.getUserName());
            messageDTO.setText(messageEntity.getText());
            messageDTO.setExpiration_date(messageEntity.getExpirationTime().toString());
        }

        return messageDTO;
    }

    public List<MessageDTO> getAllMessages(String username) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<MessageEntity> messages = messageRepository.findByUserName(username);

        for (MessageEntity messageEntity : messages) {
            int flag = messageEntity.getExpirationTime().compareTo(new Date());
            if (flag >= 0) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setId(messageEntity.getId());
                messageDTO.setText(messageEntity.getText());
                messageDTOList.add(messageDTO);
            }
        }

        return messageDTOList;
    }
}
