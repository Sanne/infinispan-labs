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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
	private final ConcurrentMap<Ticket, String> reservedTickets = new ConcurrentHashMap<Ticket, String>();

	@PostConstruct
	public void populate() {
		createEvent("Devoxx", 8);
	}

	public List<Ticket> getReservedTickets() {
		return new ArrayList( reservedTickets.keySet() );
	}

	public void clearReservations() {
		for ( Ticket reservedTicket : reservedTickets.keySet() ) {
			tickets.put( reservedTicket, new TicketState( "available" ) );
		}
		reservedTickets.clear();
	}

	public void bookTicket(String id) {
		throw new UnsupportedOperationException();
	}

	public String getNodeId() {
		return "local";
	}

	public String getOwners(String ticketId, String event) {
		Ticket ticket = new Ticket( ticketId, event );
		String owner = reservedTickets.get( ticket );
		if ( owner == null) {
			return " - available - ";
		}
		else {
			return owner;
		}
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
		TicketState ticketState = tickets.get( ticket );
		if ( ticketState == null ) {
			throw new IllegalStateException( "ticket not existing" );
		}
		String ok = reservedTickets.putIfAbsent( ticket, name );
		if ( ok == null ) {
			tickets.put( ticket, new TicketState( "reserved" ) );
		}
		else {
			//abort
		}
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
