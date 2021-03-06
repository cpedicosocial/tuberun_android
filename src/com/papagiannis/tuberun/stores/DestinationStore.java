package com.papagiannis.tuberun.stores;

import java.util.ArrayList;
import com.papagiannis.tuberun.Destination;
import com.papagiannis.tuberun.plan.Point;
import android.content.Context;

public class DestinationStore<T> extends Store<T> {
	private static DestinationStore<Destination> instanceDestination;

	public static DestinationStore<Destination> getInstance() {
		if (instanceDestination == null) {
			instanceDestination = new DestinationStore<Destination>();
			//there must always be a home for serialisation reasons
			instanceDestination.home=new Destination("", Point.ADDRESS);
			instanceDestination.home.setHome(true);
		}
		return instanceDestination;
	}

	private DestinationStore() {
		FILENAME = "tuberun.destinations";
	}
	
	@Override
	public void add(T f, Context a) {
		//no duplicates
		if (list.contains(f)) {
			return;
		}
		//keep the list bounded to 3 entries
		if (list.size()>=3) list.remove(0);	
		super.add(f, a);
		
	}
	
	private T home=null;
	public void setHome(T home, Context a) {
		this.home=home;
		storeToFile(a);
	}
	
	public void eraseHome(Context a) {
		instanceDestination.home=new Destination("", Point.ADDRESS);
		instanceDestination.home.setHome(true);
		storeToFile(a);
	}
	
	public T getHome(Context activity) {
		if (list==null) list=getFromFile(activity);
		return home;
	}
	
	
	
	
	//Home is store inside the persistent list as the last element.
	//It is removed from the list when the file is read
	@Override
	protected ArrayList<T> getFromFile(Context activity) {
		ArrayList<T> result= super.getFromFile(activity);
		if (result.size()>0) home=result.remove(result.size()-1);
		return result;
	}
	
	@Override
	public void storeToFile(Context activity) {
		if (home!=null) list.add(home);
//		list.clear();
		super.storeToFile(activity);
		list.remove(list.size()-1); 
		
	}
}
