package com.nisum.domain.model.phone.gateways;


import com.nisum.domain.model.phone.Phone;

public interface PhoneRepository {

    Phone save(Phone phone);
}
