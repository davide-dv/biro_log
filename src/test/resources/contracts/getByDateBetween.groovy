import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


Contract.make {
    description ""
    request {
        url "/api/log/date/1970-01-01T00:00:00/1970-01-01T00:00:10"
        method GET()
    }
    response {
        status 200
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        }
        body([[
                date: "1970-01-01T00:00:00.000+00:00",
                message: "json_formatted_data",
                origin: "service-origin",
        ]])
    }
}