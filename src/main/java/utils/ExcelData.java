package utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelData {
    List list = new ArrayList();
    File file = new File("src/main/resources/testdata/testdata.xlsx");
    String resource ;

    FileInputStream inputStream;
    OutputStream outputStream;
    XSSFWorkbook xssfWorkbook;

    {
        try {
            resource = file.getCanonicalPath();
            System.out.println("resource:"+resource);
        }catch (Exception e){

        }
    }

    public List<String> getlist(int sheet) {
        try {
            inputStream = new FileInputStream(resource);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheet);
            XSSFRow xssfRow = xssfSheet.getRow(0);
            for (int i=0;i<xssfRow.getPhysicalNumberOfCells();i++)
            if (xssfRow != null) {
                XSSFCell name = xssfRow.getCell(i);
                list.add(name.toString());
            }

//        System.out.println(list.toString());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Object[][] getdata(int sheet){
        String[][] objects;
        try {
            inputStream = new FileInputStream(file);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheet);
            // 为了返回值是Object[][],定义一个多行单列的二维数组
//            System.out.println("******"+xssfSheet.getLastRowNum());
            objects = new String[xssfSheet.getLastRowNum()][];
            for (int i = 1; i < xssfSheet.getLastRowNum()+1; i++) {
                XSSFRow xssfRow = xssfSheet.getRow(i);
//                System.out.println("******"+xssfRow.getPhysicalNumberOfCells());
                objects[i - 1] = new String[xssfRow.getPhysicalNumberOfCells()];
                if (xssfRow != null) {
                    for (int j = 1; j <= xssfRow.getPhysicalNumberOfCells(); j++) {

                        XSSFCell data = xssfRow.getCell(j-1);
//                        System.out.println(data);
                        objects[i - 1][j - 1] = data.toString();
//                        System.out.println(objects[i-1][j-1]);
                    }
                }
            }
            inputStream.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void writeResult(String data,int sheet,String result){
        ExcelData excelData=new ExcelData();
        List list=excelData.getlist(sheet);
        try {
            inputStream = new FileInputStream(file);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheet);

            XSSFCell cell=xssfSheet.getRow(1).getCell(list.indexOf(result));
            cell.setCellValue(data);
            outputStream = FileUtils.openOutputStream(file);
            xssfWorkbook.write(outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExcelData excelData=new ExcelData();
        List list=excelData.getlist(1);
        excelData.writeResult("1",1,"ActualResponseData(实际响应数据)");

    }

}

