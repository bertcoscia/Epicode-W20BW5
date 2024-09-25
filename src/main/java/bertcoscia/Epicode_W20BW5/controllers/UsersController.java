package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.User;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.UserDTO;
import bertcoscia.Epicode_W20BW5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /*ME*/
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.usersService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody UserDTO body) {
        return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    @PutMapping("/me/avatar")
    public User uploadMeAvatar(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.usersService.uploadImage(currentAuthenticatedUser.getId(), image);
    }

    /*ADMIN*/
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.usersService.findAll(page, size, sortBy);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID userId) {
        return this.usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')") // Solo gli admin possono modificare gli utenti
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return this.usersService.findByIdAndUpdate(userId, body);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID userId) {
        this.usersService.findByIdAndDelete(userId);
    }

    @PutMapping("/{userId}/avatar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User uploadAvatar(@PathVariable UUID userId, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.usersService.uploadImage(userId, image);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateRuolo(@PathVariable UUID userId, @RequestBody @Validated String nomeRuolo) {
        return this.usersService.updateRuolo(userId, nomeRuolo);
    }
}
