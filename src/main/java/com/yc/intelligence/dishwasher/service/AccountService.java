package com.yc.intelligence.dishwasher.service;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.common.ResultUtil;
import com.yc.intelligence.dishwasher.entity.Account;
import com.yc.intelligence.dishwasher.entity.enums.AccountTypeEnum;
import com.yc.intelligence.dishwasher.model.LoginVo;
import com.yc.intelligence.dishwasher.repository.AccountRepository;
import com.yc.intelligence.dishwasher.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Result register(LoginVo vo){
        Account account = new Account(vo.getMobilePhone(),vo.getUserName(), AccountTypeEnum.ORDINARY, MD5Util.getMD5(vo.getPassword()));
        accountRepository.save(account);
        return ResultUtil.success();
    }

    public Result login(LoginVo vo){
        Account account = accountRepository.findByMobilePhone(vo.getMobilePhone());
        if (account == null){
            return ResultUtil.error(1,"用户名不存在");
        }else {
            if (MD5Util.getMD5(vo.getPassword()).equals(account.getPassword())){
                account.setPassword(null);
                return ResultUtil.success(account);
            }else {
                return ResultUtil.error(1,"密码错误");
            }
        }
    }
}
