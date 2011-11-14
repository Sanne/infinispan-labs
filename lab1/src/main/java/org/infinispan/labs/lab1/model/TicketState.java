package org.infinispan.labs.lab1.model;

import java.io.Serializable;

public class TicketState implements Serializable {
	
	final String state;

	public TicketState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
}
