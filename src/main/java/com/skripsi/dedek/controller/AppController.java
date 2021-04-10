package com.skripsi.dedek.controller;

import com.skripsi.dedek.entity.Dosen;
import com.skripsi.dedek.entity.Karyawan;
import com.skripsi.dedek.entity.Mahasiswa;
import com.skripsi.dedek.repository.DosenRepository;
import com.skripsi.dedek.repository.KaryawanRepository;
import com.skripsi.dedek.repository.MahasiswaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private DosenRepository dosenRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @GetMapping("")
    public String home(){
        return "index";
    }

    @GetMapping("/dosen")
    public String dosen(Model model){
        model.addAttribute("dosenList", dosenRepository.findAll());
        return "dosen_list";
    }

    @GetMapping("/karyawan")
    public String karyawan(Model model){
        model.addAttribute("karyawanList", karyawanRepository.findAll());
        return "karyawan_list";
    }

    @GetMapping("/mahasiswa")
    public String mahasiswa(Model model){
        model.addAttribute("mahasiswaList", mahasiswaRepository.findAll());
        return "mahasiswa_list";
    }

    @GetMapping("/newKaryawanForm")
    public String newKaryawanForm(Model model) {
        // create model attribute to bind form data
        Karyawan karyawan = new Karyawan();
        model.addAttribute("karyawan", karyawan);
        return "new_karyawan";
    }

    @GetMapping("/newMahasiswaForm")
    public String newMahasiswaForm(Model model) {
        // create model attribute to bind form data
        Mahasiswa mahasiswa = new Mahasiswa();
        model.addAttribute("mahasiswa", mahasiswa);
        return "new_mahasiswa";
    }

    @GetMapping("/newDosenForm")
    public String newDosenForm(Model model) {
        // create model attribute to bind form data
        Dosen dosen = new Dosen();
        model.addAttribute("dosen", dosen);
        return "new_dosen";
    }

    @PostMapping("/saveDosen")
    public String saveDosen(@ModelAttribute("dosen") Dosen dosen) {
        // save employee to database
        dosen.setTotal(dosen.getK1() + dosen.getK2() + dosen.getK3() + dosen.getK4() + dosen.getK5() + dosen.getK6() + dosen.getK7() + dosen.getK8() + dosen.getK9());
        dosenRepository.save(dosen);
        return "redirect:/dosen";
    }

    @PostMapping("/saveMahasiswa")
    public String saveMahasiswa(@ModelAttribute("dosen") Mahasiswa mahasiswa) {
        // save employee to database
        mahasiswa.setTotal(mahasiswa.getK1() + mahasiswa.getK2() + mahasiswa.getK3() + mahasiswa.getK4() + mahasiswa.getK5() + mahasiswa.getK6());
        mahasiswaRepository.save(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @PostMapping("/saveKaryawan")
    public String saveKaryawan(@ModelAttribute("dosen") Karyawan karyawan) {
        // save employee to database
        karyawan.setTotal(karyawan.getK1() + karyawan.getK2() + karyawan.getK3() + karyawan.getK4());
        karyawanRepository.save(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/updateDosen/{id}")
    public String updateDosen(@PathVariable( value = "id") String id, Model model) {

        // get employee from the service
        Dosen dosen = dosenRepository.findById(id).orElseThrow();

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("dosen", dosen);
        return "update_dosen";
    }

    @GetMapping("/updateMahasiswa/{id}")
    public String updateMahasiswa(@PathVariable( value = "id") String id, Model model) {

        // get employee from the service
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id).orElseThrow();

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("mahasiswa", mahasiswa);
        return "update_mahasiswa";
    }

    @GetMapping("/updateKaryawan/{id}")
    public String updateKaryawan(@PathVariable( value = "id") String id, Model model) {

        // get employee from the service
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("karyawan", karyawan);
        return "update_karyawan";
    }

    @GetMapping("/deleteDosen/{id}")
    public String deleteDosen(@PathVariable (value = "id") String id) {

        dosenRepository.deleteById(id);
        return "redirect:/dosen";
    }

    @GetMapping("/deleteMahasiswa/{id}")
    public String deleteMahasiswa(@PathVariable (value = "id") String id) {

        mahasiswaRepository.deleteById(id);
        return "redirect:/mahasiswa";
    }

    @GetMapping("/deleteKaryawan/{id}")
    public String deleteKaryawan(@PathVariable (value = "id") String id) {

        karyawanRepository.deleteById(id);
        return "redirect:/karyawan";
    }
}
