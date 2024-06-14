package com.amalia.demoJPA.users.service;

import com.amalia.demoJPA.ExceptionHandling.DataNotFoundException;
import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.currencies.repository.CurrenciesRepository;
import com.amalia.demoJPA.languages.entity.Languages;
import com.amalia.demoJPA.languages.repository.LanguageRepository;
import com.amalia.demoJPA.users.dto.ForgotPasswordDTO;
import com.amalia.demoJPA.users.dto.ProfileRequestDTO;
import com.amalia.demoJPA.users.dto.RegisterRequestDTO;
import com.amalia.demoJPA.users.entity.Users;
import com.amalia.demoJPA.users.repository.UserRepository;
import com.amalia.demoJPA.users.service.UserService;
import com.amalia.demoJPA.auth.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrenciesRepository currenciesRepository;
    private final LanguageRepository languagesRepository;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrenciesRepository currenciesRepository, LanguageRepository languagesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currenciesRepository = currenciesRepository;
        this.languagesRepository = languagesRepository;
    }

    @Override
    public Users register(RegisterRequestDTO user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataNotFoundException("Email already exists");
        }
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            throw new DataNotFoundException("Password and confirmation password don't match");
        }

        Users newUser = user.toEntity();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);

        Currencies defaultCurrency = getDefaultCurrency();
        Languages defaultLanguage = getDefaultLanguage();
        newUser.setCurrencies(defaultCurrency);
        newUser.setLanguages(defaultLanguage);

        return userRepository.save(newUser);
    }

    private Currencies getDefaultCurrency() {
        Optional<Currencies> defaultCurrencyOptional = Optional.ofNullable(currenciesRepository.findFirstByOrderByIdAsc());
        return defaultCurrencyOptional.orElseThrow(() -> new IllegalArgumentException("Default currency not found"));
    }

    private Languages getDefaultLanguage() {
        Optional<Languages> defaultLanguageOptional = Optional.ofNullable(languagesRepository.findFirstByOrderByIdAsc());
        return defaultLanguageOptional.orElseThrow(() -> new IllegalArgumentException("Default language not found"));
    }


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public Users forgotPassword(ForgotPasswordDTO forgotPasswordRequestDTO) {
        Users user = userRepository.findByEmail(forgotPasswordRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPassword(passwordEncoder.encode(forgotPasswordRequestDTO.getPassword()));
        user.setUpdateAt(Instant.now());
        return userRepository.save(user);
    }

    @Override
    public ProfileRequestDTO updateProfile(ProfileRequestDTO profileRequestDTO) {
        var claims = Claims.getClaimsFromJwt();
        var email = (String) claims.get("sub");
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setEmail(profileRequestDTO.getEmail());
        user.setImages(profileRequestDTO.getProfilePhoto());
        user.setUsername(profileRequestDTO.getUsername());
        userRepository.save(user);
        return profileRequestDTO;
    }

    @Override
    public ProfileRequestDTO getProfile() {
        var claims = Claims.getClaimsFromJwt();
        var email = (String) claims.get("sub");

        Users user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO();
        profileRequestDTO.setEmail(user.getEmail());
        profileRequestDTO.setProfilePhoto(user.getImages());
        profileRequestDTO.setUsername(user.getUsername());
        return profileRequestDTO;
    }

    @Override
    public Users profile() {
        var claims = Claims.getClaimsFromJwt();
        var email = (String) claims.get("sub");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
