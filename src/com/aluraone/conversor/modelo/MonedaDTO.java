package com.aluraone.conversor.modelo;

public record MonedaDTO(String base_code,
                        String target_code,
                        double conversion_rate) {
}
