/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java8;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author DArasank
 */
@SuppressWarnings("all")
public class TestClass {

    public static void main(String[] args) {

//        TestInterface test = () -> System.out.println("inside m1 method");
//        TestInterface test2 = new TestClass()::m2;
//        test.m1();
//        test2.m1();
//        uniqueCharacters("ababc");
        String targetEnvName = "test1";
        String defEnvName = "test2";

        boolean envFlag = true;
        if (StringUtils.isNotBlank(targetEnvName) && !defEnvName.equalsIgnoreCase(targetEnvName)) {
            envFlag = false;

        }
        System.out.println("------" + envFlag);
              

    }

//    public static void uniqueCharacters(String test) {
//        String temp = "";
//        for (int i = 0; i < test.length(); i++) {
//            char current = test.charAt(i);
//            
//            System.out.println(" current---"+current);
//            if (temp.indexOf(current) < 0) {
//                temp = temp + current;
//            } else {
//                temp = temp.replace(String.valueOf(current), "");
//            }
//            System.out.println(" temp---"+temp);
//        }
//        
//        System.out.println(temp + " ");
//        
//    }
    public static void uniqueCharacters(String test) {
        String temp = "";
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < test.length(); i++) {

            char key = test.charAt(i);
            if (map.get(key) != null) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }

//             System.out.println("test.charAt(i)---"+test.charAt(i));
            if (temp.indexOf(test.charAt(i)) == - 1) {
                temp = temp + test.charAt(i);
            }
        }

        System.out.println(temp + " " + map);

    }
}
