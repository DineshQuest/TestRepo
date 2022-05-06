/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erwin.jsonquery.jsonata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author DArasank
 */
@SuppressWarnings("all")
public class Test {

    public static Logger logger = Logger.getLogger(Test.class.getName());
    static String delimeter = "@ERWIN@";
    static String schemaDelimeter = "@SCHEMALIST_ERWIN@";

    public static void main(String[] args) {

        Integer i1 = 100;
        Integer i2 = 100;

        if (i1 == i2) {
            System.out.println("same");
        } else {
            System.out.println("diff");
        }

        Integer i3 = new Integer(100);
        Integer i4 = new Integer(100);
        
        
        

        if (i3 == i4) {
            System.out.println("same  i4");
        } else {
            System.out.println("diff i4");
        }
        
        int i =10;
        int j =10;
        
         if (i == j) {
            System.out.println("same  i4");
        } else {
            System.out.println("diff i4");
        }

    }

    public static void main5(String[] args) throws IOException {
        String serverNameOrFilePath = "localhost\\erwin/test";

        String filePath = "D:\\InputFiles\\Infa_Param_Xwalk.txt";

        String modifiedParamFolderName = "test";
        File file = new File(filePath);
        String paramaFilePath = "D:\\InputFiles\\paramFiles\\";
//        paramaFilePath = FilenameUtils.normalizeNoEndSeparator(paramaFilePath, true) + "/";
        String modifiedParamPath = paramaFilePath + modifiedParamFolderName + "\\";
        paramaFilePath = FilenameUtils.normalizeNoEndSeparator(paramaFilePath, true) + "/";
        modifiedParamPath = FilenameUtils.normalizeNoEndSeparator(modifiedParamPath, true) + "/";
        String crossWalkFileContent = FileUtils.readFileToString(file, "UTF-8");
        if (StringUtils.isNotBlank(crossWalkFileContent)) {

            String[] crossWalkFileArray = crossWalkFileContent.split("\\n");

            int count = 0;
            for (String crossFileContent : crossWalkFileArray) {

                if (count != 0 && crossFileContent.contains(",") && crossFileContent.split(",").length >= 3) {
                    String paramFileName = crossFileContent.split(",")[2];

                    String paramFileNamePath = paramaFilePath + paramFileName;
                    System.out.println("paramaFilePath----" + paramaFilePath);
                    System.out.println("paramFileName----" + paramFileName);
                    System.out.println("paramFileNamePath----" + paramFileNamePath);
                    paramFileNamePath = FilenameUtils.normalizeNoEndSeparator(paramFileNamePath, true);
                    paramFileNamePath = paramFileNamePath.trim();
                    File paramFile = new File(paramFileNamePath.trim());
                    System.out.println("paramFileNamePath----" + paramFileNamePath);

//                    String prmFileContent = FileUtils.readFileToString(paramFile, "UTF-8");
//                    System.out.println("prmFileContent---" + prmFileContent);
                    System.out.println("paramFileNamePath---" + paramFileNamePath);

                    String prmFileContent = new String(Files.readAllBytes(Paths.get(paramFileNamePath)));

                    if (paramFile.exists() && paramFile.canRead()) {
//                        String prmFileContent = FileUtils.readFileToString(paramFile, "UTF-8");
                        String[] prmFileContentArray = prmFileContent.split("\\n");
                        String workFlowName = "";
                        String modifiedContent = "";
                        for (String prmFileData : prmFileContentArray) {

                            if (prmFileData.contains(":") && prmFileData.contains(".WF:")) {
                                workFlowName = prmFileData.substring(0, prmFileData.lastIndexOf("]"));
                            } else if (prmFileData.contains(".s")) {

                                prmFileData = prmFileData.substring(prmFileData.indexOf("."));
                                prmFileData = workFlowName + ".ST:" + prmFileData;

                            }
                            modifiedContent = modifiedContent + "/n" + prmFileData;
                        }

                        modifiedParamPath = FilenameUtils.normalizeNoEndSeparator(modifiedParamPath, true) + "/";
                        File modifiedFile = new File(modifiedParamPath);
                        if (!modifiedFile.exists()) {
                            modifiedFile.mkdirs();
                        }
                        modifiedParamPath = modifiedParamPath + paramFileName;
                        modifiedFile = new File(modifiedParamPath);

                        FileUtils.writeStringToFile(modifiedFile, modifiedContent, "UTF-8");

                        System.out.println("workFlowName---" + workFlowName);
                        System.out.println("modifiedContent---" + modifiedContent);

                    }

                }

                count++;

            }

            serverNameOrFilePath = serverNameOrFilePath.replaceAll("[^a-zA-Z0-9.//]", "_");
            System.out.println(serverNameOrFilePath);
        }
    }

