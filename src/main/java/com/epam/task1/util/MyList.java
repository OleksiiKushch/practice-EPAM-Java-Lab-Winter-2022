package com.epam.task1.util;

import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Resizable-array implementation of the List interface. Permits all elements, including {@code null}.
 *
 * <p>Each MyList instance has a capacity. The capacity is the size of the array used to store the elements in the list.
 * Default capacity {@link #DEFAULT_CAPACITY} {@code = 5}, sets in constructor without parameters.
 * It is always at least as large as the list size. As elements are added to an MyList, its capacity grows automatically.
 * The details of the growth policy see description for the method {@link #grow(int)}.
 *
 * <p>This implementation is not synchronized. The iterators returned by this class's iterator.
 * If the list is structurally modified at any time after the iterator is created the iterator also change structure
 *
 * @author Oleksii Kushch
 *
 * @param <E> the type of elements in this list
 */
public class MyList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size;

    public MyList(int capacity) {
        if (capacity >= 0) {
            elementData = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public MyList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
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
            return (E) elementData[cursor++];
        }
    }

    /**
     * @return iterator over the elements in this list in proper sequence by specific condition ({@link Predicate})
     * @throws IllegalArgumentException if parameter {@code filter} equal {@code null}
     */
    public Iterator<E> filteredIterator(Predicate<E> filter) {
        return new MyFilteredIterator(filter);
    }

    private class MyFilteredIterator implements Iterator<E> {
        private final Iterator<E> iterator = iterator();
        /**
         * the condition by which the list ({@link #iterator}) is filtered
         */
        private final Predicate<E> filter;

        private E next;
        /**
         * flag variable which indicates whether the next element
         * <p>in this iterator (concrete filtered iterator {@link MyFilteredIterator})
         */
        private boolean hasNext = true;

        public MyFilteredIterator(final Predicate<E> filter) {
            if (filter == null) {
                throw new IllegalArgumentException();
            }
            this.filter = filter;
            findNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = next;
            findNext();
            return returnValue;
        }

        /**
         * finds the next element of the list ({@link #iterator}) that matches the specified condition ({@link #filter})
         */
        private void findNext() {
            while (iterator.hasNext()) {
                next = iterator.next();
                if (filter.test(next)) {
                    return;
                }
            }
            next = null;
            hasNext = false;
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, array.getClass());
        }
        System.arraycopy(elementData, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    @Override
    public boolean add(E element) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index < 0) {
            return false;
        }
        fastRemove(index);
        return true;
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

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        rangeCheckForAdd(index);
        Object[] postArray = collection.toArray();
        int numberOfPostElements = postArray.length;
        if (numberOfPostElements == 0) {
            return false;
        }
        if (numberOfPostElements > elementData.length - size) {
            grow(size + numberOfPostElements);
        }
        int numberOfMoved = size - index;
        if (numberOfMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numberOfPostElements, numberOfMoved);
        System.arraycopy(postArray, 0, elementData, index, numberOfPostElements);
        size = size + numberOfPostElements;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (collection.contains(elementData[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!collection.contains(elementData[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
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

    private void grow() {
        grow(size + 1);
    }

    /**
     * As elements are added, capacity grows automatically by this method.
     * <p>The growth policy are {@code capacity * 1.5 + 1}
     * <p>If needs increase the capacity of an MyList more than the growth policy use parameter {@code minimumGrowCapacity}
     * which sets the minimum by how much the capacitance should grow
     *
     * @param minimumGrowCapacity minimum by how much the capacitance should grow
     */
    private void grow(int minimumGrowCapacity) {
        long newSize = elementData.length;
        while (newSize < minimumGrowCapacity) {
            newSize += (newSize * 3) / 2 + 1;
        }
        newSize = newSize > Integer.MAX_VALUE ? Integer.MAX_VALUE : newSize;
        elementData = Arrays.copyOf(elementData, (int) newSize);
    }

    private void fastRemove(int index) {
        if (size - 1 > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        }
        elementData[--size] = null;
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
}
