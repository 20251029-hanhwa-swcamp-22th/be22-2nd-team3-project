package com.ohgiraffers.hw22thteamproject.user.command.application.service;

import com.ohgiraffers.hw22thteamproject.user.command.application.dto.request.UserCreateRequest;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;
import com.ohgiraffers.hw22thteamproject.user.command.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;


    /* 신규 회원 가입 */
    @Transactional
    public void registUser(UserCreateRequest userCreateRequest) {
        /* 중복되는 데이터가 입력되었는지 검증 */
        this.userDomainService.validateValue(userCreateRequest);

        /* Request(DTO) to User Entity */
        User user = this.modelMapper.map(userCreateRequest, User.class);

        /* 비밀번호 암호화 */
        user.setEncodedPassword(this.passwordEncoder.encode(userCreateRequest.getPassword()));
        /* 저장 */
        this.userRepository.save(user);
    }

}
