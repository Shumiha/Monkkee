package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Contact {
    String firstName;
    String lastName;
    String accountName;
    String mobile;
    String phone;
    String salutation;
}