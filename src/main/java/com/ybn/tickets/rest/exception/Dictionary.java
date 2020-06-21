package com.ybn.tickets.rest.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dictionary {
    @Getter(AccessLevel.NONE)
    private List<DictionaryEntry> entries;

    public List<DictionaryEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    public DictionaryEntry findByKey(String key) {
        return this.entries.stream().filter(e -> e.getKey().equals(key)).findFirst().orElse(null);
    }
}
