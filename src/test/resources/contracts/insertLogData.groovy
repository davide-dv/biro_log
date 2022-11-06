import org.apache.http.protocol.HTTP
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


Contract.make {
    description ""
    request {
        url "/api/log/"
        method POST()
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        }
        body([
                message: "message",
                origin: "service-origin"
        ])
    }
    response {
        status 200
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        }
        body([
                message: "message",
                origin: "service-origin"
        ])
    }
}