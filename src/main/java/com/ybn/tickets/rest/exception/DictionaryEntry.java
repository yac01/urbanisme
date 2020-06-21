package com.ybn.tickets.rest.exception;

import lombok.Data;

@Data
public class DictionaryEntry {
    private String key;
    private String message;
    private String description;
    private int status;
}
