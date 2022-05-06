/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erwin.jsonquery.jsonata;

import com.api.jsonata4java.expressions.Expressions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.commons.io.FileUtils;

@SuppressWarnings("all")
public class JsonataDemo {

    public static void main(String[] args) {

        JsonNode jsonNodeObject = null;
        

        String json = returnJsonData();

        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonNodeObject = mapper.readTree(json);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String query = "$count($)";
        String jsonObjectCountInFile = getTheResultBasedOnQuery(query, jsonNodeObject);
        int jsonObjectCount = 0;
        try {
            jsonObjectCount = Integer.parseInt(jsonObjectCountInFile);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObjectCount = 1;
        }
        String result = "";
        for (int i = 0; i < jsonObjectCount; i++) {
            String tableName = "\"TEST.HR2\"";
            result = "";
            query = "";
            query = tableName + "in $[" + i + "].Tables";
            System.out.println("query------" + query);
            result = getTheResultBasedOnQuery(query, jsonNodeObject);

            if ("true".equalsIgnoreCase(result)) {
                query = "$[" + i + "].SystemName  & \"  \" & $[" + i + "].EnvironmentName";
                System.out.println("Query----" + query);
                result = getTheResultBasedOnQuery(query, jsonNodeObject);
                System.out.println("result------" + result);
                break;
            }
        }

    }

    public static String getTheResultBasedOnQuery(String query, JsonNode jsonNodeObject) {
        String result = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Expressions expr = null;
            expr = Expressions.parse(query);
            JsonNode resultJsonNode = expr.evaluate(jsonNodeObject);

            if (resultJsonNode == null) {
                result = "** no match **";
            } else {
                result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultJsonNode);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String returnJsonData() {
        String json = "";
        try {

//            String filePath = "C:\\erwin\\Metadata\\ALLTest1\\LOCALHOST_1_ADVENTUREWORKS2012_CS.json";
            String filePath = "C:\\erwin\\Metadata\\LOCALHOST_XE.json";
            File fileLocation = new File(filePath);
            json = FileUtils.readFileToString(fileLocation, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}
