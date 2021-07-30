package com.penilaianakademisi.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DosenRequest {
    private String nama;

    private Double k1;

    private Double k2;

    private Double k3;

    private Double k4;

    private Double k5;

    private Double k6;

    private Double k7;

    private Double k8;

    private Double k9;

    public DosenRequest(String request) {
        this.nama = request;
        this.k1 = 1.5;
        this.k2 = 1.5;
        this.k3 = 1.5;
        this.k4 = 1.5;
        this.k5 = 1.5;
        this.k6 = 1.5;
        this.k7 = 1.5;
        this.k8 = 1.5;
        this.k9 = 1.5;
    }
}
