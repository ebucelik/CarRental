package ac.at.fhcampuswien.carrental.rest.models;

import lombok.Data;

@Data
public class RefreshTokenResponseDTO {
    private String accessToken;

    public RefreshTokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}

