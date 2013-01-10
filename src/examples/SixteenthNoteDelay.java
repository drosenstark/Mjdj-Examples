package examples;

import com.confusionists.mjdjApi.midi.MessageWrapper;
import com.confusionists.mjdjApi.midi.ShortMessageWrapper;
import com.confusionists.mjdjApi.morph.DeviceNotFoundException;
import com.confusionists.mjdjApi.morph.AbstractMorph;
import com.confusionists.mjdjApi.util.MidiTimerTask;
import com.confusionists.mjdjApi.util.MidiTimerTaskSendToMorphs;

public class SixteenthNoteDelay extends AbstractMorph {


	public String diagnose() {
		return null;
	}


	public String getName() {
		return "Sixteenth-Note Delay";
	}

	public Object getSerializable() {
		return null;
	}

	public void init() throws DeviceNotFoundException {
	}

	public boolean process(MessageWrapper messageIn, String from) throws Throwable {
		ShortMessageWrapper message = messageIn.getAsShortMessageWrapper();
		if (message != null && message.isNoteOn() || message.isNoteOff()) {
            getService().schedule(getDelayedMessage(message), 0, .25f);
            getService().schedule(getDelayedMessage(message), 0, .5f);
            getService().schedule(getDelayedMessage(message), 0, .75f);
		}
		return false;
	}
	
	private MidiTimerTask getDelayedMessage(ShortMessageWrapper message) {
        ShortMessageWrapper delayMessage = message.deepClone();
        int delayVolume = (int)Math.round((float)delayMessage.getData2() * (float).2);
        delayMessage.alterData2(delayVolume);
        MidiTimerTask task = new MidiTimerTaskSendToMorphs();
        task.init(getService(), delayMessage, this);
        return task;
	}

	public void setSerializable(Object serializable) {
		// TODO Auto-generated method stub

	}

}
