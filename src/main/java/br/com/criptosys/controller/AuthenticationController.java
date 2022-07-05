package br.com.criptosys.controller;

import br.com.criptosys.dto.ChangePasswordDTO;
import br.com.criptosys.dto.PasswordRecoveryDTO;
import br.com.criptosys.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Operation(description = "Envia email de recuperção de senha. Send email to password recovery"
            , summary = "Password recovery")
    @PostMapping("/password-recovery")
    public ResponseEntity<Void> passwordRecovery(@Valid @RequestBody PasswordRecoveryDTO passwordRecoveryDTO) {
        this.authenticationService.passwordRecovery(passwordRecoveryDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Validate the code to recovery password"
            , summary = "Password recovery code validation")
    @GetMapping("/code-validate")
    public ResponseEntity<?> codeValidate(@NotBlank @RequestParam(value = "codigo") String codigo) {
//        try {
//            codigoService.validarCodigo(codigo);
//            return ResponseEntity.status(200).build();
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(404).body(new ErroDto("Código inválido.", false));
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(new ErroDto("Erro ao recuperar código.", false));
//        }
        return null;
    }

    @Operation(description = "Change password from user"
            , summary = "Change password")
    @PostMapping("/change-password/")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return null;
    }
}
