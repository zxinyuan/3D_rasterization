/*
 * 
 * Name: Xinyuan Zhang
 * Class: CS480
 * 
 * Assignment 4
 * Due: 2019/12/10
 * Problem Number: /
 * 
 * Description: 
 *  File given for Point3D object. Added color attribute to each Point3D.
 *  
 */

//****************************************************************************
//       3D vector class for example program
//****************************************************************************
// History :
//   Nov 6, 2014 Created by Stan Sclaroff
//

public class Point3D
{
	public float x, y, z;
	public static final float ROUNDOFF_THRESHOLD = 0.0001f;
	public ColorType c;
	public Point3D n;
	
	public Point3D(float _x, float _y, float _z, ColorType _c)
	{
		x=_x;
		y=_y;
		z=_z;
		c=_c;
	}
	
	public Point3D(float _x, float _y, float _z)
	{
		x=_x;
		y=_y;
		z=_z;
		c = new ColorType(1.0f, 1.0f, 1.0f);
	}
	
	public Point3D(Point3D _v)
	{
		x=_v.x;
		y=_v.y;
		z=_v.z;
		c=new ColorType(_v.c.r, _v.c.g, _v.c.b);
	}
	
	public Point3D()
	{
		x=y=z=(float)0.0;
		c = new ColorType(1.0f, 1.0f, 1.0f);
	}
	
	public void set(float _x, float _y, float _z)
	{
		x=_x;
		y=_y;
		z=_z;
		c=new ColorType(1.0f, 1.0f, 1.0f);
	}
	
	// compute the cross-product this (x) v and return result in out
	public void crossProduct(Point3D v, Point3D out)
	{
		Point3D temp = new Point3D();
		temp.x = this.y*v.z-this.z*v.y;
		temp.y = this.z*v.x-this.x*v.z;
		temp.z = this.x*v.y-this.y*v.x;
		
		out.x = temp.x;
		out.y = temp.y;
		out.z = temp.z;
	}
	
	// compute the cross-product this (x) v and return result
	public Point3D crossProduct(Point3D v)
	{
		Point3D out = new Point3D();
		out.x = this.y*v.z-this.z*v.y;
		out.y = this.z*v.x-this.x*v.z;
		out.z = this.x*v.y-this.y*v.x;
		return(out);
	}
	
	// compute dot product of v and this vector
	public float dotProduct(Point3D v)
	{
		return(v.x*this.x+v.y*this.y+v.z*this.z);
	}
			
	// subtract vector v from this vector and return result in out
	public void minus(Point3D v, Point3D out)
	{
		out.x = this.x-v.x;
		out.y = this.y-v.y;
		out.z = this.z-v.z;
	}
		
	// subtract vector v from this vector and return result
	public Point3D minus(Point3D v)
	{
		Point3D out = new Point3D();
		out.x = this.x-v.x;
		out.y = this.y-v.y;
		out.z = this.z-v.z;
		return(out);
	}
	
	// scale this vector by s and return result
	public Point3D scale(float s)
	{
		Point3D out = new Point3D();
		out.x = this.x*s;
		out.y = this.y*s;
		out.z = this.z*s;
		return(out);
	}
	
	// add the vector v to this vector and return result 
	public Point3D plus(Point3D v)
	{
		Point3D out = new Point3D();
		out.x = this.x+v.x;
		out.y = this.y+v.y;
		out.z = this.z+v.z;
		return(out);
	}
	
	// compute the length / magnitude
	public double magnitude()
	{
		double mag = Math.sqrt(dotProduct(this));
		return(mag);
	}
	
	// produce unit vector
	public Point3D normalize()
	{
		double mag = this.magnitude();
		if(mag>ROUNDOFF_THRESHOLD)
		{
			this.x /= mag;
			this.y /= mag;
			this.z /= mag;
		}
		return this;
		// should probably throw an error exception here for zero magnitude 
	}
	
	// compute the reflection of this vector around vector n
	public Point3D reflect(Point3D n)
	{

		float dot = 2*this.dotProduct(n);
		Point3D out = n.scale(dot);
		out = out.minus(this);
		
		return(out);
	}
	
}