package ru.diasoft;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

public class Excel {
    private final String fullPaths = "C:\\apache-tomcat-8.5.29\\conf\\menext.xlsx";

    /**
     * Записываем данные в Excel
     */
    public void saveExcel() throws IOException {
        Json json = new Json();
        json.jsonRead();

        FileInputStream in = new FileInputStream(fullPaths);
        // Книга
        XSSFWorkbook studentsSheet = new XSSFWorkbook(in);
        // Лист
        XSSFSheet worksheet = studentsSheet.getSheetAt(0);
        // Заголовок
        Row headerRow = worksheet.createRow(0);
        Cell headerCellSum = headerRow.createCell(1);
        headerCellSum.setCellValue("Сумма сессий");
        for (int numcell = 0; numcell < json.getStringCheckSessionsMap().size(); numcell++) {
            Cell headerCell = headerRow.createCell(numcell + 2);
            headerCell.setCellValue(json.getStringCheckSessionsMap().keySet().toArray()[numcell].toString());
        }
        // Записи
        int lastRow = worksheet.getLastRowNum();
        Row row = worksheet.createRow(++lastRow);
        row.createCell(0).setCellValue(LocalDateTime.now().withNano(0).toString());
        int numCell = 1;
        int sumSession = 0;
        Iterator it = json.getStringCheckSessionsMap().entrySet().iterator();
        while (it.hasNext()) {
            ++numCell;
            Map.Entry<String, CheckSessions> pair = (Map.Entry) it.next();
            sumSession += pair.getValue().getSessionsCount();
            row.createCell(numCell).setCellValue(pair.getValue().getSessionsCount());
        }
        row.createCell(1).setCellValue(sumSession);
        in.close();

        FileOutputStream out = new FileOutputStream(new File(fullPaths));
        studentsSheet.write(out);
        // out.close();
    }
}
