package task1.subtask2;

import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class MyList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 5;
    private static final int MOD_LOADER = 2;

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

    public MyList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        size = a.length;
        elementData = Arrays.copyOf(a, size);
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyItr();
    }

    private class MyItr implements Iterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            return (E) elementData[cursor++];
        }
    }

    public Iterator<E> filteredIterator(Predicate<E> filter) {
        return new MyFilteredItr(filter);
    }

    private class MyFilteredItr implements Iterator<E> {
        private final Iterator<E> iterator = iterator();
        private final Predicate<E> filter;

        private E next;
        private boolean hasNext = true;

        public MyFilteredItr(final Predicate<E> filter) {
            this.filter = Objects.requireNonNullElseGet(filter, AcceptAllFilter::new);
            findNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E returnValue = next;
            findNext();
            return returnValue;
        }

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

        private final class AcceptAllFilter<T> implements Predicate<T> {
            public boolean test(final T type) {
                return true;
            }
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (size == elementData.length)
            grow();
        elementData[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0)
            return false;
        fastRemove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0)
            return false;
        for (E e : c)
            add(e);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        Object[] postArr = c.toArray();
        int numPostElements = postArr.length;
        if (numPostElements == 0)
            return false;
        if (numPostElements > elementData.length - size)
            grow(size + numPostElements);
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numPostElements, numMoved);
        System.arraycopy(postArr, 0, elementData, index, numPostElements);
        size = size + numPostElements;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(elementData[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elementData[i])) {
                remove(i--);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
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
        if (size == elementData.length)
            grow();
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
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Object[] a = Arrays.copyOfRange(elementData, fromIndex, toIndex);
        return new MyList<>((Collection<? extends E>) Arrays.asList(a));
    }

    private void grow() {
        grow(size + 1);
    }

    private void grow(int minGrowCapacity) {
        long newSize = elementData.length;
        while (newSize < minGrowCapacity)
            newSize += newSize * MOD_LOADER;
        newSize = newSize > Integer.MAX_VALUE ? Integer.MAX_VALUE : newSize;
        elementData = Arrays.copyOf(elementData, (int) newSize);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void fastRemove(int i) {
        if (size - 1 > i)
            System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
        elementData[--size] = null;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
