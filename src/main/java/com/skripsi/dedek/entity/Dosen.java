package com.skripsi.dedek.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
@Data
public class Dosen extends Timestamp {
    @GeneratedValue(generator = "dosen_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "dosen_id", strategy = "uuid")
    @Id
    private String id;

    @Column(nullable = false)
    private String nama;

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

    @Column(nullable = false)
    private Double k7;

    @Column(nullable = false)
    private Double k8;

    @Column(nullable = false)
    private Double k9;

    @Column(nullable = false)
    private Double total;
}
