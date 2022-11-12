package tech.hdurmaz.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer income;
    private String phoneNumber;
    private String identityNumber;
}
