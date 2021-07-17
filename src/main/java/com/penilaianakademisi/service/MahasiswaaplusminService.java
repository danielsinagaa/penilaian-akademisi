package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.MahasiswaAplusMin;
import com.penilaianakademisi.repository.MahasiswaAplusminRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MahasiswaaplusminService extends AbstractService<MahasiswaAplusMin> {
    public MahasiswaaplusminService(MahasiswaAplusminRepository repository) {
        super(repository);
    }

    @Override
    public MahasiswaAplusMin save(MahasiswaAplusMin entity) {
        if (findAll().size() > 0){
            deleteById(findAll().get(0).getId());
        }

        return super.save(entity);
    }
}
