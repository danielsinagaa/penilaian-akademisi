package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.DosenDesc;
import com.penilaianakademisi.repository.DosenDescRepository;
import org.springframework.stereotype.Service;

@Service
public class DosenDescService extends AbstractService<DosenDesc> {
    public DosenDescService(DosenDescRepository repository) {
        super(repository);
    }

    public DosenDesc get(){
        return repository.findAll().get(0);
    }
}
