package com.penilaianakademisi.repository;

import com.penilaianakademisi.entity.DosenAplusmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenAplusminRepository extends JpaRepository<DosenAplusmin, String> {
}
