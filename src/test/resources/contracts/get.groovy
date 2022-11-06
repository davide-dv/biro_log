import org.apache.http.protocol.HTTP
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


Contract.make {
    description ""
    request {
        url "/api/log/"
        method GET()
    }
    response {
        status 200
    }
}