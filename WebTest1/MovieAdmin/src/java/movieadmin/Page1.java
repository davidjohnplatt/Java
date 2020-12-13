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
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.Label;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.MessageGroup;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.Table;
import com.sun.webui.jsf.component.TableColumn;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextArea;
import com.sun.webui.jsf.component.TextField;
import javax.faces.FacesException;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.convert.IntegerConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.LongRangeValidator;
import movieslib.Movie;
import movieslib.MovieListDataProvider;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Page1 extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        yearLongRangeValidator.setMaximum(2010);
        yearLongRangeValidator.setMinimum(1900);
        minutesLongRangeValidator.setMinimum(0);
        moviesTable.setInternalVirtualForm(true);
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

    private HtmlPanelGrid currentViewPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getCurrentViewPanel() {
        return currentViewPanel;
    }

    public void setCurrentViewPanel(HtmlPanelGrid hpg) {
        this.currentViewPanel = hpg;
    }

    private Label currentGenreLabel = new Label();

    public Label getCurrentGenreLabel() {
        return currentGenreLabel;
    }

    public void setCurrentGenreLabel(Label l) {
        this.currentGenreLabel = l;
    }

    private DropDown currentGenreDropDown = new DropDown();

    public DropDown getCurrentGenreDropDown() {
        return currentGenreDropDown;
    }

    public void setCurrentGenreDropDown(DropDown dd) {
        this.currentGenreDropDown = dd;
    }

    private HtmlPanelGrid tablePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(HtmlPanelGrid hpg) {
        this.tablePanel = hpg;
    }

    private Button updateButton = new Button();

    public Button getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(Button b) {
        this.updateButton = b;
    }

    private Table moviesTable = new Table();

    public Table getMoviesTable() {
        return moviesTable;
    }

    public void setMoviesTable(Table t) {
        this.moviesTable = t;
    }

    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    private HtmlPanelGrid addMoviePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getAddMoviePanel() {
        return addMoviePanel;
    }

    public void setAddMoviePanel(HtmlPanelGrid hpg) {
        this.addMoviePanel = hpg;
    }

    private Label addLabel = new Label();

    public Label getAddLabel() {
        return addLabel;
    }

    public void setAddLabel(Label l) {
        this.addLabel = l;
    }

    private HtmlPanelGrid spacePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getSpacePanel() {
        return spacePanel;
    }

    public void setSpacePanel(HtmlPanelGrid hpg) {
        this.spacePanel = hpg;
    }

    private Label addGenreLabel = new Label();

    public Label getAddGenreLabel() {
        return addGenreLabel;
    }

    public void setAddGenreLabel(Label l) {
        this.addGenreLabel = l;
    }

    private DropDown addGenreDropDown = new DropDown();

    public DropDown getAddGenreDropDown() {
        return addGenreDropDown;
    }

    public void setAddGenreDropDown(DropDown dd) {
        this.addGenreDropDown = dd;
    }

    private Label addTitleLabel = new Label();

    public Label getAddTitleLabel() {
        return addTitleLabel;
    }

    public void setAddTitleLabel(Label l) {
        this.addTitleLabel = l;
    }

    private TextField addTitleTextField = new TextField();

    public TextField getAddTitleTextField() {
        return addTitleTextField;
    }

    public void setAddTitleTextField(TextField tf) {
        this.addTitleTextField = tf;
    }

    private Label addYearLabel = new Label();

    public Label getAddYearLabel() {
        return addYearLabel;
    }

    public void setAddYearLabel(Label l) {
        this.addYearLabel = l;
    }

    private TextField addYearTextField = new TextField();

    public TextField getAddYearTextField() {
        return addYearTextField;
    }

    public void setAddYearTextField(TextField tf) {
        this.addYearTextField = tf;
    }

    private Label addLengthLabel = new Label();

    public Label getAddLengthLabel() {
        return addLengthLabel;
    }

    public void setAddLengthLabel(Label l) {
        this.addLengthLabel = l;
    }

    private TextField addLengthTextField = new TextField();

    public TextField getAddLengthTextField() {
        return addLengthTextField;
    }

    public void setAddLengthTextField(TextField tf) {
        this.addLengthTextField = tf;
    }

    private Label addRatingLabel = new Label();

    public Label getAddRatingLabel() {
        return addRatingLabel;
    }

    public void setAddRatingLabel(Label l) {
        this.addRatingLabel = l;
    }

    private DropDown addRatingDropDown = new DropDown();

    public DropDown getAddRatingDropDown() {
        return addRatingDropDown;
    }

    public void setAddRatingDropDown(DropDown dd) {
        this.addRatingDropDown = dd;
    }

    private Label addImageLabel = new Label();

    public Label getAddImageLabel() {
        return addImageLabel;
    }

    public void setAddImageLabel(Label l) {
        this.addImageLabel = l;
    }

    private TextField addImageTextField = new TextField();

    public TextField getAddImageTextField() {
        return addImageTextField;
    }

    public void setAddImageTextField(TextField tf) {
        this.addImageTextField = tf;
    }

    private Label addDescriptionLabel = new Label();

    public Label getAddDescriptionLabel() {
        return addDescriptionLabel;
    }

    public void setAddDescriptionLabel(Label l) {
        this.addDescriptionLabel = l;
    }

    private TextArea addDescriptionArea = new TextArea();

    public TextArea getAddDescriptionArea() {
        return addDescriptionArea;
    }

    public void setAddDescriptionArea(TextArea ta) {
        this.addDescriptionArea = ta;
    }

    private Button addButton = new Button();

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button b) {
        this.addButton = b;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }

    private TextField titleTextField = new TextField();

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(TextField tf) {
        this.titleTextField = tf;
    }

    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }

    private DropDown ratingDropDown = new DropDown();

    public DropDown getRatingDropDown() {
        return ratingDropDown;
    }

    public void setRatingDropDown(DropDown dd) {
        this.ratingDropDown = dd;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private TextField yearTextField = new TextField();

    public TextField getYearTextField() {
        return yearTextField;
    }

    public void setYearTextField(TextField tf) {
        this.yearTextField = tf;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }

    private TextField lengthTextField = new TextField();

    public TextField getLengthTextField() {
        return lengthTextField;
    }

    public void setLengthTextField(TextField tf) {
        this.lengthTextField = tf;
    }

    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }

    private TextField imageTextField = new TextField();

    public TextField getImageTextField() {
        return imageTextField;
    }

    public void setImageTextField(TextField tf) {
        this.imageTextField = tf;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private TextArea descriptionArea = new TextArea();

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public void setDescriptionArea(TextArea ta) {
        this.descriptionArea = ta;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private Button previewButton = new Button();

    public Button getPreviewButton() {
        return previewButton;
    }

    public void setPreviewButton(Button b) {
        this.previewButton = b;
    }

    private Button removeButton = new Button();

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button b) {
        this.removeButton = b;
    }

    private LongRangeValidator yearLongRangeValidator = new LongRangeValidator();

    public LongRangeValidator getYearLongRangeValidator() {
        return yearLongRangeValidator;
    }

    public void setYearLongRangeValidator(LongRangeValidator lrv) {
        this.yearLongRangeValidator = lrv;
    }

    private LongRangeValidator minutesLongRangeValidator = new LongRangeValidator();

    public LongRangeValidator getMinutesLongRangeValidator() {
        return minutesLongRangeValidator;
    }

    public void setMinutesLongRangeValidator(LongRangeValidator lrv) {
        this.minutesLongRangeValidator = lrv;
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
    public Page1() {
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
            log("Page1 Initialization Failure", e);
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
        if (currentGenreDropDown.getValue() == null) {
            currentGenreDropDown.setValue(getSessionBean1().getCurrentGenre());
        }
        if (addGenreDropDown.getSelected() == null) {
            String firstGenre = getApplicationBean1().getGenres()[0];
            addGenreDropDown.setSelected(firstGenre);
        }
        if (addRatingDropDown.getSelected() == null) {
            String firstRating = getApplicationBean1().getRatings()[0];
            addRatingDropDown.setSelected(firstRating);
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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }

    public void currentGenreDropDown_processValueChange(ValueChangeEvent event) {
        // change current genre
        getSessionBean1().setCurrentGenre((String) event.getNewValue());       
        // the genre virtual form, in which only the currentGenreDropDown participates, has been submitted
        // make sure the input fields in the movies table do not retain their submitted values
        form1.discardSubmittedValues("update");
    }

    public String updateButton_action() {
        MovieListDataProvider movieListDp = getSessionBean1().getMovieListDp();
        try {
            movieListDp.commitChanges();
        }
        catch (Exception e) {
            error("Could not update movies");
            log("Could not update movies: " + e, e);
            movieListDp.revertChanges();
        }
        return null;
    }

    public String removeButton_action() {
        RowKey rowToRemove = tableRowGroup1.getRowKey();
        MovieListDataProvider movieListDp = getSessionBean1().getMovieListDp();
        Integer movieId = (Integer)movieListDp.getValue(movieListDp.getFieldKey("id"), rowToRemove);
        try {
            movieListDp.removeRow(rowToRemove);
            movieListDp.commitChanges();
        }
        catch (Exception e) {
            error("Could not remove movie");
            log("Could not remove movie " + movieId + ": " + e, e);
            movieListDp.revertChanges();
        }
        tableRowGroup1.setFirst(0);
        //work around limitation in the dataprovider (uses IndexRowKeys)
        //the preview/remove virtual form, which has no participants, has been submitted
        //make sure the input fields in the movies table do not retain their submitted values
        form1.discardSubmittedValues("update");
        return null;
    }

    public String previewButton_action() {
        RowKey rowWhereButtonClicked = tableRowGroup1.getRowKey();
        getRequestBean1().setPreviewRow(rowWhereButtonClicked);
        return "info";
    }

    public String addButton_action() {
        Movie movie = new Movie();
        movie.setGenre((String)addGenreDropDown.getSelected());
        movie.setTitle((String)addTitleTextField.getText());
        movie.setYear((Integer)addYearTextField.getText());
        movie.setLength((Integer)addLengthTextField.getText());
        movie.setRating((String)addRatingDropDown.getSelected());
        movie.setImage((String)addImageTextField.getText());
        movie.setDescription((String)addDescriptionArea.getText());
        try {
            getSessionBean1().addMovie(movie);
        } catch (Exception e) {
            error("Could not add movie");
            log("Could not add movie " + movie.getId() + ": " + e, e);
        }
        tableRowGroup1.setFirst(0);
        addGenreDropDown.setSelected(null);
        addTitleTextField.setText(null);
        addYearTextField.setText(null);
        addLengthTextField.setText(null);
        addRatingDropDown.setSelected(null);
        addImageTextField.setText(null);
        addDescriptionArea.setText(null);
        return null;
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected Preview getPreview() {
        return (Preview)getBean("Preview");
    }
}

