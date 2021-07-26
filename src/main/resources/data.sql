INSERT INTO dosen_desc (id, k1, k2, k3, k4, k5, k6, k7, k8, k9) SELECT '1', 20, 15, 10, 5, 5, 10, 10, 10, 15 WHERE NOT EXISTS (SELECT id FROM dosen_desc WHERE id = '1');

INSERT INTO mahasiswa_desc (id, k1, k2, k3, k4, k5, k6) SELECT '1', 20, 20, 15, 10, 20, 5 WHERE NOT EXISTS (SELECT id FROM mahasiswa_desc WHERE id = '1');

INSERT INTO karyawan_desc (id, k1, k2, k3, k4) SELECT '1', 15, 15, 35, 35 WHERE NOT EXISTS (SELECT id FROM karyawan_desc WHERE id = '1');

