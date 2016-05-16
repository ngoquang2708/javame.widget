package com.ngoquang2708.widget;

import javax.microedition.lcdui.Graphics;

/*
 * All coordinates are relative to its parent.
 * 
 * All child widget should not use its x, y coordinates for painting operation.
 * All coordinates are translated using Graphics.translate(x, y) in its parent
 * so paint method of child widget can safety draw on rectangle (0, 0, w, h).
 */

/**
 * 
 * @author Ngô Minh Quang  {@literal <ngoquang2708@gmail.com>}
 */
public interface Widget extends Paintable {
	
	boolean isVisableOn(Graphics g);

	void setParent(Container c);
	
	Container getParent();
	
	void setStyle(Style s);
	
	Style getStyle();
	
	int getAbsX();
	
	int getX();
	
	void setX(int x);
	
	int getAbsY();
	
	int getY();
	
	void setY(int y);
	
	int getW();
	
	void setW(int w);
	
	int getPrefW();
	
	int getH();
	
	void setH(int h);
	
	int getPrefH();

	void addNotify();
	
	void removeNotify();
	
	boolean pointerPressed(int x, int y);

	boolean pointerReleased(int x, int y);

	boolean pointerDragged(int x, int y);
	
	boolean keyPressed(int key);
	
	boolean keyReleased(int key);
	
	boolean keyRepeated(int key);
	
}
