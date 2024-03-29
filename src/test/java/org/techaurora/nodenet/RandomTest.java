package org.techaurora.nodenet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RandomTest {

    @Test
    void test() {
//        printClassInfo("".getClass());
//        printClassInfo(Runnable.class);
//        printClassInfo(java.time.Month.class);
        String a = "asd";
        List<Object> list = new ArrayList<>();
        list.add(a);
        list.get(0).getClass().cast(a);
        printClassInfo(list.get(0).getClass());
//        printClassInfo(int.class);
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }
}
