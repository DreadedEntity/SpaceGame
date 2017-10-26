package Core;

import java.awt.Color;
import java.awt.Rectangle;

public class RectDouble extends Rectangle {
	private double x;
	private double y;
	private double width;
	private double height;
	private Color color;
	
	public RectDouble() {
		x = y = 0;
		width = height = 20;
		color = Color.WHITE;
	}
	
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
