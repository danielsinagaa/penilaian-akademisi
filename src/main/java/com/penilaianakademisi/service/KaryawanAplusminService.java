package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.KaryawanAplusMin;
import com.penilaianakademisi.repository.KaryawanAplusminRepository;
import org.springframework.stereotype.Service;

@Service
public class KaryawanAplusminService extends AbstractService<KaryawanAplusMin> {
    public KaryawanAplusminService(KaryawanAplusminRepository repository) {
        super(repository);
    }

    @Override
    public KaryawanAplusMin save(KaryawanAplusMin entity) {

        if (findAll().size() > 0){
            deleteById(findAll().get(0).getId());
        }

        return super.save(entity);
    }
}
