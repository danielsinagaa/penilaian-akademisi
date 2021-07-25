package com.penilaianakademisi.controller;

import com.penilaianakademisi.entity.*;
import com.penilaianakademisi.entity.excel.DosenReportExcel;
import com.penilaianakademisi.entity.excel.KaryawanReportExcel;
import com.penilaianakademisi.entity.excel.MahasiswaReportExcel;
import com.penilaianakademisi.entity.model.*;
import com.penilaianakademisi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
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

    private Login userLogin = new Login("admin","password",false,"");

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
        model.addAttribute("aPlusMin", karyawanAplusminService.findAll());
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
        Set<Karyawan> karyawanSet = new HashSet<>(karyawanService.findAll());
        KaryawanAplusMin aPlusMin = new KaryawanAplusMin();

        if (karyawanAplusminService.findAll().size() != 0){
            aPlusMin = karyawanAplusminService.findAll().get(0);
        }

        karyawanSet.forEach(it -> sortedKaryawan.add(it));

        model.addAttribute("karyawanList", karyawanService.findAll());
        model.addAttribute("sortedKaryawanList", sortedKaryawan);
        model.addAttribute("aPlusMin", aPlusMin);
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
        Set<Mahasiswa> mahasiswaSet = new HashSet<>(mahasiswaService.findAll());
        MahasiswaAplusMin aPlusMin = new MahasiswaAplusMin();

        if (mahasiswaaplusminService.findAll().size() != 0){
            aPlusMin = mahasiswaaplusminService.findAll().get(0);
        }

        mahasiswaSet.forEach(it -> {
            System.out.println(it.toString());
            sortedMahasiswa.add(it);
        });

        model.addAttribute("mahasiswaList", mahasiswaService.findAll());
        model.addAttribute("sortedMahasiswaList", sortedMahasiswa);
        model.addAttribute("aPlusMin", aPlusMin);
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
        Set<Dosen> dosenSet = new HashSet<>(dosenService.findAll());
        DosenAplusmin aPlusMin = new DosenAplusmin();

        if (dosenAplusminService.findAll().size() != 0){
            aPlusMin = dosenAplusminService.findAll().get(0);
        }

        dosenSet.forEach(it -> sortedDosen.add(it));

        model.addAttribute("dosenList", dosenService.findAll());
        model.addAttribute("sortedDosenList", sortedDosen);
        model.addAttribute("aPlusMin", aPlusMin);
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
