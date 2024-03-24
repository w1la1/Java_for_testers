package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {
    @Test
    void arrayTests() {
        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests() {
        var list = new ArrayList<>(List.of("a", "b", "c"));//создание пустого списка и добавление в него параметров(можно менять)
        //var list = List.of("a", "b", "c");//список с параметрами(нельзя изменить)
        //var list = new ArrayList<String>(); создание пустого списка(можно менять)

        //list.add("a"); добавление параметров в пустой список
        //list.add("b");
        //list.add("c");
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }
    @Test
    void setTests() {
        var set = new HashSet<>(List.of("a","b","c","a"));
//        var set = Set.copyOf(List.of("a","b","c","a"));
        Assertions.assertEquals(3, set.size());
        //var element =  set.stream().findAny().get();
        set.add("d");
        Assertions.assertEquals(4,set.size());
    }
    @Test
    void testMap() {
        var digits = new HashMap<Character,String>();
        digits.put('1',"one");
        digits.put('2',"two");
        digits.put('3',"three");
        digits.put('4',"four");
        digits.put('5',"five");
    }
}
