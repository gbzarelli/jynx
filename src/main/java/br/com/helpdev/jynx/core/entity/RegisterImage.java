package br.com.helpdev.jynx.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;

@Builder
@Getter
public class RegisterImage {

    private final Path path;
    private final String imageName;
    private final Status status;

}
