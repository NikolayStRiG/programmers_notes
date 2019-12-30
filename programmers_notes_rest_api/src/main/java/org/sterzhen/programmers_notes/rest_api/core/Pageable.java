package org.sterzhen.programmers_notes.rest_api.core;

/**
 * Abstract interface for pagination information.
 */
public interface Pageable {

    int getPageNumber();

    int getPageSize();

    Sort getSort();

    Filter getFilter();
}
