package com.turkcell.turkcellcrm.customerService.business.dtos.request.customer.individualCustomer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    @NotNull
    private int id;
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    private String middleName;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    @Size(min = 2, max = 30)
    private String gender;
    @Size(min = 2, max = 30)
    private String fatherName;
    @Size(min = 2, max = 30)
    private String motherName;
    @NotNull
    @Size(min = 11, max = 11, message = "Nationality ID must be 11.")
    private String nationalityNumber;
    @Size(min = 10, max = 30)
    private String email;
    @NotNull
    @Size(min = 10, max = 10)
    private String mobilePhoneNumber;
}