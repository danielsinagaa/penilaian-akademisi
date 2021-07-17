package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.KaryawanAplusMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanAplusminRepository extends JpaRepository<KaryawanAplusMin, String> {
}
