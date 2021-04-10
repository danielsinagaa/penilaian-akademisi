package com.skripsi.dedek.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
@Data
public class Karyawan  extends Timestamp{
    @GeneratedValue(generator = "karyawan_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "karyawan_id", strategy = "uuid")
    @Id
    private String id;

    @Column(nullable = false)
    private String nama;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double k1;

    @Column(nullable = false)
    private Double k2;

    @Column(nullable = false)
    private Double k3;

    @Column(nullable = false)
    private Double k4;

}
