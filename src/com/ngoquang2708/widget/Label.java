package com.ngoquang2708.widget;

public class Label extends Button {
	
	public Label(String text) {
		super(text);
	}

	public boolean pointerDragged(int x, int y) {
		return true;
	}
	
	public boolean pointerPressed(int x, int y) {
		return true;
	}
	
	public boolean pointerReleased(int x, int y) {
		return true;
	}
	
	public boolean keyPressed(int key) {
		return false;
	}
	
	public boolean keyReleased(int key) {
		return false;
	}
	
	public boolean keyRepeated(int key) {
		return false;
	}
}