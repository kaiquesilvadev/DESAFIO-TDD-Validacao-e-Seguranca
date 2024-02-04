package com.devsuperior.demo.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class ApiErro {

    private OffsetDateTime timestamp;
    private Integer status;
    private String erro;
    private String message;
    private String path;
    private List<Field> errors = new ArrayList<>();

    public static class Field {
        private String fieldName;
        private String message;

        public Field(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ApiErroBuilder {
        private OffsetDateTime timestamp;
        private Integer status;
        private String erro;
        private String message;
        private String path;
        private List<Field> errors = new ArrayList<>();

        public ApiErroBuilder timestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiErroBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public ApiErroBuilder erro(String erro) {
            this.erro = erro;
            return this;
        }

        public ApiErroBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErroBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ApiErroBuilder errors(List<Field> errors) {
            this.errors = errors;
            return this;
        }

        public ApiErro build() {
            ApiErro apiErro = new ApiErro();
            apiErro.setTimestamp(this.timestamp);
            apiErro.setStatus(this.status);
            apiErro.setErro(this.erro);
            apiErro.setMessage(this.message);
            apiErro.setPath(this.path);
            apiErro.seterrors(this.errors);
            return apiErro;
        }
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Field> geterrors() {
        return errors;
    }

    public void seterrors(List<Field> errors) {
        this.errors = errors;
    }
}
