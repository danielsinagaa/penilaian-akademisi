package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.DosenAplusmin;
import com.penilaianakademisi.repository.DosenAplusminRepository;
import org.springframework.stereotype.Service;

@Service
public class DosenAplusminService extends AbstractService<DosenAplusmin> {
    public DosenAplusminService(DosenAplusminRepository repository) {
        super(repository);
    }

    @Override
    public DosenAplusmin save(DosenAplusmin entity) {
        if (findAll().size() > 0){
            deleteById(findAll().get(0).getId());
        }

        return super.save(entity);
    }
}
