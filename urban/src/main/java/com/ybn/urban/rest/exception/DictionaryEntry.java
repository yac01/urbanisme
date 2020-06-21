package com.ybn.urban.rest.exception;

import lombok.Data;

@Data
public class DictionaryEntry {
    private String key;
    private String message;
    private String description;
    private int status;
}
