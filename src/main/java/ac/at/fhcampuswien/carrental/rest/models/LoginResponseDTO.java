package ac.at.fhcampuswien.carrental.rest.models;

import lombok.Data;

@Data
public class LoginResponseDTO {
    String accessToken;
    String refreshToken;

    public LoginResponseDTO(String accessToken,
                            String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
