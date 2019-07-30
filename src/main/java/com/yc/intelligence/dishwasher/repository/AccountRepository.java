package com.yc.intelligence.dishwasher.repository;

import com.yc.intelligence.dishwasher.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
