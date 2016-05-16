package com.ngoquang2708.widget;


public class VerticalContainer extends BaseContainer {

	protected int gapSize;

	public VerticalContainer() {
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
		int nextY = nextY();
		if (super.add(w)) {
			w.setX(0);
			w.setY(nextY);
			return true;
		}
		return false;
	}

	public int getPrefW() {
		int prefW = 0;
		for (int i = 0; i < size(); ++i)
			prefW = Math.max(prefW, get(i).getW());
		return prefW;
	}
	
	public int getPrefH() {
		int prefH = (size() - 1) * gapSize;
		for (int i = 0; i < size(); ++i)
			prefH += get(i).getH();
		return prefH;
	}

	private int nextY() {
		Widget lastWidget = size() == 0 ? null : (Widget) widgets.lastElement();
		return lastWidget == null
				? 0
				: lastWidget.getY() + lastWidget.getH() + gapSize;
	}
}