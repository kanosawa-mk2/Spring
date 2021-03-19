package com.example.demo.login.domain.model;

import javax.validation.GroupSequence;

// 検証の順序指定
@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}
