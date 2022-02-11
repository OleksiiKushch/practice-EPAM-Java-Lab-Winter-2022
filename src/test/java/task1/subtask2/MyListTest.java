package task1.subtask2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyListTest {

    private List<String> list;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void init() {
        list = new MyList<>(Arrays.asList("A", "B", "C"));
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @Test
    void size() {
        assertEquals(3, list.size());
        assertEquals(0, new MyList<>().size());
        assertEquals(0, new MyList<>(2).size());
        assertEquals(3, new MyList<>(list).size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(new MyList<>().isEmpty());
        assertTrue(new MyList<>(2).isEmpty());
        assertFalse(new MyList<>(list).isEmpty());
    }

    @Test
    void contains() {
        assertTrue(list.contains("A"));
        assertTrue(list.contains("C"));
        assertFalse(list.contains("D"));
        assertFalse(list.contains(null));
    }

    @Test
    void iterator() {
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        it.next();
        assertEquals("C", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void filteredIterator() {
        MyList<Integer> testList1 = new MyList<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<Integer> it = testList1.filteredIterator(e -> e % 2 == 0);
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void toArray() {
        Object[] testArr1 = list.toArray();
        assertEquals("A", testArr1[0]);
        assertEquals(3, testArr1.length);
    }

    @Test
    void testToArray() {
    }

    @Test
    void add() {
        assertTrue(list.add("D"));
        assertEquals(4, list.size());
        assertEquals("[A, B, C, D]", list.toString());
    }

    @Test
    void remove() {
        assertTrue(list.remove("A"));
        assertEquals(2, list.size());
        assertEquals("[B, C]", list.toString());
        assertFalse(list.remove("b"));
        assertEquals(2, list.size());
        assertEquals("[B, C]", list.toString());
    }

    @Test
    void containsAll() {
        assertTrue(list.containsAll(new ArrayList<>(Arrays.asList("A", "B"))));
        assertFalse(list.containsAll(new ArrayList<>(Arrays.asList("A", "B", "D"))));
    }

    @Test
    void addAll() {
        assertTrue(list.addAll(new ArrayList<>(Arrays.asList("A", "B"))));
        assertEquals(5, list.size());
        assertEquals("[A, B, C, A, B]", list.toString());
        assertFalse(list.addAll(new ArrayList<>()));
    }

    @Test
    void addAllWithIndex() {
        assertTrue(list.addAll(1, new ArrayList<>(Arrays.asList("a", "b"))));
        assertEquals(5, list.size());
        assertEquals("[A, a, b, B, C]", list.toString());
        assertFalse(list.addAll(3, new ArrayList<>()));
    }

    @Test
    void addAllWithIndex_with_extra_growing() {
        assertTrue(list.addAll(1, new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "i", "j"))));
        assertEquals(11, list.size());
        assertEquals("[A, a, b, c, d, e, f, i, j, B, C]", list.toString());
    }

    @Test
    void removeAll() {
        assertTrue(list.removeAll(new ArrayList<>(Arrays.asList("A", "B"))));
        assertEquals(1, list.size());
        assertEquals("[C]", list.toString());
        assertFalse(list.removeAll(new ArrayList<>(List.of("D"))));
        assertFalse(list.removeAll(new ArrayList<String>()));
    }

    @Test
    void retainAll() {
        assertTrue(list.retainAll(new ArrayList<>(Arrays.asList("A", "B"))));
        assertEquals(2, list.size());
        assertEquals("[A, B]", list.toString());
        assertTrue(list.retainAll(new ArrayList<>(List.of("D"))));
        assertTrue(list.isEmpty());

        List<Integer> testList1 = new MyList<>(Arrays.asList(1, 2, 3));
        assertTrue(testList1.retainAll(new ArrayList<Integer>()));
        assertTrue(testList1.isEmpty());
    }

    @Test
    void clear() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void get() {
        assertEquals("C", list.get(2));
        assertEquals("A", list.get(0));
    }

    @Test
    void set() {
        list.set(1, "b");
        assertEquals(3, list.size());
        assertEquals("[A, b, C]", list.toString());

        list.set(2, "_C");
        assertEquals(3, list.size());
        assertEquals("[A, b, _C]", list.toString());
    }

    @Test
    void addWithIndex() {
        list.add(1, "a");
        assertEquals(4, list.size());
        assertEquals("[A, a, B, C]", list.toString());

        list.add(4, "c");
        assertEquals(5, list.size());
        assertEquals("[A, a, B, C, c]", list.toString());
    }

    @Test
    void removeWithIndex() {
        assertEquals("B", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("[A, C]", list.toString());

        assertEquals("C", list.remove(1));
        assertEquals(1, list.size());
        assertEquals("[A]", list.toString());
    }

    @Test
    void indexOf() {
        assertEquals(-1, list.indexOf("D"));
        assertEquals(2, list.indexOf("C"));

        List<String> testList1 = new MyList<>(Arrays.asList("a", null, null));
        assertEquals(1, testList1.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, list.lastIndexOf("D"));
        assertEquals(2, list.lastIndexOf("C"));

        List<String> testList1 = new MyList<>(Arrays.asList("a", null, null));
        assertEquals(2, testList1.lastIndexOf(null));
    }

    @Test
    void listIterator() {
    }

    @Test
    void testListIterator() {
    }

    @Test
    void subList() {
        List<String> testSubList1 = list.subList(1, 2);
        assertEquals(1, testSubList1.size());
        assertEquals("[B]", testSubList1.toString());

        List<String> testSubList2 = list.subList(0, 2);
        assertEquals(2, testSubList2.size());
        assertEquals("[A, B]", testSubList2.toString());

        List<String> testSubList3 = list.subList(2, 3);
        assertEquals(1, testSubList3.size());
        assertEquals("[C]", testSubList3.toString());
    }

    @Test
    void testToString() {
        assertEquals("[A, B, C]", list.toString());
        assertEquals("[]", new MyList<>().toString());
        assertEquals("[]", new MyList<>(2).toString());
        assertEquals("[A, B, C]", new MyList<>(list).toString());
    }
}