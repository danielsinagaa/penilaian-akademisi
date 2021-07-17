package com.penilaianakademisi.entity.model;

import com.penilaianakademisi.entity.Karyawan;

import java.util.Comparator;

public class KaryawanComparator implements Comparator<Karyawan> {
    @Override
    public int compare(Karyawan o1, Karyawan o2) {
        return o1.compareTo(o2);
    }
}
