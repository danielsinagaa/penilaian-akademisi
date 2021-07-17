package com.penilaianakademisi.entity;

import com.penilaianakademisi.entity.model.KaryawanRequest;
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
    private Double k1;

    @Column(nullable = false)
    private Double k2;

    @Column(nullable = false)
    private Double k3;

    @Column(nullable = false)
    private Double k4;

    @Column(nullable = false)
    private Double pembagik1;

    @Column(nullable = false)
    private Double pembagik2;

    @Column(nullable = false)
    private Double pembagik3;

    @Column(nullable = false)
    private Double pembagik4;

    @Column(nullable = false)
    private Double ternormalisasik1;

    @Column(nullable = false)
    private Double ternormalisasik2;

    @Column(nullable = false)
    private Double ternormalisasik3;

    @Column(nullable = false)
    private Double ternormalisasik4;

    @Column(nullable = false)
    private Double dPlus;

    @Column(nullable = false)
    private Double dMin;

    @Column(nullable = false)
    private Double preferensi;

    public Karyawan() {
    }

    public Karyawan(KaryawanRequest karyawanRequest) {
        this.nama = karyawanRequest.getNama();
        this.k1 = karyawanRequest.getK1();
        this.k2 = karyawanRequest.getK2();
        this.k3 = karyawanRequest.getK3();
        this.k4 = karyawanRequest.getK4();
    }

    public int compareTo(Karyawan t) {
        if (equals(t)) {
            return 0;
        }
        int diff = ( t.getPreferensi()).compareTo(this.preferensi);
        if (diff == 0) {
            return ( t.nama).compareTo(this.nama);
        }
        return diff;
    }
}
