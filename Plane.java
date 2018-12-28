package winter18project4;
class Point {
		
	private double tempX, tempY;
	public Point(double x, double y) {
		tempX = x;
		tempY = y;
	}
	//tunak tunak tun is life
	public double getX() {
		return tempX;
	}
	
	public double getY() {
		return tempY;
	}
	
	//gets distance from one point to the next
	public double getDistance(Point point) {
		double diffX = this.getX() - point.getX();
		double diffY = this.getY() - point.getY();
		double xSq = Math.pow(diffX, 2);
		double ySq = Math.pow(diffY, 2);
		double totDis = Math.sqrt(xSq + ySq);
		return totDis;
	}
}


abstract class Shape{
	public abstract double getArea();
	
	public abstract double getPerimeter();
}


interface Symmetric{
	Point getPointOfSymmetry();
}


//creates a triangle
class Triangle extends Shape{
	private Point tempFirstPoint, tempSecondPoint, tempThirdPoint;
	public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
		tempFirstPoint = firstPoint;
		tempSecondPoint = secondPoint;
		tempThirdPoint = thirdPoint;
	}
	
	public Point getFirstPoint() {
		return tempFirstPoint;
	}
	
	public Point getSecondPoint() {
		return tempSecondPoint;
	}
	
	public Point getThirdPoint() {
		return tempThirdPoint;
	}

	
	public double getArea() {
		double a = tempFirstPoint.getDistance(tempSecondPoint);
		double b = tempSecondPoint.getDistance(tempThirdPoint);
		double c = tempThirdPoint.getDistance(tempFirstPoint);
		double s = (a + b + c)/2;
		double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return area;
	}

	
	public double getPerimeter() {
		double a = tempFirstPoint.getDistance(tempSecondPoint);
		double b = tempSecondPoint.getDistance(tempThirdPoint);
		double c = tempThirdPoint.getDistance(tempFirstPoint);
		double perimeter = a + b + c;
		return perimeter;
	}
}


//creates rectangle
class Rectangle extends Shape{
	private Point tempTopLeft;
	private double tempLength, tempWidth;
	public Rectangle(Point topLeftPoint, double length, double width) {
		tempTopLeft = topLeftPoint;
		tempLength = length;
		tempWidth = width;
	}

	public Point getTopLeftPoint() {
		return tempTopLeft;
	}
	
	public double getLength() {
		return tempLength;
	}
	
	public double getWidth() {
		return tempWidth;
	}
	
	
	public double getArea() {
		double area = tempLength * tempWidth;
		return area;
	}

	
	public double getPerimeter() {
		double perimeter = (2 * tempLength) + (2 * tempWidth);
		return perimeter;
	}
}


//creates trapezoid
class Trapezoid extends Shape {
	private Point tempTopPoint, tempBottomPoint;
	private double tempTopSide, tempBottomSide;
	public Trapezoid(Point topLeftPoint, Point bottomLeftPoint, double topSide, double bottomSide) {
		tempTopPoint = topLeftPoint;
		tempBottomPoint = bottomLeftPoint;
		tempTopSide = topSide;
		tempBottomSide = bottomSide;
	}
	
	public Point getTopLeftPoint() {
		return tempTopPoint;
	}
	
	public Point getBottomLeftPoint() {
		return tempBottomPoint;
	}
	
	public double getTopSide() {
		return tempTopSide;
	}
	
	public double getBottomSide() {
		return tempBottomSide;
	}
	
	
	public double getArea() {
		double h = Math.abs(tempBottomPoint.getY() - tempTopPoint.getY());
		double area = ((tempTopSide + tempBottomSide) *  h)/2;
		return area;
	}

	
	public double getPerimeter() {
		double c = tempTopPoint.getDistance(tempBottomPoint);
		double otherTopX = tempTopPoint.getX() + tempTopSide;
		double otherBottomX = tempBottomPoint.getX() + tempBottomSide;
		double Xdiff = Math.abs(otherTopX - otherBottomX);
		double Ydiff = Math.abs(tempTopPoint.getY() - tempBottomPoint.getY());
		double sqX = Math.pow(Xdiff, 2);
		double sqY = Math.pow(Ydiff, 2);
		double d = Math.sqrt(sqX + sqY);
		double perimeter = c + tempTopSide + d + tempBottomSide;
		return perimeter;
	}	
}


//creates circle
class Circle extends Shape implements Symmetric{
	
	private Point tempCenter;
	private double tempRadius;
	public Circle(Point center, double radius) {
		tempCenter = center;
		tempRadius = radius;
	}
	
	public Point getCenter() {
		return tempCenter;
	}
	
	public double getRadius() {
		return tempRadius;
	}
	

