package com.sleepyocean.exercise.complicate.ikm;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 测试
 *
 * @author gehoubao
 **/
public class MainTest {
    public static void main(String args[]) {
        test14();
    }

    private static void test14() {
        StringBuilder builder = new StringBuilder("Wellcome everybody");
        StringJoiner joiner = new StringJoiner(" ", "H", "?");
        builder.replace(0, 7, "elcome");
        builder.setCharAt(7, '!');
        joiner.add("ello and").add(builder);
        System.out.println(joiner.toString());
    }

    private static void test13() throws FileNotFoundException {
        Integer i = 50, j = 50;
        System.out.println(i == j);
        new GregorianCalendar().isLeapYear(2020);
        new BufferedReader(new FileReader("te.txt")).lines().findFirst().orElse(null);
        String s = null;
        s.length();
    }

    private static void test12() {
        Stream.of(Arrays.asList(Arrays.asList(0, 1, 2, 3, 4, 5, 6),
                        Arrays.asList(0, 1, 2, 3, 4),
                        Arrays.asList(0, 1, 2, 3, 4))).flatMap(i -> i.stream())
                .mapToInt(j -> Integer.parseInt(j.toString())).max().ifPresent(s -> System.out.println(s));
    }

    private static void test11() {
        List<Integer> as = Arrays.asList(4, 2, 5);

        Collections.sort(as, (a, b) -> {
            return a - b;
        });
        System.out.println(as);
    }

    private static void test10() {
        System.out.println("1 " + ZoneId.of("GMT"));
        System.out.println("2" + TimeZone.getTimeZone("GMT"));
        System.out.println("3" + System.setProperty("user.timezone", "UTC"));
        System.out.println("4" + ZoneOffset.of("00:00"));
    }

    private static void test9() {
        Long i = new Long(10);
        long k = -5;
        System.out.println(Long.compare(i, k));
        System.out.println(Long.compareUnsigned(i, k));
    }

    private static void test8() {
        Integer x = 3;
        Integer y = null;
        try {
            System.out.println(Integer.compareUnsigned(x, 3) == 0 || Integer.compareUnsigned(y, 0) == 0);
        } catch (Exception ex) {
            System.out.println(ex.getCause().toString());
        }
        try {
            System.out.println(y.compareTo(null) == 0 || true);
        } catch (Exception ex) {
            System.out.println(ex.getCause().toString());
        }
    }

    private static void test7() throws IOException {
        Path inputFile = Paths.get("input.txt");
        Path outputFile = Paths.get("output.txt");
        BufferedReader reader = Files.newBufferedReader(inputFile, Charset.defaultCharset());
        BufferedWriter writer = Files.newBufferedWriter(outputFile, Charset.defaultCharset());
        String lineFromFIle = "";
        while ((lineFromFIle = reader.readLine()) != null) {
            writer.append(lineFromFIle);
        }
        reader.close();
        writer.close();
    }

    private static void test5() {
        File file = new File("FD.txt");
        File backup = new File("FD.txt.bak");
        backup.delete();
        file.renameTo(backup);
    }

    private static void test4() {
        System.out.println(new Float(23.12));
    }

    private static void test3() {
        String first = "first";
        String second = new String("first");
        "first".concat("second");
        System.out.println(first.equals(second));
        System.out.println(first == second);
        System.out.println(first);
        System.out.println(second == "first");
    }

    private static void test2() {
        Double d = null;
        System.out.println((d instanceof Double) ? 1 : 0);

        String e = "1";
        System.out.println(("1" != e) ? 1 : 0);

        boolean b = false;
        System.out.println((b = true) ? 1 : 0);

        int c = 0;
        System.out.println((0 == c++) ? 1 : 0);
    }

    private static void test1() {
        Supplier<String> i = () -> "Car";
        Consumer<String> c = x -> System.out.print(x.toLowerCase());
        Consumer<String> d = x -> System.out.print(x.toUpperCase());
        c.andThen(d).accept(i.get());
        System.out.println();
    }

    private void test6(String[] arg) {
        System.out.println(arg);
        System.out.println(arg[1]);
    }

    static class Parent {
        protected static int count = 0;

        public Parent() {
            count++;
        }

        static int getCount() {
            return count;
        }
    }

    static class Child extends Parent {
        public Child() {
            count++;
        }

        public static void main(String[] args) {
            System.out.println("Count=" + getCount());
            Child obj = new Child();
            System.out.println("Count=" + getCount());
        }
    }

}