package com.antique.service;

import com.antique.domain.User;
import com.antique.dto.user.UserRequestDTO;
import com.antique.exception.user.UserNotFoundException;
import com.antique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long updateUserDetails(Long userId, UserRequestDTO userRequestDto) {
        // 1. 기존 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // 2. 닉네임과 주소 업데이트
        user.updateNicknameAndAddress(userRequestDto.getNickname(), userRequestDto.getAddress());

        // 3. 저장 및 반환
        userRepository.save(user);
        return user.getUserId(); // 업데이트된 유저 ID 반환
    }

    @Transactional
    public Long updateUserNickname(Long userId, String nickname) {
        // 1. 기존 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // 2. 닉네임 업데이트
        user.updateNickname(nickname);

        // 3. 저장 및 반환
        userRepository.save(user);
        return user.getUserId(); // 업데이트된 유저 ID 반환
    }

    @Transactional
    public Long updateUserAddress(Long userId, String address) {
        // 1. 기존 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // 2. 닉네임 업데이트
        user.updateAddress(address);

        // 3. 저장 및 반환
        userRepository.save(user);
        return user.getUserId(); // 업데이트된 유저 ID 반환
    }
}
