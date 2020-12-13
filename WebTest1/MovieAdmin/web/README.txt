=============================================================================
README.txt
=============================================================================

Movie Administration is an example that demonstrates how to use virtual forms, ObjectListDataProvider, and RequestBean in Visual Web Pack. 

The Movie Administration example is a hypothetical administrative application for a movie rental business. The business maintains data about its 
movie collection in a data store, and the administrative application enables employees to make changes to that data. The business also has a separate 
external web site that enables customers to browse the collection. The same data store is used for the external web site.

An employee using the application can view the movies in a particular genre by making a selection in the Current Genre dropdown list. The Movies table 
shows the movies in the current genre. Within the Movies table, the title, year, length in minutes, rating, image URL, and description fields can be 
edited; the changes are saved when the employee clicks the Update button or uses the table's paging controls (when available). The employee can remove 
a movie from the collection by clicking the Remove button in one of the rows. Clicking the Preview button in one of the rows navigates to Preview.jsp, 
which enables the employee to preview the detail page for a movie as it would be seen by a customer browsing the external web site. (Preview.jsp also 
enables the employee to change the genre of the movie being previewed.) A separate set of controls appears below the table for adding a movie to the 
collection.

For more information, see the accompanying online technical article "How to Use Virtual Forms." 

Installation 

1. Open both the Movies Class Library project and the MovieAdmin project (this project). 
2. If the MovieAdmin project is not set as the main project, right-click on the MovieAdmin project and choose "Set Main Project." 
3. In the Projects window, right-click the MovieAdmin project node, and choose Properties from the contextual menu. 
4. Within the Project Properties dialog box, in the tree on the left, click on Libraries. Then click the Add Project button. 
5. In the Add Project dialog box, browse to and click on the MoviesClassLibrary project, and then click the Add Project JAR Files button. 
6. In the Project Properties dialog box, click the OK button.  
7. In the Projects window, right-click the MoviesClassLibrary project node, and choose Build Project from the contextual menu. 







