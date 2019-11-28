package dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
public class UserDTO {

    private Long id;

    @Email(message = "Invalid email")
    private String email;

    @Length(min = 3, max = 50, message = "Name should have between 3 and 50 characters")
    private String name;

    @NotNull
    @Length(min = 6, message = "Password should have at least 6 characters")
    private String password;
}
