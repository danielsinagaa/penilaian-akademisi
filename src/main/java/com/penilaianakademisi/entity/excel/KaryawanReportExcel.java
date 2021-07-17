package com.penilaianakademisi.entity.excel;

import com.penilaianakademisi.entity.Karyawan;
import com.penilaianakademisi.entity.KaryawanAplusMin;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.SortedSet;

public class KaryawanReportExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Set<Karyawan> reports;
    private SortedSet<Karyawan> sortedData;
    private KaryawanAplusMin aPlusMin;

    public KaryawanReportExcel(Set<Karyawan> reports, SortedSet<Karyawan> sortedData, KaryawanAplusMin aPlusMin ) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("KaryawanReport");
        this.reports = reports;
        this.sortedData = sortedData;
        this.aPlusMin = aPlusMin;
    }

    private void kriteriaHeader() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        style.setFont(font);

        int c = 0;
        Cell cell = row.createCell(c);
        cell.setCellValue("No.");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("Nama");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K1");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K2");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K3");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K4");
        cell.setCellStyle(style);
        c++;

    }

    private int kriteriaRows() {

        int rows = 1;

        Row row = sheet.createRow(1);
        for (Karyawan t : reports) {
            row = sheet.createRow(rows);

            Cell cell = row.createCell(0);
            cell.setCellValue(rows);
            rows++;

            CellStyle noStyle = workbook.createCellStyle();

            noStyle.setAlignment(HorizontalAlignment.LEFT);
            noStyle.setBorderBottom(BorderStyle.THIN);
            noStyle.setBorderLeft(BorderStyle.THIN);
            noStyle.setBorderRight(BorderStyle.THIN);
            noStyle.setBorderTop(BorderStyle.THIN);

            cell.setCellStyle(noStyle);
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(t.getNama());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(noStyle);

            cell = row.createCell(2);
            cell.setCellValue(t.getK1());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(noStyle);

            cell = row.createCell(3);
            cell.setCellValue(t.getK2());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(noStyle);

            cell = row.createCell(4);
            cell.setCellValue(t.getK3());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(noStyle);

            cell = row.createCell(5);
            cell.setCellValue(t.getK4());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(noStyle);
        }

        return rows;
    }

    private void kriteriaHeader(int r, String name) {

        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        style.setFont(font);

        int rows = r + 1;
        Row row = sheet.createRow(rows);

        sheet.addMergedRegion(new CellRangeAddress(rows, rows, 0, 10));
        Cell cell = row.createCell(0);
        cell.setCellValue(name);
        cell.setCellStyle(style);

        row = sheet.createRow(rows + 1);
        int c = 0;
        cell = row.createCell(c);
        cell.setCellValue("No.");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("Nama");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K1");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K2");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K3");
        cell.setCellStyle(style);
        c++;

        cell = row.createCell(c);
        cell.setCellValue("K4");
        cell.setCellStyle(style);
        c++;
    }

    private int normalisasiRows() {

        int rows = kriteriaRows() + 3;

        int i = 1;
        Row row;
        for (Karyawan t : reports) {
            row = sheet.createRow(rows++);

            Cell cell = row.createCell(0);
            cell.setCellValue(i);
            i++;

            CellStyle noStyle = workbook.createCellStyle();

            noStyle.setAlignment(HorizontalAlignment.LEFT);
            noStyle.setBorderBottom(BorderStyle.THIN);
            noStyle.setBorderLeft(BorderStyle.THIN);
            noStyle.setBorderRight(BorderStyle.THIN);
            noStyle.setBorderTop(BorderStyle.THIN);

            cell.setCellStyle(noStyle);
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(t.getNama());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(noStyle);

            cell = row.createCell(2);
            cell.setCellValue(t.getPembagik1());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(noStyle);

            cell = row.createCell(3);
            cell.setCellValue(t.getPembagik2());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(noStyle);

            cell = row.createCell(4);
            cell.setCellValue(t.getPembagik3());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(noStyle);

            cell = row.createCell(5);
            cell.setCellValue(t.getPembagik4());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(noStyle);

        }

        return rows;
    }

    private int matriksSolusiIdeal() {

        int rows = normalisasiRows() + 3;

        Row row = sheet.createRow(rows);

        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        CellStyle noStyle = workbook.createCellStyle();

        noStyle.setAlignment(HorizontalAlignment.LEFT);
        noStyle.setBorderBottom(BorderStyle.THIN);
        noStyle.setBorderLeft(BorderStyle.THIN);
        noStyle.setBorderRight(BorderStyle.THIN);
        noStyle.setBorderTop(BorderStyle.THIN);

        cell.setCellStyle(noStyle);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Solusi Ideal Positif");
        sheet.autoSizeColumn(1);

        cell.setCellStyle(noStyle);

        cell = row.createCell(2);
        cell.setCellValue(aPlusMin.getK1Plus());
        sheet.autoSizeColumn(2);

        cell.setCellStyle(noStyle);

        cell = row.createCell(3);
        cell.setCellValue(aPlusMin.getK2Plus());
        sheet.autoSizeColumn(3);

        cell.setCellStyle(noStyle);

        cell = row.createCell(4);
        cell.setCellValue(aPlusMin.getK3Plus());
        sheet.autoSizeColumn(4);

        cell.setCellStyle(noStyle);

        cell = row.createCell(5);
        cell.setCellValue(aPlusMin.getK4Plus());
        sheet.autoSizeColumn(5);

        cell.setCellStyle(noStyle);

        row = sheet.createRow(rows + 1);

        cell = row.createCell(0);
        cell.setCellValue(2);
        sheet.autoSizeColumn(0);
        cell.setCellStyle(noStyle);

        cell = row.createCell(1);
        cell.setCellValue("Solusi Ideal Negatif");
        sheet.autoSizeColumn(1);

        cell.setCellStyle(noStyle);

        cell = row.createCell(2);
        cell.setCellValue(aPlusMin.getK1Min());
        sheet.autoSizeColumn(2);

        cell.setCellStyle(noStyle);

        cell = row.createCell(3);
        cell.setCellValue(aPlusMin.getK2Min());
        sheet.autoSizeColumn(3);

        cell.setCellStyle(noStyle);

        cell = row.createCell(4);
        cell.setCellValue(aPlusMin.getK3Min());
        sheet.autoSizeColumn(4);

        cell.setCellStyle(noStyle);

        cell = row.createCell(5);
        cell.setCellValue(aPlusMin.getK4Min());
        sheet.autoSizeColumn(5);

        cell.setCellStyle(noStyle);


        return rows;
    }

    public int solusiPreferensi(){
        int rows = matriksSolusiIdeal() + 3;

        CellStyle noStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        noStyle.setFont(font);

        noStyle.setAlignment(HorizontalAlignment.LEFT);
        noStyle.setBorderBottom(BorderStyle.THIN);
        noStyle.setBorderLeft(BorderStyle.THIN);
        noStyle.setBorderRight(BorderStyle.THIN);
        noStyle.setBorderTop(BorderStyle.THIN);

        Row row = sheet.createRow(rows);
//        sheet.addMergedRegion(new CellRangeAddress(rows, rows, 0, 2));

        Cell cell = row.createCell(0);
        cell.setCellValue("Jarak Solusi & Nilai Preferensi");
        cell.setCellStyle(noStyle);
        rows++;

        row = sheet.createRow(rows);
        cell = row.createCell(0);
        cell.setCellValue("No.");
        cell.setCellStyle(noStyle);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Nama");
        sheet.autoSizeColumn(1);
        cell.setCellStyle(noStyle);

        cell = row.createCell(2);
        cell.setCellValue("Jarak Solusi Ideal Positif (d+)");
        sheet.autoSizeColumn(2);
        cell.setCellStyle(noStyle);

        cell = row.createCell(3);
        cell.setCellValue("Jarak Solusi Ideal Negatif (d-)");
        sheet.autoSizeColumn(3);
        cell.setCellStyle(noStyle);

        cell = row.createCell(4);
        cell.setCellValue("Preferensi");
        sheet.autoSizeColumn(4);
        cell.setCellStyle(noStyle);

        int i = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont f = workbook.createFont();
        f.setBold(false);
        f.setFontHeight(11);
        style.setFont(f);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        for (Karyawan t : reports){
            rows++;
            row = sheet.createRow(rows);

            cell = row.createCell(0);
            cell.setCellValue(i);
            i++;

            cell.setCellStyle(style);
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(t.getNama());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(t.getDPlus());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(t.getDMin());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(t.getPreferensi());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);
        }

        return rows;
    }

    public int rangking(){
        CellStyle noStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        noStyle.setFont(font);

        noStyle.setAlignment(HorizontalAlignment.LEFT);
        noStyle.setBorderBottom(BorderStyle.THIN);
        noStyle.setBorderLeft(BorderStyle.THIN);
        noStyle.setBorderRight(BorderStyle.THIN);
        noStyle.setBorderTop(BorderStyle.THIN);

        int rows = solusiPreferensi() + 2;

        Row row = sheet.createRow(rows);

        sheet.addMergedRegion(new CellRangeAddress(rows, rows, 0, 3));
        Cell cell = row.createCell(0);
        cell.setCellValue("Ranking");
        rows++;


        row = sheet.createRow(rows);
        cell = row.createCell(0);
        cell.setCellValue("No.");
        cell.setCellStyle(noStyle);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Nama");
        sheet.autoSizeColumn(1);
        cell.setCellStyle(noStyle);

        cell = row.createCell(2);
        cell.setCellValue("Preferensi");
        sheet.autoSizeColumn(2);
        cell.setCellStyle(noStyle);

        cell = row.createCell(3);
        cell.setCellValue("Ranking");
        sheet.autoSizeColumn(3);
        cell.setCellStyle(noStyle);

        int i = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont f = workbook.createFont();
        f.setBold(false);
        f.setFontHeight(11);
        style.setFont(f);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        for (Karyawan t : sortedData){
            rows++;
            row = sheet.createRow(rows);

            cell = row.createCell(0);
            cell.setCellValue(i);

            cell.setCellStyle(style);
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(t.getNama());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(t.getPreferensi());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(i);
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);
            i++;
        }

        return rows;
    }

    public void export(HttpServletResponse response) throws IOException {
        kriteriaHeader();
        kriteriaRows();
        kriteriaHeader(kriteriaRows(),  "Normalisasi Terbobot");
        normalisasiRows();
        kriteriaHeader(normalisasiRows(),  "Matriks Solusi Ideal");
        matriksSolusiIdeal();
        solusiPreferensi();
        rangking();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
