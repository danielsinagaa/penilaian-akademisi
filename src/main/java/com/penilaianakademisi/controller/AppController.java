package com.penilaianakademisi.controller;

import com.penilaianakademisi.entity.*;
import com.penilaianakademisi.entity.excel.DosenReportExcel;
import com.penilaianakademisi.entity.excel.KaryawanReportExcel;
import com.penilaianakademisi.entity.excel.MahasiswaReportExcel;
import com.penilaianakademisi.entity.model.*;
import com.penilaianakademisi.service.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AppController {
    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private DosenService dosenService;

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private MahasiswaaplusminService mahasiswaaplusminService;

    @Autowired
    private DosenAplusminService dosenAplusminService;

    @Autowired
    private KaryawanAplusminService karyawanAplusminService;

    @Autowired
    private DosenDescService dosenDescService;

    @Autowired
    private MahasiswaDescService mahasiswaDescService;

    @Autowired
    private KaryawanDescService karyawanDescService;

    private Login userLogin = new Login("admin","password",true,"");

    private final String baseDir = "/Users/macbook/Documents/skripsi/penilaian-akademisi/src/main/resources/static/upload";

    @PostMapping("/upload/dosen")
    public String uploadDosen(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File(baseDir, "excelData.xlsx"));

        FileInputStream inputStream = new FileInputStream(baseDir + "/excelData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        int lastCell = sheet.getRow(1).getLastCellNum();

        if (lastCell != 11 ){
            return "redirect:/dosen/detail";
        }

        importDosenExcel(sheet, lastRow, lastCell);

        return "redirect:/dosen/detail";
    }

    @PostMapping("/upload/mahasiswa")
    public String uploadMahasiswa(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File(baseDir, "excelData.xlsx"));

        FileInputStream inputStream = new FileInputStream(baseDir + "/excelData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        int lastCell = sheet.getRow(1).getLastCellNum();

        if (lastCell != 8 ){
            return "redirect:/mahasiswa/detail";
        }

        importMahasiswaExcel(sheet, lastRow, lastCell);

        return "redirect:/mahasiswa/detail";
    }

    @PostMapping("/upload/karyawan")
    public String uploadKaryawan(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File(baseDir, "excelData.xlsx"));

        FileInputStream inputStream = new FileInputStream(baseDir + "/excelData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        int lastCell = sheet.getRow(1).getLastCellNum();

        if (lastCell != 6){
            return "redirect:/karyawan/detail";
        }

        importKaryawanExcel(sheet, lastRow, lastCell);

        return "redirect:/karyawan/detail";
    }

    private void importDosenExcel(XSSFSheet sheet, int lastRow, int lastCell) {
        for (int i = 1; i <= lastRow; i++){
            DosenRequest dosenRequest = new DosenRequest();
            XSSFRow row = sheet.getRow(i);

            for (int c = 1; c <= lastCell; c++){

                XSSFCell cell = row.getCell(c);

                if (c == 1){
                    dosenRequest.setNama(cell.getStringCellValue());
                } else if (c == 2){
                    dosenRequest.setK1(cell.getNumericCellValue());
                } else if (c == 3){
                    dosenRequest.setK2(cell.getNumericCellValue());
                } else if (c == 4){
                    dosenRequest.setK3(cell.getNumericCellValue());
                } else if (c == 5){
                    dosenRequest.setK4(cell.getNumericCellValue());
                } else if (c == 6){
                    dosenRequest.setK5(cell.getNumericCellValue());
                } else if (c == 7){
                    dosenRequest.setK6(cell.getNumericCellValue());
                } else if (c == 8){
                    dosenRequest.setK7(cell.getNumericCellValue());
                } else if (c == 9){
                    dosenRequest.setK8(cell.getNumericCellValue());
                } else if (c == 10){
                    dosenRequest.setK9(cell.getNumericCellValue());
                }
            }
            dosenService.save(dosenRequest);
        }
    }

    private void importMahasiswaExcel(XSSFSheet sheet, int lastRow, int lastCell) {
        for (int i = 1; i <= lastRow; i++){
            MahasiswaRequest request = new MahasiswaRequest();
            XSSFRow row = sheet.getRow(i);

            System.out.print(i + ". || ");
            for (int c = 1; c <= lastCell; c++){

                XSSFCell cell = row.getCell(c);

                if (c == 1){
                    request.setNama(cell.getStringCellValue());
                } else if (c == 2){
                    request.setK1(cell.getNumericCellValue());
                } else if (c == 3){
                    request.setK2(cell.getNumericCellValue());
                } else if (c == 4){
                    request.setK3(cell.getNumericCellValue());
                } else if (c == 5){
                    request.setK4(cell.getNumericCellValue());
                } else if (c == 6){
                    request.setK5(cell.getNumericCellValue());
                } else if (c == 7){
                    request.setK6(cell.getNumericCellValue());
                }
            }
            mahasiswaService.save(request);
        }
    }

    private void importKaryawanExcel(XSSFSheet sheet, int lastRow, int lastCell) {
        for (int i = 1; i <= lastRow; i++){
            KaryawanRequest request = new KaryawanRequest();
            XSSFRow row = sheet.getRow(i);

            for (int c = 1; c <= lastCell; c++){

                XSSFCell cell = row.getCell(c);

                if (c == 1){
                    request.setNama(cell.getStringCellValue());
                } else if (c == 2){
                    request.setK1(cell.getNumericCellValue());
                } else if (c == 3){
                    request.setK2(cell.getNumericCellValue());
                } else if (c == 4){
                    request.setK3(cell.getNumericCellValue());
                } else if (c == 5){
                    request.setK4(cell.getNumericCellValue());
                }
            }
            karyawanService.save(request);
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        Login loginRequest = new Login();
        userLogin.setLogin(false);
        model.addAttribute("loginRequest", loginRequest);
        return "login_page";
    }

    @PostMapping("/login")
    public String logging(@ModelAttribute Login loginRequest){
        if (loginRequest.getUsername().equals(userLogin.getUsername()) && loginRequest.getPassword().equals(userLogin.getPassword())){
            userLogin.setLogin(true);
            return "redirect:/";
        }

        return "redirect:/login";
    }

    @GetMapping("")
    public String home(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        Integer dosen = dosenService.findAll().size();
        Integer mahasiswa = mahasiswaService.findAll().size();
        Integer karyawan = karyawanService.findAll().size();
        model.addAttribute("dosen", dosen.toString());
        model.addAttribute("mahasiswa", mahasiswa.toString());
        model.addAttribute("karyawan", karyawan.toString());
        return "home";
    }

    @GetMapping("/dosen")
    public String dosen(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        if (dosenService.findAll().size() == 0){
            return "list_dosen_empty";
        }
        model.addAttribute("dosenList", dosenService.findAll());
        model.addAttribute("aPlusMin", dosenAplusminService.findAll());
        return "list_dosen";
    }

    @GetMapping("/karyawan")
    public String karyawan(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        if (karyawanService.findAll().size() == 0){
            return "list_karyawan_empty";
        }
        model.addAttribute("karyawanList", karyawanService.findAll());
        model.addAttribute("aPlusMin", karyawanAplusminService.findAll().get(0));
        return "list_karyawan";
    }

    @GetMapping("/karyawan/detail")
    public String karyawanDetail(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        SortedSet<Karyawan> sortedKaryawan = new TreeSet<>(new KaryawanComparator());
        SortedSet<Karyawan> karyawanSet = new TreeSet<>(new KaryawanComparatorName().reversed());
        KaryawanAplusMin aPlusMin = new KaryawanAplusMin();

        if (karyawanAplusminService.findAll().size() != 0){
            aPlusMin = karyawanAplusminService.findAll().get(0);
        }

        karyawanService.findAll().forEach(it -> {
            sortedKaryawan.add(it);
            karyawanSet.add(it);
        });

        model.addAttribute("karyawanList", karyawanSet);
        model.addAttribute("sortedKaryawanList", sortedKaryawan);
        model.addAttribute("aPlusMin", aPlusMin);
        model.addAttribute("desc", karyawanDescService.get());
        return "detail_karyawan";
    }

    @GetMapping("/mahasiswa/detail")
    public String mahasiswaDetail(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        SortedSet<Mahasiswa> sortedMahasiswa = new TreeSet<>(new MahasiswaComparator());
        SortedSet<Mahasiswa> mahasiswaSet = new TreeSet<> (new MahasiswaComparatorName().reversed());
        MahasiswaAplusMin aPlusMin = new MahasiswaAplusMin();

        if (mahasiswaaplusminService.findAll().size() != 0){
            aPlusMin = mahasiswaaplusminService.findAll().get(0);
        }

        mahasiswaService.findAll().forEach(it -> {
            mahasiswaSet.add(it);
            sortedMahasiswa.add(it);
        });

        model.addAttribute("mahasiswaList", mahasiswaSet);
        model.addAttribute("sortedMahasiswaList", sortedMahasiswa);
        model.addAttribute("aPlusMin", aPlusMin);
        model.addAttribute("desc", mahasiswaDescService.get());
        return "detail_mahasiswa";
    }

    @GetMapping("/dosen/detail")
    public String dosenDetail(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        SortedSet<Dosen> sortedDosen = new TreeSet<>(new DosenComparator());
        SortedSet<Dosen> dosenSet = new TreeSet<>(new DosenComparatorName().reversed());
        DosenAplusmin aPlusMin = new DosenAplusmin();

        if (dosenAplusminService.findAll().size() != 0){
            aPlusMin = dosenAplusminService.findAll().get(0);
        }

        dosenService.findAll().forEach(it -> {
            sortedDosen.add(it);
            dosenSet.add(it);
        });

        model.addAttribute("dosenList", dosenSet);
        model.addAttribute("sortedDosenList", sortedDosen);
        model.addAttribute("aPlusMin", aPlusMin);
        model.addAttribute("desc", dosenDescService.get());
        return "detail_dosen";
    }

    @GetMapping("/mahasiswa")
    public String mahasiswa(Model model){
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        if (mahasiswaService.findAll().size() == 0){
            return "list_mahasiswa_empty";
        }
        model.addAttribute("mahasiswaList", mahasiswaService.findAll());
        model.addAttribute("aPlusMin", mahasiswaaplusminService.findAll());
        return "list_mahasiswa";
    }

    @GetMapping("/newKaryawanForm")
    public String newKaryawanForm(Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // create model attribute to bind form data
        KaryawanRequest karyawan = new KaryawanRequest();
        model.addAttribute("karyawan", karyawan);
        return "new_karyawan";
    }

    @GetMapping("/newMahasiswaForm")
    public String newMahasiswaForm(Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // create model attribute to bind form data
        MahasiswaRequest mahasiswa = new MahasiswaRequest();
        model.addAttribute("mahasiswa", mahasiswa);
        return "new_mahasiswa";
    }

    @GetMapping("/newDosenForm")
    public String newDosenForm(Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // create model attribute to bind form data
        DosenRequest dosen = new DosenRequest();
        model.addAttribute("dosen", dosen);
        return "new_dosen";
    }

    @PostMapping("/saveDosen")
    public String saveDosen(@ModelAttribute("dosen") DosenRequest dosen, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        dosenService.save(dosen);
        return "redirect:/dosen";
    }

    @PostMapping("/saveMahasiswa")
    public String saveMahasiswa(@ModelAttribute("dosen") MahasiswaRequest mahasiswa, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        mahasiswaService.save(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @PostMapping("/saveKaryawan")
    public String saveKaryawan(@ModelAttribute("dosen") KaryawanRequest karyawan, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        karyawanService.save(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/updateDosen/{id}")
    public String updateDosen(@PathVariable( value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        // get employee from the service
        Dosen dosen = dosenService.findById(id);
        model.addAttribute("dosen", new DosenEdit(dosen));
        return "update_dosen";
    }

    @GetMapping("/updateDescDosen")
    public String updateDescDosen(Model model) {
        DosenDesc desc = dosenDescService.get();
        model.addAttribute("desc", desc);
        return "update_desc_dosen";
    }

    @GetMapping("/updateDescMahasiswa")
    public String updateDescMahasiswa(Model model) {
        MahasiswaDesc desc = mahasiswaDescService.get();
        model.addAttribute("desc", desc);
        return "update_mahasiswa_dosen";
    }

    @GetMapping("/updateDescKaryawan")
    public String updateDescKaryawan(Model model) {
        KaryawanDesc desc = karyawanDescService.get();
        model.addAttribute("desc", desc);
        return "update_karyawan_dosen";
    }

    @GetMapping("/updateMahasiswa/{id}")
    public String updateMahasiswa(@PathVariable( value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        // get employee from the service
        Mahasiswa mahasiswa = mahasiswaService.findById(id);
        model.addAttribute("mahasiswa", new MahasiswaEdit(mahasiswa));
        return "update_mahasiswa";
    }

    @GetMapping("/updateKaryawan/{id}")
    public String updateKaryawan(@PathVariable( value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        // get employee from the service
        Karyawan karyawan = karyawanService.findById(id);
        model.addAttribute("karyawan", new KaryawanEdit(karyawan));
        return "update_karyawan";
    }

    @PostMapping("/editDosen")
    public String editDosen(@ModelAttribute("dosen") DosenEdit dosen, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        dosenService.edit(dosen);
        return "redirect:/dosen";
    }

    @PostMapping("/editDescDosen")
    public String editDescDosen(@ModelAttribute("desc") DosenDesc desc, Model model) {

        dosenDescService.save(desc);
        return "redirect:/dosen/detail";
    }

    @PostMapping("/editMahasiswaDesc")
    public String editMahasiswaDesc(@ModelAttribute("desc") MahasiswaDesc desc, Model model) {

        mahasiswaDescService.save(desc);
        return "redirect:/mahasiswa/detail";
    }

    @PostMapping("/editKaryawanDesc")
    public String editKaryawanDesc(@ModelAttribute("desc") KaryawanDesc desc, Model model) {

        karyawanDescService.save(desc);
        return "redirect:/karyawan/detail";
    }

    @PostMapping("/editMahasiswa")
    public String editMahasiswa(@ModelAttribute("dosen") MahasiswaEdit mahasiswa, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        mahasiswaService.edit(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @PostMapping("/editKaryawan")
    public String editKaryawan(@ModelAttribute("dosen") KaryawanEdit karyawan, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }
        // save employee to database
        karyawanService.edit(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/deleteDosen/{id}")
    public String deleteDosen(@PathVariable (value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        dosenService.delete(id);

        if (dosenService.findAll().size() == 0){
            return "list_dosen_empty";
        }

        if (dosenService.findAll().size() == 0 ){
            return "list_dosen_empty";
        }
        return "redirect:/dosen";
    }

    @GetMapping("/deleteMahasiswa/{id}")
    public String deleteMahasiswa(@PathVariable (value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        mahasiswaService.delete(id);

        if (mahasiswaService.findAll().size() == 0){
            return "list_mahasiswa_empty";
        }

        mahasiswaService.delete(id);
        if (mahasiswaService.findAll().size() == 0 ){
            return "list_mahasiswa_empty";
        }
        return "redirect:/mahasiswa";
    }

    @GetMapping("/deleteKaryawan/{id}")
    public String deleteKaryawan(@PathVariable (value = "id") String id, Model model) {
        if (!userLogin.getLogin()){
            Login loginRequest = new Login();
            model.addAttribute("loginRequest", loginRequest);
            userLogin.setLogin(false);
            return "login_page";
        }

        karyawanService.delete(id);
        if (karyawanService.findAll().size() == 0){
            return "list_karyawan_empty";
        }
        karyawanService.delete(id);
        if (karyawanService.findAll().size() == 0 ){
            return "list_karyawan_empty";
        }
        return "redirect:/karyawan";
    }

    @GetMapping("/download/{report}")
    public void downloadReportExcel(HttpServletResponse response, @PathVariable (value = "report") String report) throws IOException {
        String title = "Dosen";

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        if (report.equals("karyawan")){
            title = "karyawan";
            String fileName = title + "_Report_" + currentDate + ".xlsx";
            String headerValue = "attachment; filename=" + fileName;
            response.setHeader(headerKey, headerValue);
            SortedSet<Karyawan> sortedData = new TreeSet<>(new KaryawanComparator());
            Set<Karyawan> dataSet = new HashSet<>(karyawanService.findAll());
            KaryawanAplusMin aPlusMin = new KaryawanAplusMin();
            dataSet.forEach(it -> sortedData.add(it));
            if (karyawanAplusminService.findAll().size() != 0){
                aPlusMin = karyawanAplusminService.findAll().get(0);
            }
            KaryawanReportExcel excel = new KaryawanReportExcel(dataSet, sortedData, aPlusMin);
            excel.export(response);

        } else if (report.equals("mahasiswa")){
            title = "mahasiswa";
            String fileName = title + "_Report_" + currentDate + ".xlsx";
            String headerValue = "attachment; filename=" + fileName;
            response.setHeader(headerKey, headerValue);
            SortedSet<Mahasiswa> sortedData = new TreeSet<>(new MahasiswaComparator());
            Set<Mahasiswa> dataSet = new HashSet<>(mahasiswaService.findAll());
            MahasiswaAplusMin aPlusMin = new MahasiswaAplusMin();
            dataSet.forEach(it -> sortedData.add(it));
            if (mahasiswaaplusminService.findAll().size() != 0){
                aPlusMin = mahasiswaaplusminService.findAll().get(0);
            }
            MahasiswaReportExcel excel = new MahasiswaReportExcel(dataSet, sortedData, aPlusMin);
            excel.export(response);

        }else {
            String fileName = title + "_Report_" + currentDate + ".xlsx";
            String headerValue = "attachment; filename=" + fileName;
            response.setHeader(headerKey, headerValue);
            SortedSet<Dosen> sortedDosen = new TreeSet<>(new DosenComparator());
            Set<Dosen> dosenSet = new HashSet<>(dosenService.findAll());
            DosenAplusmin aPlusMin = new DosenAplusmin();
            dosenSet.forEach(it -> sortedDosen.add(it));
            if (dosenAplusminService.findAll().size() != 0){
                aPlusMin = dosenAplusminService.findAll().get(0);
            }
            DosenReportExcel excel = new DosenReportExcel(dosenSet, sortedDosen, aPlusMin);
            excel.export(response);
        }

    }
}
