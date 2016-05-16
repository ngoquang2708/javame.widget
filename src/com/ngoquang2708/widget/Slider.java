package com.ngoquang2708.widget;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Slider extends BaseWidget {
	
	private final int max;
	
	private int current;
	
	private boolean isPressing;
	
	private PointerListener pointerListener;
	
	private String label;
	
	private int labelW;
	
	private Image buffer;
	
	private volatile boolean isChanged;
	
	public Slider(String label, int max) {
		this(max);
		this.label = label;
	}

	public Slider(int max) {
		this.max = Math.max(1, max);
		this.isChanged = true;
		getStyle().setInt("barColor", 0x007f7f7f);
	}
	
	public void setLabelW(int w) {
		this.labelW = w;
	}

	public void setPointerListener(PointerListener pointerListener) {
		this.pointerListener = pointerListener;
	}
	
	public PointerListener getPointerListener() {
		return pointerListener;
	}
	
	public void setCurrent(int current) {
		this.current = Math.max(0, Math.min(max, current));
		isChanged = true;
	}
	
	public int getCurrent() {
		return current; 
	}
	
	public boolean pointerPressed(int x, int y) {
		isPressing = true;
		x -= getLabelW();
		if (x >= 0) {
			current = x * max / getSlideAreaW();
			if (pointerListener != null) {
				try {
					pointerListener.onPress(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public boolean pointerDragged(int x, int y) {
		x -= getLabelW();
		if (isPressing) {
			if (x < 0)
				x = 0;
			else if (x > getSlideAreaW())
				x = getSlideAreaW();
			current = x * max / getSlideAreaW();
			if (pointerListener != null) {
				try {
					pointerListener.onDrag(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isPressing;
	}
	
	public boolean pointerReleased(int x, int y) {
		isPressing = false;
		if (pointerListener != null) {
			try {
				pointerListener.onRelease(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public int getPrefW() {
		return getLabelW() + 100;
	}
	
	public int getPrefH() {
		return getStyle().getFont().getHeight();
	}

	public void paint(Graphics g) {
		if (isChanged)
			updateBuffer();
		g.drawImage(buffer, 0, 0, Graphics.TOP | Graphics.LEFT);
	}
	
	private void updateBuffer() {
		Image buffer = this.buffer == null 
				? (this.buffer = Image.createImage(getW(), getH()))
				: this.buffer;
		Graphics g = buffer.getGraphics();
		int hCenter = (getH() - getStyle().getFont().getHeight()) >> 1;
		int hCenterAnchor = Graphics.TOP | Graphics.HCENTER;
		g.setColor(getStyle().getBgColor());
		g.fillRect(0, 0, getW(), getH());
		g.setColor(getStyle().getInt("barColor"));
		g.fillRect(getLabelW(), 0, getSlideBarW(), getH());
		g.drawRect(0, 0, getLabelW() - 1, getH() - 1);
		g.setFont(getStyle().getFont());
		g.setColor(getStyle().getFgColor());
		g.drawString(String.valueOf(label), getLabelW() >> 1, hCenter, hCenterAnchor);
		g.drawString(String.valueOf(current), getLabelW() + (getSlideAreaW() >> 1), hCenter, hCenterAnchor);
	}
	
	private int getLabelW() {
		if (labelW <= 0)
			labelW = getStyle().getFont().stringWidth(String.valueOf(label));
		return labelW;
	}
	
	private int getSlideAreaW() {
		return getW() - getLabelW();
	}

	private int getSlideBarW() {
		return current == 0 ? 0 : (int) Math.ceil(current * getSlideAreaW() / max);
	}
}