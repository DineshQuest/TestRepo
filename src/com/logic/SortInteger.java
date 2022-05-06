/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author DArasank
 */
@SuppressWarnings("all")
public class SortInteger {
   
    public static void main(String[] args) {
        
        int[] numArray = {10,15,20};
        
        List<int[]> numList = Arrays.asList(numArray);
        
         Set<Integer> numSet = new HashSet<>();
        
        for(int num : numArray){
            
            String number = num+"";
            if(number.startsWith("1")){
                numSet.add(num);
            }
            
            
        }
        
        
        
        
        numList.stream().forEach( i -> {
            System.out.println("i---"+i);
        });
        
        
//        System.out.println(10+20+"-----");
                
        
//    List<String> numList1=    numList.stream().map( s->s+"").filter(s ->s.startsWith("1")).collect(Collectors.toList());
        
        System.out.println("numSet---"+numSet);
    }
    
}
