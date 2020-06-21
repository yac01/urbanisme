package com.ybn.tickets.rest.exception;

import lombok.Getter;

import java.util.UUID;
@Getter
public class TicketException extends RuntimeException {
    private String id;
    private String key;
    private String[] params;

    public TicketException(Throwable e, String key, String...params) {
        super(e);
        this.key = key;
        this.params = params;
    }

    public  TicketException(String key, String...params) {
        this.assignId();
        this.key = key;
        this.params = params;
    }

    private void assignId() {
        this.id = UUID.randomUUID().toString();
    }
}
