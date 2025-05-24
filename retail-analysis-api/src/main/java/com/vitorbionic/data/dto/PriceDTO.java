package com.vitorbionic.data.dto;

import java.time.LocalDate;

public record PriceDTO(Long id, Long filmId, Double price, LocalDate startValidity, LocalDate endValidity) {

}
