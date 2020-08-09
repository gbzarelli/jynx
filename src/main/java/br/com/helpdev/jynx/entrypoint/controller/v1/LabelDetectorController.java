package br.com.helpdev.jynx.entrypoint.controller.v1;

import br.com.helpdev.jynx.core.DetectorAsyncUseCase;
import br.com.helpdev.jynx.entrypoint.controller.v1.dto.MultipartBody;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/v1/label-detector")
class LabelDetectorController {

    private final DetectorAsyncUseCase detectorAsyncUseCase;

    @Inject
    LabelDetectorController(final DetectorAsyncUseCase detectorAsyncUseCase) {
        this.detectorAsyncUseCase = detectorAsyncUseCase;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerImageToLabelDetect(@MultipartForm MultipartBody data) {
        final var uuid = detectorAsyncUseCase.registerImageToLabelDetect(data.getFile(),
                data.getFileName());
        return Response.created(URI.create(
                String.format("/v1/label-detector/%s", uuid.toString())
        )).build();
    }


}