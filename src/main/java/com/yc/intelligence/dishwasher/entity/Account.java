package com.yc.intelligence.dishwasher.entity;

import com.yc.intelligence.dishwasher.common.BaseModel;
import com.yc.intelligence.dishwasher.entity.enums.AccountTypeEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "account",uniqueConstraints = {@UniqueConstraint(columnNames = {"mobilePhone"})})
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Account extends BaseModel {

    private static final long serialVersionUID = -399228065494594045L;

    @Column(name = "mobile_phone",length = 11,unique = true,nullable = false)
    private String mobilePhone;

    @Column(name = "user_name",length = 50)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type",length = 20)
    private AccountTypeEnum accountType = AccountTypeEnum.ORDINARY;

    @Column(name = "password",length = 50)
    private String password;

    public Account(String mobilePhone,String userName,AccountTypeEnum accountType,String password){
        this.mobilePhone = mobilePhone;
        this.userName = userName;
        this.accountType = accountType;
        this.password = password;
    }
}
