/**
 * Has only basic path test case.
 */

package com.myfitnesspal.demo;

import com.myfitnesspal.demo.dto.MessageDTO;
import com.myfitnesspal.demo.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMessageAPI {

    @Autowired
    MessageService messageService;

    @Transactional
    @Test
    public void testMessageFlow() throws InterruptedException {
        String user = "user1";
        String user2 = "user2";
        String exampleText = "text example";

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUsername(user);
        messageDTO.setText(exampleText);
        messageDTO.setTimeout(1);

        //Verify message creation
        MessageDTO result = messageService.storeMessage(messageDTO);
        assertTrue("One message published", result != null && null != result.getId());

        //Verify get message for id
        Long messageId = result.getId();
        MessageDTO message = messageService.getMessageForId(messageId);
        assertTrue("verify text", exampleText.equals(message.getText()));
        assertTrue("verify username", user.equals(message.getUsername()));

        //Verify no messages found for another user2
        List<MessageDTO> messageDTOsForUser2 = messageService.getAllMessages(user2);
        assertTrue(messageDTOsForUser2.size() == 0);


        //Verify message shows up before expiry for list api for user1
        List<MessageDTO> messageDTOS = messageService.getAllMessages(user);
        assertTrue(messageDTOS.size() == 1);
        message = messageDTOS.get(0);
        assertTrue(exampleText.equals(message.getText()));
        assertTrue(messageId.equals(message.getId()));


        //Verify message doesnt show up after message expiry
        Thread.sleep(60000);
        messageDTOS = messageService.getAllMessages(user);
        assertTrue(messageDTOS.size() == 0);
    }
}
