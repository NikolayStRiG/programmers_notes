package org.sterzhen.programmers_notes.rest_api.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is an entity list page
 *
 * @param <T> dto
 */
public class Page<T> implements Iterable<T>, Supplier<Stream<T>> {

    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> content = new ArrayList<>();

    public Page() {
    }

    public Page(int number, int size, int totalPages, long totalElements, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public <U> Page<U> map(Function<? super T, ? extends U> mapper) {
        var page = new Page<U>();
        page.setNumber(number);
        page.setSize(size);
        page.setTotalPages(totalPages);
        page.setTotalElements(totalElements);
        page.setContent(content.stream().map(mapper).collect(Collectors.toList()));
        return page;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public Stream<T> get() {
        return content.stream();
    }
}
