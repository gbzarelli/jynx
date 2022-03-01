package br.com.helpdev.jynx.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RegisterImage {

    private final UUID uuid;
    private final SavedImage savedImage;
    private final String imageName;
    private final Status status;

}
