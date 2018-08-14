# CST238SRS03  
The Chaos Game  

Name:  Steven Reeves

Notes:  

Create a application that takes various inputs:

Number of iterations. (find a good default)
Default = 5000

A fraction between [0.0, 1.0]. (default to 0.5)
Percent Seek bar

A color. (use a default of your choice)
Default = white, the color wheel starts there 

The number of vertices. (minimum 3)
Vertices at 3

A start and period for restricted vertices from previous selection.
Shown, not implemented 

Display a graphic in a user interface by creating a custom ImageView.
Displayed on draw page

Draw Algorithm:

Randomly pick one of the vertices.
Int first_v = rand.nextInt(vertices);

Randomly pick the next vertex using any user specified restrictions.
Start and Period stretch goals

Move the user selected fraction [0.0, 1.0] towards the randomly selected and possibly restricted vertex.
Using the new location as the starting location, loop for the user selected interations.

for (int i = 0; i < iterations; i++ ) {
	int random_v = rand.nextInt(vertices);
	p_drawPoints.add(new Point((int)((p_drawPoints.get(i).x + p_points.get(random_v).x)*percent),(int)((p_drawPoints.get(i).y + p_points.get(random_v).y)*percent)));
}

Draw a point of the selected color at the new location.

for (Point p : p_drawPoints)
{
	canvas.drawPoint((float) p.x, (float) p.y, paint_);
}

Other notes:
imeOptions = action done. "I only want to change one thing."
point size = 3, iteration 50000 happy medium
point size  = 1, iteration 500000 crashes

Issues:  

SDK 28:
	- Had to switch back to 27, Design tab for Activities not working.
	https://stackoverflow.com/questions/49292487/failed-to-find-style-coordinatorlayoutstyle-in-current-theme
	
	- Java drawing starting at top left... Lots of time getting vertices drawn...
	- Remove operators from number input
	- Make color picker a popup
	https://medium.com/@skydoves/how-to-implement-color-picker-in-android-61d8be348683
	- Color only changes after other parameter change. 
	- Implement start and period 