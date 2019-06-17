package com.datacanvas.aps.pipes.service.common;

import java.io.Serializable;


public class RequestMapObject implements Serializable {

    public enum RequestType {
        RELEASE("release"),INSTALL("install"),UNINSTALL("uninstall");

        private String value;

        private RequestType(String value) {
            this.value = value;
        }

        public String toString(){
            return value;
        }
    }

    @Override
    public String toString() {
        return "RequestMapObject{" +
                "channel='" + channel + '\'' +
                ", tocken='" + tocken + '\'' +
                ", requestType='" + requestType + '\'' +
                ", souce=" + souce +
                ", callback='" + callback + '\'' +
                '}';
    }

    String channel;
    String tocken;
    String requestType;
    Object souce;
    String callback;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public Object getSouce() {
        return souce;
    }

    public void setSouce(Object souce) {
        this.souce = souce;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
