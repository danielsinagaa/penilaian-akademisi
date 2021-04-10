package com.skripsi.dedek.repository;

import com.skripsi.dedek.entity.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
}
