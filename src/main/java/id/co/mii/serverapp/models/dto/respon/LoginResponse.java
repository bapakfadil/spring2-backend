package id.co.mii.serverapp.models.dto.respon;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String email;
    private List<String> authorities;
}
