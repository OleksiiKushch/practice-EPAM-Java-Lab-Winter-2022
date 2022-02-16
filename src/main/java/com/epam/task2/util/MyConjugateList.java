package com.epam.task2.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This list implementation represents two orderly lists (parts), the first is unmodifiable and the second is modifiable.
 * <p>Separate access to part of the list is not provided, all methods work simultaneously with two parts
 * (unmodifiable {@link #unmodifiableElementData}  and modifiable {@link #modifiableElementData})
 * which gives the impression that this is one list.
 * <p>
 * <p>Noted:
 * <p>parameters (collections) passed to the constructor, the first one will always be considered
 * <p>unmodifiable and the second modifiable
 * <p>
 * @author Oleksii Kushch
 *
 * @param <E> the type of elements in this list
 */
public final class MyConjugateList<E> {
    private final Object[] unmodifiableElementData;
    private Object[] modifiableElementData;

    private int size;
    private final int unmodifiablePartSize;
    private int modifiablePartSize;

    public MyConjugateList(Collection<? extends E> unmodifiableCollection,
                           Collection<? extends E> modifiableCollection) {
        this.unmodifiableElementData = unmodifiableCollection.toArray();
        this.modifiableElementData = modifiableCollection.toArray();
        unmodifiablePartSize = unmodifiableCollection.size();
        modifiablePartSize = modifiableCollection.size();
        size = unmodifiablePartSize + modifiablePartSize;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new MyConjugateIterator();
    }

    private class MyConjugateIterator implements Iterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (cursor >= unmodifiablePartSize) {
                return (E) modifiableElementData[cursor++ - unmodifiablePartSize];
            }
            return (E) unmodifiableElementData[cursor++];
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        if (index >= unmodifiablePartSize) {
            return (E )modifiableElementData[index - unmodifiablePartSize];
        }
        return (E) unmodifiableElementData[index];
    }

    /**
     * @throws UnsupportedOperationException when trying to set (change) the element
     * <p>from unmodifiable part {@link #unmodifiableElementData} of the list
     */
    public E set(int index, E element) {
        rangeCheck(index);
        if (index >= unmodifiablePartSize) {
            @SuppressWarnings("unchecked")
            E oldValue = (E) modifiableElementData[index - unmodifiablePartSize];
            modifiableElementData[index - unmodifiablePartSize] = element;
            return oldValue;
        }
        throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
    }

    /**
     * @throws UnsupportedOperationException when trying to add the element
     * <p>to unmodifiable part {@link #unmodifiableElementData} of the list
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index >= unmodifiablePartSize) {
            fastAdd(index - unmodifiablePartSize, element);
        } else {
            throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
        }
    }

    /**
     * @throws UnsupportedOperationException when trying to remove the element
     * <p>from unmodifiable part {@link #unmodifiableElementData} of the list
     */
    public E remove(int index) {
        rangeCheck(index);
        if (index >= unmodifiablePartSize) {
            @SuppressWarnings("unchecked")
            E oldValue = (E) modifiableElementData[index - unmodifiablePartSize];
            fastRemove(index - unmodifiablePartSize);
            return oldValue;
        }
        throw new UnsupportedOperationException(cannotChangeUnmodifiablePartOfList());
    }


    /**
     * Adds an element by index to the modified part {@link #modifiableElementData} of the list,
     * <p>automatically increasing its capacity by 1 if needed,
     * <p>because when removing capacity does not change (decreases) and there may be free space (cells)
     */
    private void fastAdd(int index, E element) {
        if (modifiablePartSize == modifiableElementData.length) {
            modifiableElementData = Arrays.copyOf(modifiableElementData, modifiablePartSize + 1); // grow array by 1
        }
        System.arraycopy(modifiableElementData, index, modifiableElementData, index + 1, modifiablePartSize - index);
        modifiableElementData[index] = element;
        modifiablePartSize++;
        size++;
    }

    private void fastRemove(int index) {
        if (modifiablePartSize - 1 > index) {
            System.arraycopy(modifiableElementData, index + 1, modifiableElementData, index, modifiablePartSize - 1 - index);
        }
        modifiableElementData[--modifiablePartSize] = null;
        size--;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * unlike {@link #rangeCheck}, when adding, we can add element by last index + 1
     */
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
                + (unmodifiablePartSize - 1) + " index inclusive)";
    }
}
