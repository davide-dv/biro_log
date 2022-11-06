package it.biro.biro_log;

import io.restassured.RestAssured;
import it.biro.biro_log.entities.LogData;
import it.biro.biro_log.repositories.LogDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

@SpringBootTest(classes = BiroLogApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "server.port=0")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/snippets")
abstract class BaseClass {

    @LocalServerPort
    int port;

    @MockBean
    private LogDataRepository logDataRepository;

    @BeforeEach
    public void setup() {
        Mockito.when(this.logDataRepository.save(LogData.builder().message("message").origin("service-origin").build()))
        .thenReturn(LogData.builder().message("message").origin("service-origin").build());
        Mockito.when(this.logDataRepository.findByOrigin("service-origin"))
                .thenReturn(Collections.singletonList(LogData.builder().message("message").origin("service-origin").build()));
        RestAssured.baseURI = "http://localhost:" + port;
    }

}
