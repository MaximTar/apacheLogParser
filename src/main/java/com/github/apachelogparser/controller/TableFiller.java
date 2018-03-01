package main.java.com.github.apachelogparser.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxtar on 2/12/18.
 * This class is for filling table with logStrings.
 */
class TableFiller {

    private TableFiller() {
    }


    static void fillTable(TableViewController controller, ObservableList<LogString> logData, MaskHandler.Parameters parameters) {

        Map<Integer, Character> maskParams = parameters.getParameters();
        Map<Integer, String> maskAddParams = parameters.getAdditionalParameters();
        Map<Integer, TableColumn<LogString, String>> tableColumnList = createTableColumnList(maskParams, maskAddParams);

        controller.getTable().setItems(logData);

        // FIXME find out correct method
        for (int i = 0; i < tableColumnList.size(); i++) {
            controller.getTable().getColumns().add(tableColumnList.get(i + 1));
        }
        for (int i = 0; i < tableColumnList.size(); i++) {
            controller.getTable().getColumns().set(i, tableColumnList.get(i + 1));
        }
    }

    private static Map<Integer, TableColumn<LogString, String>> createTableColumnList
            (Map<Integer, Character> maskParams, Map<Integer, String> maskAdditionalParams) {

        Map<Integer, TableColumn<LogString, String>> tableColumnList = new HashMap<>();
        if (maskParams.containsValue('a')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'a');
            String add = maskAdditionalParams.get(key);
            String name = "Remote IP";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> remoteIpCol = new TableColumn<>(name);
            remoteIpCol.setCellValueFactory(new PropertyValueFactory<>("remoteIp"));
            tableColumnList.put(key, remoteIpCol);
        }
        if (maskParams.containsValue('A')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'A');
            String add = maskAdditionalParams.get(key);
            String name = "Local IP";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> localIpCol = new TableColumn<>(name);
            localIpCol.setCellValueFactory(new PropertyValueFactory<>("localIp"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'A'), localIpCol);
        }
        if (maskParams.containsValue('B')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'B');
            String add = maskAdditionalParams.get(key);
            String name = "Size of response\nin bytes";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> sizeOfResponseInBytesCol = new TableColumn<>(name);
            sizeOfResponseInBytesCol.setCellValueFactory(new PropertyValueFactory<>("sizeOfResponseInBytes"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'B'), sizeOfResponseInBytesCol);
        }
        if (maskParams.containsValue('b')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'b');
            String add = maskAdditionalParams.get(key);
            String name = "Size of response\nin bytes in CLF";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> sizeOfResponseInBytesInClfCol = new TableColumn<>(name);
            sizeOfResponseInBytesInClfCol.setCellValueFactory(new PropertyValueFactory<>("sizeOfResponseInBytesInClf"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'b'), sizeOfResponseInBytesInClfCol);
        }
        if (maskParams.containsValue('C')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'C');
            String add = maskAdditionalParams.get(key);
            String name = "The contents\nof cookie";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> contentsOfCookieCol = new TableColumn<>(name);
            contentsOfCookieCol.setCellValueFactory(new PropertyValueFactory<>("contentsOfCookie"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'C'), contentsOfCookieCol);
        }
        if (maskParams.containsValue('D')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'D');
            String add = maskAdditionalParams.get(key);
            String name = "The time taken\nto serve the request";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> timeToServeRequestCol = new TableColumn<>(name);
            timeToServeRequestCol.setCellValueFactory(new PropertyValueFactory<>("timeToServeRequest"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'D'), timeToServeRequestCol);
        }
        if (maskParams.containsValue('e')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'e');
            String add = maskAdditionalParams.get(key);
            String name = "The contents of the\nenvironment variable";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> contentsOfEnvironmentVarCol = new TableColumn<>(name);
            contentsOfEnvironmentVarCol.setCellValueFactory(new PropertyValueFactory<>("contentsOfEnvironmentVar"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'e'), contentsOfEnvironmentVarCol);
        }
        if (maskParams.containsValue('f')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'f');
            String add = maskAdditionalParams.get(key);
            String name = "Filename";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> filenameCol = new TableColumn<>(name);
            filenameCol.setCellValueFactory(new PropertyValueFactory<>("filename"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'f'), filenameCol);
        }
        if (maskParams.containsValue('h')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'h');
            String add = maskAdditionalParams.get(key);
            String name = "Remote host";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> remoteHostCol = new TableColumn<>(name);
            remoteHostCol.setCellValueFactory(new PropertyValueFactory<>("remoteHost"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'h'), remoteHostCol);
        }
        if (maskParams.containsValue('H')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'H');
            String add = maskAdditionalParams.get(key);
            String name = "The request protocol";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> requestProtocolCol = new TableColumn<>(name);
            requestProtocolCol.setCellValueFactory(new PropertyValueFactory<>("requestProtocol"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'H'), requestProtocolCol);
        }
        if (maskParams.containsValue('i')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'i');
            String add = maskAdditionalParams.get(key);
            String name = "Header line(s)\nin the request\nsent to the server";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> contentsOfHeaderInRequestCol = new TableColumn<>(name);
            contentsOfHeaderInRequestCol.setCellValueFactory(new PropertyValueFactory<>("contentsOfHeaderInRequest"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'i'), contentsOfHeaderInRequestCol);
        }
        if (maskParams.containsValue('l')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'l');
            String add = maskAdditionalParams.get(key);
            String name = "Remote logname";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> remoteLogNameCol = new TableColumn<>(name);
            remoteLogNameCol.setCellValueFactory(new PropertyValueFactory<>("remoteLogName"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'l'), remoteLogNameCol);
        }
        if (maskParams.containsValue('m')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'm');
            String add = maskAdditionalParams.get(key);
            String name = "The request method";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> requestMethodCol = new TableColumn<>(name);
            requestMethodCol.setCellValueFactory(new PropertyValueFactory<>("requestMethod"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'm'), requestMethodCol);
        }
        if (maskParams.containsValue('n')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'n');
            String add = maskAdditionalParams.get(key);
            String name = "The contents of note";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> contentsOfNoteCol = new TableColumn<>(name);
            contentsOfNoteCol.setCellValueFactory(new PropertyValueFactory<>("contentsOfNote"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'n'), contentsOfNoteCol);
        }
        if (maskParams.containsValue('o')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'o');
            String add = maskAdditionalParams.get(key);
            String name = "The contents of";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> contentsOfHeaderInReplyCol = new TableColumn<>(name);
            contentsOfHeaderInReplyCol.setCellValueFactory(new PropertyValueFactory<>("contentsOfHeaderInReply"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'o'), contentsOfHeaderInReplyCol);
        }
        if (maskParams.containsValue('p')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'p');
            String add = maskAdditionalParams.get(key);
            String name = "The canonical port\nof the server";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> canonicalPortCol = new TableColumn<>(name);
            canonicalPortCol.setCellValueFactory(new PropertyValueFactory<>("canonicalPort"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'p'), canonicalPortCol);
        }
        if (maskParams.containsValue('P')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'P');
            String add = maskAdditionalParams.get(key);
            String name = "The process ID\nof the child";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> processIdOfChildCol = new TableColumn<>(name);
            processIdOfChildCol.setCellValueFactory(new PropertyValueFactory<>("processIdOfChild"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'P'), processIdOfChildCol);
        }
