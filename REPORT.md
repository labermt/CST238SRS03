# CST238SRS03  
The Chaos Game  

Name:  Silverio Reyes

Notes: The chaos application is used to generate a fractal based on vertices (v) to from a polygon shape as long as v > 3. There are a total of 6 input fields in which the user can select from using a drop down coloumn and entry fields.

Issues: Some of the issues are that when you create a canvas, you must extend the View class however, if you do this within an activity where you have data passed in as an intent or through shared preference, it will not recognize it. As a solution I made the canvas as a subclass in which setter and getters where employed in order to pass the user entry parameters to draw the shape on the canvas. There where issues with setting up the xml in order to draw the canvas. At this point I investigated about making a custom view through the image view property and created a custom bitmap and canvas. At this point the application became far more complicated than what it should have been. 

There was another repo started by the professor which had the canvas working using fragments. After figuring it out how they work, this became the simplist way to pass data using fragments and to draw the vertices on the canvas. This should have been the boiler plate for the project. For demonstration, I have 2 versions. Version 1 is the original concept in which I will explain the process and intention. Version 2 is the updated concept using the fragment template created by the professor which has the entry fields and default values set to draw 3 vertices and 25,000 random points between half a distance to the next random generated vertex. It forms a fractal of traingles with two different colors (red and green).
