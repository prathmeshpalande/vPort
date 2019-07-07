package com.vport.model;

public class GeneralResponseObject {

    private Integer responseCode;
    private String responseMessage;
    private Object responseData;

    public static GeneralResponseObject getDefaultObject(Integer responseCode) {
        GeneralResponseObject responseObject;

        switch(responseCode) {
            case 0: {
                responseObject = new GeneralResponseObject(0, "Failure", null);
                break;
            }
            case 1: {
                responseObject = new GeneralResponseObject(1, "Success", null);
                break;
            }
            default: {
                responseObject = new GeneralResponseObject(0, "Failure", null);
            }
        }

        return responseObject;
    }
    public GeneralResponseObject() {

    }

    public GeneralResponseObject(Integer responseCode, String responseMessage, Object responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
