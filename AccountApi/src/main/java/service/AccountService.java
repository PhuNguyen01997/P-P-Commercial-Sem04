package service;

import entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(int id);
    boolean checkLogin(String username, String password);
    Account save(Account account);
    void deleteById(int id);
}
