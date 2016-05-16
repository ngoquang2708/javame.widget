package com.ngoquang2708.widget;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;

public class BaseContainer extends BaseWidget implements Container {

	public static boolean debug = false;
	
	protected final Vector widgets = new Vector();

	protected boolean isDragging;

	protected Widget draggingWidget;

	protected volatile int oldDraggingX;

	protected volatile int oldDraggingY;

	protected int scrollX;

	protected int scrollY;

	public BaseContainer() {
		;
	}

	public boolean contains(Widget w) {
		return widgets.contains(w);
	}

	public boolean add(Widget w) {
		if (!widgets.contains(w)) {
			widgets.addElement(w);
			w.setParent(this);
			w.addNotify();
			return true;
		}
		return false;
	}

	public boolean remove(Widget w) {
		if (widgets.removeElement(w)) {
			w.setParent(null);
			w.removeNotify();
			return true;
		}
		return false;
	}

	public int indexOf(Widget w) {
		return widgets.indexOf(w);
	}

	public Widget get(int index) {
		return (Widget) widgets.elementAt(index);
	}

	public int size() {
		return widgets.size();
	}

	public void clear() {
		widgets.removeAllElements();
	}

	public int getScrollX() {
		return scrollX;
	}

	public void setScrollX(int scrollX) {
		this.scrollX = Math.min(
				Math.max(0, scrollX),
				Math.max(0, getPrefW() - getW()));
	}

	public int getScrollY() {
		return scrollY;
	}

	public void setScrollY(int scrollY) {
		this.scrollY = Math.min(
				Math.max(0, scrollY),
				Math.max(0, getPrefH() - getH()));
	}

	public void pack() {
		;
	}

	public boolean pointerPressed(int x, int y) {
		Widget w = get(x, y);
		oldDraggingX = -1;
		oldDraggingY = -1;
		x += getScrollX();
		y += getScrollY();
		return w != null && w.pointerPressed(x - w.getX(), y - w.getY());
	}

	public boolean pointerReleased(int x, int y) {
		Widget w;
		if (draggingWidget != null) {
			w = draggingWidget;
			draggingWidget = null;
			x += getScrollX();
			y += getScrollY();
			return w.pointerReleased(x - w.getX(), y - w.getY());
		}
		if (isDragging) {
			isDragging = false;
			return false;
		}
		w = get(x, y);
		x += getScrollX();
		y += getScrollY();
		return w != null && w.pointerReleased(x - w.getX(), y - w.getY());
	}

	public boolean pointerDragged(int x, int y) {
		Widget w;
		if ((w = draggingWidget) != null) {
			x += getScrollX();
			y += getScrollY();
			return w.pointerDragged(x - w.getX(), y - w.getY());
		}
		w = get(x, y);
		if (w != null && w.pointerDragged(
						x + getScrollX() - w.getX(),
						y + getScrollY() - w.getY())) {
			draggingWidget = w;
			return true;
		}

		int newScrollX = 0;
		int newScrollY = 0;
		if (oldDraggingX != -1)
			setScrollX((newScrollX = getScrollX() - (x - oldDraggingX)));
		if (oldDraggingY != -1)
			setScrollY((newScrollY = getScrollY() - (y - oldDraggingY)));
		oldDraggingX = x;
		oldDraggingY = y;
		isDragging = true;
		return (newScrollX == getScrollX() || newScrollY == getScrollY());
	}

	public void paint(Graphics g) {
		g.translate(-getScrollX(), -getScrollY());
		for (int i = 0; i < size(); i++) {
			Widget w = get(i);
			g.setClip(getScrollX(), getScrollY(), getW(), getH());
			g.clipRect(w.getX(), w.getY(), w.getW(), w.getH());
			g.translate(w.getX(), w.getY());
			if (w.isVisableOn(g))
				w.paint(g);
			if (debug) {
				g.translate(-w.getX(), -w.getY());
				g.setClip(getScrollX(), getScrollY(), getW(), getH());
				g.clipRect(w.getX(), w.getY(), w.getW(), w.getH());
				g.setColor(w instanceof Container ? 0x0000ff00 : 0x00ff0000);
				g.drawRect(g.getClipX(), g.getClipY(),
						g.getClipWidth() - 1, g.getClipHeight() - 1);
				g.translate(w.getX(), w.getY());
			}
			g.translate(-w.getX(), -w.getY());
		}
		g.translate(getScrollX(), getScrollY());
	}

	private Widget get(int x, int y) {
		x += getScrollX();
		y += getScrollY();
		for (int i = size() - 1; i >= 0; --i) {
			Widget w = get(i);
			if (w.getX() <= x && w.getX() + w.getW() >= x && w.getY() <= y
					&& w.getY() + w.getH() >= y)
				return w;
		}
		return null;
	}
}