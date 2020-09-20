package br.com.helpdev.jynx.dataprovider.database;

import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.dataprovider.database.entity.LabelDetectorEntity;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
class LabelDetectorDatabaseImplTest {

    @Inject
    LabelDetectorDatabaseImpl labelDetectorDatabase;

    @Test
    @Transactional
    @DisplayName("Should register image in database with success")
    void shouldRegisterImageInDatabaseWithSuccess() {
        final var registerImage = RegisterImage.builder()
                .imageName("test")
                .path(Path.of("test"))
                .status(Status.FAILURE)
                .build();

        final var uuidRegistered = labelDetectorDatabase.registerImage(registerImage);
        final var entity = LabelDetectorEntity.<LabelDetectorEntity>findById(uuidRegistered);

        assertNotNull(uuidRegistered);
        assertNotNull(entity);
        assertEquals(registerImage.getStatus(), entity.getStatus());
        assertEquals(registerImage.getImageName(), entity.getImageEntity().getImageName());
        assertEquals(registerImage.getPath().toString(), entity.getImageEntity().getPath());
    }

}