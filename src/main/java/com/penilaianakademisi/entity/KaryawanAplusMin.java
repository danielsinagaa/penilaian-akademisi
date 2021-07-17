package com.penilaianakademisi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Table
@Entity
@Data
@NoArgsConstructor
public class KaryawanAplusMin extends Timestamp{
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
    private Double k1Min;
    @Column
    private Double k2Min;
    @Column
    private Double k3Min;
    @Column
    private Double k4Min;

    public KaryawanAplusMin(Double k1Plus, Double k2Plus, Double k3Plus, Double k4Plus, Double k1Min, Double k2Min, Double k3Min, Double k4Min) {
        this.k1Plus = k1Plus;
        this.k2Plus = k2Plus;
        this.k3Plus = k3Plus;
        this.k4Plus = k4Plus;
        this.k1Min = k1Min;
        this.k2Min = k2Min;
        this.k3Min = k3Min;
        this.k4Min = k4Min;
    }
}
