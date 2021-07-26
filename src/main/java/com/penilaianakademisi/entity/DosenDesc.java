package com.penilaianakademisi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "dosen_desc")
@Entity
@Data
@NoArgsConstructor
public class DosenDesc {
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

    @Column
    private String deskripsiK7;

    @Column
    private String deskripsiK8;

    @Column
    private String deskripsiK9;

    @Column(nullable = false)
    private Integer k1;

    @Column(nullable = false)
    private Integer k2;

    @Column(nullable = false)
    private Integer k3;

    @Column(nullable = false)
    private Integer k4;

    @Column(nullable = false)
    private Integer k5;

    @Column(nullable = false)
    private Integer k6;

    @Column(nullable = false)
    private Integer k7;

    @Column(nullable = false)
    private Integer k8;

    @Column(nullable = false)
    private Integer k9;

    public DosenDesc(String id, Integer k1, Integer k2, Integer k3, Integer k4, Integer k5, Integer k6, Integer k7, Integer k8, Integer k9) {
        this.id = id;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
        this.k6 = k6;
        this.k7 = k7;
        this.k8 = k8;
        this.k9 = k9;
    }
}
