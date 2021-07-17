package com.penilaianakademisi.entity.model;

import com.penilaianakademisi.entity.Mahasiswa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MahasiswaEdit {
    private String id;

    private String nama;

    private Double k1;

    private Double k2;

    private Double k3;

    private Double k4;

    private Double k5;

    private Double k6;

    public MahasiswaEdit(Mahasiswa t){
        id = t.getId();
        nama = t.getNama();
        k1 = t.getK1();
        k2 = t.getK2();
        k3 = t.getK3();
        k4 = t.getK4();
        k5 = t.getK5();
        k6 = t.getK6();
    }
}