	public Point getPointOfSymmetry() {
		return tempCenter;
	}

	
	public double getArea() {
		double area = Math.PI * (Math.pow(tempRadius, 2));
		return area;
	}

	
	public double getPerimeter() {
		double perimeter = 2 * Math.PI * tempRadius;
		return perimeter;
	}
}


//create equilateral triangle
class EquilateralTriangle extends Triangle implements Symmetric {
	private Point tempTop, secondP, thirdP;
	private double tempSide;
	
	public EquilateralTriangle(Point topPoint, double side) {
		super(topPoint, new Point((topPoint.getX() - Math.abs((Math.sin(30*Math.PI / 180))* side)),(topPoint.getY()-(Math.cos(30*Math.PI / 180)*side))),new Point((topPoint.getX()+ Math.abs(Math.sin((30*Math.PI / 180))*side)),topPoint.getY()-Math.abs(Math.cos((30*Math.PI / 180))*side)));
		tempTop = topPoint;
		tempSide = side;
	}
	
	public Point getTopPoint() {
		return tempTop;
	}
	
	public double getSide() {
		return tempSide;
	}
	
	public Point getFirstPoint() {
		return tempTop;
	}
	
	public Point getSecondPoint() {
		Point second = new Point((tempTop.getX()-Math.abs((Math.sin((30*Math.PI / 180))*tempSide))),(tempTop.getY()-(Math.cos((30*Math.PI / 180))*tempSide)));
		secondP = second;
		return secondP;
	}
	
	public Point getThirdPoint() {
		Point third = new Point((tempTop.getX()+ Math.abs(Math.sin((30*Math.PI / 180))*tempSide)),tempTop.getY()-Math.abs(Math.cos((30*Math.PI / 180))*tempSide));
		thirdP = third;
		return thirdP;
	}
	
	
	public double getArea() {
		double area = ((Math.sqrt(3) * Math.pow(tempSide, 2))/4);
		return area;
	}
	
	
	public double getPerimeter() {
		double perimeter = 3 * tempSide;
		return perimeter;
	}
	
	
	public Point getPointOfSymmetry() {
		double xPos = tempTop.getX();
		double yPos = (tempTop.getY() + this.getSecondPoint().getY() + this.getThirdPoint().getY())/3;
		Point somePos = new Point(xPos, yPos);
		if (xPos == 0 && yPos == 0) {
			return null;
		}
		return somePos;
	}	
}

//create Square
class Square extends Rectangle implements Symmetric {
	private Point tempTop;
	private double tempside;
	public Square (Point topLeft, double side) {
		super(topLeft, side, side);
		tempTop = topLeft;
		tempside = side;
	}
	private Point getTopPoint() {
		return tempTop;
	}
	
	public double getSide() {
		return tempside;
	}
	
	
	public double getArea() {
		double area = Math.pow(tempside, 2);
		return area;
	}
	
	
	public double getPerimeter() {
		double perimeter = 4 * tempside;
		return perimeter;
	}
	
	
	
	public Point getPointOfSymmetry() {
		Point pos = new Point((tempTop.getX() + (tempside/2)), (tempTop.getY() - (tempside/2)));
		if (pos.getX() == 0 && pos.getY() == 0) {
			return null;
		}
		return pos;
	}
}

 class Plane {
	private Shape[] aPlane;
	public Plane() {
		aPlane = new Shape[0];
	}
	
	public Shape[] getShape() {
		return aPlane;
	}
	
	
	public void addShape(Shape shape) {
		Shape[] newPlane = new Shape[aPlane.length + 1];
		for (int i = 0; i < aPlane.length; i++) {
			newPlane[i] = aPlane[i];
		}
		newPlane[aPlane.length] = shape;
		aPlane = newPlane;
	}
	
	private double something = 0;
	public double getSumOfAreas() {
		for(int i = 0; i < aPlane.length; i++) {
			something = something + aPlane[i].getArea();
		}
		return something;
	}
	
	private double someSum = 0;
	public double getSumOfPerimeters() {
		for(int j = 0; j < aPlane.length; j++) {
			someSum = someSum + aPlane[j].getPerimeter();
		}
		return someSum;
	}
	
	
	public Point getCenterOfPointOfSymmetries() {
		double someX = 0, someY = 0, aveX, aveY;
		int count = 0;
		
		for (int k = 0; k < aPlane.length; k++) {
			if (aPlane[k] instanceof Symmetric) {
				someX = someX + ((Symmetric) aPlane[k]).getPointOfSymmetry().getX();
				someY = someY + ((Symmetric) aPlane[k]).getPointOfSymmetry().getY();
				count++;
			}
		}
		aveX = someX/count;
		aveY = someY/count;
		
		
		if (count == 0) {

			return null;
			
		}
		Point somePoint = new Point(aveX,aveY);
		
		
			return somePoint;
		
	}
}
