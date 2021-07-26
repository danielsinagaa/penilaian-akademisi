package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.DosenDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenDescRepository extends JpaRepository<DosenDesc, String> {
}
