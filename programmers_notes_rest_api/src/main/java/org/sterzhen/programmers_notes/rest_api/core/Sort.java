package org.sterzhen.programmers_notes.rest_api.core;

import java.util.List;

/**
 * Sort option for queries.
 */
public interface Sort {

    List<Order> getOrders();

    interface Order {
        Direction getDirection();
        String getProperty();
    }

    enum  Direction {
        ASC, DESC
    }
}
