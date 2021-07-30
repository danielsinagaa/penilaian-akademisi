package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.DosenDesc;
import com.penilaianakademisi.entity.Mahasiswa;
import com.penilaianakademisi.entity.MahasiswaDesc;
import com.penilaianakademisi.entity.model.MahasiswaRequest;
import com.penilaianakademisi.repository.MahasiswaDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MahasiswaDescService extends AbstractService<MahasiswaDesc> {
    @Autowired
    private MahasiswaService mahasiswaService;

    public MahasiswaDescService(MahasiswaDescRepository repository) {
        super(repository);
    }

    public MahasiswaDesc get(){
        return repository.findAll().get(0);
    }

    @Override
    public MahasiswaDesc save(MahasiswaDesc entity) {
        super.save(entity);

        mahasiswaService.save(new MahasiswaRequest("description example"));

        return entity;
    }
}
