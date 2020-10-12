package br.com.helpdev.jynx.core.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SavedImage {

    private final String id;
    private final String location;
    private final String path;

}
