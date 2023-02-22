/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.log.test;

import java.util.logging.Logger;
import org.apache.log4j.builders.appender.FileAppenderBuilder;



/**
 *
 * @author DArasank
 */
public class Example {

    static Logger log = Logger.getLogger(Example.class.getName());

    public static void main(String[] args) {
       
        FileAppenderBuilder fileAppenderBuilder =new FileAppenderBuilder();
//        fileAppenderBuilder.
        log.info("Hello this is an info message");
        System.out.println("test2");
    }

}
