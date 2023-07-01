package com.quickbus.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDetail implements Serializable {
    private String recipient;
    private String msgBody;
    private String subject;
}
