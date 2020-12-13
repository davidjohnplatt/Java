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

import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Body;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.Hyperlink;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Label;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.MessageGroup;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.StaticText;
import javax.faces.FacesException;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ValueChangeEvent;
import movieslib.Movie;
import movieslib.MovieListDataProvider;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Preview extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    
    private Page page1 = new Page();
    
    public Page getPage1() {
        return page1;
    }
    
    public void setPage1(Page p) {
        this.page1 = p;
    }
    
    private Html html1 = new Html();
    
    public Html getHtml1() {
        return html1;
    }
    
    public void setHtml1(Html h) {
        this.html1 = h;
    }
    
    private Head head1 = new Head();
    
    public Head getHead1() {
        return head1;
    }
    
    public void setHead1(Head h) {
        this.head1 = h;
    }
    
    private Link link1 = new Link();
    
    public Link getLink1() {
        return link1;
    }
    
    public void setLink1(Link l) {
        this.link1 = l;
    }
    
    private Body body1 = new Body();
    
    public Body getBody1() {
        return body1;
    }
    
    public void setBody1(Body b) {
        this.body1 = b;
    }
    
    private Form form1 = new Form();
    
    public Form getForm1() {
        return form1;
    }
    
    public void setForm1(Form f) {
        this.form1 = f;
    }

    private Hyperlink returnLink = new Hyperlink();

    public Hyperlink getReturnLink() {
        return returnLink;
    }

    public void setReturnLink(Hyperlink h) {
        this.returnLink = h;
    }

    private HtmlPanelGrid genrePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getGenrePanel() {
        return genrePanel;
    }

    public void setGenrePanel(HtmlPanelGrid hpg) {
        this.genrePanel = hpg;
    }

    private Label reviseGenreLabel = new Label();

    public Label getReviseGenreLabel() {
        return reviseGenreLabel;
    }

    public void setReviseGenreLabel(Label l) {
        this.reviseGenreLabel = l;
    }

    private DropDown genreDropDown = new DropDown();

    public DropDown getGenreDropDown() {
        return genreDropDown;
    }

    public void setGenreDropDown(DropDown dd) {
        this.genreDropDown = dd;
    }

    private HtmlPanelGrid moviePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getMoviePanel() {
        return moviePanel;
    }

    public void setMoviePanel(HtmlPanelGrid hpg) {
        this.moviePanel = hpg;
    }

    private StaticText titleText = new StaticText();

    public StaticText getTitleText() {
        return titleText;
    }

    public void setTitleText(StaticText st) {
        this.titleText = st;
    }

    private StaticText yearText = new StaticText();

    public StaticText getYearText() {
        return yearText;
    }

    public void setYearText(StaticText st) {
        this.yearText = st;
    }

    private ImageComponent imageComponent1 = new ImageComponent();

    public ImageComponent getImageComponent1() {
        return imageComponent1;
    }

    public void setImageComponent1(ImageComponent ic) {
        this.imageComponent1 = ic;
    }

    private HtmlPanelGrid detailsPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getDetailsPanel() {
        return detailsPanel;
    }

    public void setDetailsPanel(HtmlPanelGrid hpg) {
        this.detailsPanel = hpg;
    }

    private Label genreLabel = new Label();

    public Label getGenreLabel() {
        return genreLabel;
    }

    public void setGenreLabel(Label l) {
        this.genreLabel = l;
    }

    private StaticText genreText = new StaticText();

    public StaticText getGenreText() {
        return genreText;
    }

    public void setGenreText(StaticText st) {
        this.genreText = st;
    }

    private Label ratingLabel = new Label();

    public Label getRatingLabel() {
        return ratingLabel;
    }

    public void setRatingLabel(Label l) {
        this.ratingLabel = l;
    }

    private StaticText ratingText = new StaticText();

    public StaticText getRatingText() {
        return ratingText;
    }

    public void setRatingText(StaticText st) {
        this.ratingText = st;
    }

    private Label lengthLabel = new Label();

    public Label getLengthLabel() {
        return lengthLabel;
    }

    public void setLengthLabel(Label l) {
        this.lengthLabel = l;
    }

    private StaticText lengthText = new StaticText();

    public StaticText getLengthText() {
        return lengthText;
    }

    public void setLengthText(StaticText st) {
        this.lengthText = st;
    }

    private Label descriptionLabel = new Label();

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public void setDescriptionLabel(Label l) {
        this.descriptionLabel = l;
    }

    private StaticText descriptionText = new StaticText();

    public StaticText getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(StaticText st) {
        this.descriptionText = st;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    // </editor-fold>
    
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Preview() {
    }
    
    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     *
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
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
            log("Preview Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
    }
    
    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    public void preprocess() {
    }
    
    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    public void prerender() {
        RowKey newRowToPreview = getRequestBean1().getPreviewRow();
        if (newRowToPreview != null) {
            getSessionBean1().getMovieListDp().setCursorRow(newRowToPreview);
        }
        if (genreDropDown.getSelected() == null) {
            MovieListDataProvider dp = getSessionBean1().getMovieListDp();
            String genre = (String)dp.getValue(dp.getFieldKey("genre"));
            genreDropDown.setSelected(genre);
        }
    }
    
    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
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
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected movieadmin.SessionBean1 getSessionBean1() {
        return (movieadmin.SessionBean1)getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected movieadmin.RequestBean1 getRequestBean1() {
        return (movieadmin.RequestBean1)getBean("RequestBean1");
    }

    public void genreDropDown_processValueChange(ValueChangeEvent event) {
        MovieListDataProvider dp = getSessionBean1().getMovieListDp();
        RowKey movieRowKey = dp.getCursorRow();
        Movie movie = (Movie)dp.getObject(movieRowKey);
        try {
            getSessionBean1().reviseGenre(movie, (String)genreDropDown.getSelected());
        }
        catch (Exception e) {
            error("Could not revise movie genre");
            log("Could not revise genre of movie " + (movie == null ? "(null movie)" : movie.getId().toString()) + ": " + e, e);
        }     
    }

    public String returnLink_action() {
        return "returnPage1";
    }
}

