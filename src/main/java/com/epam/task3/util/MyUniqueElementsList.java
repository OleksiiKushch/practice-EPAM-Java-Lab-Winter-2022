package com.epam.task3.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Extends variant of {@link ArrayList} which stores only one instance of each object.
 *
 * @param <E> the type of elements in this list
 * @author Oleksii Kushch
 */
public class MyUniqueElementsList<E> extends ArrayList<E> {
    private static final long serialVersionUID = -6629734347240948579L;

    private static final String MSG_CANT_BE_DUPLICATES = "The element is already exist in this list";

    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    public MyUniqueElementsList() {
        this(DEFAULT_CAPACITY);
    }

    public MyUniqueElementsList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * @throws IllegalArgumentException if elements in the specified collection aren't unique
     * @throws NullPointerException if the specified collection is null
     */
    public MyUniqueElementsList(Collection<? extends E> collection) {
        super(checkUniquenessCollection(collection));
    }

    /**
     * @return the specified collection if all elements in the collection are unique else aren't throw
     * {@link IllegalArgumentException}
     * @throws NullPointerException if elements in the specified collection aren't unique
     * @throws NullPointerException if the specified collection is null
     */
    private static <E> Collection<? extends E> checkUniquenessCollection(Collection<? extends E> collection) {
        if (!isUniqueElementsCollection(collection)) {
            throw new IllegalArgumentException(MSG_CANT_BE_DUPLICATES);
        }
        return collection;
    }

    /**
     * Noted: The inserted element can be contained in this list, if only its index matches the insertion index
     * of the new element.
     * @throws IllegalArgumentException if the element is not unique to this list
     */
    @Override
    public E set(int index, E element) {
        if (!contains(element) ||
                indexOf(element) == index && stream().filter(e -> Objects.equals(e, element)).count() == 1) {
            return super.set(index, element);
        } else {
            throw new IllegalArgumentException(MSG_CANT_BE_DUPLICATES);
        }
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
     * @throws NullPointerException     if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkUniquenessCollectionForAddAll(collection);
        return super.addAll(collection);
    }

    /**
     * @throws IllegalArgumentException if any of the elements of the collection is not unique to this list
     * @throws NullPointerException     if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkUniquenessCollectionForAddAll(collection);
        return super.addAll(index, collection);
    }

    /**
     * @throws IllegalArgumentException if the resulting list does not have unique elements
     * @throws NullPointerException     if operator is null
     */
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        super.replaceAll(operator);
        if (!isUniqueElementsCollection(this)) {
            throw new IllegalArgumentException(MSG_CANT_BE_DUPLICATES);
        }
    }

    /**
     * Checks for the uniqueness of an element with respect to this list,
     * if not unique throws an {@link IllegalArgumentException}
     */
    private void checkUniquenessElementForAdd(E element) {
        if (contains(element)) {
            throw new IllegalArgumentException(MSG_CANT_BE_DUPLICATES);
        }
    }

    /**
     * Checks each element of the collection for uniqueness with respect to this list,
     * if any of the elements is not unique throws an {@link IllegalArgumentException}
     */
    private void checkUniquenessCollectionForAddAll(Collection<? extends E> collection) {
        if (collection.stream().anyMatch(super::contains) || !isUniqueElementsCollection(collection)) {
            throw new IllegalArgumentException(MSG_CANT_BE_DUPLICATES);
        }
    }

    /**
     * @return {@code true} if all elements in the collection are unique and {@code false} if aren't
     * @throws NullPointerException if the specified collection is null
     */
    public static <E> boolean isUniqueElementsCollection(Collection<? extends E> collection) {
        return collection.size() == collection.stream().distinct().count();
    }
}
