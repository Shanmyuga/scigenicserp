package com.sci.bpm.controller.lookup;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class ExcelProcessorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HSSFWorkbook workbook = (HSSFWorkbook) req.getSession(true).getAttribute("workbook");
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.addHeader("Content-Disposition", "attachment; filename="
                + "report.xlsx");
        //  response.setContentLength(out.size());

        //Send content to Browser
        //File outfile = new File("C:\\tools\\delivery_output.xlsx");   //creating a new file instance
        //FileOutputStream fileOut = new FileOutputStream(outfile);
        workbook.write(resp.getOutputStream());

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside servlet");
        try {
List<LinkedHashMap<String, Object>> mylist = (List<LinkedHashMap<String, Object>>) req.getSession(false).getAttribute("workbook");

            HashMap<String, Object> colnameMap = mylist.get(0);
            Set<String> colkeyset = colnameMap.keySet();

            HSSFWorkbook wb = new HSSFWorkbook();
            //Workbook wb = new XSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("Report sheet");


            // Create a cell and put a value in it.


            // Write the output to a file
            try {
                FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                wb.write(fileOut);
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            HSSFRow row = sheet.createRow(0);
            short id = 0;
            for (String intkey : colkeyset) {
                // Create a row and put some cells in it. Rows are 0 based.
                HSSFCell cell = row.createCell(id);
                cell.setCellValue(new HSSFRichTextString(intkey));
                id++;

            }

            for (int idx = 1; idx < mylist.size(); idx++) {

                HashMap<String, Object> rowdataMap = mylist.get(idx);
                Set<String> colnameset = rowdataMap.keySet();

                HSSFRow row1 = sheet.createRow(idx);

                short j = 0;
                for (String intkey : colkeyset) {

                    HSSFCell cell = row1.createCell(j);
                    Object celldata = rowdataMap.get(intkey);
                    if (celldata instanceof String && celldata != null) {
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(new HSSFRichTextString(celldata.toString()));
                    } else if (celldata instanceof Double && celldata != null) {
                        cell.setCellValue(((Double) celldata).doubleValue());
                    } else if (celldata instanceof BigDecimal && celldata != null) {
                        cell.setCellValue(((BigDecimal) celldata).doubleValue());
                    } else if (celldata instanceof java.sql.Date && celldata != null) {
                        cell.setCellValue((java.sql.Date) celldata);
                    } else if (celldata instanceof BigInteger && celldata != null) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellValue(((BigInteger) celldata).doubleValue());
                    } else {
                        if (celldata == null) {
                            celldata = new String("");
                        }
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(new HSSFRichTextString(celldata.toString()));
                    }

                    j++;

                }

            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            //  response.setContentLength(out.size());

            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=sample.xls");

        //Send content to Browser
        //File outfile = new File("C:\\tools\\delivery_output.xlsx");   //creating a new file instance
        //FileOutputStream fileOut = new FileOutputStream(outfile);
        wb.write(baos);
            resp.getOutputStream().write(baos.toByteArray());
        }
        catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
