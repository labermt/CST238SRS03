# CST238SRS03  
The Chaos Game  

Name:  Silverio Reyes

Notes: The chaos application is used to generate a fractal based on vertices (v) to from a polygon shape as long as v > 3. There are a total of 6 input fields in which the user can select from using a drop down coloumn and entry fields.

Issues: Some of the issues are that when you create a canvas, you must extend the View class however, if you do this within an activity where you have data passed in as an intent or through shared preference, it will not recognize it. As a solution I made the canvas as subclass in which setter and getters where employed in order to pass the user entry parameters to draw the shape on canvas. There where issues with setting up the xml inorder to draw the canvas. At this point I investigated about making a custome view through the image view. At this point the application became far more complicate than what it should be. 

There was another repo started by the professor which had the canvas working using fragments. After figure it out how they work, this becamee the simplist way to pass data to fragment and to draw the vertices on the canvas. This should have been the boiler plate for the project. For demonstration, I have 2 versions. Version 1 is the original concept in which I will explain the process and intention. Version 2 is the update concept using the fragment template created by the professor which has the entry fields and default values set to draw 3 vertices and 25,00 random points between half a distance to the next random generated vertice. It forms a fractal of traingles with two different colors (red and green).
