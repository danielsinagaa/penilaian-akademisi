package com.penilaianakademisi.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MahasiswaRequest {
    private String nama;

    private Double k1;

    private Double k2;

    private Double k3;

    private Double k4;

    private Double k5;

    private Double k6;

    public MahasiswaRequest(String request) {
        this.nama = request;
        this.k1 = 1.5;
        this.k2 = 1.5;
        this.k3 = 1.5;
        this.k4 = 1.5;
        this.k5 = 1.5;
        this.k6 = 1.5;
    }
}
