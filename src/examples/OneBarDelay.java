package examples;

import com.confusionists.mjdjApi.midi.MessageWrapper;
import com.confusionists.mjdjApi.midi.ShortMessageWrapper;
import com.confusionists.mjdjApi.morph.DeviceNotFoundException;
import com.confusionists.mjdjApi.morph.AbstractMorph;
import com.confusionists.mjdjApi.util.MidiTimerTask;
import com.confusionists.mjdjApi.util.MidiTimerTaskSendToMorphs;

public class OneBarDelay extends AbstractMorph {


	public String diagnose() {
		return null;
	}


	public String getName() {
		return "One-bar Delay";
	}

	public Object getSerializable() {
		return null;
	}

	public void init() throws DeviceNotFoundException {
	}

	public boolean process(MessageWrapper messageIn, String from) throws Throwable {
		ShortMessageWrapper message = messageIn.getAsShortMessageWrapper();
		if (message != null && message.isNoteOn() || message.isNoteOff()) {
            ShortMessageWrapper delayMessage = message.deepClone();
            int delayVolume = (int)Math.round((float)delayMessage.getData2() * (float).2);
            delayMessage.alterData2(delayVolume);
            MidiTimerTask task = new MidiTimerTaskSendToMorphs();
            task.init(getService(), delayMessage, this);
            getService().schedule(task, 4);
		}
		return false;
	}

	public void setSerializable(Object serializable) {
		// TODO Auto-generated method stub

	}

}
