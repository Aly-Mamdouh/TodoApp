package jobhackersystem.org.error;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ApiBodyException {
    private final String message;
    private final LocalDateTime date;
    private final HttpStatus httpStatus;

    public ApiBodyException(String message, LocalDateTime date, HttpStatus httpStatus) {
        this.message = message;
        this.date = date;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
