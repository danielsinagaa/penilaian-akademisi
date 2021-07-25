package com.penilaianakademisi.service;

import com.penilaianakademisi.entity.Dosen;
import com.penilaianakademisi.entity.DosenAplusmin;
import com.penilaianakademisi.entity.model.DosenEdit;
import com.penilaianakademisi.entity.model.DosenRequest;
import com.penilaianakademisi.repository.DosenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DosenService extends AbstractService<Dosen> {
    @Autowired
    private DosenAplusminService aPlusMinService;

    public DosenService(DosenRepository repository) {
        super(repository);
    }

    public void delete(String id) {
        List<Dosen> dosenList = findAll();
        DosenAplusmin aPlusMin;

        dosenList.remove(findById(id));

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();
        List<Double> k7List = new ArrayList<>();
        List<Double> k8List = new ArrayList<>();
        List<Double> k9List = new ArrayList<>();

        dosenList.forEach(d -> {
            k1List.add(d.getK1());
            k2List.add(d.getK2());
            k3List.add(d.getK3());
            k4List.add(d.getK4());
            k5List.add(d.getK5());
            k6List.add(d.getK6());
            k7List.add(d.getK7());
            k8List.add(d.getK8());
            k9List.add(d.getK9());
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
        temp = 0.0;


        for (Double k : k7List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK7 = Math.sqrt(temp);
        temp = 0.0;


        for (Double k : k8List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK8 = Math.sqrt(temp);

        temp = 0.0;


        for (Double k : k9List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK9 = Math.sqrt(temp);


        for (Dosen d : dosenList){
            double pembagiK1 = (d.getK1() / totalPembagiK1);
            double pembagiK2 = (d.getK2() / totalPembagiK2);
            double pembagiK3 = (d.getK3() / totalPembagiK3);
            double pembagiK4 = (d.getK4() / totalPembagiK4);
            double pembagiK5 = (d.getK5() / totalPembagiK5);
            double pembagiK6 = (d.getK6() / totalPembagiK6);
            double pembagiK7 = (d.getK7() / totalPembagiK7);
            double pembagiK8 = (d.getK8() / totalPembagiK8);
            double pembagiK9 = (d.getK9() / totalPembagiK9);

            d.setPembagik1(roundUp(d.getK1() / totalPembagiK1));
            d.setPembagik2(roundUp(d.getK2() / totalPembagiK2));
            d.setPembagik3(roundUp(d.getK3() / totalPembagiK3));
            d.setPembagik4(roundUp(d.getK4() / totalPembagiK4));
            d.setPembagik5(roundUp(d.getK5() / totalPembagiK5));
            d.setPembagik6(roundUp(d.getK6() / totalPembagiK6));
            d.setPembagik7(roundUp(d.getK7() / totalPembagiK7));
            d.setPembagik8(roundUp(d.getK8() / totalPembagiK8));
            d.setPembagik9(roundUp(d.getK9() / totalPembagiK9));

            d.setTernormalisasik1(roundUp(pembagiK1 * 20));
            d.setTernormalisasik2(roundUp(pembagiK2 * 15));
            d.setTernormalisasik3(roundUp(pembagiK3 * 10));
            d.setTernormalisasik4(roundUp(pembagiK4 * 5));
            d.setTernormalisasik5(roundUp(pembagiK5 * 5));
            d.setTernormalisasik6(roundUp(pembagiK6 * 10));
            d.setTernormalisasik7(roundUp(pembagiK7 * 10));
            d.setTernormalisasik8(roundUp(pembagiK8 * 10));
            d.setTernormalisasik9(roundUp(pembagiK9 * 15));
        }

        if  (dosenList.size() != 0 ){
            Double aPlusK1 = dosenList.stream()
                .map(Dosen::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK2 = dosenList.stream()
                .map(Dosen::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK3 = dosenList.stream()
                .map(Dosen::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK4 = dosenList.stream()
                .map(Dosen::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK5 = dosenList.stream()
                .map(Dosen::getTernormalisasik5)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK6 = dosenList.stream()
                .map(Dosen::getTernormalisasik6)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK7 = dosenList.stream()
                .map(Dosen::getTernormalisasik7)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK8 = dosenList.stream()
                .map(Dosen::getTernormalisasik8)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK9 = dosenList.stream()
                .map(Dosen::getTernormalisasik9)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        Double aMinK1 = dosenList.stream()
                .map(Dosen::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK2 = dosenList.stream()
                .map(Dosen::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK3 = dosenList.stream()
                .map(Dosen::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK4 = dosenList.stream()
                .map(Dosen::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK5 = dosenList.stream()
                .map(Dosen::getTernormalisasik5)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK6 = dosenList.stream()
                .map(Dosen::getTernormalisasik6)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK7 = dosenList.stream()
                .map(Dosen::getTernormalisasik7)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK8 = dosenList.stream()
                .map(Dosen::getTernormalisasik8)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK9 = dosenList.stream()
                .map(Dosen::getTernormalisasik9)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        aPlusMin = new DosenAplusmin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aPlusK7, aPlusK8, aPlusK9, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6,aMinK7, aMinK8, aMinK9);
        aPlusMinService.save(aPlusMin);

        for (Dosen d : dosenList){
            d.setDPlus(roundUp(getDplus(d, aPlusMin)));
            d.setDMin(roundUp(getDmin(d, aPlusMin)));
            d.setPreferensi(roundUp(d.getDMin() / ( d.getDMin() + d.getDPlus() ) ));

            save(d);
        }
        }
    }

    public void edit(DosenEdit request) {
        List<Dosen> dosenList = findAll();
        DosenAplusmin aPlusMin;

        Dosen dosenEdit = findById(request.getId());
        int dosenIndex = dosenList.indexOf(dosenEdit);

        dosenEdit.setNama(request.getNama());
        dosenEdit.setK1(request.getK1());
        dosenEdit.setK2(request.getK2());
        dosenEdit.setK3(request.getK3());
        dosenEdit.setK4(request.getK4());
        dosenEdit.setK5(request.getK5());
        dosenEdit.setK6(request.getK6());
        dosenEdit.setK7(request.getK7());
        dosenEdit.setK8(request.getK8());
        dosenEdit.setK9(request.getK9());

        dosenList.set(dosenIndex, dosenEdit);

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();
        List<Double> k7List = new ArrayList<>();
        List<Double> k8List = new ArrayList<>();
        List<Double> k9List = new ArrayList<>();

        dosenList.forEach(d -> {
            k1List.add(d.getK1());
            k2List.add(d.getK2());
            k3List.add(d.getK3());
            k4List.add(d.getK4());
            k5List.add(d.getK5());
            k6List.add(d.getK6());
            k7List.add(d.getK7());
            k8List.add(d.getK8());
            k9List.add(d.getK9());
        });

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());
        k5List.add(request.getK5());
        k6List.add(request.getK6());
        k7List.add(request.getK7());
        k8List.add(request.getK8());
        k9List.add(request.getK9());

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
        temp = 0.0;


        for (Double k : k7List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK7 = Math.sqrt(temp);

        temp = 0.0;


        for (Double k : k8List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK8 = Math.sqrt(temp);

        temp = 0.0;


        for (Double k : k9List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK9 = Math.sqrt(temp);


        for (Dosen d : dosenList){
            double pembagiK1 = (d.getK1() / totalPembagiK1);
            double pembagiK2 = (d.getK2() / totalPembagiK2);
            double pembagiK3 = (d.getK3() / totalPembagiK3);
            double pembagiK4 = (d.getK4() / totalPembagiK4);
            double pembagiK5 = (d.getK5() / totalPembagiK5);
            double pembagiK6 = (d.getK6() / totalPembagiK6);
            double pembagiK7 = (d.getK7() / totalPembagiK7);
            double pembagiK8 = (d.getK8() / totalPembagiK8);
            double pembagiK9 = (d.getK9() / totalPembagiK9);

            d.setPembagik1(roundUp(d.getK1() / totalPembagiK1));
            d.setPembagik2(roundUp(d.getK2() / totalPembagiK2));
            d.setPembagik3(roundUp(d.getK3() / totalPembagiK3));
            d.setPembagik4(roundUp(d.getK4() / totalPembagiK4));
            d.setPembagik5(roundUp(d.getK5() / totalPembagiK5));
            d.setPembagik6(roundUp(d.getK6() / totalPembagiK6));
            d.setPembagik7(roundUp(d.getK7() / totalPembagiK7));
            d.setPembagik8(roundUp(d.getK8() / totalPembagiK8));
            d.setPembagik9(roundUp(d.getK9() / totalPembagiK9));

            d.setTernormalisasik1(roundUp(pembagiK1 * 20));
            d.setTernormalisasik2(roundUp(pembagiK2 * 15));
            d.setTernormalisasik3(roundUp(pembagiK3 * 10));
            d.setTernormalisasik4(roundUp(pembagiK4 * 5));
            d.setTernormalisasik5(roundUp(pembagiK5 * 5));
            d.setTernormalisasik6(roundUp(pembagiK6 * 10));
            d.setTernormalisasik7(roundUp(pembagiK7 * 10));
            d.setTernormalisasik8(roundUp(pembagiK8 * 10));
            d.setTernormalisasik9(roundUp(pembagiK9 * 15));
        }

        if  (dosenList.size() != 0 ){
            Double aPlusK1 = dosenList.stream()
                .map(Dosen::getTernormalisasik1)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK2 = dosenList.stream()
                .map(Dosen::getTernormalisasik2)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK3 = dosenList.stream()
                .map(Dosen::getTernormalisasik3)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK4 = dosenList.stream()
                .map(Dosen::getTernormalisasik4)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK5 = dosenList.stream()
                .map(Dosen::getTernormalisasik5)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK6 = dosenList.stream()
                .map(Dosen::getTernormalisasik6)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK7 = dosenList.stream()
                .map(Dosen::getTernormalisasik7)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK8 = dosenList.stream()
                .map(Dosen::getTernormalisasik8)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        Double aPlusK9 = dosenList.stream()
                .map(Dosen::getTernormalisasik9)
                .mapToDouble(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        Double aMinK1 = dosenList.stream()
                .map(Dosen::getTernormalisasik1)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK2 = dosenList.stream()
                .map(Dosen::getTernormalisasik2)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK3 = dosenList.stream()
                .map(Dosen::getTernormalisasik3)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK4 = dosenList.stream()
                .map(Dosen::getTernormalisasik4)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK5 = dosenList.stream()
                .map(Dosen::getTernormalisasik5)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK6 = dosenList.stream()
                .map(Dosen::getTernormalisasik6)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK7 = dosenList.stream()
                .map(Dosen::getTernormalisasik7)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK8 = dosenList.stream()
                .map(Dosen::getTernormalisasik8)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
        Double aMinK9 = dosenList.stream()
                .map(Dosen::getTernormalisasik9)
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        aPlusMin = new DosenAplusmin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aPlusK7, aPlusK8, aPlusK9, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6,aMinK7, aMinK8, aMinK9);
        aPlusMinService.save(aPlusMin);

        for (Dosen d : dosenList){
            d.setDPlus(roundUp(getDplus(d, aPlusMin)));
            d.setDMin(roundUp(getDmin(d, aPlusMin)));
            d.setPreferensi(roundUp(d.getDMin() / ( d.getDMin() + d.getDPlus() ) ));

            save(d);
        }
        }

    }
    public void save(DosenRequest request){
        DosenAplusmin aPlusMin;
        List<Dosen> dosenList = findAll();

        deleteAll();

        List<Double> k1List = new ArrayList<>();
        List<Double> k2List = new ArrayList<>();
        List<Double> k3List = new ArrayList<>();
        List<Double> k4List = new ArrayList<>();
        List<Double> k5List = new ArrayList<>();
        List<Double> k6List = new ArrayList<>();
        List<Double> k7List = new ArrayList<>();
        List<Double> k8List = new ArrayList<>();
        List<Double> k9List = new ArrayList<>();

        dosenList.forEach(d -> {
            k1List.add(d.getK1());
            k2List.add(d.getK2());
            k3List.add(d.getK3());
            k4List.add(d.getK4());
            k5List.add(d.getK5());
            k6List.add(d.getK6());
            k7List.add(d.getK7());
            k8List.add(d.getK8());
            k9List.add(d.getK9());
        });

        k1List.add(request.getK1());
        k2List.add(request.getK2());
        k3List.add(request.getK3());
        k4List.add(request.getK4());
        k5List.add(request.getK5());
        k6List.add(request.getK6());
        k7List.add(request.getK7());
        k8List.add(request.getK8());
        k9List.add(request.getK9());

        dosenList.add(new Dosen(request));

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
        temp = 0.0;


        for (Double k : k7List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK7 = Math.sqrt(temp);

        temp = 0.0;


        for (Double k : k8List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK8 = Math.sqrt(temp);

        temp = 0.0;


        for (Double k : k9List){
            temp += Math.pow(k, 2);
        }
        Double totalPembagiK9 = Math.sqrt(temp);


        for (Dosen d : dosenList){
            double pembagiK1 = (d.getK1() / totalPembagiK1);
            double pembagiK2 = (d.getK2() / totalPembagiK2);
            double pembagiK3 = (d.getK3() / totalPembagiK3);
            double pembagiK4 = (d.getK4() / totalPembagiK4);
            double pembagiK5 = (d.getK5() / totalPembagiK5);
            double pembagiK6 = (d.getK6() / totalPembagiK6);
            double pembagiK7 = (d.getK7() / totalPembagiK7);
            double pembagiK8 = (d.getK8() / totalPembagiK8);
            double pembagiK9 = (d.getK9() / totalPembagiK9);

            d.setPembagik1(roundUp(d.getK1() / totalPembagiK1));
            d.setPembagik2(roundUp(d.getK2() / totalPembagiK2));
            d.setPembagik3(roundUp(d.getK3() / totalPembagiK3));
            d.setPembagik4(roundUp(d.getK4() / totalPembagiK4));
            d.setPembagik5(roundUp(d.getK5() / totalPembagiK5));
            d.setPembagik6(roundUp(d.getK6() / totalPembagiK6));
            d.setPembagik7(roundUp(d.getK7() / totalPembagiK7));
            d.setPembagik8(roundUp(d.getK8() / totalPembagiK8));
            d.setPembagik9(roundUp(d.getK9() / totalPembagiK9));

            d.setTernormalisasik1(roundUp(pembagiK1 * 20));
            d.setTernormalisasik2(roundUp(pembagiK2 * 15));
            d.setTernormalisasik3(roundUp(pembagiK3 * 10));
            d.setTernormalisasik4(roundUp(pembagiK4 * 5));
            d.setTernormalisasik5(roundUp(pembagiK5 * 5));
            d.setTernormalisasik6(roundUp(pembagiK6 * 10));
            d.setTernormalisasik7(roundUp(pembagiK7 * 10));
            d.setTernormalisasik8(roundUp(pembagiK8 * 10));
            d.setTernormalisasik9(roundUp(pembagiK9 * 15));
        }

        if (dosenList.size() != 0 ) {
            Double aPlusK1 = dosenList.stream()
                    .map(Dosen::getTernormalisasik1)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK2 = dosenList.stream()
                    .map(Dosen::getTernormalisasik2)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK3 = dosenList.stream()
                    .map(Dosen::getTernormalisasik3)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK4 = dosenList.stream()
                    .map(Dosen::getTernormalisasik4)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK5 = dosenList.stream()
                    .map(Dosen::getTernormalisasik5)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK6 = dosenList.stream()
                    .map(Dosen::getTernormalisasik6)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK7 = dosenList.stream()
                    .map(Dosen::getTernormalisasik7)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK8 = dosenList.stream()
                    .map(Dosen::getTernormalisasik8)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            Double aPlusK9 = dosenList.stream()
                    .map(Dosen::getTernormalisasik9)
                    .mapToDouble(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);

            Double aMinK1 = dosenList.stream()
                    .map(Dosen::getTernormalisasik1)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK2 = dosenList.stream()
                    .map(Dosen::getTernormalisasik2)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK3 = dosenList.stream()
                    .map(Dosen::getTernormalisasik3)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK4 = dosenList.stream()
                    .map(Dosen::getTernormalisasik4)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK5 = dosenList.stream()
                    .map(Dosen::getTernormalisasik5)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK6 = dosenList.stream()
                    .map(Dosen::getTernormalisasik6)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK7 = dosenList.stream()
                    .map(Dosen::getTernormalisasik7)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK8 = dosenList.stream()
                    .map(Dosen::getTernormalisasik8)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            Double aMinK9 = dosenList.stream()
                    .map(Dosen::getTernormalisasik9)
                    .mapToDouble(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);

            aPlusMin = new DosenAplusmin(aPlusK1, aPlusK2, aPlusK3, aPlusK4, aPlusK5, aPlusK6, aPlusK7, aPlusK8, aPlusK9, aMinK1, aMinK2, aMinK3, aMinK4, aMinK5, aMinK6,aMinK7, aMinK8, aMinK9);
            aPlusMinService.save(aPlusMin);

            for (Dosen d : dosenList){
                d.setDPlus(roundUp(getDplus(d, aPlusMin)));
                d.setDMin(roundUp(getDmin(d, aPlusMin)));
                d.setPreferensi(roundUp((getDmin(d, aPlusMin)) / (d.getDPlus() + d.getDMin()) ));

                save(d);
            }
        }
    }

    private Double getDplus(Dosen dosen, DosenAplusmin aPlusMin){
        Double d1 = Math.pow((aPlusMin.getK1Plus() - dosen.getTernormalisasik1()), 2);
        Double d2 = Math.pow((aPlusMin.getK2Plus() - dosen.getTernormalisasik2()), 2);
        Double d3 = Math.pow((aPlusMin.getK3Plus() - dosen.getTernormalisasik3()), 2);
        Double d4 = Math.pow((aPlusMin.getK4Plus() - dosen.getTernormalisasik4()), 2);
        Double d5 = Math.pow((aPlusMin.getK5Plus() - dosen.getTernormalisasik5()), 2);
        Double d6 = Math.pow((aPlusMin.getK6Plus() - dosen.getTernormalisasik6()), 2);
        Double d7 = Math.pow((aPlusMin.getK7Plus() - dosen.getTernormalisasik7()), 2);
        Double d8 = Math.pow((aPlusMin.getK8Plus() - dosen.getTernormalisasik8()), 2);
        Double d9 = Math.pow((aPlusMin.getK9Plus() - dosen.getTernormalisasik9()), 2);

        double total = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9;

        return Math.sqrt(total);
    }

    private Double getDmin(Dosen dosen, DosenAplusmin aPlusMin){
        Double d1 = Math.pow(( dosen.getTernormalisasik1() - aPlusMin.getK1Min() ), 2);
        Double d2 = Math.pow(( dosen.getTernormalisasik2() - aPlusMin.getK2Min() ), 2);
        Double d3 = Math.pow(( dosen.getTernormalisasik3() - aPlusMin.getK3Min() ), 2);
        Double d4 = Math.pow(( dosen.getTernormalisasik4() - aPlusMin.getK4Min() ), 2);
        Double d5 = Math.pow(( dosen.getTernormalisasik5() - aPlusMin.getK5Min() ), 2);
        Double d6 = Math.pow(( dosen.getTernormalisasik6() - aPlusMin.getK6Min() ), 2);
        Double d7 = Math.pow(( dosen.getTernormalisasik7() - aPlusMin.getK7Min() ), 2);
        Double d8 = Math.pow(( dosen.getTernormalisasik8() - aPlusMin.getK8Min() ), 2);
        Double d9 = Math.pow(( dosen.getTernormalisasik9() - aPlusMin.getK9Min() ), 2);

        double total = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9;

        return Math.sqrt(total);
    }
}
