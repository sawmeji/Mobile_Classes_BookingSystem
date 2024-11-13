package com.mks.backendtest_bookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    @JsonView(Views.Thin.class)
    private T data;

    @JsonView(Views.Thin.class)
    private boolean status;

    @JsonView(Views.Thin.class)
    private String message;

    private BSUser user;

    private String token;

    private String boId;



    public Response() {
        super();
    }

    public Response(boolean status, String message) {
        super();
        setStatus(status);
        setMessage(message);
    }


}
