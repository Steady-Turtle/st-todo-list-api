package com.example.anna.entity._common;

import lombok.Getter;

@Getter
public class EnumValue {
    private String code;
    private String korName;
    private String engName;


    public EnumValue(EnumModel enumModel) {
        this.code = enumModel.getCode();
        this.korName = enumModel.getKorName();
        this.engName=enumModel.getEngName();
    }
}
