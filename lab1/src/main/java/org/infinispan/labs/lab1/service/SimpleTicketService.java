/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.labs.lab1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.infinispan.labs.lab1.model.Ticket;
import org.infinispan.labs.lab1.model.TicketState;

@Named
@ApplicationScoped
public class SimpleTicketService implements TicketService {

	private final Map<Ticket, TicketState> tickets = new HashMap<Ticket, TicketState>();

	/**
	 * value is the name of the person the ticket is assigned to
	 */
	private final Map<Ticket, String> reservedTickets = new HashMap<Ticket, String>();

	@PostConstruct
	public void populate() {
		createEvent("Devoxx", 8);
	}

	public List<Ticket> getReservedTickets() {
		return new ArrayList( tickets.keySet() );
	}

	public void clearAllocations() {
		tickets.clear();
	}

	public void bookTicket(String id) {
		throw new UnsupportedOperationException();
	}

	public String getNodeId() {
		return "local";
	}

	public String getOwners(String key) {
		return "local";
	}

	@Override
	public void createEvent(String eventName, int numberOfTickets) {
		for ( int i = 0; i < numberOfTickets; i++ ) {
			tickets.put( new Ticket( String.valueOf( i ), eventName ), new TicketState( "available" ) );
		}
	}

	@Override
	public void reserveTicket(String name, String ticketId, String event) {
		Ticket ticket = new Ticket( ticketId, event );
		tickets.put( ticket, new TicketState( "reserved" ) );
		reservedTickets.put( ticket, name );
	}

	@Override
	public List<Ticket> getAvailableTickets() {
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		for ( Ticket ticket : tickets.keySet() ) {
			TicketState ticketState = tickets.get( ticket );
			if ( "available".equals( ticketState.getState() ) ) {
				list.add( ticket );
			}
		}
		return list;
	}

}
