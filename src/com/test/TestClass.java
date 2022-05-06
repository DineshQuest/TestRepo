/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DArasank
 */
public class TestClass {

    public static void main(String[] args) {

        String name = "hello world";
        //hollo werld

        char[] ch = name.toCharArray();

        char temp = ' ';

        for (int i = 0; i < ch.length; i++) {

            

            for (int j = ch.length - 1; j >= 0; j--) {

                if (i == 'a' || i == 'e' || i == 'i' || i == 'o' || i == 'u') {

                    temp = ch[j];
                    ch[j] = ch[i];
                    ch[j] = temp;

                }

            }

        }
        
        System.out.println("---"+new String(ch));
        

    }

}
