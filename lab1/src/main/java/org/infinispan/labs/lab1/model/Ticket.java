package org.infinispan.labs.lab1.model;

import java.io.Serializable;

public class Ticket implements Serializable {
	
	final String id;
	final String event;
	
	public Ticket() {
		id = "";
		event = "";
	}
	
	public Ticket(String id, String event) {
		this.id = id;
		this.event = event;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEvent() {
		return event;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( event == null ) ? 0 : event.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Ticket other = (Ticket) obj;
		if ( event == null ) {
			if ( other.event != null ) return false;
		}
		else if ( !event.equals( other.event ) ) return false;
		if ( id == null ) {
			if ( other.id != null ) return false;
		}
		else if ( !id.equals( other.id ) ) return false;
		return true;
	}

}
