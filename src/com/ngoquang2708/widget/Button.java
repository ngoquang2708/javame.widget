package com.ngoquang2708.widget;

import javax.microedition.lcdui.Graphics;

public class Button extends BaseWidget {

	protected boolean isPressing;
	
	protected String text;
	
	protected PointerListener pointerListener;
	
	private boolean drawRect;
	
	public Button(String text) {
		this.text = String.valueOf(text);
	}
	
	public void setDrawRect(boolean drawRect) {
		this.drawRect = drawRect;
	}
	
	public void setPointerListener(PointerListener pl) {
		pointerListener = pl;
	}
	
	public void setText(String text) {
		this.text = String.valueOf(text);
	}
	
	public String getText() {
		return text;
	}
	
	public int getPrefW() {
		return getStyle().getFont().stringWidth(String.valueOf(text)) + getPrefH();
	}
	
	public int getPrefH() {
		return getStyle().getFont().getHeight() << 1;
	}
	
	public boolean pointerPressed(int x, int y) {
		isPressing = true;
		if (pointerListener != null) {
			try {
				pointerListener.onPress(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean pointerReleased(int x, int y) {
		if (isPressing) {
			if (pointerListener != null &&
					x >= 0 && x <= getW() && y >= 0 && y < getH()) {
				try {
					pointerListener.onRelease(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			isPressing = false;
		}
		return true;
	}

	public boolean pointerDragged(int x, int y) {
		if (isPressing && pointerListener != null) {
			try {
				pointerListener.onDrag(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isPressing;
	}
	
	public void paint(Graphics g) {
		if (getStyle().getBgImage() != null)
			g.drawImage(getStyle().getBgImage(), 0, 0, Graphics.TOP | Graphics.LEFT);
		if (isPressing) {
			g.setColor(getStyle().getBgColor());
			g.fillRect(0, 0, getW(), getH());
		}
		g.setColor(isPressing ? -getStyle().getFgColor() : getStyle().getFgColor());
		g.setFont(getStyle().getFont());
		if (drawRect)
			g.drawRect(0, 0, getW() - 1, getH() - 1);
		if (getText() != null && getText().length() != 0) {
			g.drawString(getText(), getW() >> 1, (getH() - g.getFont().getHeight()) >> 1, Graphics.TOP | Graphics.HCENTER);
		}
	}
}