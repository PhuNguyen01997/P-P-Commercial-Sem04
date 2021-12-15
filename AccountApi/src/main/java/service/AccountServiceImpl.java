package service;

import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password) != null;
    }

    @Override
    public Account save(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    public void deleteById(int id) {

    }
}
