package com.github.ishanmihir19.learn.designpatterns.decorator;

public class DecoratorMain {

	public static void main(String[] args) {
		Choc choc = new KitKatDeco(new FiveStarDeco(new Toffee()));
		choc.addFlovour();
		
		Choc kk = new FiveStarDeco(new Toffee());
		kk.addFlovour();
		
		FiveStarDeco starDeco = (FiveStarDeco) kk;
		if(!starDeco.isChewy()) {
			new KitKatDeco(kk).addFlovour();
		} else {
			System.out.println("No issues");
		}
		
	}

}
