package de.d3adspace.mantikor.commons.codec;

import de.d3adspace.mantikor.commons.MantikorCommons;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.StringTokenizer;

@Getter
@RequiredArgsConstructor
public enum HTTPStatus {

    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    /**
     * The status code.
     */
    private final int statusCode;

    /**
     * The status message.
     */
    private final String statusMessage;

    public static HTTPStatus fromCode(String token) {

        StringTokenizer tokenizer = new StringTokenizer(token);
        int statusCode = Integer.parseInt(tokenizer.nextToken());

        String statusMessage = tokenizer.nextToken(MantikorCommons.CRLF);

        return Arrays.stream(values())
                .filter(httpStatus -> httpStatus.getStatusCode() == statusCode && statusMessage.equalsIgnoreCase(httpStatus.getStatusMessage()))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}
