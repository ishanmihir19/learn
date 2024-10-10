package com.github.ishanmihir19.learn.designpatterns.decorator;

public abstract class ChocDeco implements Choc {

	private final Choc choc;

	ChocDeco(Choc choc) {
		this.choc = choc;
	}
	
	@Override
	public void addFlovour() {
		choc.addFlovour();
	}

}
