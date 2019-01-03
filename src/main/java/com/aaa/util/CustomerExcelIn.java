package com.aaa.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName CustomerExcelIn
 * @Description TODO
 * @Author LP
 * @Date 2018/12/25 19:40
 * @Version 1.0
 **/
public class CustomerExcelIn {
    /**
     * 上传文件的读取
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> readXLSX(String path) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        if (path.endsWith(".xlsx")) {
            InputStream is = new FileInputStream(path);
            //创建一个工作薄
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            List<String> pictureList = readPicture(path);
            //循环工作表Sheet
            for (int numSheet = 0; numSheet < numberOfSheets; numSheet++) {
                XSSFSheet sheet = xssfWorkbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                //循环行Row
                int lastRowNum = sheet.getLastRowNum();
                for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
                    Map<String, Object> map = new HashMap<>(16);
                    XSSFRow row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    } else {
                        XSSFCell customerName = row.getCell(0);
                        XSSFCell sex = row.getCell(1);
                        XSSFCell firstTel = row.getCell(2);
                        XSSFCell secondTel = row.getCell(3);
                        XSSFCell idType = row.getCell(4);
                        XSSFCell idCard = row.getCell(5);
                        XSSFCell marriage = row.getCell(6);
                        XSSFCell job = row.getCell(7);
                        XSSFCell customerLevel = row.getCell(8);
                        XSSFCell customerSource = row.getCell(9);
                        XSSFCell comment = row.getCell(10);
                        XSSFCell createTime = row.getCell(11);
                        map.put("createtime", getValue(createTime));
                        map.put("customername", getValue(customerName));
                        map.put("sex", getValue(sex));
                        map.put("firsttel", getValue(firstTel));
                        map.put("secondtel", getValue(secondTel));
                        map.put("idtype", getValue(idType));
                        map.put("idcard", getValue(idCard));
                        map.put("marriage", getValue(marriage));
                        map.put("job", getValue(job));
                        map.put("customerlevel", getValue(customerLevel));
                        map.put("customersource", getValue(customerSource));
                        map.put("comment", getValue(comment));
                        list.add(map);
                    }
                }
            }
            is.close();
        }
        return list;
    }

    /**
     * 单元格数据类型判断
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell cell) {
        if (0 == cell.getCellType()) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return String.valueOf(formater.format(d));
            }
        }
        cell.setCellType(1);
        //返回字符串类型的值
        return String.valueOf(cell.getStringCellValue());
    }
    /**
     * 读取图片
     */
    public static List<String> readPicture(String path) throws Exception {
        List<String> list = new ArrayList<>();
        InputStream inp = new FileInputStream(path);
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inp);
        List<XSSFPictureData> allPictures = workbook.getAllPictures();

        for (int i = 0; i < allPictures.size(); i++) {
            XSSFPictureData xssfPictureData = allPictures.get(i);
            byte[] data = xssfPictureData.getData();
            String ext =xssfPictureData.suggestFileExtension();
            //生成随机且唯一的文件名称
            String randomName = UUID.randomUUID().toString();
            String imageType = null;
            if (ext.equals("jpeg")) {
                imageType = ".jpg";
            } else if (ext.equals("png")) {
                imageType = ".png";
            } else {
                continue;
            }

            FileOutputStream out = new FileOutputStream("D:\\fileUpload\\" + randomName + imageType);
            out.write(data);
            out.close();
            list.add("/dscs/" + randomName + imageType);
        }
        return list;
    }
}
