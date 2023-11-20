package com.example.anna.api.user.controller;

import com.example.anna.api._config.security.jwt.JwtAuthenticationResponse;
import com.example.anna.api.user.service.UserService;
import com.example.anna.common._common.AnnaModelMapper;
import com.example.anna.common._common.util.MessageUtil;
import com.example.anna.entity.user.domain.User;
import com.example.anna.api._config.security.UserPrincipal;
import com.example.anna.api.user.dto.ChangePwdDto;
import com.example.anna.api.user.dto.LoginDto;
import com.example.anna.api.user.dto.SignupDto;
import com.example.anna.api.user.exception.InvalidUserInfoException;
import com.example.anna.api.user.service.UserLoginService;
import com.example.anna.api.user.service.UserRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "회원관리 API")
@RestController
@RequiredArgsConstructor
public class UserAuthController {
    private final UserLoginService userLoginService;

    private final UserRegisterService userRegisterService;

    private final UserService userService;

    private final AnnaModelMapper annaModelMapper;

    /**
     * 회원가입
     *
     * @param signupDto
     * @param errors
     * @return
     */
    @Operation(summary = "회원가입")
    @PostMapping("/register/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@Valid @RequestBody SignupDto signupDto,
                                                                  Errors errors) {
        if (errors.hasErrors())
            throw new InvalidUserInfoException(MessageUtil.getMessage("INVALID_USER_INFO"), errors);

        User newUser = this.userRegisterService.register(new User(signupDto.getUserId(), signupDto.getPassword(), signupDto.getEmail()));

        // 회원가입 후 자동로그인 (jwt 토큰 반환)
        return ResponseEntity.ok(this.userLoginService.login(
                newUser.getUserId(), signupDto.getPassword()));
    }

    /**
     * 아이디, 이메일 중복확인
     *
     * @param type
     * @param value
     * @return
     */
    @Operation(summary = "아이디 / 이메일 중복확인")
    @GetMapping("/register/check")
    public ResponseEntity<Boolean> isUse(@RequestParam String type,
                                         @RequestParam String value) {
        return ResponseEntity.ok(this.userRegisterService.isUse(type, value));
    }


    /**
     * 로그인
     *
     * @param loginDto
     * @return
     */
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto, Errors errors) {
        if (errors.hasErrors())
            throw new InvalidUserInfoException(MessageUtil.getMessage("INVALID_USER_INFO"), errors);

        return ResponseEntity.ok(this.userLoginService.login(loginDto.getUserId(), loginDto.getPassword()));
    }

    /**
     * 회원 탈퇴
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "회원 탈퇴")
    @PutMapping("/withdraw")
    public void withdrawUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.withdrawUser(userPrincipal.getUser().getUserId());
    }

    /**
     * 비밀번호 변경
     *
     * @param changePwdDto
     * @return
     */
    @Operation(summary = "비밀번호 변경")
    @PostMapping("/change/password")
    public ResponseEntity<ChangePwdDto> changePassword(@Valid @RequestBody ChangePwdDto changePwdDto, Errors errors) {

        if (errors.hasErrors())
            throw new InvalidUserInfoException(MessageUtil.getMessage("INVALID_USER_INFO"), errors);

        User user = userService.changePassword(changePwdDto.getUserId(), changePwdDto.getPassword());
        return ResponseEntity.ok(annaModelMapper.convert(user, ChangePwdDto.class));
    }


}
