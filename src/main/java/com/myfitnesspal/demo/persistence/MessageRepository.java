package com.myfitnesspal.demo.persistence;

import com.myfitnesspal.demo.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByUserNameAndExpiredIsFalse(String username);
}
