package com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.request;

import lombok.Getter;

@Getter
public class DisposalCreateRequest {

    long ingredientStockNo; // 식재료 번호
    long disposalQuantity; // 폐기 수량
    String disposalReason; // 폐기 이력

}
