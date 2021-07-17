package com.penilaianakademisi.entity.model;

import com.penilaianakademisi.entity.Mahasiswa;

import java.util.Comparator;

public class MahasiswaComparator implements Comparator<Mahasiswa> {
    @Override
    public int compare(Mahasiswa o1, Mahasiswa o2) {
        return o1.compareTo(o2);
    }
}
