package com.ngoquang2708.widget;

public interface Container extends Widget {
	
	boolean add(Widget w);

	boolean remove(Widget w);

	boolean contains(Widget w);

	int indexOf(Widget w);

	Widget get(int index);
	
	int size();
	
	void clear();
	
	int getScrollX();
	
	void setScrollX(int scrollX);
	
	int getScrollY();
	
	void setScrollY(int scrollY);

	void pack();

}