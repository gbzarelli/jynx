package br.com.helpdev.jynx.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
public class LabelDetectorStatus {
    @Getter
    private final UUID uuid;
    private final Status status;
    private final String message;
}
