package examples;

import javax.sound.midi.ShortMessage;

import com.confusionists.mjdjApi.midi.MessageWrapper;
import com.confusionists.mjdjApi.midi.ShortMessageWrapper;
import com.confusionists.mjdjApi.morph.DeviceNotFoundException;
import com.confusionists.mjdjApi.morph.AbstractMorph;
import com.confusionists.mjdjApi.util.MidiTimerTask;

public class OneBeatSustain extends AbstractMorph {

    public boolean process(MessageWrapper messageIn, String from) throws Exception {
		ShortMessageWrapper message = messageIn.getAsShortMessageWrapper();
		if (message == null)
			return false; // early out, not short message
		if (message.getCommand() == ShortMessage.NOTE_ON) {
            ShortMessage noteOn = message.getShortMessage();
            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, noteOn.getChannel(), noteOn.getData1(), noteOn.getData2());
            MidiTimerTask task = new MidiTimerTask();
            task.init(getService(), MessageWrapper.newInstance(noteOff), this);
            getService().schedule(task, 1);
            return false; 
        } else if (message.getCommand() == ShortMessage.NOTE_OFF) {
        		return true; // eat the note offs, since we provide our own
        } 
        return false;
    }

    

	public String diagnose() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "One-beat sustain v. 1.1";
	}

	public Object getSerializable() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() throws DeviceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	public void setSerializable(Object serializable) {
		// TODO Auto-generated method stub
		
	}
}




