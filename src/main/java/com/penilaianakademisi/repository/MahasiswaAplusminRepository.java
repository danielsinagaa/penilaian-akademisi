package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.MahasiswaAplusMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaAplusminRepository extends JpaRepository<MahasiswaAplusMin, String> {
}
