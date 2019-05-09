package com.dhxh.guns.modular.dhxh.utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by gbj on 2018/4/19.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void saveFile(XSSFWorkbook workbook, String path, String fileName) {
        File file = new File(path);
        if (!file.exists()) {
            boolean flag = file.mkdirs();
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path + "/" + fileName);
            workbook.write(os);
        } catch (Exception e) {
            logger.error("保存临时Excel表异常");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("保存临时Excel表，IOException");
                }
            }
        }
    }

    public static void downloadFile(String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        String fileName = file.getName();
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf8");
            os.write(buffer);
            os.flush();
        } catch (Exception e) {
            logger.error("导出Excel表异常：{}", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
