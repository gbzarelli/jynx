package br.com.helpdev;

import br.com.helpdev.jynx.entrypoint.controller.v1.LabelDetectorControllerTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeLabelDetectorControllerIT extends LabelDetectorControllerTest {

    // Execute the same tests but in native mode.
}