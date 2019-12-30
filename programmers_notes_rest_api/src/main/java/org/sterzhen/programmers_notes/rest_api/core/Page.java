package org.sterzhen.programmers_notes.rest_api.core;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class is an entity list page
 *
 * @param <T> dto
 */
public interface Page<T> extends Iterable<T>, Supplier<Stream<T>> {

    int getNumber();

    int getSize();

    int getTotalPages();

    long getTotalElements();

    List<T> getContent();

    <U> Page<U> map(Function<? super T, ? extends U> var1);
}
