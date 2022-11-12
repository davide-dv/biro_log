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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@SpringBootTest(classes = BiroLogApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "server.port=0")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/snippets")
abstract class BaseClass {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @LocalServerPort
    int port;

    @MockBean
    private LogDataRepository logDataRepository;

    @BeforeEach
    public void setup() throws ParseException {
        RestAssured.baseURI = "http://localhost:" + port;

        LogData logData = LogData.builder()
                                .message("json_formatted_data")
                                .origin("service-origin")
                                .date(new Date(0)).build();

        Mockito.when(this.logDataRepository.save(logData))
                .thenReturn(logData);

        Mockito.when(this.logDataRepository.findByOrigin("service-origin"))
                .thenReturn(
                        Collections.singletonList(
                                LogData.builder()
                                        .message("json_formatted_data")
                                        .origin("service-origin")
                                        .date(new Date(0)).build()));

        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date start = formatter.parse("1970-01-01T00:00:00");
        Date end = formatter.parse("1970-01-01T00:00:10");
        Mockito.when(this.logDataRepository.findByDateBetween(start, end))
                .thenReturn(
                        Collections.singletonList(logData)
                );
        Mockito.when(this.logDataRepository.findByOriginAndDateBetween("service-origin", start, end))
                .thenReturn(
                        Collections.singletonList(logData)
                );
    }

}
