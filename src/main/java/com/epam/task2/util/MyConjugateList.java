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
public final class MyConjugateList<E> implements List<E> {
    private final List<E> unmodifiableList;
    private List<E> modifiableList;

    private int size;

    public MyConjugateList(List<E> unmodifiableList,
                           List<E> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
        size = unmodifiableList.size() + modifiableList.size();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (cursor >= unmodifiableList.size()) {
                    return modifiableList.get(cursor++ - unmodifiableList.size());
                }
                return unmodifiableList.get(cursor++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[unmodifiableList.size() + modifiableList.size()];
        System.arraycopy(unmodifiableList.toArray(), 0, result, 0, unmodifiableList.size());
        System.arraycopy(modifiableList.toArray(), 0, result, unmodifiableList.size(), modifiableList.size());
        return result;
    }

    /**
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) toArray();
        }
        System.arraycopy(toArray(), 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }

    /**
     * Appends the specified element to the end of modifiable part {@link #modifiableList},
     * because, by the implementation, the modifiable part is second after the unmodifiable part {@link #unmodifiableList}
     * and to the end of the whole list.
     */
    @Override
    public boolean add(E element) {
        size++;
        return modifiableList.add(element);
    }

    /**
     * Removes the first occurrence of the specified element from modifiable part {@link #modifiableList},
     * unmodifiable part {@link #unmodifiableList} is ignored.
     */
    @Override
    public boolean remove(Object object) {
        boolean result = modifiableList.remove(object);
        if (result) {
            size--;
        }
        return result;
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
        if (collection.size() == 0) {
            return false;
        }
        for (E element : collection) {
            add(element);
        }
        return true;
    }

    /**
     * Inserts all the elements in the specified collection into this list (by implementation only to modifiable part
     * {@link #modifiableList}), starting at the specified position.
     *
     * @throws UnsupportedOperationException when trying to add the elements
     * <p>to unmodifiable part {@link #unmodifiableList} of the list
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        rangeCheckForAdd(index);
        if (index >= unmodifiableList.size()) {
            size += collection.size();
            return modifiableList.addAll(index - unmodifiableList.size(), collection);
        }
        throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
    }

    /**
     * Removes from modifiable part {@link #modifiableList} this list all of its elements that are contained in
     * the specified collection, unmodifiable part {@link #unmodifiableList} is ignored.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        int oldSizeModifiablePart = modifiableList.size();
        boolean result = modifiableList.removeAll(collection);
        size -= oldSizeModifiablePart - modifiableList.size();
        return result;
    }

    /**
     * Retains only the elements in modifiable part {@link #modifiableList} this list that are contained in
     * the specified collection. In other words, removes from modifiable part this list all of its elements that
     * are not contained in the specified collection, unmodifiable part {@link #unmodifiableList} is ignored.
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        int oldSizeModifiablePart = modifiableList.size();
        boolean result = modifiableList.retainAll(collection);
        size -= oldSizeModifiablePart - modifiableList.size();
        return result;
    }

    /**
     * Removes all the elements from modifiable part {@link #modifiableList},
     * unmodifiable part {@link #unmodifiableList} is ignored.
     */
    @Override
    public void clear() {
        size -= modifiableList.size();
        modifiableList.clear();
    }

    public E get(int index) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.get(index - unmodifiableList.size());
        }
        return unmodifiableList.get(index);
    }

    /**
     * @throws UnsupportedOperationException when trying to set (change) the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    public E set(int index, E element) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            E oldValue = modifiableList.get(index - unmodifiableList.size());
            modifiableList.set(index - unmodifiableList.size(), element);
            return oldValue;
        }
        throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
    }

    /**
     * @throws UnsupportedOperationException when trying to add the element
     * <p>to unmodifiable part {@link #unmodifiableList} of the list
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index >= unmodifiableList.size()) {
            modifiableList.add(index - unmodifiableList.size(), element);
            size++;
        } else {
            throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
        }
    }

    /**
     * @throws UnsupportedOperationException when trying to remove the element
     * <p>from unmodifiable part {@link #unmodifiableList} of the list
     */
    public E remove(int index) {
        rangeCheck(index);
        if (index >= unmodifiableList.size()) {
            E oldValue = modifiableList.get(index - unmodifiableList.size());
            modifiableList.remove(index - unmodifiableList.size());
            size--;
            return oldValue;
        }
        throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /** unlike {@link #rangeCheck}, when adding, we can add element by last index + 1 */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private String cannotChangeUnmodifiablePartOfList() {
        return "Cannot change unmodifiable part of list (from 0 index to "
                + (unmodifiableList.size() - 1) + " index inclusive)";
    }
}
