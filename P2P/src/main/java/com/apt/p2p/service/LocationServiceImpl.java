package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.view.*;
import com.apt.p2p.repository.DistrictRepository;
import com.apt.p2p.repository.ProvinceRepository;
import com.apt.p2p.repository.WardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    @Value("${GHN.sandbox.token}")
    private String ghnToken;
    private String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data";
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ProvinceModel> provinceFindAll() {
        HttpEntity<String> httpEntity = getHeader();

        ResponseEntity<ProvinceModelGHN> response = restTemplate.exchange(url + "/province", HttpMethod.GET, httpEntity, ProvinceModelGHN.class);
        ProvinceModel[] arr = response.getBody().getData();

        List<ProvinceModel> list = Arrays.asList(arr);

        return list;
    }

    @Override
    public List<DistrictModel> districtFindAllByProvinceId(int provinceId) {
        HttpEntity<String> httpEntity = getHeader();

        String appendPath = "?province_id=" + provinceId;
        ResponseEntity<DistrictModelGHN> response = restTemplate.exchange(url + "/district" + appendPath, HttpMethod.GET, httpEntity, DistrictModelGHN.class);
        DistrictModel[] arr = response.getBody().getData();

        List<DistrictModel> list = Arrays.stream(arr).filter(pm -> pm.getSupportType() != 0).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<WardModel> wardFindAllByDistrictId(int districtId) {
        HttpEntity<String> httpEntity = getHeader();

        String appendPath = "?district_id=" + districtId;
        ResponseEntity<WardModelGHN> response = restTemplate.exchange(url + "/ward" + appendPath, HttpMethod.GET, httpEntity, WardModelGHN.class);
        WardModel[] arr = response.getBody().getData();

        List<WardModel> list = Arrays.stream(arr).filter(wm -> wm.getSupportType() != 0).collect(Collectors.toList());

        return list;
    }

    private HttpEntity<String> getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", ghnToken);
        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        return httpEntity;
    }
}
