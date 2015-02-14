package edu.wpi.first.wpilibj;

public class LimitSwitch extends DigitalInput {

	boolean normallyOpen = true;
	
	public LimitSwitch(int channel) {
		this(channel, true);
	}

	public LimitSwitch(int channel, boolean normallyOpen) {
		super(channel);

		this.normallyOpen = normallyOpen;
	}

	public boolean isNormallyOpen() {
		return normallyOpen;
	}

	public boolean isPressed() {
		boolean circuitClosed = super.get();
		
		if (normallyOpen)
			return circuitClosed;
		else
			return !circuitClosed;
	}

}
