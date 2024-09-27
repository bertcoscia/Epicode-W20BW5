package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.payloads.UserDTO;
import bertcoscia.Epicode_W20BW5.payloads.UserLoginDTO;
import bertcoscia.Epicode_W20BW5.payloads.UserLoginRespDto;
import bertcoscia.Epicode_W20BW5.services.AuthService;
import bertcoscia.Epicode_W20BW5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEntityRespDTO save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new NewEntityRespDTO(this.usersService.saveUser(body).getId());
        }

    }

    @PostMapping("/login")
    public UserLoginRespDto login(@RequestBody UserLoginDTO payload) {
        return new UserLoginRespDto(this.authService.checkCredentialsAndGenerateToken(payload));
    }
}
