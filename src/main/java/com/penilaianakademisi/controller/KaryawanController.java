package com.penilaianakademisi.controller;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.Karyawan;
import com.penilaianakademisi.entity.Mahasiswa;
import com.penilaianakademisi.entity.model.DosenRequest;
import com.penilaianakademisi.entity.model.KaryawanRequest;
import com.penilaianakademisi.entity.model.MahasiswaRequest;
import com.penilaianakademisi.service.DosenService;
import com.penilaianakademisi.service.KaryawanService;
import com.penilaianakademisi.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
@RestController
public class KaryawanController {
    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private DosenService dosenService;

    @PostMapping("/karyawan")
    public List<Karyawan> add(@RequestBody KaryawanRequest request){
        karyawanService.save(request);

        return karyawanService.findAll();
    }

    @GetMapping("/karyawan")
    public List<Karyawan> findAll(){

        return karyawanService.findAll();
    }

    @PostMapping("/mahasiswa")
    public List<Mahasiswa> add(@RequestBody MahasiswaRequest request){
        mahasiswaService.save(request);

        return mahasiswaService.findAll();
    }

    @PostMapping("/dosen")
    public List<Dosen> add(@RequestBody DosenRequest request){
        dosenService.save(request);

        return dosenService.findAll();
    }
}
