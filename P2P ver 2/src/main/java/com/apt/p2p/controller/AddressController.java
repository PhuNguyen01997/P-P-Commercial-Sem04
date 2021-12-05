package com.apt.p2p.controller;

import com.apt.p2p.entity.Address;
import com.apt.p2p.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/")
    public List<Address> getAddresses() {
        return addressService.getAddress();
    }

    @GetMapping("/{id}")
    public Address getAddresses(@PathVariable Integer id) {
        return addressService.getAddressDetail(id);
    }

    @PostMapping("/")
    public Address createNewAddress(@RequestBody Address address) {
        return addressService.createNewAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }

    @PutMapping("/{id}")
    public Address editAddress(@PathVariable Integer id, @RequestBody Address address) {
        return addressService.editAddress(address);
    }
}