    public static String replaceSpecialCharaterFromStart(String input) {
        String str = "";
        try {
            char[] charArry = input.toCharArray();
            int length = charArry.length;

            for (int i = length - 1; i >= 0; i--) {
                char c = charArry[i];
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                    str = input.substring(0, input.lastIndexOf(c) + 1);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }
        return str;
    }

    public static void main3(String[] args) {

        Map<String, List<String>> tableList = new HashMap<>();

        List<String> columnList = new ArrayList();

        columnList.add("empName");
        columnList.add("sal");

        tableList.put("Emp", columnList);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        stringBuilder.append("test");
        stringBuilder.append("test");
//        System.out.println("" + stringBuilder.toString());

        Float updateMappingVersion = 0.0f;

//        System.out.println("-----" + updateMappingVersion);
        Float updateMappingVersion1 = null;
//        System.out.println("-----" + updateMappingVersion1);
        String eachConnectionDetails = "";
        String connectionName = "";
        String connectionString = "";

        int length = 0;
        length = eachConnectionDetails.split(Pattern.quote("||")).length;

        if (length >= 2) {
            connectionName = eachConnectionDetails.split(Pattern.quote("||"))[0];
            connectionString = eachConnectionDetails.split(Pattern.quote("||"))[1];

        } else {
            System.out.println("in else");
            connectionName = eachConnectionDetails.split(Pattern.quote("||"))[0];
        }

        System.out.println("connectionName---" + connectionName + "\n" + "connectionString---" + connectionString);
    }

    public static void main2(String[] args) {
        String inputFilePath = "D:\\23_Informatica_Dec08_2021\\Informatic From Harika\\inputFiles";

        String xmlFilePath = "D:\\23_Informatica_Dec08_2021\\Informatic From Harika\\inputFiles\\test1\\wf_UMCM_MEMBR_DEMGRPHC_LKP_FULL_LOAD (4).xml";

        inputFilePath = inputFilePath.replace("\\", "/");
        xmlFilePath = xmlFilePath.replace("\\", "/");
        String appendedFilePath = "";
        String archivePath = "C:\\erwin\\archive";
        archivePath = archivePath.replace("\\", "/");
//         for (String inputFolder : appendedFilePathArray) {
//                if (StringUtils.isNotBlank(inputFolder) && !(inputFolder.contains(".xml") || inputFolder.contains(".XML"))) {
//                    StringBuilder archiveBuilder = new StringBuilder();
//                    archivePath = archiveBuilder.append(archivePath).append("/").append(inputFolder).toString();
//                    archiveFolder = new File(archivePath);
//                    if (!archiveFolder.exists()) {
//                        archiveFolder.mkdir();
//                    }
//
//                }
//            }
        String[] archivePathArray = appendedFilePath.split("/");
        System.out.println("archivePath---" + appendedFilePath);
    }

    public static void main1(String[] args) {
//        logger.set
        Set<String> keySet = new HashSet<>();
        keySet.add("lower embed__PKG__");
        keySet.add("lower embed1");

        String dbName = "dbName1" + schemaDelimeter + "[dbo]" + delimeter + "dbName2";
        if (dbName.contains(schemaDelimeter)) {
            System.out.println("test-----");
        }

        logger.info(keySet.toString());
        System.out.println("serv------" + keySet.toString());
        stuff();
    }

    public static void stuff() {
        int i = 48;

        {
            String subjectHeirchy = "";
        }
        int j = 48;
        System.out.println(j); // prints 48
    }
}