//            if (maskParams.containsValue('P')) { // w Format
//        TableColumn<LogString, String> processIdOfThreadOfChildCol = new TableColumn<>("The process ID\nor thread id of the child");
//            }
        if (maskParams.containsValue('q')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'q');
            String add = maskAdditionalParams.get(key);
            String name = "The query string";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> queryStringCol = new TableColumn<>(name);
            queryStringCol.setCellValueFactory(new PropertyValueFactory<>("queryString"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'q'), queryStringCol);
        }
        if (maskParams.containsValue('r')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'r');
            String add = maskAdditionalParams.get(key);
            String name = "First line of request";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> firstLineOfRequestCol = new TableColumn<>(name);
            firstLineOfRequestCol.setCellValueFactory(new PropertyValueFactory<>("firstLineOfRequest"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'r'), firstLineOfRequestCol);
        }
        if (maskParams.containsValue('s')) {
            int key = MaskHandler.getKeyByValue(maskParams, 's');
            String add = maskAdditionalParams.get(key);
            String name = "Status";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> statusCol = new TableColumn<>(name);
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 's'), statusCol);
        }
        if (maskParams.containsValue('t')) {
            int key = MaskHandler.getKeyByValue(maskParams, 't');
            String add = maskAdditionalParams.get(key);
            String name = "Time the request\nwas received";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> timeInStandardFormatCol = new TableColumn<>(name);
            timeInStandardFormatCol.setCellValueFactory(new PropertyValueFactory<>("timeInStandardFormat"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 't'), timeInStandardFormatCol);
        }
