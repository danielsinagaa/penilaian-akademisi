package com.penilaianakademisi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mahasiswa_desc") @Entity @Data
public class MahasiswaDesc {
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

    @Column
    private String deskripsiK5;

    @Column
    private String deskripsiK6;

    @Column(nullable = false)
    private Double k1;

    @Column(nullable = false)
    private Double k2;

    @Column(nullable = false)
    private Double k3;

    @Column(nullable = false)
    private Double k4;

    @Column(nullable = false)
    private Double k5;

    @Column(nullable = false)
    private Double k6;
}
