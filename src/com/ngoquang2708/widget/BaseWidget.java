package com.ngoquang2708.widget;

import javax.microedition.lcdui.Graphics;

public class BaseWidget implements Widget {
	
	protected Container parent;
	
	protected Style style;

	protected int x;

	protected int y;
	
	protected int w;
	
	protected int h;
	
	public BaseWidget() {
		style = new Style();
	}

	public void setStyle(Style s) {
		this.style = s;
	}
	
	public Style getStyle() {
		return style;
	}
	
	public void setParent(Container c) {
		this.parent = c;
	}
	
	public Container getParent() {
		return parent;
	}

	public void paint(Graphics g) {
		;
	}

	public void addNotify() {
		;
	}

	public void removeNotify() {
		;
	}
	
	public int getAbsX() {
		return getParent().getAbsX() - getParent().getScrollX() + getX();
	}
	
	public int getAbsY() {
		return getParent().getAbsY() - getParent().getScrollY() + getY();
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getW() {
		return w > 0 ? w : (w = getPrefW());
	}
	
	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h > 0 ? h : (h = getPrefH());
	}
	
	public void setH(int h) {
		this.h = h;		
	}
	
	public int getPrefW() {
		return w;
	}
	
	public int getPrefH() {
		return h;
	}
	
	public boolean isVisableOn(Graphics g) {
		int minX = Math.max(g.getClipX(), 0);
		int minY = Math.max(g.getClipY(), 0);
		int maxX = Math.min(g.getClipX() + g.getClipWidth(), getW());
		int maxY = Math.min(g.getClipY() + g.getClipHeight(), getH());
		return maxX >= minX && maxY >= minY ;
	}

	public boolean pointerPressed(int x, int y) {
		return false;
	}

	public boolean pointerReleased(int x, int y) {
		return false;
	}

	public boolean pointerDragged(int x, int y) {
		return false;
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