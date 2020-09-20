package br.com.helpdev.jynx.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.com.helpdev.jynx")
class CleanArchUnitTest {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .layer("Core").definedBy("br.com.helpdev.jynx.core..")
            .layer("EntryPoint").definedBy("br.com.helpdev.jynx.entrypoint..")
            .layer("DataProvider").definedBy("br.com.helpdev.jynx.dataprovider..")
            .layer("Configuration").definedBy("br.com.helpdev.jynx.config..")

            .whereLayer("Core").mayOnlyBeAccessedByLayers("DataProvider", "EntryPoint", "Configuration")
            .whereLayer("EntryPoint").mayOnlyBeAccessedByLayers("DataProvider", "Configuration")
            .whereLayer("DataProvider").mayOnlyBeAccessedByLayers("Configuration")
            .whereLayer("Configuration").mayNotBeAccessedByAnyLayer();
}
