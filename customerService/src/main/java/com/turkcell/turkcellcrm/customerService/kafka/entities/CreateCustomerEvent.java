package com.turkcell.turkcellcrm.customerService.kafka.entities;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateCustomerEvent {
    private String nationalityNumber;
    private int id;
    private String accountAccountNumber;
    private String mobilePhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
}
