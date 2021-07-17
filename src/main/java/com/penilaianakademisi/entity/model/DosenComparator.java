package com.penilaianakademisi.entity.model;

import com.penilaianakademisi.entity.Dosen;

import java.util.Comparator;

public class DosenComparator implements Comparator<Dosen> {

    @Override
    public int compare(Dosen o1, Dosen o2) {
        return o1.compareTo(o2);
    }
}
