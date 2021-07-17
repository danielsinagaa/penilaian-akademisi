package com.penilaianakademisi.entity.model;

import com.penilaianakademisi.entity.Karyawan;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KaryawanEdit {
    private String id;

    private String nama;

    private Double k1;

    private Double k2;

    private Double k3;

    private Double k4;

    public KaryawanEdit(Karyawan t) {
        id = t.getId();
        nama = t.getNama();
        k1 = t.getK1();
        k2 = t.getK2();
        k3 = t.getK3();
        k4 = t.getK4();
    }
}
