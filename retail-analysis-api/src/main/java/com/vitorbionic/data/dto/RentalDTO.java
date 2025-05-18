package com.vitorbionic.data.dto;

public record RentalDTO(Long id, Long timeId, Long filmId, Long clientId, Long storeId, Double paidValue, Integer quantity) {

}
