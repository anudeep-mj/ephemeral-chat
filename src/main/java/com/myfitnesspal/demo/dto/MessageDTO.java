package com.myfitnesspal.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MessageDTO {

    public Long id;

    public String username;

    public String text;

    public Integer timeout;

    public String expiration_date;
}
