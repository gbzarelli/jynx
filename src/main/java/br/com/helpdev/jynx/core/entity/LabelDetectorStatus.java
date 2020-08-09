package br.com.helpdev.jynx.core.entity;

import lombok.Builder;

import java.util.UUID;

@Builder
public class LabelDetectorStatus {
    private final UUID uuid;
    private final Status status;
    private final String message;
}
