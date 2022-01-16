package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.form.CalShippingForm;
import com.apt.p2p.model.form.CalShippingResponse;
import com.apt.p2p.model.form.CalShippingResponseData;
import com.apt.p2p.model.view.*;
import com.apt.p2p.repository.AddressRepository;
import com.apt.p2p.repository.DistrictRepository;
import com.apt.p2p.repository.ProvinceRepository;
import com.apt.p2p.repository.WardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
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
    private AddressRepository addressRepository;
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

    @Override
    public CalShippingResponseData calShippingFree(CalShippingForm form) {
        String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";
        Address toAddress = addressRepository.findById(form.getToAddressId()).get();
        List<Address> fromAddresses = addressRepository.findAllById(Arrays.asList(form.getFromAddressId()));

        CalShippingResponseData data = fromAddresses.stream().map(shopAddress -> {
            CalShippingResponseData result = null;
            try {
                JSONObject paramsMap = new JSONObject();
                paramsMap.put("service_type_id", 2);
                paramsMap.put("insurance_value", form.getInsuranceValue());
                paramsMap.put("coupon", null);
                paramsMap.put("from_district_id", shopAddress.getDistrictId());
                paramsMap.put("to_district_id", toAddress.getDistrictId());
                paramsMap.put("to_ward_code", toAddress.getWardId());
                paramsMap.put("weight", 2000);
                paramsMap.put("length", 30);
                paramsMap.put("width", 15);
                paramsMap.put("height", 15);

                HttpHeaders headers = new HttpHeaders();
                headers.set("token", ghnToken);
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<String>(paramsMap.toString(), headers);

                ResponseEntity<CalShippingResponse> response = restTemplate.postForEntity(url, request, CalShippingResponse.class);
                result = response.getBody().getData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }).max(Comparator.comparing(CalShippingResponseData::getTotal)).get();

        return data;
    }

    private HttpEntity<String> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", ghnToken);
        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        return httpEntity;
    }
}
