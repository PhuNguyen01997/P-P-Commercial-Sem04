package com.apt.p2p.controller;

import com.apt.p2p.entity.StatusOrder;
import com.apt.p2p.repository.StatusOrderRepository;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatusOrderController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private StatusOrderRepository statusOrderRepository;

    @GetMapping("api/status")
    @ResponseBody
    public List<StatusOrder> findAll() {
        return statusOrderRepository.findAll();
    }
}
