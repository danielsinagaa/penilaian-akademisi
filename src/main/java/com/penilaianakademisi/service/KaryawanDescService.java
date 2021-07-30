package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.DosenDesc;
import com.penilaianakademisi.entity.Karyawan;
import com.penilaianakademisi.entity.KaryawanDesc;
import com.penilaianakademisi.entity.model.KaryawanRequest;
import com.penilaianakademisi.repository.KaryawanDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KaryawanDescService extends AbstractService<KaryawanDesc> {
    @Autowired
    private KaryawanService karyawanService;
    public KaryawanDescService(KaryawanDescRepository repository) {
        super(repository);
    }

    public KaryawanDesc get(){
        return repository.findAll().get(0);
    }

    @Override
    public KaryawanDesc save(KaryawanDesc entity) {
        super.save(entity);

        karyawanService.save(new KaryawanRequest("description example"));

        return entity;
    }
}
