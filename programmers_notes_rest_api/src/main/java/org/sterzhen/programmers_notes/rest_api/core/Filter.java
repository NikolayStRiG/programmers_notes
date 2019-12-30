package org.sterzhen.programmers_notes.rest_api.core;

import java.util.List;

/**
 * Filter option for queries.
 */
public interface Filter {

    List<Predicate> getPredicate();

    interface Predicate {
        String getProperty();
        String getValue();
        String getType();
    }
}
