package com.importApi;

import com.commercetools.importapi.client.ProjectApiRoot;
import com.commercetools.importapi.defaultconfig.ImportApiRootBuilder;
import com.commercetools.importapi.defaultconfig.ServiceRegion;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;
import org.springframework.context.annotation.Bean;

public class client {
    @Bean
    public static ProjectApiRoot createApiClient() {
        ProjectApiRoot impoter=  ImportApiRootBuilder.of()
                .defaultClient(ClientCredentials.of()
                                .withClientId("hNGnjwada0_ppTlp1dUO5pcp")
                                .withClientSecret("gsAsXyB-JBeAPP8AqwDbSC9L5wAB7SIv")
                                .build(),
                        ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1)
                .build("ayush-ct-pratice");

        return impoter;
    }
}

//CTP_PROJECT_KEY=ayush-ct-pratice
//        CTP_CLIENT_SECRET=gsAsXyB-JBeAPP8AqwDbSC9L5wAB7SIv
//        CTP_CLIENT_ID=hNGnjwada0_ppTlp1dUO5pcp
//        CTP_AUTH_URL=https://auth.australia-southeast1.gcp.commercetools.com
//        CTP_API_URL=https://api.australia-southeast1.gcp.commercetools.com
//        CTP_SCOPES=manage_project:ayush-ct-pratice