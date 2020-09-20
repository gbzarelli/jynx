package br.com.helpdev.jynx.entrypoint.controller.v1;

import br.com.helpdev.jynx.core.usecase.RegisterToDetectAsyncUseCase;
import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;
import br.com.helpdev.jynx.entrypoint.controller.v1.objects.MultipartBody;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static java.lang.String.format;

@Path(LabelDetectorController.ROOT_PATH)
public class LabelDetectorController {

    public static final String ROOT_PATH = "/v1/label-detector";

    private final RegisterToDetectAsyncUseCase registerToDetectAsyncUseCase;

    @Inject
    LabelDetectorController(final RegisterToDetectAsyncUseCase registerToDetectAsyncUseCase) {
        this.registerToDetectAsyncUseCase = registerToDetectAsyncUseCase;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerImageToLabelDetect(final @MultipartForm MultipartBody data) {
        final var uuid = registerToDetectAsyncUseCase.registerImageToLabelDetect(data.getFile(),
                data.getFileName());
        return created(uuid);
    }

    private Response created(final LabelDetectorStatus uuid) {
        return Response.created(URI.create(
                format(ROOT_PATH.concat("/%s"), uuid.toString())
        )).build();
    }


}