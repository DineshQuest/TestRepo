/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author SSourav
 */
@SuppressWarnings("all")
public class JsonEncryptDecrypts_Maps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:\\erwin\\Metadata - Feb10-NormalData_Csv Env\\BOOKS.json";
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

//            JSONArray jsonArray = (JSONArray) obj;
//            System.out.println("Actual -->\n" + jsonArray);
//            String jstring = jsonArray.toJSONString();
            String jstring = obj.toString();
            String encrypted = encrypter(jstring);
            String jsongFilePath = "C:\\erwin\\Metadata_1\\Test\\test1.json";
            String fileName = "test" + 1 + ".json";
            jsongFilePath = "C:\\erwin\\Metadata_1\\" + fileName;

            System.out.println("\n\nEncrypted --> \n" + encrypted);

            writeDataToFile(jsongFilePath, encrypted);
            File jsonFile = new File(jsongFilePath);
//            HashMap map = decryptDataFromFileForJSONObject(jsonFile);
            ArrayList serverDatabaseList = decryptDataFromFileForJSONArray(jsonFile);
            System.out.println(serverDatabaseList.get(0));
            for (int i = 0; i < serverDatabaseList.size(); i++) {
                String data = (String) serverDatabaseList.get(i);

                HashMap serverDatabaseMap = new HashMap();
                String[] pairs = data.split(",");
                for (int j = 0; j < pairs.length; j++) {
                    String pair = pairs[j];
                    String[] keyValue = pair.split(":");
                    serverDatabaseMap.put(keyValue[0], keyValue[1]);
                }

                List tablesList = null;
                List schemaList = null;

                tablesList = (ArrayList) serverDatabaseMap.get("Tables");
                schemaList = (ArrayList) serverDatabaseMap.get("Schemas");
            }

//            String decrypted = decrypter(encrypted);
//            System.out.println("\n\n Decrypted --> \n" + decrypted);
            System.out.println("=======");
//            System.out.println("map---" + map);
//            System.out.println(serverDatabaseList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypter(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec("Erwin".getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTxtBytes = value.getBytes("UTF-8");
            byte[] encBytes = cipher.doFinal(plainTxtBytes);
            return new sun.misc.BASE64Encoder().encode(encBytes);
        } catch (Exception ex) {
            return value;
        }
    }

    public static Object decrypter(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec("Erwin".getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encBytes = new sun.misc.BASE64Decoder().decodeBuffer(value);
            byte[] plainTxtBytes = cipher.doFinal(encBytes);

            return new String(plainTxtBytes);
        } catch (Exception ex) {
            return value;
        }
    }

    public static void writeDataToFile(String jsongFilePath, String encryptedData) {

        try (FileWriter fileWriter = new FileWriter(jsongFilePath, false)) {

            fileWriter.write(encryptedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList decryptDataFromFileForJSONArray(File jsonFile) {
        ArrayList serverDatabaseList = null;
        JSONArray jsonArray1 = new JSONArray();
        try {

            JSONParser parser = new JSONParser();
            ObjectMapper mapper = new ObjectMapper();
            try {
                String encryptedData = FileUtils.readFileToString(jsonFile, "UTF-8");

                Object object = null;

                object = decrypter(encryptedData);
                object = parser.parse(object.toString());
                 JSONArray jsonArray = (JSONArray) object;

//                jsonArray.add(object);

//                Object json = new JSONTokener(obj.toString()).nextValue();
//                JSONArray jsonObject = (JSONArray) json;
                serverDatabaseList = mapper.convertValue(jsonArray, ArrayList.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverDatabaseList;
    }

    public static HashMap<String, String> decryptDataFromFileForJSONObject(File jsonFile) {

        HashMap<String, String> map = new HashMap<>();
        JSONParser parser = new JSONParser();
        try {

            ObjectMapper mapper = new ObjectMapper();

            String encryptedData = FileUtils.readFileToString(jsonFile, "UTF-8");

            Object object = decrypter(encryptedData);
            Object obj = parser.parse(object.toString());
            JSONObject jsonObject = (JSONObject) obj;

            map = mapper.convertValue(jsonObject.get("Databases"), HashMap.class);

        } catch (IOException | IllegalArgumentException | ParseException e) {
            e.printStackTrace();
        }
        return map;
    }
}
