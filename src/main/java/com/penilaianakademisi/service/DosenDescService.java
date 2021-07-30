package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.DosenDesc;
import com.penilaianakademisi.entity.model.DosenRequest;
import com.penilaianakademisi.repository.DosenDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DosenDescService extends AbstractService<DosenDesc> {
    @Autowired
    private DosenService dosenService;

    public DosenDescService(DosenDescRepository repository) {
        super(repository);
    }

    public DosenDesc get(){
        return repository.findAll().get(0);
    }

    @Override
    public DosenDesc save(DosenDesc entity) {
        super.save(entity);

        dosenService.save(new DosenRequest("description example"));

        return entity;
    }
}
