package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.MahasiswaDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaDescRepository extends JpaRepository<MahasiswaDesc, String> {
}
