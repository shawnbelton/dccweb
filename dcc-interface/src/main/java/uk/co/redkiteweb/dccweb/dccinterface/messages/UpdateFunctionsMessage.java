package uk.co.redkiteweb.dccweb.dccinterface.messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 03/11/16.
 */
public class UpdateFunctionsMessage implements Message {

    private int address;
    private boolean addressMode;
    private final Map<Integer, Boolean> functions;

    public UpdateFunctionsMessage() {
        functions = new HashMap<>();
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(final int address) {
        this.address = address;
    }

    public boolean isAddressMode() {
        return addressMode;
    }

    public void setAddressMode(final boolean addressMode) {
        this.addressMode = addressMode;
    }

    public void addFunction(final int number, final boolean state) {
        functions.put(number, state);
    }

    public Boolean getFunctionState(final int number) {
        Boolean functionState = Boolean.FALSE;
        if (functions.containsKey(number)) {
            functionState = functions.get(number);
        }
        return functionState;
    }

    @Override
    public String getLogMessage() {
        return String.format("Updating Function(s) %s on %d",getFunctionStatusList(),address);
    }

    private String getFunctionStatusList() {
        final StringBuilder list = new StringBuilder();
        String separator = "";
        for(Map.Entry<Integer, Boolean> function : functions.entrySet()) {
            list.append(separator);
            list.append(function.getKey());
            list.append(" ");
            list.append(function.getValue());
            separator = ", ";
        }
        return list.toString();
    }
}
