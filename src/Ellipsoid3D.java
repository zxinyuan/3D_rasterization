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
 *  Ellipsoid3D object with 1 mesh. 
 *  
 */

public class Ellipsoid3D {
	
	private Point3D center;
	private float rx, ry, rz;
	private int stacks,slices;
	public Mesh3D mesh;
	
	public Ellipsoid3D(float _x, float _y, float _z, float _rx, float _ry, float _rz, int _stacks, int _slices)
	{
		center = new Point3D(_x,_y,_z);
		rx = _rx;
		ry = _ry;
		rz = _rz;
		stacks = _stacks;
		slices = _slices;
		initMesh();
	}
	
	public void set_center(float _x, float _y, float _z)
	{
		center.x=_x;
		center.y=_y;
		center.z=_z;
		fillMesh();  // update the triangle mesh
	}
	
	public void set_radius(float _rx, float _ry, float _rz)
	{
		rx = _rx;
		ry = _ry;
		rz = _rz;
		fillMesh(); // update the triangle mesh
	}
	
	public void set_stacks(int _stacks)
	{
		stacks = _stacks;
		initMesh(); // resized the mesh, must re-initialize
	}
	
	public void set_slices(int _slices)
	{
		slices = _slices;
		initMesh(); // resized the mesh, must re-initialize
	}
	
	public int get_n()
	{
		return slices;
	}
	
	public int get_m()
	{
		return stacks;
	}
	
	private void initMesh()
	{
		mesh = new Mesh3D(stacks,slices);
		fillMesh();  // set the mesh vertices and normals
	}
	
	// fill the triangle mesh vertices and normals
	// using the current parameters for the ellipsoid
	private void fillMesh() {
		int i, j;
		float theta, phi;
		float d_theta = (float)(2*Math.PI)/((float)stacks-1);
		float d_phi = (float)Math.PI/((float)slices-1);
		float cos_theta, sin_theta;
		float cos_phi, sin_phi;
		
		for (i = 0, theta = (float)-Math.PI; i < stacks; ++i, theta += d_theta){
			cos_theta = (float)Math.cos(theta);
			sin_theta = (float)Math.sin(theta);
			
			for (j = 0, phi = (float)-Math.PI/2; j < slices; ++j, phi += d_phi) {
				cos_phi = (float)Math.cos(phi);
				sin_phi = (float)Math.sin(phi);
				
				mesh.v[i][j].x = center.x+rx*cos_phi*cos_theta;
				mesh.v[i][j].y = center.y+ry*cos_phi*sin_theta;
				mesh.v[i][j].z = center.z+rz*sin_phi;
				
				mesh.n[i][j].x = cos_phi * cos_theta;
				mesh.n[i][j].y = cos_phi * sin_theta;
				mesh.n[i][j].z = sin_phi;
			}
		}
	}
}


