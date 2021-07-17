package com.penilaianakademisi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
public class MahasiswaAplusMin extends Timestamp {
    @GeneratedValue(generator = "karyawan_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "karyawan_id", strategy = "uuid")
    @Id
    private String id;

    @Column
    private Double k1Plus;
    @Column
    private Double k2Plus;
    @Column
    private Double k3Plus;
    @Column
    private Double k4Plus;
    @Column
    private Double k5Plus;
    @Column
    private Double k6Plus;
    @Column
    private Double k1Min;
    @Column
    private Double k2Min;
    @Column
    private Double k3Min;
    @Column
    private Double k4Min;
    @Column
    private Double k5Min;
    @Column
    private Double k6Min;

    public MahasiswaAplusMin(Double k1Plus, Double k2Plus, Double k3Plus, Double k4Plus, Double k5Plus, Double k6Plus, Double k1Min, Double k2Min, Double k3Min, Double k4Min, Double k5Min, Double k6Min) {
        this.k1Plus = k1Plus;
        this.k2Plus = k2Plus;
        this.k3Plus = k3Plus;
        this.k4Plus = k4Plus;
        this.k5Plus = k5Plus;
        this.k6Plus = k6Plus;
        this.k1Min = k1Min;
        this.k2Min = k2Min;
        this.k3Min = k3Min;
        this.k4Min = k4Min;
        this.k5Min = k5Min;
        this.k6Min = k6Min;
    }
}
