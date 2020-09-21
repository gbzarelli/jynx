package br.com.helpdev.jynx.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "register", description = "Register operations."),
                @Tag(name = "consult", description = "Consult operations.")
        },
        info = @Info(
                title = "Jynx API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Guilherme Biff Zarelli",
                        url = "https://helpdev.com.br",
                        email = "gbzarelli@helpdev.com.br"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class JynxApplication extends Application {
}
