package com.github.ishanmihir19.learn.designpatterns.decorator;

public class KitKatDeco extends ChocDeco{
	
	public KitKatDeco(Choc choc) {
		super(choc);
	}
	
	@Override
	public void addFlovour() {
		super.addFlovour();
		System.out.println("Kitkat crunch");
	}
	
	public Boolean isCrunchy() {
		return true;
	}
	
}
