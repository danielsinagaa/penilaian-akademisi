package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.MahasiswaDesc;
import com.penilaianakademisi.repository.MahasiswaDescRepository;
import org.springframework.stereotype.Service;

@Service
public class MahasiswaDescService extends AbstractService<MahasiswaDesc> {
    public MahasiswaDescService(MahasiswaDescRepository repository) {
        super(repository);
    }

    public MahasiswaDesc get(){
        return repository.findAll().get(0);
    }
}
