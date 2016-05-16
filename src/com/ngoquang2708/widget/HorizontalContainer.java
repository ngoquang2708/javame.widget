package com.ngoquang2708.widget;


public class HorizontalContainer extends BaseContainer {

	protected int gapSize;

	public HorizontalContainer() {
		;
	}

	public void setGapSize(int gapSize) {
		if ((this.gapSize = gapSize) < 0)
			throw new IllegalArgumentException();
	}

	public int getGapSize() {
		return gapSize;
	}

	public boolean add(Widget w) {
		int nextX = nextX();
		if (super.add(w)) {
			w.setX(nextX);
			w.setY(0);
			return true;
		}
		return false;
	}

	public int getPrefW() {
		int prefW = (size() - 1) * gapSize;
		for (int i = 0; i < size(); ++i)
			prefW += get(i).getW();
		return prefW;
	}
	
	public int getPrefH() {
		int prefH = 0;
		for (int i = 0; i < size(); ++i)
			prefH = Math.max(prefH, get(i).getH());
		return prefH;
	}

	private int nextX() {
		Widget lastWidget = size() == 0 ? null : (Widget) widgets.lastElement();
		return lastWidget == null
				? 0
				: lastWidget.getX() + lastWidget.getW() + gapSize;
	}
}