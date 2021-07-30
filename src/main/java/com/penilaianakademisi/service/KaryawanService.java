package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.Karyawan;
import com.penilaianakademisi.entity.KaryawanAplusMin;
import com.penilaianakademisi.entity.model.KaryawanEdit;
import com.penilaianakademisi.entity.model.KaryawanRequest;
import com.penilaianakademisi.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class KaryawanService extends AbstractService<Karyawan> {

    @Autowired
    private KaryawanAplusminService aplusminService;

    public KaryawanService(KaryawanRepository repository) {
        super(repository);
    }

    public void delete(String id){
        List<Karyawan> karyawanList = findAll();

        KaryawanAplusMin aPlusMin;

        karyawanList.remove(findById(id));

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();

        for (Karyawan k : karyawanList){
            k1List.add(k.getK1());
            k2List.add(k.getK2());
            k3List.add(k.getK3());
            k4List.add(k.getK4());
        }

        double temp = 0.0;

        for (Double k : k1List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK1 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k2List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK2 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k3List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK3 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k4List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK4 = Math.sqrt(temp);

        for (Karyawan k: karyawanList){
            k.setPembagik1(roundUp(k.getK1() / totalPembagiK1));
            k.setPembagik2(roundUp(k.getK2() / totalPembagiK2));
            k.setPembagik3(roundUp(k.getK3() / totalPembagiK3));
            k.setPembagik4(roundUp(k.getK4() / totalPembagiK4));

            double pembagik1 = k.getK1() / totalPembagiK1;
            double pembagik2 = k.getK2() / totalPembagiK2;
            double pembagik3 = k.getK3() / totalPembagiK3;
            double pembagik4 = k.getK4() / totalPembagiK4;

            k.setTernormalisasik1(roundUp(pembagik1 * 15));
            k.setTernormalisasik2(roundUp(pembagik2 * 15));
            k.setTernormalisasik3(roundUp(pembagik3 * 35));
            k.setTernormalisasik4(roundUp(pembagik4 * 35));
        }

        if (karyawanList.size() != 0 ){
            Double aPlusK1 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik1)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(null);
            Double aPlusK2 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik2)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(null);
            Double aPlusK3 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik3)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(null);
            Double aPlusK4 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik4)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(null);

            Double aMinK1 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik1)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(null);
            Double aMinK2 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik2)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(null);
            Double aMinK3 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik3)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(null);
            Double aMinK4 = karyawanList.stream()
                    .map(Karyawan::getTernormalisasik4)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(null);

            aPlusMin = new KaryawanAplusMin(aPlusK1, aPlusK2, aPlusK3,aPlusK4, aMinK1,aMinK2,aMinK3, aMinK4);
            aplusminService.save(aPlusMin);

            for (Karyawan k : karyawanList){
                k.setDPlus(roundUp(getDplus(k, aPlusMin)));
                k.setDMin(roundUp(getDmin(k, aPlusMin)));
                k.setPreferensi(roundUp(getDmin(k, aPlusMin) / (getDmin(k, aPlusMin) + getDplus(k, aPlusMin))));

                save(k);
            }
        }
    }

    public void edit(KaryawanEdit request) {
        List<Karyawan> karyawanList = findAll();
        KaryawanAplusMin aPlusMin;

        Karyawan karyawanEdit = findById(request.getId());
        int karyawanIndex = karyawanList.indexOf(karyawanEdit);

        karyawanEdit.setNama(request.getNama());
        karyawanEdit.setK1(request.getK1());
        karyawanEdit.setK2(request.getK2());
        karyawanEdit.setK3(request.getK3());
        karyawanEdit.setK4(request.getK4());

        karyawanList.set(karyawanIndex, karyawanEdit);

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();

        for (Karyawan k : karyawanList){
            k1List.add(k.getK1());
            k2List.add(k.getK2());
            k3List.add(k.getK3());
            k4List.add(k.getK4());
        }

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());

        double temp = 0.0;

        for (Double k : k1List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK1 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k2List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK2 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k3List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK3 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k4List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK4 = Math.sqrt(temp);

        for (Karyawan k: karyawanList){
            k.setPembagik1(roundUp(k.getK1() / totalPembagiK1));
            k.setPembagik2(roundUp(k.getK2() / totalPembagiK2));
            k.setPembagik3(roundUp(k.getK3() / totalPembagiK3));
            k.setPembagik4(roundUp(k.getK4() / totalPembagiK4));

            double pembagik1 = k.getK1() / totalPembagiK1;
            double pembagik2 = k.getK2() / totalPembagiK2;
            double pembagik3 = k.getK3() / totalPembagiK3;
            double pembagik4 = k.getK4() / totalPembagiK4;

            k.setTernormalisasik1(roundUp(pembagik1 * 15));
            k.setTernormalisasik2(roundUp(pembagik2 * 15));
            k.setTernormalisasik3(roundUp(pembagik3 * 35));
            k.setTernormalisasik4(roundUp(pembagik4 * 35));
        }

        if (karyawanList.size() != 0 ){
            Double aPlusK1 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK2 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK3 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK4 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);

        Double aMinK1 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK2 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK3 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK4 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);

        aPlusMin = new KaryawanAplusMin(aPlusK1, aPlusK2, aPlusK3,aPlusK4, aMinK1,aMinK2,aMinK3, aMinK4);
        aplusminService.save(aPlusMin);

        for (Karyawan k : karyawanList){
            k.setDPlus(roundUp(getDplus(k, aPlusMin)));
            k.setDMin(roundUp(getDmin(k, aPlusMin)));
            k.setPreferensi(roundUp(getDmin(k, aPlusMin) / ( getDmin(k, aPlusMin) + getDplus(k, aPlusMin))));

            save(k);
        }
        }

    }

    public void save(KaryawanRequest request) {
        KaryawanAplusMin aPlusMin;

        List<Karyawan> karyawanList = findAll();

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();

        for (Karyawan k : karyawanList){
            k1List.add(k.getK1());
            k2List.add(k.getK2());
            k3List.add(k.getK3());
            k4List.add(k.getK4());
        }

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());

        if (!request.getNama().equals("description example")){
            karyawanList.add(new Karyawan(request));
        }

        double temp = 0.0;

        for (Double k : k1List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK1 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k2List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK2 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k3List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK3 = Math.sqrt(temp);
        temp = 0.0;

        for (Double k : k4List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK4 = Math.sqrt(temp);

        for (Karyawan k: karyawanList){
            k.setPembagik1(roundUp(k.getK1() / totalPembagiK1));
            k.setPembagik2(roundUp(k.getK2() / totalPembagiK2));
            k.setPembagik3(roundUp(k.getK3() / totalPembagiK3));
            k.setPembagik4(roundUp(k.getK4() / totalPembagiK4));

            double pembagik1 = k.getK1() / totalPembagiK1;
            double pembagik2 = k.getK2() / totalPembagiK2;
            double pembagik3 = k.getK3() / totalPembagiK3;
            double pembagik4 = k.getK4() / totalPembagiK4;

            k.setTernormalisasik1(roundUp(pembagik1 * 15));
            k.setTernormalisasik2(roundUp(pembagik2 * 15));
            k.setTernormalisasik3(roundUp(pembagik3 * 35));
            k.setTernormalisasik4(roundUp(pembagik4 * 35));
        }

        if (karyawanList.size() != 0 ){
            Double aPlusK1 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK2 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK3 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK4 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);

        Double aMinK1 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK2 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK3 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK4 = karyawanList.stream()
                .map(Karyawan::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);

        aPlusMin = new KaryawanAplusMin(aPlusK1, aPlusK2, aPlusK3,aPlusK4, aMinK1,aMinK2,aMinK3, aMinK4);
        aplusminService.save(aPlusMin);

        for (Karyawan k : karyawanList){
            k.setDPlus(roundUp(getDplus(k, aPlusMin)));
            k.setDMin(roundUp(getDmin(k, aPlusMin)));
            k.setPreferensi(roundUp(getDmin(k, aPlusMin) / ( getDmin(k, aPlusMin) + getDplus(k, aPlusMin))));

            save(k);
        }
        }

    }

    private Double getDplus(Karyawan karyawan, KaryawanAplusMin aplusMin){
        return Math.sqrt(((Math.pow((aplusMin.getK1Plus() - karyawan.getTernormalisasik1()),2) )) + ((Math.pow((aplusMin.getK2Plus() - karyawan.getTernormalisasik2()),2))) + ((Math.pow((aplusMin.getK3Plus() - karyawan.getTernormalisasik3()),2))) + ((Math.pow((aplusMin.getK4Plus() - karyawan.getTernormalisasik4()),2))));
    }

    private Double getDmin(Karyawan karyawan, KaryawanAplusMin aplusMin){
        return Math.sqrt(((Math.pow((karyawan.getTernormalisasik1() - aplusMin.getK1Min() ),2) )) + ((Math.pow((karyawan.getTernormalisasik2() - aplusMin.getK2Min() ),2) )) + ((Math.pow((karyawan.getTernormalisasik3() - aplusMin.getK3Min() ),2) )) + ((Math.pow((karyawan.getTernormalisasik4() - aplusMin.getK4Min() ),2) )));
    }
}
