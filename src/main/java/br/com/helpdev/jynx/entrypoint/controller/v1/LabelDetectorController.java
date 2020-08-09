package br.com.helpdev.jynx.entrypoint.controller.v1;

import br.com.helpdev.jynx.core.DetectorAsyncUseCase;
import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;
import br.com.helpdev.jynx.entrypoint.controller.v1.objects.MultipartBody;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static java.lang.String.format;

@Path(LabelDetectorController.ROOT_PATH)
class LabelDetectorController {

    public static final String ROOT_PATH = "/v1/label-detector";

    private final DetectorAsyncUseCase detectorAsyncUseCase;

    LabelDetectorController(final DetectorAsyncUseCase detectorAsyncUseCase) {
        this.detectorAsyncUseCase = detectorAsyncUseCase;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerImageToLabelDetect(final @MultipartForm MultipartBody data) {
        final var uuid = detectorAsyncUseCase.registerImageToLabelDetect(data.getFile(),
                data.getFileName());
        return created(uuid);
    }

    private Response created(final LabelDetectorStatus uuid) {
        return Response.created(URI.create(
                format(ROOT_PATH.concat("/%s"), uuid.toString())
        )).build();
    }


}