package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.KaryawanDesc;
import com.penilaianakademisi.repository.KaryawanDescRepository;
import org.springframework.stereotype.Service;

@Service
public class KaryawanDescService extends AbstractService<KaryawanDesc> {
    public KaryawanDescService(KaryawanDescRepository repository) {
        super(repository);
    }

    public KaryawanDesc get(){
        return repository.findAll().get(0);
    }
}
