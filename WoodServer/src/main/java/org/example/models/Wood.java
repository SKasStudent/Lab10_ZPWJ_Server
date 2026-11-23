package org.example.models;

public record Wood(
        String species,
        String type,
        String origin,
        String color,
        double availableStock
) {}