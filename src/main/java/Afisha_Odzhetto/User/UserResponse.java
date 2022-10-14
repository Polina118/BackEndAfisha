package Afisha_Odzhetto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserResponse {
    int id;
    String login;
    boolean moderator;
    boolean admin;

}
