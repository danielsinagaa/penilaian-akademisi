package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.Dosen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenRepository extends JpaRepository<Dosen, String> {
}
