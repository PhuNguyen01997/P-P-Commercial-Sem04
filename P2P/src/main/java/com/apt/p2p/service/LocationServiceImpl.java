package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.ProvinceModelGHN;
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
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Value("${GHN.sandbox.token}")
    private String ghnToken;
    private String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/";
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

        ResponseEntity<ProvinceModelGHN> result = restTemplate.exchange(url + "/province", HttpMethod.GET, httpEntity, ProvinceModelGHN.class);
        ProvinceModelGHN body = result.getBody();
        return Arrays.asList(body.getData());
//        return provinceRepository.findAllByOrderByName();
    }

    @Override
    public List<District> districtFindAll() {
        return districtRepository.findAllByOrderByName();
    }

    @Override
    public List<Ward> wardFindAll() {
        return wardRepository.findAllByOrderByName();
    }

    @Override
    public List<District> districtFindByProvinceId(String provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);
    }

    @Override
    public List<Ward> wardFindByDistrictId(String districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }

    private HttpEntity<String> getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", ghnToken);
        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        return httpEntity;
    }
}
