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
 *  File given for Mesh3D object. 
 *  
 */

//****************************************************************************
//       3D triangle mesh with normals
//****************************************************************************
// History :
//   Nov 6, 2014 Created by Stan Sclaroff
//
public class Mesh3D
{
	public int cols,rows;
	public Point3D[][] v;
	public Point3D[][] n;
	
	public Mesh3D(int _rows, int _cols)
	{
		cols=_cols;
		rows=_rows;
		v = new Point3D[rows][cols];
		n = new Point3D[rows][cols];
		for(int i=0;i<rows;++i)
			for(int j=0;j<cols;++j)
			{
				v[i][j] = new Point3D();
				n[i][j] = new Point3D();
			}
	}
	
	public void rotateMesh(Quaternion q, Point3D center)
	{
		Quaternion q_inv = q.conjugate();
		Point3D vec;
		
		Quaternion p;
		
		for(int i=0;i<rows;++i)
			for(int j=0;j<cols;++j)
			{
				// apply pivot rotation to vertices, given center point
				p = new Quaternion((float)0.0,v[i][j].minus(center)); 
				p=q.multiply(p);
				p=p.multiply(q_inv);
				vec = p.get_v();
				v[i][j]=vec.plus(center);
				
				// rotate the normals
				p = new Quaternion((float)0.0,n[i][j]);
				p=q.multiply(p);
				p=p.multiply(q_inv);
				n[i][j] = p.get_v();
			}
		
		
	}
}