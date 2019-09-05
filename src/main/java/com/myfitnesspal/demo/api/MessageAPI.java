package com.myfitnesspal.demo.api;

import com.myfitnesspal.demo.dto.MessageDTO;
import com.myfitnesspal.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("")
public class MessageAPI {

    @Autowired
    MessageService messageService;

    /**
     * Posts message to messageService which in turn persists the message with expiration time
     * if timeout is not sent, the default timeout is set at 60
     * @param messageDTO
     * @return
     */
    @PostMapping("/chat")
    public ResponseEntity postMessage(@RequestBody MessageDTO messageDTO) {
        if (messageDTO.getUsername() != null && messageDTO.getText() != null) {
            Map<String, Long> result = messageService.storeMessage(messageDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Username/Text not sent");
        }
    }

    /**
     * Returns message for messageId
     * @param messageId
     * @return
     */
    @GetMapping("/chat/{messageId}")
    public ResponseEntity getMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getMessageForId(messageId));
    }

    /**
     * Returns unexpired messages for specified user
     * @param username
     * @return
     */
    @GetMapping("/chats/{username}")
    public ResponseEntity getAllMessages(@PathVariable String username) {
        return ResponseEntity.ok(messageService.getAllMessages(username));
    }
}
