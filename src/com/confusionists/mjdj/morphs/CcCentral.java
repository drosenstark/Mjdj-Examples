package com.confusionists.mjdj.morphs;

import com.confusionists.mjdjApi.midi.*;

public class CcCentral {
	
	public static CcCentral instance = new CcCentral();
	
	private CcCentral() {}

	private int ccNumber = 1;
	private int channel = 5;
	
	public ChannelCc getCc() {
		if (ccNumber > 30 && ccNumber < 40) ccNumber = 40; // I think it's 32 that is problematic
		return new ChannelCc(channel, ccNumber++);  // TODO: increment channel when all CCs are used up
	}
	

}
