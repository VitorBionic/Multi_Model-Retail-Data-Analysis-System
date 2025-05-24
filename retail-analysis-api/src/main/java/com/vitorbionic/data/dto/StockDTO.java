package com.vitorbionic.data.dto;

import java.time.LocalDate;

public record StockDTO(Long id, Long filmId, Integer quantity, LocalDate startValidity, LocalDate endValidity) {

}
