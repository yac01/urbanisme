package com.ybn.tickets.repository.custom;

public interface UserRepositoryCustom {
    boolean isUnique(String field, String value);
}
