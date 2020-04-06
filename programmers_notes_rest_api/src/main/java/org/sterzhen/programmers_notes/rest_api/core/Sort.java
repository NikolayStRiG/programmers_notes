package org.sterzhen.programmers_notes.rest_api.core;

import java.util.List;

/**
 * Sort option for queries.
 */
public class Sort {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order {

        private Direction direction;
        private String property;

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    enum  Direction {
        ASC, DESC
    }
}
