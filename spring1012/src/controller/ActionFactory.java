package controller;

import action.Action;
import action.FboardAction;

public class ActionFactory {
	private static ActionFactory factory;
	private ActionFactory() {
		
	}
	
	public static ActionFactory getFactory() {
		if(factory==null) 
			factory = new ActionFactory();
		return factory;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("fboard")) {
			action = new FboardAction();
		}
		return action;
	}
}
