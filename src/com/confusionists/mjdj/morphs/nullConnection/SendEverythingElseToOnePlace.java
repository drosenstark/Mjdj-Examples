package com.confusionists.mjdj.morphs.nullConnection;

import java.util.List;

import com.confusionists.mjdjApi.midi.*;
import com.confusionists.mjdjApi.morph.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* marked abstract for cleanup, this class is ready to go! */
public  abstract class SendEverythingElseToOnePlace extends NullConnection {

    String deviceName = "Apple Computer, Inc. - IAC Driver mjdj2live OUT";//"nerds.de - ipMIDI Port 1 OUT";// "Apple Computer, Inc. - IAC Driver IAC Bus 1 OUT";
    @Override
	public String getName() {
        return "Send Everything Else to " + deviceName;
    }

	public void init(List<String> deviceNames) throws DeviceNotFoundException {
		
		for (String name : deviceNames) {
			if (name.equals(deviceName)) {
				getService().log(getName() + " found its target driver");
				return;
			}
		}
		getService().log(getName() + " did NOT find target driver.");
		
	}
    
    
    @Override
	public boolean process(MessageWrapper message, String from) throws Exception {
        getService().send(message, deviceName);
        return true;
    }

	@Override
	public String diagnose() {
		return null;
	}
}
