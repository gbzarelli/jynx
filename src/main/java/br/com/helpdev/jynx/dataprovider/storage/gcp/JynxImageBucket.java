package br.com.helpdev.jynx.dataprovider.storage.gcp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JynxImageBucket {

    public static JynxImageBucket withName(final String name) {
        return new JynxImageBucket(name);
    }

    @NonNull
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
