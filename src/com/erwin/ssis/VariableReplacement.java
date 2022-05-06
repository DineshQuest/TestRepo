/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erwin.ssis;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author DArasank
 */
@SuppressWarnings("all")
public class VariableReplacement {
    
    public static void main(String[] args) {
        
        String var = "EXEC [\"+ Party +\"].[\"+ DIM_Customer +\"_Delete] \"+ (0 == FALSE ? (DT_WSTR, 10 )0 : (DT_WSTR, 10 ) 0)";
//      var = "EXEC  [ Party ].[ DIM_Customer]  (0 == FALSE ? (DT_WSTR, 10 )0 : (DT_WSTR, 10 ) 0)";
        
        var=  removeSpaceAfterDotAndPlusSymbolsFromVariableValues(var);
      
        System.out.println("\ntest---4-"+var);
    }
    
     public static String removeSpaceAfterDotAndPlusSymbolsFromVariableValues(String varibleValue) {
        varibleValue = varibleValue.replaceAll("\\.\\s+", ".");
        try {
            String test = "";
            
            System.out.println("test---1-"+varibleValue+"\n");

            test = varibleValue.replaceAll("\"\\s*\\+", "\"").replaceAll("\\+\\s*\"", "\"");
            
            System.out.println("test---2-"+test+"\n");
            if (!StringUtils.isBlank(test) && !(test.toUpperCase().contains("EXECUTE ") || test.toUpperCase().contains("EXEC "))) {
                test = test.replaceAll("\"\\s*", "\"").replaceAll("\\s*\"", " ");
              
            } else {
                test = test.replaceAll("\"\\s+", "").replaceAll("\\s+\"", "");
                test = test.replace("\"", "");
            }

            System.out.println("test---3-"+test+"\n");
            
//              test = test.replaceAll("\\[\\s+", "[").replaceAll("\\s+]", "]");
            varibleValue = test;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return varibleValue;
    }
    
}
