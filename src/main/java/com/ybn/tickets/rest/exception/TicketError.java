package com.ybn.tickets.rest.exception;

import lombok.Data;

@Data
public class TicketError {
    private String key;
    private String description;
    private String message;
}
