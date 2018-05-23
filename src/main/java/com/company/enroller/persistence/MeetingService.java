package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Meeting> getAll() {
		return connector.getSession().createCriteria(Meeting.class).list();
	}

	public Meeting findById(Long id) {
		return (Meeting) connector.getSession().get(Meeting.class, id);
	}

	public void addMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		
	}
	public void removeMeeting(Meeting meeting) {
		// TODO
	}

}
