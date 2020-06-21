package com.ybn.urban.validator;


import com.ybn.urban.rest.exception.ExceptionKeyCode;
import com.ybn.urban.rest.exception.TicketException;

public class Validator {
    public static void validateNotNull(String value, String errKey, String fieldName) {
        if (value == null) {
            throw new TicketException(errKey, fieldName);
        }
    }

    public static void validateNotEmpty(String value, String errKey, String fieldName) {
        validateNotNull(value, ExceptionKeyCode.E_G_0001, fieldName);
        if (value.equals("")) {
            throw new TicketException(errKey, fieldName);
        }
    }

    public static void throwIfNotTrue(boolean condition, String fieldName, String errKey) {
        if (!condition) {
            throw new TicketException(errKey, fieldName);
        }
    }
}