//        if (maskParams.containsValue('t')){ // w Format
//        TableColumn<LogString, String> timeInGivenFormatCol = new TableColumn<>("The time, in the form\ngiven by format");
//        }
        if (maskParams.containsValue('T')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'T');
            String add = maskAdditionalParams.get(key);
            String name = "The time taken\nto serve the request";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> timeTakenToServeCol = new TableColumn<>(name);
            timeTakenToServeCol.setCellValueFactory(new PropertyValueFactory<>("timeTakenToServe"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'T'), timeTakenToServeCol);
        }
        if (maskParams.containsValue('u')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'u');
            String add = maskAdditionalParams.get(key);
            String name = "Remote user";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> remoteUserCol = new TableColumn<>(name);
            remoteUserCol.setCellValueFactory(new PropertyValueFactory<>("remoteUser"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'u'), remoteUserCol);
        }
        if (maskParams.containsValue('U')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'U');
            String add = maskAdditionalParams.get(key);
            String name = "The URL path requested";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> urlPathRequestedCol = new TableColumn<>(name);
            urlPathRequestedCol.setCellValueFactory(new PropertyValueFactory<>("urlPathRequested"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'U'), urlPathRequestedCol);
        }
        if (maskParams.containsValue('v')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'v');
            String add = maskAdditionalParams.get(key);
            String name = "The canonical server name";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> canonicalServerNameCol = new TableColumn<>(name);
            canonicalServerNameCol.setCellValueFactory(new PropertyValueFactory<>("canonicalServerName"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'v'), canonicalServerNameCol);
        }
        if (maskParams.containsValue('V')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'V');
            String add = maskAdditionalParams.get(key);
            String name = "The server name";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> serverNameCol = new TableColumn<>(name);
            serverNameCol.setCellValueFactory(new PropertyValueFactory<>("serverName"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'V'), serverNameCol);
        }
        if (maskParams.containsValue('X')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'X');
            String add = maskAdditionalParams.get(key);
            String name = "Connection status";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> connectionStatusCol = new TableColumn<>(name);
            connectionStatusCol.setCellValueFactory(new PropertyValueFactory<>("connectionStatus"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'X'), connectionStatusCol);
        }
        if (maskParams.containsValue('I')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'I');
            String add = maskAdditionalParams.get(key);
            String name = "Bytes received";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> bytesReceivedCol = new TableColumn<>(name);
            bytesReceivedCol.setCellValueFactory(new PropertyValueFactory<>("bytesReceived"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'I'), bytesReceivedCol);
        }
        if (maskParams.containsValue('O')) {
            int key = MaskHandler.getKeyByValue(maskParams, 'O');
            String add = maskAdditionalParams.get(key);
            String name = "Bytes sent";
            if (!add.equals("")) {
                name += "\nUser params: " + add;
            }
            TableColumn<LogString, String> bytesSentCol = new TableColumn<>(name);
            bytesSentCol.setCellValueFactory(new PropertyValueFactory<>("bytesSent"));
            tableColumnList.put(MaskHandler.getKeyByValue(maskParams, 'O'), bytesSentCol);
        }
        return tableColumnList;
    }
}
