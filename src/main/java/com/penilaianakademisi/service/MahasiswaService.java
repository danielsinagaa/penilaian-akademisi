package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Mahasiswa;
import com.penilaianakademisi.entity.MahasiswaAplusMin;
import com.penilaianakademisi.entity.model.MahasiswaEdit;
import com.penilaianakademisi.entity.model.MahasiswaRequest;
import com.penilaianakademisi.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MahasiswaService extends AbstractService<Mahasiswa> {
    @Autowired
    private MahasiswaaplusminService aPlusMinService;

    public MahasiswaService(MahasiswaRepository repository) {
        super(repository);
    }

    public void delete(String id){
        List<Mahasiswa> mahasiswaList = findAll();
        MahasiswaAplusMin aPlusMin;

        mahasiswaList.remove(findById(id));

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();

        mahasiswaList.forEach(m -> {
            k1List.add(m.getK1());
            k2List.add(m.getK2());
            k3List.add(m.getK3());
            k4List.add(m.getK4());
            k5List.add(m.getK5());
            k6List.add(m.getK6());
        });

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
        temp = 0.0;


        for (Double k : k5List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK5 = Math.sqrt(temp);

        temp = 0.0;

        for (Double k : k6List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK6 = Math.sqrt(temp);


        for (Mahasiswa m : mahasiswaList){
            m.setPembagik1(roundUp(m.getK1() / totalPembagiK1));
            m.setPembagik2(roundUp(m.getK2() / totalPembagiK2));
            m.setPembagik3(roundUp(m.getK3() / totalPembagiK3));
            m.setPembagik4(roundUp(m.getK4() / totalPembagiK4));
            m.setPembagik5(roundUp(m.getK5() / totalPembagiK5));
            m.setPembagik6(roundUp(m.getK6() / totalPembagiK6));

            m.setTernormalisasik1(roundUp(m.getPembagik1() * 20));
            m.setTernormalisasik2(roundUp(m.getPembagik2() * 20));
            m.setTernormalisasik3(roundUp(m.getPembagik3() * 15));
            m.setTernormalisasik4(roundUp(m.getPembagik4() * 10));
            m.setTernormalisasik5(roundUp(m.getPembagik5() * 20));
            m.setTernormalisasik6(roundUp(m.getPembagik6() * 5));
        }

        if (mahasiswaList.size() != 0 ){
            Double aPlusK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);

        Double aMinK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);

        aPlusMin = new MahasiswaAplusMin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6);
        aPlusMinService.save(aPlusMin);

        for (Mahasiswa m : mahasiswaList){
            m.setDPlus(roundUp(getDplus(m, aPlusMin)));
            m.setDMin(roundUp(getDmin(m, aPlusMin)));
            m.setPreferensi(roundUp(m.getDMin() / (m.getDMin() + m.getDPlus())));

            save(m);
        }
        }
    }

    public void edit(MahasiswaEdit request) {
        List<Mahasiswa> mahasiswaList = findAll();
        MahasiswaAplusMin aPlusMin;

        Mahasiswa mahasiswaEdit = findById(request.getId());
        int mahasiswaIndex = mahasiswaList.indexOf(mahasiswaEdit);

        mahasiswaEdit.setNama(request.getNama());
        mahasiswaEdit.setK1(request.getK1());
        mahasiswaEdit.setK2(request.getK2());
        mahasiswaEdit.setK3(request.getK3());
        mahasiswaEdit.setK4(request.getK4());
        mahasiswaEdit.setK5(request.getK5());
        mahasiswaEdit.setK6(request.getK6());

        mahasiswaList.set(mahasiswaIndex, mahasiswaEdit);

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();

        mahasiswaList.forEach(m -> {
            k1List.add(m.getK1());
            k2List.add(m.getK2());
            k3List.add(m.getK3());
            k4List.add(m.getK4());
            k5List.add(m.getK5());
            k6List.add(m.getK6());
        });

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());
        k5List.add(request.getK5());
        k6List.add(request.getK6());

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
        temp = 0.0;


        for (Double k : k5List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK5 = Math.sqrt(temp);

        temp = 0.0;

        for (Double k : k6List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK6 = Math.sqrt(temp);


        for (Mahasiswa m : mahasiswaList){
            m.setPembagik1(roundUp(m.getK1() / totalPembagiK1));
            m.setPembagik2(roundUp(m.getK2() / totalPembagiK2));
            m.setPembagik3(roundUp(m.getK3() / totalPembagiK3));
            m.setPembagik4(roundUp(m.getK4() / totalPembagiK4));
            m.setPembagik5(roundUp(m.getK5() / totalPembagiK5));
            m.setPembagik6(roundUp(m.getK6() / totalPembagiK6));

            m.setTernormalisasik1(roundUp(m.getPembagik1() * 20));
            m.setTernormalisasik2(roundUp(m.getPembagik2() * 20));
            m.setTernormalisasik3(roundUp(m.getPembagik3() * 15));
            m.setTernormalisasik4(roundUp(m.getPembagik4() * 10));
            m.setTernormalisasik5(roundUp(m.getPembagik5() * 20));
            m.setTernormalisasik6(roundUp(m.getPembagik6() * 5));
        }

        if (mahasiswaList.size() != 0 ){
            Double aPlusK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);

        Double aMinK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);

        aPlusMin = new MahasiswaAplusMin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6);
        aPlusMinService.save(aPlusMin);

        for (Mahasiswa m : mahasiswaList){
            m.setDPlus(roundUp(getDplus(m, aPlusMin)));
            m.setDMin(roundUp(getDmin(m, aPlusMin)));
            m.setPreferensi(roundUp(m.getDMin() / (m.getDMin() + m.getDPlus())));

            save(m);
        }
        }


    }
    public void save(MahasiswaRequest request){
        MahasiswaAplusMin aPlusMin;
        List<Mahasiswa> mahasiswaList = findAll();

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();

        mahasiswaList.forEach(m -> {
            k1List.add(m.getK1());
            k2List.add(m.getK2());
            k3List.add(m.getK3());
            k4List.add(m.getK4());
            k5List.add(m.getK5());
            k6List.add(m.getK6());
        });

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());
        k5List.add(request.getK5());
        k6List.add(request.getK6());

        mahasiswaList.add(new Mahasiswa(request));

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
        temp = 0.0;


        for (Double k : k5List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK5 = Math.sqrt(temp);

        temp = 0.0;

        for (Double k : k6List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK6 = Math.sqrt(temp);


        for (Mahasiswa m : mahasiswaList){
            m.setPembagik1(roundUp(m.getK1() / totalPembagiK1));
            m.setPembagik2(roundUp(m.getK2() / totalPembagiK2));
            m.setPembagik3(roundUp(m.getK3() / totalPembagiK3));
            m.setPembagik4(roundUp(m.getK4() / totalPembagiK4));
            m.setPembagik5(roundUp(m.getK5() / totalPembagiK5));
            m.setPembagik6(roundUp(m.getK6() / totalPembagiK6));

            m.setTernormalisasik1(roundUp(m.getPembagik1() * 20));
            m.setTernormalisasik2(roundUp(m.getPembagik2() * 20));
            m.setTernormalisasik3(roundUp(m.getPembagik3() * 15));
            m.setTernormalisasik4(roundUp(m.getPembagik4() * 10));
            m.setTernormalisasik5(roundUp(m.getPembagik5() * 20));
            m.setTernormalisasik6(roundUp(m.getPembagik6() * 5));
        }

        if (mahasiswaList.size() != 0 ){
            Double aPlusK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);
        Double aPlusK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .max().orElseThrow(null);

        Double aMinK1 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK2 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK3 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK4 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK5 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik5)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);
        Double aMinK6 = mahasiswaList.stream()
                .map(Mahasiswa::getTernormalisasik6)
                .mapToDouble(v -> v)
                .min().orElseThrow(null);

        aPlusMin = new MahasiswaAplusMin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6);
        aPlusMinService.save(aPlusMin);

        for (Mahasiswa m : mahasiswaList){
            m.setDPlus(roundUp(getDplus(m, aPlusMin)));
            m.setDMin(roundUp(getDmin(m, aPlusMin)));
            m.setPreferensi(roundUp(m.getDMin() / (m.getDMin() + m.getDPlus())));

            save(m);
        }
        }

    }

    private Double getDplus(Mahasiswa mahasiswa, MahasiswaAplusMin aPlusMin){
        Double d1 = Math.pow((aPlusMin.getK1Plus() - mahasiswa.getTernormalisasik1()), 2);
        Double d2 = Math.pow((aPlusMin.getK2Plus() - mahasiswa.getTernormalisasik2()), 2);
        Double d3 = Math.pow((aPlusMin.getK3Plus() - mahasiswa.getTernormalisasik3()), 2);
        Double d4 = Math.pow((aPlusMin.getK4Plus() - mahasiswa.getTernormalisasik4()), 2);
        Double d5 = Math.pow((aPlusMin.getK5Plus() - mahasiswa.getTernormalisasik5()), 2);
        Double d6 = Math.pow((aPlusMin.getK6Plus() - mahasiswa.getTernormalisasik6()), 2);

        return Math.sqrt(d1 + d2 + d3 + d4 + d5 + d6);
    }

    private Double getDmin(Mahasiswa mahasiswa, MahasiswaAplusMin aPlusMin){
        Double d1 = Math.pow(( mahasiswa.getTernormalisasik1() - aPlusMin.getK1Min() ), 2);
        Double d2 = Math.pow(( mahasiswa.getTernormalisasik2() - aPlusMin.getK2Min() ), 2);
        Double d3 = Math.pow(( mahasiswa.getTernormalisasik3() - aPlusMin.getK3Min() ), 2);
        Double d4 = Math.pow(( mahasiswa.getTernormalisasik4() - aPlusMin.getK4Min() ), 2);
        Double d5 = Math.pow(( mahasiswa.getTernormalisasik5() - aPlusMin.getK5Min() ), 2);
        Double d6 = Math.pow(( mahasiswa.getTernormalisasik6() - aPlusMin.getK6Min() ), 2);

        return Math.sqrt(d1 + d2 + d3 + d4 + d5 + d6);
    }

}
