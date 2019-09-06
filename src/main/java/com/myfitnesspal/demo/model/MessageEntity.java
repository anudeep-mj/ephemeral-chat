package com.myfitnesspal.demo.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "MESSAGE_ENTITY", indexes = {@Index(name = "idx_userName_expired", columnList = "userName, expired")})
@Entity
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String text;

    @Column
    private Boolean expired;

    @Column(name = "expiration_time")
    private Date expirationTime;
}
