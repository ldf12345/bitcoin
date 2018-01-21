package com.veronique.app.bitcoin.utils;

import com.alibaba.fastjson.JSON;
import com.veronique.app.bitcoin.domain.BaseDO;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by jolley on 17/6/20.
 */
public class FileUtils {

    public static String JSON_DATA_DIR = System.getProperty("user.dir") + "/src/main/resources/json/";


    public static <T extends BaseDO> List<T> parseArray(String dataFile, Class<T> clazz) {
        try {
            String dataJson = getDataJson(dataFile);

            if (StringUtils.isNotBlank(dataJson)) {
                return JSON.parseArray(dataJson, clazz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getDataJson(String dataFile) {
        String datafile = JSON_DATA_DIR  +  dataFile;
        File file = new File(datafile);
        if (!file.exists()) {
            System.out.println(datafile + " is not exist!!!!");
        }
        return FileUtils.readFile(datafile);
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFile(String fileName) {
        String reStr = "";
        File file = new File(fileName);
        InputStreamReader reader;
        BufferedReader br = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(reader);
            String tempString;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = br.readLine()) != null) {
                // 显示行号
                reStr += tempString.trim();
                //line++;
            }
            reader.close();
            return reStr;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }


    public static void writeFile(String fileName, String data) {
        String reStr = "";
        File file = new File(JSON_DATA_DIR + fileName);
        OutputStreamWriter writer;
        BufferedWriter bw = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(data);
            bw.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
