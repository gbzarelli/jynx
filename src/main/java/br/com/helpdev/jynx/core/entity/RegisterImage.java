package br.com.helpdev.jynx.core.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterImage {

    private final SavedImage savedImage;
    private final String imageName;
    private final Status status;

}
