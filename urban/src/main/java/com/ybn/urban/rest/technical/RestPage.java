package com.ybn.urban.rest.technical;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Getter
public class RestPage<T> {
    private Collection<T> content;
    @Getter(AccessLevel.NONE)
    private long totalElement;
    @Getter(AccessLevel.NONE)
    private long resultCount;

    public String getTotalElement() {
        return String.valueOf(totalElement);
    }

    public String getResultCount() {
        return String.valueOf(resultCount);
    }
    public RestPage(Collection content, long totalElement) {
        this.content = content;
        this.totalElement = totalElement;
        this.resultCount = content.size();
    }

    public static <V> RestPage<V> from(Page<V> page) {
        Collection<V> collection = page.get().collect(Collectors.toList());

        if (collection != null && collection.size() > 0) {
            return new RestPage(collection, page.getTotalElements());
        }
        return new RestPage<>(Collections.emptyList(), 0);
    }
}
