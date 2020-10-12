package br.com.helpdev.jynx.config;

import br.com.helpdev.jynx.dataprovider.storage.gcp.JynxImageBucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class GCPStorageConfiguration {

    public static final String PROJECT_ID = "jynx-api";
    public static final String BUCKET_NAME = "jynx-images";

    @Produces
    public Storage provideStorage() {
        return StorageOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .build()
                .getService();
    }

    @Produces
    public JynxImageBucket provideImageBucket() {
        return JynxImageBucket.withName(BUCKET_NAME);
    }
}
