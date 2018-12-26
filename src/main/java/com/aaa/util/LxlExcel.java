package com.aaa.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author LP
 * @Date 2018/12/18 9:21
 * @Version 1.0
 **/
public class LxlExcel {
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
                        try {
                            XSSFCell id = row.getCell(0);
                            XSSFCell customerid = row.getCell(1);
                            XSSFCell  customername= row.getCell(2);
                            XSSFCell sort = row.getCell(3);
                            XSSFCell userid = row.getCell(4);
                            XSSFCell username = row.getCell(5);
                            XSSFCell numbers = row.getCell(6);
                            XSSFCell opinions = row.getCell(7);
                            XSSFCell describes = row.getCell(8);
                            XSSFCell theme = row.getCell(9);
                            XSSFCell createtime = row.getCell(10);
                            XSSFCell endtime = row.getCell(11);
                            XSSFCell status = row.getCell(12);
                            XSSFCell degree = row.getCell(13);
                            XSSFCell way = row.getCell(14);
                            XSSFCell connections = row.getCell(15);
                            XSSFCell recordid = row.getCell(16);
                            XSSFCell recordname = row.getCell(17);
                            XSSFCell endid = row.getCell(18);
                            XSSFCell endname = row.getCell(19);
                            XSSFCell result = row.getCell(20);
                            XSSFCell content = row.getCell(21);
                            XSSFCell comment = row.getCell(22);
                            XSSFCell isdel = row.getCell(23);
                            map.put("id", getValue(id));
                            map.put("customerid", getValue(customerid));
                            map.put("customername", getValue(customername));
                            map.put("sort", getValue(sort));
                            map.put("userid", getValue(userid));
                            map.put("username", getValue(username));
                            map.put("numbers", getValue(numbers));
                            map.put("opinions", getValue(opinions));
                            map.put("describes", getValue(describes));
                            map.put("theme", getValue(theme));
                            map.put("createtime", getValue(createtime));
                            map.put("endtime", getValue(endtime));
                            map.put("status", getValue(status));
                            map.put("degree", getValue(degree));
                            map.put("way", getValue(way));
                            map.put("connections", getValue(connections));
                            map.put("recordid", getValue(recordid));
                            map.put("recordname", getValue(recordname));
                            map.put("result", getValue(result));
                            map.put("endid", getValue(endid));
                            map.put("endname", getValue(endname));
                            map.put("content", getValue(content));
                            map.put("comment", getValue(comment));
                            map.put("isdel", getValue(isdel));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

    public static Result excelCreate(List<Map<String,Object>> list,String excelPath,int totalPrice){
        List<String> titleList = new ArrayList<>();
        titleList.add("任务编号");
        titleList.add("店铺名称");
        titleList.add("客单价(元)");
        titleList.add("客单价备注");
        titleList.add("主图");
        titleList.add("搜索关键词1");
        titleList.add("搜索关键词2");
        titleList.add("搜索关键词3");
        titleList.add("搜索关键词4");
        titleList.add("搜索关键词5");
        titleList.add("筛选条件");
        titleList.add("城市合伙人姓名");
        Result result = new Result();
        File file = new File(excelPath);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //ISystemConstant.EXCEPTION_CODE
            result.setCode(1);
            result.setMsg("分配失败");
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");

        //设置表格表头边框样式
        /*HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //设置表格表头字体样式
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 11);
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);*/

        //表头的创建
        HSSFRow row = sheet.createRow(0);
        for (int j = 0; j < 12; j++) {
            sheet.setColumnWidth(j,256*20);
            HSSFCell cell = row.createCell(j);
            //cell.setCellStyle(cellStyle1);
            cell.setCellValue(titleList.get(j));
        }
        // 设置行的高度
        row.setHeightInPoints(50);

        //设置表格的样式
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setWrapText(true);
        /*cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 11);
        cellStyle2.setFont(font1);
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);*/

        //添加表格的数据

        for (int k = 0; k <= list.size(); k++) {


            if (k==list.size()){
                HSSFRow row1 = sheet.createRow(k + 1);
                HSSFCell cell = row1.createCell(2);
                cellStyle2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                cell.setCellStyle(cellStyle2);
                cell.setCellValue(totalPrice);
                row1.setHeightInPoints(100);
            }else {
                Map<String,Object > map = list.get(k);
                String image = (String) map.get("image");
                String replaceImage = image.replace("/dscs/", "D:\\fileUpload\\").replace(" ","");

                HSSFRow row1 = sheet.createRow(k+1);
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                BufferedImage bufferImg;
                for (int i = 0; i < map.size()-1; i++) {
                    if (i==1){
                        HSSFCell cell = row1.createCell(i);
                        //cell.setCellStyle(cellStyle1);
                        cell.setCellValue((String) map.get("sname"));
                    } else if(i==2){
                        HSSFCell cell = row1.createCell(i);
                        cellStyle2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue((Double) map.get("price"));
                    }else if (i==4){
                        HSSFCell cell = row1.createCell(i);
                        cell.setCellStyle(cellStyle2);
                        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
                        try {
                            bufferImg = ImageIO.read(new File(replaceImage));
                            ImageIO.write(bufferImg,"png",byteArrayOut);
                            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
                            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                            //anchor主要用于设置图片的属性
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 4, 1,(short) 4, k+1, (short) 5, k+2);
                            //anchor.setAnchorType(3);
                            //插入图片
                            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

                        } catch (Exception e) {
                            e.printStackTrace();
                            //ISystemConstant.EXCEPTION_CODE
                            result.setCode(2);
                            result.setMsg("分配失败");

                        }

                    } else {
                        HSSFCell cell1 = row1.createCell(0);
                        cell1.setCellStyle(cellStyle2);
                        cell1.setCellValue((String) map.get("gno"));

                        HSSFCell cell3 = row1.createCell(3);
                        cell3.setCellStyle(cellStyle2);
                        cell3.setCellValue((String) map.get("remark"));

                        HSSFCell cell5 = row1.createCell(5);
                        cell5.setCellStyle(cellStyle2);
                        cell5.setCellValue((String) map.get("keywordone"));

                        HSSFCell cell6 = row1.createCell(6);
                        cell6.setCellStyle(cellStyle2);
                        cell6.setCellValue((String) map.get("keywordtwo"));

                        HSSFCell cell7 = row1.createCell(7);
                        cell7.setCellStyle(cellStyle2);
                        cell7.setCellValue((String) map.get("keywordthree"));

                        HSSFCell cell8 = row1.createCell(8);
                        cell8.setCellStyle(cellStyle2);
                        cell8.setCellValue((String) map.get("keywordfour"));

                        HSSFCell cell9 = row1.createCell(9);
                        cell9.setCellStyle(cellStyle2);
                        cell9.setCellValue((String) map.get("keywordfive"));

                        HSSFCell cell10 = row1.createCell(10);
                        cell10.setCellStyle(cellStyle2);
                        cell10.setCellValue((String) map.get("conditions"));

                        HSSFCell cell11 = row1.createCell(11);
                        cell11.setCellStyle(cellStyle2);
                        cell11.setCellValue((String) map.get("ename"));
                    }
                }
                row1.setHeightInPoints(100);
            }


        }
        workbook.setActiveSheet(0);
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            //ISystemConstant.EXCEPTION_CODE
            result.setCode(1);
            result.setMsg("分配失败");
        }
        return result;
    }




}
