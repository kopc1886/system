package com.company;

import java.io.*;
import java.util.*;
import java.util.function.*;

class Filter1 implements FileFilter {
    Filter1() {

    }

    @Override
    public boolean accept(File pathname) {
        System.out.println("accept=> " + pathname);
        return !pathname.isDirectory();
    }
}

enum Days {
    ONE(1),
    TWO(3);

    Days(int a) {
        System.out.println(a);
    }
}

public class Main {

    public static boolean isDir(File name) {
        return name.isDirectory();
    }

    public static<T> void workWithCollection
            (Iterable<T> list, Predicate<T> filter, Consumer<T> work) {

        for(T item: list) {
            if (filter.test(item)) {
                work.accept(item);
            }
        }
    }

    public static void main(String[] args) {
        final File file = new File(".");
        // File []files = file.listFiles(new Filter1());
        File []files = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.toString().endsWith("iml");
            }
        });

        for(File f: files) {
            System.out.println(f);
        }

        System.out.println(Days.ONE);

        for(Days d: Days.values()) {
            System.out.println(d);
        }

        /*
        * Collections
        * */
        List<String> states = new ArrayList<String>();
        // добавим в список ряд элементов
        states.add("Германия");
        states.add("Франция");
        states.add("Великобритания");
        states.add("Испания");
        states.add(1, "Италия"); // добавляем элемент по индексу 1

        // states = new LinkedList<String>(states); // выбираем коллекцию LinkedList

        System.out.println(states.get(1));// получаем 2-й объект
        states.set(1, "Дания"); // установка нового значения для 2-го объекта

        System.out.printf("В списке %d элементов \n", states.size());
        for(String state : states){

            System.out.println(state);
        }

        if(states.contains("Германия")){

            System.out.println("Список содержит государство Германия");
        }

        // удалим несколько объектов
        states.remove("Германия");
        states.remove(0);

        Object[] countries = states.toArray();
        for(Object country : countries){

            System.out.println(country);
        }
        /* end
        * */


        /* Лямбда выражения
        * */
        //File tmpFiles[] = file.listFiles((fileName)-> !fileName.isDirectory());
        File tmpFiles[] = file.listFiles(Main::isDir);
        for(File f: tmpFiles) {
            System.out.println(f);
        }

        workWithCollection(Arrays.asList(file.listFiles()), Main::isDir, (item) -> System.out.println(item));
        workWithCollection(Arrays.asList(
                new Integer[]{234, 2, 342341, 123, 31, 90}),
                (i) -> i%2 == 0,
                (i) -> System.out.println(i));

        //states.stream().allMatch((s)-> s.toString().endsWith("ия"));
        System.out.println("STREAM =>");
        List<Integer> intList = new ArrayList<Integer>();
        intList.addAll(Arrays.asList(new Integer[]{234, 2, 342341, 123, 31, 900}));
        double average = intList.stream()
                .filter((i)->i%2==0).mapToInt((i) -> i).average().getAsDouble();

        Optional<Integer> maxNumber = intList.stream()
                .filter((i)->i%2==0).max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        System.out.println("O1 => " + o1);
                        System.out.println("O2 => " + o2);
                        return o1 - o2;
                    }
                });

        for(Integer item: intList) {
            System.out.println(item);
        }
        System.out.println("Average => " + average);
        System.out.println("maxNumber => " + maxNumber);
    }
}
