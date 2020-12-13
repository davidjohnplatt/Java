/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */
package movieadmin;

import com.sun.data.provider.DataProviderException;
import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import movieslib.Movie;
import movieslib.MovieListDataProvider;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class SessionBean1 extends AbstractSessionBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>
    
    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
    }
    
    /**
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     *
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        List movies = movieListDp.getList();
        Movie dukeMovie = (Movie)movies.get(0);
        String dukeGenre = dukeMovie.getGenre();
        moviesByGenre.put(dukeGenre, movies);
        this.currentGenre = dukeGenre;
    }
    
    private Map moviesByGenre = new HashMap();
    
    /**
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     *
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    public void passivate() {
    }
    
    /**
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     *
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    public void activate() {
    }
    
    /**
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     *
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }
        /**
     * Holds value of property currentGenre.
     */
    private String currentGenre;

    /**
     * Getter for property currentGenre.
     * @return Value of property currentGenre.
     */
    public String getCurrentGenre() {
        return this.currentGenre;
    }

    /**
     * Setter for property currentGenre.
     * @param currentGenre New value of property currentGenre.
     */
    public void setCurrentGenre(String currentGenre) {
        this.currentGenre = currentGenre;
        List moviesInCurrentGenre = null;
        if (currentGenre != null) {
            moviesInCurrentGenre = (List)moviesByGenre.get(this.currentGenre);
            if (moviesInCurrentGenre == null) {
                moviesInCurrentGenre = new ArrayList();
            }
        }
        movieListDp.setList(moviesInCurrentGenre);
    }
       
    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Could not add null movie");
        }
        String genre = movie.getGenre();
        if (genre == null) {
            throw new IllegalArgumentException("Could not add movie because genre was null: " + movie);
        }
        //if movie is in current genre, then just add to movieListDp directly
        //otherwise, need to add to moviesInGenre
        if (genre.equals(this.currentGenre)) {
            try {
                movieListDp.appendRow(movie);
                movieListDp.commitChanges();
            }
            catch (DataProviderException e) {
                movieListDp.revertChanges();
                //rethrow e, so it is caught and logged by callers
                throw e;
            }
        }
        else {
            List moviesInGenre = (List)moviesByGenre.get(genre);
            if (moviesInGenre == null) {
                moviesInGenre = new ArrayList();
                moviesByGenre.put(genre, moviesInGenre);
            }
            moviesInGenre.add(movie);
        }
    }
    
    public void reviseGenre(Movie movie, String newGenre) {
        if (movie == null) {
            throw new IllegalArgumentException("movie was null");
        }
        if (newGenre == null) {
            throw new IllegalArgumentException("Could not revise movie genre because new genre was null: " + movie);
        }
        String oldGenre = movie.getGenre();
        if (oldGenre == null) {
            throw new IllegalArgumentException("Could not revise movie genre because old genre was null: " + movie);
        }
        //we need to remove the movie from the old genre.
        //movieListDp contains the list of movies for the old genre.
        //it is bad practice to modify the underlying list without movieListDp's knowledge,
        //so temporarily set the current genre to null, which will set MovieListDp's list to null.
        setCurrentGenre(null);
        List moviesInOldGenre = (List)moviesByGenre.get(oldGenre);  //shouldn't be null, because movie is in the old genre
        moviesInOldGenre.remove(movie);
        movie.setGenre(newGenre);
        addMovie(movie);    //will add movie to moviesByGenre
        setCurrentGenre(newGenre);  //resets list inside movieListDp
        //set movieListDp cursor onto the movie
        RowKey row = movieListDp.findFirst("id", movie.getId());
        if (row == null) {
            throw new RuntimeException("could not find movie " + movie + " after revising its genre");
        }
        movieListDp.setCursorRow(row);
    }


    /**
     * Holds value of property movieListDp.
     */
    private MovieListDataProvider movieListDp = new MovieListDataProvider();

    /**
     * Getter for property movieListDp.
     * @return Value of property movieListDp.
     */
    public MovieListDataProvider getMovieListDp() {
        return this.movieListDp;
    }
}
