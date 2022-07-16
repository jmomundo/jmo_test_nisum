package com.nisum.infraestructure.drivenadapters.database.phone;

import com.nisum.domain.model.phone.Phone;

public class PhoneMapper {
    private PhoneMapper() {
    }

    public static PhoneEntity toEntity(Phone phone) {
        var phoneEntity = new PhoneEntity();
        phoneEntity.setPhoneId(phone.getPhoneId());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setContryCode(phone.getContryCode());
        phoneEntity.setNumber(phone.getNumber());
        return phoneEntity;
    }


    public static Phone toUserDto(PhoneEntity phoneEntity) {
        return Phone.builder().phoneId(phoneEntity.getPhoneId())
                .cityCode(phoneEntity.getCityCode())
                .contryCode(phoneEntity.getContryCode())
                .number(phoneEntity.getNumber())
                .build();
    }
}
