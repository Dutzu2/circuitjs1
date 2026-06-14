/*    
    Copyright (C) Paul Falstad and Iain Sharp
    
    This file is part of CircuitJS1.

    CircuitJS1 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    CircuitJS1 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CircuitJS1.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.lushprojects.circuitjs1.client;

import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;

public class CheckboxMenuItem extends MenuItem implements Command {
	private boolean on=false;
	private String name="";
	private String shortcut="";
	private Command extcmd=null;
	static String checkBoxHtml="<div style=\"display:inline-block;width:15px;\">";

	public String getName() { return name; }
	public String getShortcut() { return shortcut; }
	
	public CheckboxMenuItem(String s){
		super(s, (Command)null);
		super.setScheduledCommand(this);
		name=s;
		setState(false);
	}
	
	public CheckboxMenuItem(String s, Command cmd){
		super(s, (Command)null);
		super.setScheduledCommand(this);
		extcmd=cmd;
		name=s;
		setState(false);
	}
	
	public CheckboxMenuItem(String s, String c, Command cmd){
		this(s, cmd);
		shortcut=c;
	}
	
	public CheckboxMenuItem(String s, String c){
		this(s);
		shortcut=c;
	}
	
	public void setShortcut(String s) {
		shortcut=s;
	}
	
	public void execute() {
		setState(!on);
		if (extcmd!=null) {
		    extcmd.execute();
		    CircuitElm.sim.repaint();
		}
      }

	public void setTitle(String s) {
	    	name = s;
	}
	
	public void setState(boolean newstate) {
		on = newstate;
		// Flex layout: a 15px checkbox column, the label, a flexible spacer and
		// the shortcut right-aligned. This adapts to any (translated) label
		// length so the text and shortcut never overlap or get clipped.
		String check = on ? "&#10004;" : "&nbsp;";
		String s = "<div style=\"display:flex;align-items:center;white-space:nowrap;\">"
			+ "<span style=\"display:inline-block;width:15px;flex:0 0 auto;\">" + check + "</span>"
			+ "<span style=\"flex:0 1 auto;\">" + name + "</span>";
		if (shortcut != null && !shortcut.isEmpty())
			s += "<span style=\"flex:1 1 auto;min-width:24px;\"></span>"
				+ "<span style=\"flex:0 0 auto;padding-left:8px;color:#777;\">" + shortcut + "</span>";
		s += "</div>";
		setHTML(s);
	}
	
	public boolean getState(){
		return on;
	}

}
