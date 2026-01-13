package com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.service;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.response.IngredientStockCreateResponse;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository.IngredientStockDomainRepository;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.service.IngredientStockDomainService;
import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientStockCommandService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final IngredientStockDomainService ingredientStockDomainService;
    private final ModelMapper modelMapper;
    private final IngredientStockDomainRepository ingredientStockDomainRepository;

    public IngredientStockCreateResponse registIngredientStock(
            String refreshToken,
            IngredientStockCreateRequest ingredientStockCreateRequest
    ) {
        // 1. 로그인한 유저의 userNo(User.user_no) 가져오기
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));

        // User객체 가져오기
        User user = this.userRepository.findByUserNo(userNo);

        // 2. 사용자로부터 입력받은 ingredientStockCreateRequest 검증 (필수?)
        this.ingredientStockDomainService.validateValue(ingredientStockCreateRequest);

        // 3. ingredientStockCreateRequest(DTO) -> ingredientStock Entity로 변환
        IngredientStock ingredientStock = this.modelMapper.map(ingredientStockCreateRequest, IngredientStock.class);
        ingredientStock.InitTime();
        ingredientStock.setUser(user);

        // 4. ingredient_stock DB table 에 저장
        this.ingredientStockDomainRepository.save(ingredientStock);

        return new IngredientStockCreateResponse(ingredientStock);
    }

}
