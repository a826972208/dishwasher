package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
}
