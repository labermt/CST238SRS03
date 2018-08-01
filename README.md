# CST238SRS03
Chaos

This is a 2x SRS.

Purpose:  

- Learn to use graphics in a user interface.  
- Understand the drawing life cycle.  
- Understanding separation of view from model.  

App Name: Chaos  

Specification:  

Create a application that takes various inputs:  

- Number of iterations.  (find a good default)  
- A fraction between [0.0, 1.0].  (default to 0.5)  
- A color. (use a default of your choice)  
- The number of vertices.  (minimum 3)  
- A start and period for restricted vertices from previous selection.  

Display a graphic in a user interface by creating a custom ImageView.  

Draw Algorithm:  

- Randomly pick one of the vertices.  

- Randomly pick the next vertex using any user specified restrictions.  

- Move the user selected fraction [0.0, 1.0] towards the randomly selected and possibly restricted vertex.  

- Draw a point of the selected color at the new location.  

- Using the new location as the starting location, loop for the user selected interations.  

Hints:  

- @Override public void onDraw(Canvas canvas).  
- Do as little work as possible in your onDraw() function.  
- See Canvas.drawPoint documentation.  
- See https://developer.android.com/guide/topics/ui/custom-components.html.  
- You will want to implement the derived ctors as described in:  
  https://developer.android.com/reference/android/view/View#public-constructors_4. 
- A technique for sharing information between fragments:  
  https://developer.android.com/topic/libraries/architecture/viewmodel#sharing  
