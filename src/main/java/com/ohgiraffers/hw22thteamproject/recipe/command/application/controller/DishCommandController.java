package com.ohgiraffers.hw22thteamproject.recipe.command.application.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.DishCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.DishUpdateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.DishCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishCommandController {

    private final DishCommandService dishCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> registDish(@RequestBody DishCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.registDish(request, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateDish(
            @RequestBody com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.DishUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.updateDish(request, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{dishNo}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(
            @PathVariable Integer dishNo,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.deleteDish(dishNo, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
