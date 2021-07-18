package com.penilaianakademisi.entity;

import com.penilaianakademisi.entity.model.MahasiswaRequest;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
@Data
public class Mahasiswa extends Timestamp{
    @GeneratedValue(generator = "mahasiswa_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "mahasiswa_id", strategy = "uuid")
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
    private Double dPlus;

    @Column(nullable = false)
    private Double dMin;

    @Column(nullable = false)
    private Double preferensi;

    public Mahasiswa() {
    }

    public Mahasiswa(MahasiswaRequest request){
        this.nama = request.getNama();
        this.k1 = request.getK1();
        this.k2 = request.getK2();
        this.k3 = request.getK3();
        this.k4 = request.getK4();
        this.k5 = request.getK5();
        this.k6 = request.getK6();
    }

    public int compareTo(Mahasiswa t) {
        if (equals(t)) {
            return 0;
        }
        int diff = ( t.getPreferensi()).compareTo(this.preferensi);
        if (diff == 0) {
            return ( t.nama).compareTo(this.nama);
        }
        return diff;
    }

    public int compareNameTo(Mahasiswa t) {
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
