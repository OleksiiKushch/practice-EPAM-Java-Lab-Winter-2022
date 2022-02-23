package com.epam.task3.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Extends variant of {@link ArrayList} which stores only one instance of each object.
 *
 * @author Oleksii Kushch
 *
 * @param <E> the type of elements in this list
 */
public class MyUniqueElementsList<E> extends ArrayList<E> {
    private static final long serialVersionUID = -6629734347240948579L;

    /** Default initial capacity */
    private static final int DEFAULT_CAPACITY = 10;

    public MyUniqueElementsList() {
        this(DEFAULT_CAPACITY);
    }

    public MyUniqueElementsList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * @throws IllegalArgumentException if the element is not unique to this list
     */
    @Override
    public boolean add(E element) {
        checkUniquenessElementForAdd(element);
        return super.add(element);
    }

    /**
     * @throws IllegalArgumentException if the element is not unique to this list
     */
    @Override
    public void add(int index, E element) {
        checkUniquenessElementForAdd(element);
        super.add(index, element);
    }

    /**
     * @throws IllegalArgumentException if any of the elements of the collection is not unique to this list
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkUniquenessCollectionForAddAll(collection);
        return super.addAll(collection);
    }

    /**
     * @throws IllegalArgumentException if any of the elements of the collection is not unique to this list
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkUniquenessCollectionForAddAll(collection);
        return super.addAll(index, collection);
    }

    /**
     * Checks for the uniqueness of an element with respect to this list,
     * <p>if not unique throws an {@link IllegalArgumentException}
     */
    private void checkUniquenessElementForAdd(E element) {
        if (contains(element)) {
            throw new IllegalArgumentException(cantBeDuplicatesMsg());
        }
    }

    /**
     * Checks each element of the collection for uniqueness with respect to this list,
     * <p>if any of the elements is not unique throws an {@link IllegalArgumentException}
     */
    private void checkUniquenessCollectionForAddAll(Collection<? extends E> collection) {
        if (collection.stream().anyMatch(super::contains)) {
            throw new IllegalArgumentException(cantBeDuplicatesMsg());
        }
    }

    private String cantBeDuplicatesMsg() {
        return "The element is already exist in this list";
    }
}
