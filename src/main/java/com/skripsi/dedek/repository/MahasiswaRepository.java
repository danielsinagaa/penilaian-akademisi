package com.skripsi.dedek.repository;

import com.skripsi.dedek.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, String> {
}
