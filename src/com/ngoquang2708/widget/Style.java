package com.ngoquang2708.widget;

import java.util.Hashtable;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;

public class Style {

	protected final Hashtable map;

	protected int fgColor;

	protected int bgColor;

	protected Font font;

	protected Image bgImage;

	public Style() {
		map = new Hashtable();
		fgColor = 0x00000000;
		bgColor = 0x00ffffff;
		font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
	}

	public void setBgImage(Image image) {
		bgImage = image;
	}

	public Image getBgImage() {
		return bgImage;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public void setFgColor(int color) {
		fgColor = color;
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int color) {
		bgColor = color;
	}

	public int getFgColor() {
		return fgColor;
	}
	
	public boolean contains(String name) {
		return map.contains(name);
	}

	public void setInt(String name, int value) {
		map.put(name, new Integer(value));
	}

	public int getInt(String name) {
		return ((Integer) map.get(name)).intValue();
	}

	public void setString(String name, String value) {
		map.put(name, value);
	}

	public String getString(String name) {
		return (String) map.get(name);
	}

	public void setObject(String name, Object obj) {
		map.put(name, obj);
	}

	public Object getObject(String name) {
		return map.get(name);
	}
}
