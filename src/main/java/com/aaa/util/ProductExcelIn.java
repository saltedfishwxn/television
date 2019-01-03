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
 * @ClassName ProductExcelIn
 * @Description TODO
 * @Author 王晓楠
 * @Date 2019/1/2 19:40
 * @Version 1.0
 **/
public class ProductExcelIn {
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
                        XSSFCell make = row.getCell(0);
                        XSSFCell line = row.getCell(1);
                        XSSFCell type = row.getCell(2);
                        XSSFCell bodyForm = row.getCell(3);
                        XSSFCell engine = row.getCell(4);
                        XSSFCell transmission = row.getCell(5);
                        XSSFCell bodyColor = row.getCell(6);
                        XSSFCell interiorColor = row.getCell(7);
                        XSSFCell cost = row.getCell(8);
                        XSSFCell sellingPrice = row.getCell(9);
                        XSSFCell pStatus = row.getCell(11);
                        XSSFCell createTime = row.getCell(12);
                        XSSFCell pComment = row.getCell(13);
                        map.put("createTime", getValue(createTime));
                        map.put("make", getValue(make));
                        map.put("line", getValue(line));
                        map.put("type", getValue(type));
                        map.put("bodyForm", getValue(bodyForm));
                        map.put("engine", getValue(engine));
                        map.put("transmission", getValue(transmission));
                        map.put("bodyColor", getValue(bodyColor));
                        map.put("interiorColor", getValue(interiorColor));
                        map.put("cost", getValue(cost));
                        map.put("sellingPrice", getValue(sellingPrice));
                        map.put("pImage",pictureList.get(rowNum-1));
                        map.put("pStatus", getValue(pStatus));
                        map.put("pComment", getValue(pComment));
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
            list.add("/crm/" + randomName + imageType);
        }
        return list;
    }
}
