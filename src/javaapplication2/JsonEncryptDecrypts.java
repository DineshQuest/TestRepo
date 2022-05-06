/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.FileReader;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author SSourav
 */
public class JsonEncryptDecrypts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:\\erwin\\Metadata\\BOOKS.json";
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;
            System.out.println("Actual -->\n" + jsonArray);
            String jstring = jsonArray.toJSONString();
            String encrypted = encrypter(jstring);
            System.out.println("\n\nEncrypted --> \n" + encrypted);
            String decrypted = decrypter(encrypted);
            System.out.println("\n\n Decrypted --> \n" + decrypted);



            System.out.println("=======");
            System.out.println(jsonArray);
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

    public static String decrypter(String value) {
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

}
