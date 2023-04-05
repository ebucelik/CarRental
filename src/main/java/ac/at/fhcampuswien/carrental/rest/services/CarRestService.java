package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarRestService {
    @NonNull
    CarEntityService carEntityService;
    @NonNull
    JwtService jwtService;


    public List<Car> getAvailableCars(String token, LocalDate from, LocalDate to) {
            if(!jwtService.isTokenExpired(token)) {
                return carEntityService.getFreeCarsBetweenDates(from, to);
            } else{
                return null;
            }
    }

    public List<Car> getAllCars(String token) {
        return null;
    }
}





/*
@Override
    @Transactional
    public void changePassword(ChangePasswordDTO passwordDTO) {
        if (!passwordDTO.newPassword().equals(passwordDTO.verifiedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password mismatch");
        }
        UserEntity user = getUserEntity(getUserName());
        validatePassword(passwordDTO.currentPassword(), user.getPassword());

        user.setPassword(passwordEncoderMapper.encodePlainText(passwordDTO.newPassword()));
    }

    @Override
    public AuthenticationDTO refreshAccessToken(RefreshTokenDTO refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.refreshToken());
        return new AuthenticationDTO(jwtProvider.generateTokenWithUserName(refreshTokenRequest.userName()), refreshTokenService.generateRefreshToken().getToken(), LocalDateTime.now().plusSeconds(jwtProvider.getJwtExpirationInMillis()), refreshTokenRequest.userName());
    }


 */