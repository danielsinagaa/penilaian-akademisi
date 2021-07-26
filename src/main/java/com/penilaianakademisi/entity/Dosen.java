package com.penilaianakademisi.entity;

import com.penilaianakademisi.entity.model.DosenRequest;
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
    private Double pembagik1;

    @Column(nullable = false)
    private Double pembagik2;

    @Column(nullable = false)
    private Double pembagik3;

    @Column(nullable = false)
    private Double pembagik4;

    @Column(nullable = false)
    private Double pembagik5;

    @Column(nullable = false)
    private Double pembagik6;

    @Column(nullable = false)
    private Double pembagik7;

    @Column(nullable = false)
    private Double pembagik8;

    @Column(nullable = false)
    private Double pembagik9;

    @Column(nullable = false)
    private Double ternormalisasik1;

    @Column(nullable = false)
    private Double ternormalisasik2;

    @Column(nullable = false)
    private Double ternormalisasik3;

    @Column(nullable = false)
    private Double ternormalisasik4;

    @Column(nullable = false)
    private Double ternormalisasik5;

    @Column(nullable = false)
    private Double ternormalisasik6;

    @Column(nullable = false)
    private Double ternormalisasik7;

    @Column(nullable = false)
    private Double ternormalisasik8;

    @Column(nullable = false)
    private Double ternormalisasik9;

    @Column(nullable = false)
    private Double dPlus;

    @Column(nullable = false)
    private Double dMin;

    @Column(nullable = false)
    private Double preferensi;

    public Dosen() {
    }

    public Dosen(DosenRequest request) {
        this.nama = request.getNama();
        this.k1 = request.getK1();
        this.k2 = request.getK2();
        this.k3 = request.getK3();
        this.k4 = request.getK4();
        this.k5 = request.getK5();
        this.k6 = request.getK6();
        this.k7 = request.getK7();
        this.k8 = request.getK8();
        this.k9 = request.getK9();
    }

    public int compareTo(Dosen t) {
        if (equals(t)) {
            return 0;
        }
        int diff = ( t.getPreferensi()).compareTo(this.preferensi);
        if (diff == 0) {
            return ( t.nama).compareTo(this.nama);
        }
        return diff;
    }

    public int compareNameTo(Dosen t) {
        if (equals(t)) {
            return 0;
        }
        int diff = ( t.getNama()).compareTo(this.nama);
        if (diff == 0) {
            return ( t.preferensi).compareTo(this.preferensi);
        }
        return diff;
    }
}
