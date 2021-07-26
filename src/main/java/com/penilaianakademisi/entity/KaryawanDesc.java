package com.penilaianakademisi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "karyawan_desc") @Data
public class KaryawanDesc {
    @Id
    private String id;

    @Column
    private String deskripsiK1;

    @Column
    private String deskripsiK2;

    @Column
    private String deskripsiK3;

    @Column
    private String deskripsiK4;

    @Column(nullable = false)
    private Double k1;

    @Column(nullable = false)
    private Double k2;

    @Column(nullable = false)
    private Double k3;

    @Column(nullable = false)
    private Double k4;
}
