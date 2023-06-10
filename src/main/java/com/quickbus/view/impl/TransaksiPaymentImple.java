package com.quickbus.view.impl;

import com.quickbus.model.oauth.User;
import com.quickbus.repository.oauth.UserRepo;
import com.quickbus.view.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class TransaksiPaymentImple implements TransaksiService {
    @Autowired
    public TransaksiRepo transaksiRepo;

    @Autowired
    public UserRepo userRepo;

    @Override
    public Map save(Transaksi transaksi){
    Map map = new HashMap();
    try{
        User peminjam = userRepo.getById(transaksi.getPeminjam().getId());
        User meminjam = userRepo.getById(transaksi.getMeminjam().getId());
        if(peminjam ==null){
            map.put("code","404");
            map.put("status","failed, user peminjam tidak ditemukan");
            return map;
        }

        if(meminjam ==null){
            map.put("code","404");
            map.put("status","failed, user meminjam tidak ditemukan");
            return map;
        }
        transaksi.setPeminjam(peminjam);
        transaksi.setMeminjam(meminjam);
        Transaksi obj = transaksiRepo.save(transaksi);
        map.put("data",obj);
        map.put("code","200");
        map.put("status","success");
        return map;
    }catch(Exception e){
        System.out.println(e.getMessage());
        map.put("code","500");
        map.put("status","failed");
        return map;
    }

    }

    @Override
    public Map updateStatus(Transaksi transaksi){
        Map map = new HashMap();
        try{
            Transaksi obj = transaksiRepo.getById(transaksi.getId());
            if(obj==null){
                map.put("code","404");
                map.put("status","failed, data transaksi tidak ditemukan");
                return map;
            }

            obj.setStatus("lunas");
            transaksiRepo.save(obj);
            map.put("data",obj);
            map.put("code","200");
            map.put("status","success");
            return map;
        }catch(Exception e){
            map.put("code","500");
            map.put("status","failed");
            return map;
        }
    }

}
