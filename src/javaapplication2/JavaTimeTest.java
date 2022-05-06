/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author DArasank
 */
public class JavaTimeTest {
    
    public static void main(String[] args) {
        String componentName = "map/test";
        componentName = componentName.replaceAll("[^a-zA-Z0-9 \\p{L}_-]/", "_");
        System.out.println("------"+componentName);
    }

    public static void main1(String[] args) throws ParseException {

        String catTimeStatmp = "2022-02-22 14:35";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = sdf.parse(catTimeStatmp);
            long millis = date.getTime();
             String executionTimeDate = getTheStringDateFromTheMilliseconds(millis);
             System.out.println("executionTimeDate------"+executionTimeDate);
            catTimeStatmp = millis + "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        long currentTime = System.currentTimeMillis();
        String executionTimeDate = getTheStringDateFromTheMilliseconds(currentTime);
        System.out.println("executionTimeDate-----" + executionTimeDate);
        Date date = sdf.parse(executionTimeDate);
        long millis = date.getTime();
        catTimeStatmp = millis + "";

    }

    public static String getTheStringDateFromTheMilliseconds(long currentTime) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String executionTimeDate = df.format(cal.getTime());
        System.out.println("------" + cal.getTime());

        return executionTimeDate;
    }

}
