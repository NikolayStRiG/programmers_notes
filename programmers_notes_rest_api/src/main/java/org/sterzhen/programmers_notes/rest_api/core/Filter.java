package org.sterzhen.programmers_notes.rest_api.core;

import java.util.List;

/**
 * Filter option for queries.
 */
public class Filter {

    private List<Predicate> predicates;

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public static class Predicate {

        private String property;
        private String value;
        private String type;

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
