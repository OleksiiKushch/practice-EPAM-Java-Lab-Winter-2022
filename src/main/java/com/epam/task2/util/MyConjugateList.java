package com.epam.task2.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This list implementation represents two orderly lists (parts), the first is unmodifiable and the second is modifiable.
 * <p>Separate access to part of the list is not provided, all methods work simultaneously with two parts
 * (unmodifiable {@link #unmodifiableList}  and modifiable {@link #modifiableList})
 * which gives the impression that this is one list.
 * <p>
 * <p>Noted:
 * <p>Parameters (collections) passed to the constructor, the first one will always be considered
 * <p>unmodifiable and the second modifiable.
 * <p>
 * @author Oleksii Kushch
 *
 * @param <E> the type of elements in this list
 */
public class MyConjugateList<E> implements List<E> {
    private final List<E> unmodifiableList;
    private List<E> modifiableList;

    public MyConjugateList(List<E> unmodifiableList,
                           List<E> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
    }

    public int size() {
        return unmodifiableList.size() + modifiableList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        Iterator<E> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] =  iterator.next();
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size()) {
            return (T[]) toArray();
        }
        Iterator<E> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            array[i++] = (T) iterator.next();
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    /**
     * Appends the specified element to the end of modifiable part {@link #modifiableList},
     * because, by the implementation, the modifiable part is second after the unmodifiable part {@link #unmodifiableList}
     * and to the end of the whole list.
     */
    @Override
    public boolean add(E element) {
        return modifiableList.add(element);
    }

    /**
     * Removes the first occurrence of the specified element from modifiable part {@link #modifiableList}.
     * @throws IllegalArgumentException if delete element contains in unmodified part (list)
     */
    @Override
    public boolean remove(Object object) {
        if (unmodifiableList.contains(object)) {
            throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
        }
        return modifiableList.remove(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return modifiableList.addAll(collection);
    }

    /**
     * Inserts all the elements in the specified collection into this list (by implementation only to modifiable part
     * {@link #modifiableList}), starting at the specified position.
     *
     * @throws IllegalArgumentException when trying to add the elements
     * <p>to unmodifiable part {@link #unmodifiableList} of the list
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        rangeCheckForAdd(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.addAll(index - unmodifiableList.size(), collection);
        }
        throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
    }

    /**
     * @throws IllegalArgumentException when trying to remove the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object element : collection) {
            if (unmodifiableList.contains(element)) {
                throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
            }
        }
        return modifiableList.removeAll(collection);
    }

    /**
     * @throws IllegalArgumentException when trying to remove the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (!collection.containsAll(unmodifiableList)) {
            throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
        }
        return modifiableList.retainAll(collection);
    }

    /**
     * @throws UnsupportedOperationException operation is not supported by this list.
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public E get(int index) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.get(index - unmodifiableList.size());
        }
        return unmodifiableList.get(index);
    }

    /**
     * @throws IllegalArgumentException when trying to set (change) the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    public E set(int index, E element) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.set(index - unmodifiableList.size(), element);
        }
        throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
    }

    /**
     * @throws IllegalArgumentException when trying to add the element
     * <p>to unmodifiable part {@link #unmodifiableList} of the list
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index >= unmodifiableList.size()) {
            modifiableList.add(index - unmodifiableList.size(), element);
        } else {
            throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
        }
    }

    /**
     * @throws IllegalArgumentException when trying to remove the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    public E remove(int index) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.remove(index - unmodifiableList.size());
        }
        throw new IllegalArgumentException(cannotChangeUnmodifiablePartOfList());
    }

    @Override
    public int indexOf(Object object) {
        int result = unmodifiableList.indexOf(object);
        if (result == -1) {
            result = modifiableList.indexOf(object);
            if (result == -1) {
                return result;
            } else {
                return result + unmodifiableList.size();
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object object) {
        int result = modifiableList.lastIndexOf(object);
        if (result == -1) {
            return unmodifiableList.lastIndexOf(object);
        }
        return result + unmodifiableList.size();
    }

    /**
     * @throws UnsupportedOperationException not implemented method
     */
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException not implemented method
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException not implemented method
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /** unlike {@link #rangeCheck}, when adding, we can add element by last index + 1 */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }

    private String cannotChangeUnmodifiablePartOfList() {
        return "Cannot change unmodifiable part of list (from 0 index to "
                + (unmodifiableList.size() - 1) + " index inclusive)";
    }
}
