package com.penilaianakademisi.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KaryawanRequest {
    private String nama;

    private Double k1;

    private Double k2;

    private Double k3;

    private Double k4;

    public KaryawanRequest(String request) {
        this.nama = request;
        this.k1 = 1.5;
        this.k2 = 1.5;
        this.k3 = 1.5;
        this.k4 = 1.5;
    }
}
