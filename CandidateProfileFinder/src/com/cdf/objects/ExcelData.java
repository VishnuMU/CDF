/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdf.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author compu
 */
public class ExcelData {

    private Map<String, Candidate> candidates;
//    private ArrayList<Candidate> candidates;

    public ExcelData() {

//        candidates = new HashMap();
    }

    public void loadData(File file) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;

        int rows; // No of rows
        rows = sheet.getPhysicalNumberOfRows();
        int cols = 0; // No of columns
        int physicalCellCount;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                physicalCellCount = sheet.getRow(i).getPhysicalNumberOfCells();
                if (physicalCellCount > cols) {
                    cols = physicalCellCount;
                }
            }
        }

        System.out.println("Row count    : " + rows);
        System.out.println("Coloumn count: " + cols);

    }

    public int searchDatabase(String input) {

        int index = -1;
       
        candidates.entrySet().stream().forEach((entry) -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key: " + key + ", Value : " + String.valueOf(value));
        });

        return index;
    }
}
