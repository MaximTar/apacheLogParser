package main.java.com.github.apachelogparser.controller;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by maxtar on 2/12/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LogString {
    // TODO update to latest version
    private LogString() {

    }


    static Builder newBuilder() {
        return new LogString().new Builder();
    }

    public String getRemoteIp() {
        return remoteIp.get();
    }

    public SimpleStringProperty remoteIpProperty() {
        return remoteIp;
    }

    public String getLocalIp() {
        return localIp.get();
    }

    public SimpleStringProperty localIpProperty() {
        return localIp;
    }

    public String getSizeOfResponseInBytes() {
        return sizeOfResponseInBytes.get();
    }

    public SimpleStringProperty sizeOfResponseInBytesProperty() {
        return sizeOfResponseInBytes;
    }

    public String getSizeOfResponseInBytesInClf() {
        return sizeOfResponseInBytesInClf.get();
    }

    public SimpleStringProperty sizeOfResponseInBytesInClfProperty() {
        return sizeOfResponseInBytesInClf;
    }

    public String getContentsOfCookie() {
        return contentsOfCookie.get();
    }

    public SimpleStringProperty contentsOfCookieProperty() {
        return contentsOfCookie;
    }

    public String getTimeToServeRequest() {
        return timeToServeRequest.get();
    }

    public SimpleStringProperty timeToServeRequestProperty() {
        return timeToServeRequest;
    }

    public String getContentsOfEnvironmentVar() {
        return contentsOfEnvironmentVar.get();
    }

    public SimpleStringProperty contentsOfEnvironmentVarProperty() {
        return contentsOfEnvironmentVar;
    }

    public String getFilename() {
        return filename.get();
    }

    public SimpleStringProperty filenameProperty() {
        return filename;
    }

    public String getRemoteHost() {
        return remoteHost.get();
    }

    public SimpleStringProperty remoteHostProperty() {
        return remoteHost;
    }

    public String getRequestProtocol() {
        return requestProtocol.get();
    }

    public SimpleStringProperty requestProtocolProperty() {
        return requestProtocol;
    }

    public String getContentsOfHeaderInRequest() {
        return contentsOfHeaderInRequest.get();
    }

    public SimpleStringProperty contentsOfHeaderInRequestProperty() {
        return contentsOfHeaderInRequest;
    }

    public String getRemoteLogName() {
        return remoteLogName.get();
    }

    public SimpleStringProperty remoteLogNameProperty() {
        return remoteLogName;
    }

    public String getRequestMethod() {
        return requestMethod.get();
    }

    public SimpleStringProperty requestMethodProperty() {
        return requestMethod;
    }

    public String getContentsOfNote() {
        return contentsOfNote.get();
    }

    public SimpleStringProperty contentsOfNoteProperty() {
        return contentsOfNote;
    }

    public String getContentsOfHeaderInReply() {
        return contentsOfHeaderInReply.get();
    }

    public SimpleStringProperty contentsOfHeaderInReplyProperty() {
        return contentsOfHeaderInReply;
    }

    public String getCanonicalPort() {
        return canonicalPort.get();
    }

    public SimpleStringProperty canonicalPortProperty() {
        return canonicalPort;
    }

    public String getProcessIdOfChild() {
        return processIdOfChild.get();
    }

    public SimpleStringProperty processIdOfChildProperty() {
        return processIdOfChild;
    }

    public String getProcessIdOfThreadOfChild() {
        return processIdOfThreadOfChild.get();
    }

    public SimpleStringProperty processIdOfThreadOfChildProperty() {
        return processIdOfThreadOfChild;
    }

    public String getQueryString() {
        return queryString.get();
    }

    public SimpleStringProperty queryStringProperty() {
        return queryString;
    }

    public String getFirstLineOfRequest() {
        return firstLineOfRequest.get();
    }

    public SimpleStringProperty firstLineOfRequestProperty() {
        return firstLineOfRequest;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getTimeInStandardFormat() {
        return timeInStandardFormat.get();
    }

    public SimpleStringProperty timeInStandardFormatProperty() {
        return timeInStandardFormat;
    }

    public String getTimeInGivenFormat() {
        return timeInGivenFormat.get();
    }

    public SimpleStringProperty timeInGivenFormatProperty() {
        return timeInGivenFormat;
    }

    public String getTimeTakenToServe() {
        return timeTakenToServe.get();
    }

    public SimpleStringProperty timeTakenToServeProperty() {
        return timeTakenToServe;
    }

    public String getRemoteUser() {
        return remoteUser.get();
    }

    public SimpleStringProperty remoteUserProperty() {
        return remoteUser;
    }

    public String getUrlPathRequested() {
        return urlPathRequested.get();
    }

    public SimpleStringProperty urlPathRequestedProperty() {
        return urlPathRequested;
    }

    public String getCanonicalServerName() {
        return canonicalServerName.get();
    }

    public SimpleStringProperty canonicalServerNameProperty() {
        return canonicalServerName;
    }

    public String getServerName() {
        return serverName.get();
    }

    public SimpleStringProperty serverNameProperty() {
        return serverName;
    }

    public String getConnectionStatus() {
        return connectionStatus.get();
    }

    public SimpleStringProperty connectionStatusProperty() {
        return connectionStatus;
    }

    public String getBytesReceived() {
        return bytesReceived.get();
    }

    public SimpleStringProperty bytesReceivedProperty() {
        return bytesReceived;
    }

    public String getBytesSent() {
        return bytesSent.get();
    }

    public SimpleStringProperty bytesSentProperty() {
        return bytesSent;
    }

    private SimpleStringProperty remoteIp = new SimpleStringProperty();
    private SimpleStringProperty localIp = new SimpleStringProperty();
    private SimpleStringProperty sizeOfResponseInBytes = new SimpleStringProperty();
    private SimpleStringProperty sizeOfResponseInBytesInClf = new SimpleStringProperty();
    private SimpleStringProperty contentsOfCookie = new SimpleStringProperty();
    private SimpleStringProperty timeToServeRequest = new SimpleStringProperty();
    private SimpleStringProperty contentsOfEnvironmentVar = new SimpleStringProperty();
    private SimpleStringProperty filename = new SimpleStringProperty();
    private SimpleStringProperty remoteHost = new SimpleStringProperty();
    private SimpleStringProperty requestProtocol = new SimpleStringProperty();
    private SimpleStringProperty contentsOfHeaderInRequest = new SimpleStringProperty();
    private SimpleStringProperty remoteLogName = new SimpleStringProperty();
    private SimpleStringProperty requestMethod = new SimpleStringProperty();
    private SimpleStringProperty contentsOfNote = new SimpleStringProperty();
    private SimpleStringProperty contentsOfHeaderInReply = new SimpleStringProperty();
    private SimpleStringProperty canonicalPort = new SimpleStringProperty();
    private SimpleStringProperty processIdOfChild = new SimpleStringProperty();
    private SimpleStringProperty processIdOfThreadOfChild = new SimpleStringProperty();
    private SimpleStringProperty queryString = new SimpleStringProperty();
    private SimpleStringProperty firstLineOfRequest = new SimpleStringProperty();
    private SimpleStringProperty status = new SimpleStringProperty();
    private SimpleStringProperty timeInStandardFormat = new SimpleStringProperty();
    private SimpleStringProperty timeInGivenFormat = new SimpleStringProperty();
    private SimpleStringProperty timeTakenToServe = new SimpleStringProperty();
    private SimpleStringProperty remoteUser = new SimpleStringProperty();
    private SimpleStringProperty urlPathRequested = new SimpleStringProperty();
    private SimpleStringProperty canonicalServerName = new SimpleStringProperty();
    private SimpleStringProperty serverName = new SimpleStringProperty();
    private SimpleStringProperty connectionStatus = new SimpleStringProperty();
    private SimpleStringProperty bytesReceived = new SimpleStringProperty();
    private SimpleStringProperty bytesSent = new SimpleStringProperty();

    public static String buildCsvLine(LogString logString) {
        StringBuilder sb = new StringBuilder();
        if (logString.getRemoteIp() != null) {
            sb.append(logString.getRemoteIp()).append(",");
        }
        if (logString.getSizeOfResponseInBytes() != null) {
            sb.append(logString.getSizeOfResponseInBytes()).append(",");
        }
        if (logString.getSizeOfResponseInBytesInClf() != null) {
            sb.append(logString.getSizeOfResponseInBytesInClf()).append(",");
        }
        if (logString.getContentsOfCookie() != null) {
            sb.append(logString.getContentsOfCookie()).append(",");
        }
        if (logString.getTimeToServeRequest() != null) {
            sb.append(logString.getTimeToServeRequest()).append(",");
        }
        if (logString.getContentsOfEnvironmentVar() != null) {
            sb.append(logString.getContentsOfEnvironmentVar()).append(",");
        }
        if (logString.getFilename() != null) {
            sb.append(logString.getFilename()).append(",");
        }
        if (logString.getRemoteHost() != null) {
            sb.append(logString.getRemoteHost()).append(",");
        }
        if (logString.getRequestProtocol() != null) {
            sb.append(logString.getRequestProtocol()).append(",");
        }
        if (logString.getContentsOfHeaderInRequest() != null) {
            sb.append(logString.getContentsOfHeaderInRequest()).append(",");
        }
        if (logString.getRemoteLogName() != null) {
            sb.append(logString.getRemoteLogName()).append(",");
        }
        if (logString.getRequestMethod() != null) {
            sb.append(logString.getRequestMethod()).append(",");
        }
        if (logString.getContentsOfNote() != null) {
            sb.append(logString.getContentsOfNote()).append(",");
        }
        if (logString.getContentsOfHeaderInReply() != null) {
            sb.append(logString.getContentsOfHeaderInReply()).append(",");
        }
        if (logString.getCanonicalPort() != null) {
            sb.append(logString.getCanonicalPort()).append(",");
        }
        if (logString.getProcessIdOfChild() != null) {
            sb.append(logString.getProcessIdOfChild()).append(",");
        }
        if (logString.getProcessIdOfThreadOfChild() != null) {
            sb.append(logString.getProcessIdOfThreadOfChild()).append(",");
        }
        if (logString.getQueryString() != null) {
            sb.append(logString.getQueryString()).append(",");
        }
        if (logString.getFirstLineOfRequest() != null) {
            sb.append(logString.getFirstLineOfRequest()).append(",");
        }
        if (logString.getStatus() != null) {
            sb.append(logString.getStatus()).append(",");
        }
        if (logString.getTimeInStandardFormat() != null) {
            sb.append(logString.getTimeInStandardFormat()).append(",");
        }
        if (logString.getTimeInGivenFormat() != null) {
            sb.append(logString.getTimeInGivenFormat()).append(",");
        }
        if (logString.getTimeTakenToServe() != null) {
            sb.append(logString.getTimeTakenToServe()).append(",");
        }
        if (logString.getRemoteUser() != null) {
            sb.append(logString.getRemoteUser()).append(",");
        }
        if (logString.getUrlPathRequested() != null) {
            sb.append(logString.getUrlPathRequested()).append(",");
        }
        if (logString.getCanonicalServerName() != null) {
            sb.append(logString.getCanonicalServerName()).append(",");
        }
        if (logString.getServerName() != null) {
            sb.append(logString.getServerName()).append(",");
        }
        if (logString.getConnectionStatus() != null) {
            sb.append(logString.getConnectionStatus()).append(",");
        }
        if (logString.getBytesReceived() != null) {
            sb.append(logString.getBytesReceived()).append(",");
        }
        if (logString.getBytesSent() != null) {
            sb.append(logString.getBytesSent()).append(",");
        }
        sb.append("\n");

        return sb.toString();
    }

    @SuppressWarnings("UnusedReturnValue")
    class Builder {

        private Builder() {

        }

        Builder setRemoteIp(String remoteIp) {
            LogString.this.remoteIp = new SimpleStringProperty(remoteIp);
            return this;
        }

        Builder setLocalIp(String localIp) {
            LogString.this.localIp = new SimpleStringProperty(localIp);
            return this;
        }

        Builder setSizeOfResponseInBytes(String sizeOfResponseInBytes) {
            LogString.this.sizeOfResponseInBytes = new SimpleStringProperty(sizeOfResponseInBytes);
            return this;
        }

        Builder setSizeOfResponseInBytesInClf(String sizeOfResponseInBytesInClf) {
            LogString.this.sizeOfResponseInBytesInClf = new SimpleStringProperty(sizeOfResponseInBytesInClf);
            return this;
        }

        Builder setContentsOfCookie(String contentsOfCookie) {
            LogString.this.contentsOfCookie = new SimpleStringProperty(contentsOfCookie);
            return this;
        }

        Builder setTimeToServeRequest(String timeToServeRequest) {
            LogString.this.timeToServeRequest = new SimpleStringProperty(timeToServeRequest);
            return this;
        }

        Builder setContentsOfEnvironmentVar(String contentsOfEnvironmentVar) {
            LogString.this.contentsOfEnvironmentVar = new SimpleStringProperty(contentsOfEnvironmentVar);
            return this;
        }

        Builder setFilename(String filename) {
            LogString.this.filename = new SimpleStringProperty(filename);
            return this;
        }

        Builder setRemoteHost(String remoteHost) {
            LogString.this.remoteHost = new SimpleStringProperty(remoteHost);
            return this;
        }

        Builder setRequestProtocol(String requestProtocol) {
            LogString.this.requestProtocol = new SimpleStringProperty(requestProtocol);
            return this;
        }

        Builder setContentsOfHeaderInRequest(String contentsOfHeaderInRequest) {
            LogString.this.contentsOfHeaderInRequest = new SimpleStringProperty(contentsOfHeaderInRequest);
            return this;
        }

        Builder setRemoteLogname(String remoteLogname) {
            LogString.this.remoteLogName = new SimpleStringProperty(remoteLogname);
            return this;
        }

        Builder setRequestMethod(String requestMethod) {
            LogString.this.requestMethod = new SimpleStringProperty(requestMethod);
            return this;
        }

        Builder setContentsOfNote(String contentsOfNote) {
            LogString.this.contentsOfNote = new SimpleStringProperty(contentsOfNote);
            return this;
        }

        Builder setContentsOfHeaderInReply(String contentsOfHeaderInReply) {
            LogString.this.contentsOfHeaderInReply = new SimpleStringProperty(contentsOfHeaderInReply);
            return this;
        }

        Builder setCanonicalPort(String canonicalPort) {
            LogString.this.canonicalPort = new SimpleStringProperty(canonicalPort);
            return this;
        }

        Builder setProcessIdOfChild(String processIdOfChild) {
            LogString.this.processIdOfChild = new SimpleStringProperty(processIdOfChild);
            return this;
        }

        Builder setProcessIdOfThreadOfChild(String processIdOfThreadOfChild) {
            LogString.this.processIdOfThreadOfChild = new SimpleStringProperty(processIdOfThreadOfChild);
            return this;
        }

        Builder setQueryString(String queryString) {
            LogString.this.queryString = new SimpleStringProperty(queryString);
            return this;
        }

        Builder setFirstLineOfRequest(String firstLineOfRequest) {
            LogString.this.firstLineOfRequest = new SimpleStringProperty(firstLineOfRequest);
            return this;
        }

        Builder setStatus(String status) {
            LogString.this.status = new SimpleStringProperty(status);
            return this;
        }

        Builder setTimeInGivenFormat(String timeInGivenFormat) {
            LogString.this.timeInGivenFormat = new SimpleStringProperty(timeInGivenFormat);
            return this;
        }

        Builder setTimeInStandardFormat(String timeInStandardFormat) {
            LogString.this.timeInStandardFormat = new SimpleStringProperty(timeInStandardFormat);
            return this;
        }

        Builder setTimeTakenToServe(String timeTakenToServe) {
            LogString.this.timeTakenToServe = new SimpleStringProperty(timeTakenToServe);
            return this;
        }

        Builder setRemoteUser(String remoteUser) {
            LogString.this.remoteUser = new SimpleStringProperty(remoteUser);
            return this;
        }

        Builder setUrlPathRequested(String urlPathRequested) {
            LogString.this.urlPathRequested = new SimpleStringProperty(urlPathRequested);
            return this;
        }

        Builder setCanonicalServerName(String canonicalServerName) {
            LogString.this.canonicalServerName = new SimpleStringProperty(canonicalServerName);
            return this;
        }

        Builder setServerName(String serverName) {
            LogString.this.serverName = new SimpleStringProperty(serverName);
            return this;
        }

        Builder setConnectionStatus(String connectionStatus) {
            LogString.this.connectionStatus = new SimpleStringProperty(connectionStatus);
            return this;
        }

        Builder setBytesReceived(String bytesReceived) {
            LogString.this.bytesReceived = new SimpleStringProperty(bytesReceived);
            return this;
        }

        Builder setBytesSent(String bytesSent) {
            LogString.this.bytesSent = new SimpleStringProperty(bytesSent);
            return this;
        }

        LogString build() {
            return LogString.this;
        }
    }

    @Override
    public String toString() {
        return "LogString{" +
                "remoteIp=" + remoteIp +
                ", localIp=" + localIp +
                ", sizeOfResponseInBytes=" + sizeOfResponseInBytes +
                ", sizeOfResponseInBytesInClf=" + sizeOfResponseInBytesInClf +
                ", contentsOfCookie=" + contentsOfCookie +
                ", timeToServeRequest=" + timeToServeRequest +
                ", contentsOfEnvironmentVar=" + contentsOfEnvironmentVar +
                ", filename=" + filename +
                ", remoteHost=" + remoteHost +
                ", requestProtocol=" + requestProtocol +
                ", contentsOfHeaderInRequest=" + contentsOfHeaderInRequest +
                ", remoteLogName=" + remoteLogName +
                ", requestMethod=" + requestMethod +
                ", contentsOfNote=" + contentsOfNote +
                ", contentsOfHeaderInReply=" + contentsOfHeaderInReply +
                ", canonicalPort=" + canonicalPort +
                ", processIdOfChild=" + processIdOfChild +
                ", processIdOfThreadOfChild=" + processIdOfThreadOfChild +
                ", queryString=" + queryString +
                ", firstLineOfRequest=" + firstLineOfRequest +
                ", status=" + status +
                ", timeInStandardFormat=" + timeInStandardFormat +
                ", timeInGivenFormat=" + timeInGivenFormat +
                ", timeTakenToServe=" + timeTakenToServe +
                ", remoteUser=" + remoteUser +
                ", urlPathRequested=" + urlPathRequested +
                ", canonicalServerName=" + canonicalServerName +
                ", serverName=" + serverName +
                ", connectionStatus=" + connectionStatus +
                ", bytesReceived=" + bytesReceived +
                ", bytesSent=" + bytesSent +
                '}';
    }
}
