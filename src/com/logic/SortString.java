/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import java.util.ArrayList;

/**
 *
 * @author DArasank
 */
public class SortString {
    
    public static void main1(String[] args) 
	{
		// Custom string input
		String str = "geeksforgeeks";
	
		// Converting string into an array for computation
		char arr[] = str.toCharArray();

		// Nested loops for comparison of characters
		// in above character array
	
		char temp;
		
		

		int i = 0;
		while (i <= arr.length) {
			int j = i + 1;
			while (j <= arr.length) {
				if (arr[j] < arr[i]) {
				
					// Comparing the characters one by one
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
				j += 1;
			}
			i += 1;
		}

		// By now loop is done means we have
		// iterated the whole array
		System.out.println(arr);
	}
    
    public static void main(String[] args) {
        
        String tableName = "\"dinesh\".\"tab1\"";
        
        
        System.out.println(getTablePartsList(tableName));
        
    }
    public static ArrayList<String> getTablePartsList(String inputTableName) {
        ArrayList<String> list = new ArrayList();
        if (inputTableName.contains("[") || inputTableName.contains("]")) {
            try {
                while (true) {
                    if (inputTableName.equals("")) {
                        break;
                    }
                    if (inputTableName.trim().startsWith("[")) {
                        list.add(inputTableName.substring(inputTableName.indexOf("[") + 1, inputTableName.indexOf("]")).trim());
                        if (inputTableName.contains("].")) {
                            inputTableName = inputTableName.substring(inputTableName.indexOf("].") + 2);
                        } else {
                            inputTableName = "";
                        }

                    } else if (inputTableName.contains(".")) {
                        list.add(inputTableName.substring(0, inputTableName.indexOf(".")).trim());
                        inputTableName = inputTableName.substring(inputTableName.indexOf(".") + 1);
                    } else {
                        list.add(inputTableName.trim());
                        inputTableName = "";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (inputTableName.contains("\"")) {
            try {
                while (true) {
                    if (inputTableName.equals("")) {
                        break;
                    }
                    if (inputTableName.trim().startsWith("\"")) {
                        list.add(inputTableName.substring(inputTableName.indexOf("\"") + 1, inputTableName.indexOf("\"", inputTableName.indexOf("\"") + 1)).trim());
                        if (inputTableName.contains("\".")) {
                            inputTableName = inputTableName.substring(inputTableName.indexOf("\".") + 2);
                        } else {
                            inputTableName = "";
                        }

                    } else if (inputTableName.contains(".")) {
                        list.add(inputTableName.substring(0, inputTableName.indexOf(".")).trim());
                        inputTableName = inputTableName.substring(inputTableName.indexOf(".") + 1);
                    } else {
                        list.add(inputTableName.trim());
                        inputTableName = "";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (inputTableName.contains(".")) {

            String dotArray[] = inputTableName.split("\\.");
            for (String subTable : dotArray) {
                list.add(subTable);
            }

        } else {
            list.add(inputTableName.trim());
        }
        return list;
    }
}
