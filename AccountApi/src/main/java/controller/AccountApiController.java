package controller;

import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.AccountService;

import java.util.List;

@RestController
public class AccountApiController {
    @Autowired
    private AccountService service;

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "This is demo restful webservice.";
    }

    @GetMapping(value = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Account>> getAccounts(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Account> getAccount(int id){
        if(service.findById(id).isPresent()){
            return new ResponseEntity<>(service.findById(id).get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
