package com.github.ishanmihir19.learn.designpatterns.decorator;

public class FiveStarDeco extends ChocDeco {

	public FiveStarDeco(Choc choc) {
		super(choc);
	}
	
	@Override
	public void addFlovour() {
		super.addFlovour();
		System.out.println("5 star silkiness");
	}
	
	public Boolean isChewy() {
		return true;
	}

}
