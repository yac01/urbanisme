package com.ybn.urban.rest.controller;

import com.ybn.urban.rest.exception.Dictionary;
import com.ybn.urban.rest.exception.DictionaryEntry;
import com.ybn.urban.rest.exception.TicketError;
import com.ybn.urban.rest.exception.TicketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {
    @Autowired
    @Qualifier("dictionary")
    private Dictionary dictionary;

    @ExceptionHandler(TicketException.class)
    public TicketError handleTicketException(TicketException e, HttpServletRequest request) {
        DictionaryEntry entry = this.dictionary.findByKey(e.getKey());
        TicketError err = new TicketError();
        if (entry != null) {
            err.setKey(entry.getKey());
            err.setMessage(entry.getKey());
            err.setDescription(entry.getDescription());
        }
        return err;
    }
}
