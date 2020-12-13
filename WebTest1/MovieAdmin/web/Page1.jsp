<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{Page1.page1}" id="page1">
            <webuijsf:html binding="#{Page1.html1}" id="html1">
                <webuijsf:head binding="#{Page1.head1}" id="head1">
                    <webuijsf:link binding="#{Page1.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{Page1.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{Page1.form1}" id="form1" virtualFormsConfig="genre | currentViewPanel:currentGenreDropDown | currentViewPanel:currentGenreDropDown , add | addMoviePanel:addGenreDropDown addMoviePanel:addYearTextField addMoviePanel:addLengthTextField addMoviePanel:addTitleTextField addMoviePanel:addRatingDropDown addMoviePanel:addImageTextField addMoviePanel:addDescriptionArea | addMoviePanel:addButton , preview/remove | | tablePanel:moviesTable:tableRowGroup1:tableColumn7:removeButton tablePanel:moviesTable:tableRowGroup1:tableColumn7:previewButton , update | tablePanel:moviesTable:tableRowGroup1:tableColumn6:descriptionArea tablePanel:moviesTable:tableRowGroup1:tableColumn4:lengthTextField tablePanel:moviesTable:tableRowGroup1:tableColumn2:ratingDropDown tablePanel:moviesTable:tableRowGroup1:tableColumn3:yearTextField tablePanel:moviesTable:tableRowGroup1:tableColumn1:titleTextField tablePanel:moviesTable:tableRowGroup1:tableColumn5:imageTextField | tablePanel:updateButton">
                        <h:panelGrid binding="#{Page1.currentViewPanel}" id="currentViewPanel" style="height: 48px; left: 48px; top: 192px; position: absolute" width="120">
                            <webuijsf:label binding="#{Page1.currentGenreLabel}" id="currentGenreLabel" text="Current Genre:"/>
                            <webuijsf:dropDown binding="#{Page1.currentGenreDropDown}" id="currentGenreDropDown" items="#{ApplicationBean1.genreOptions}"
                                onChange="webuijsf.suntheme.common.timeoutSubmitForm(this.form, 'currentViewPanel:currentGenreDropDown');" valueChangeListenerExpression="#{Page1.currentGenreDropDown_processValueChange}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{Page1.tablePanel}" id="tablePanel" style="left: 48px; top: 264px; position: absolute">
                            <webuijsf:button actionExpression="#{Page1.updateButton_action}" binding="#{Page1.updateButton}" id="updateButton" text="Update"/>
                            <webuijsf:table augmentTitle="false" binding="#{Page1.moviesTable}" id="moviesTable" paginateButton="true" paginationControls="true"
                                title="Movies" width="834">
                                <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table1");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table1");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table1");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table1");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table1");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table1");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                <webuijsf:tableRowGroup binding="#{Page1.tableRowGroup1}" id="tableRowGroup1" rows="3" sourceData="#{SessionBean1.movieListDp}" sourceVar="currentRow">
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn1}" headerText="Title" id="tableColumn1" sort="title">
                                        <webuijsf:textField binding="#{Page1.titleTextField}" id="titleTextField" text="#{currentRow.value['title']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn2}" headerText="Rating" id="tableColumn2">
                                        <webuijsf:dropDown binding="#{Page1.ratingDropDown}" id="ratingDropDown" items="#{ApplicationBean1.ratingOptions}" selected="#{currentRow.value['rating']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn3}" headerText="Year" id="tableColumn3" sort="year">
                                        <webuijsf:textField binding="#{Page1.yearTextField}" id="yearTextField" text="#{currentRow.value['year']}" validatorExpression="#{Page1.yearLongRangeValidator.validate}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn4}" headerText="Length" id="tableColumn4" sort="length">
                                        <webuijsf:textField binding="#{Page1.lengthTextField}" id="lengthTextField" text="#{currentRow.value['length']}" validatorExpression="#{Page1.minutesLongRangeValidator.validate}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn5}" headerText="Image" id="tableColumn5" sort="image">
                                        <webuijsf:textField binding="#{Page1.imageTextField}" id="imageTextField" text="#{currentRow.value['image']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn6}" headerText="Description" id="tableColumn6" sort="description">
                                        <webuijsf:textArea binding="#{Page1.descriptionArea}" id="descriptionArea" text="#{currentRow.value['description']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.tableColumn7}" id="tableColumn7" width="200">
                                        <webuijsf:button actionExpression="#{Page1.previewButton_action}" binding="#{Page1.previewButton}" id="previewButton" text="Preview"/>
                                        <webuijsf:button actionExpression="#{Page1.removeButton_action}" binding="#{Page1.removeButton}" id="removeButton" text="Remove"/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </h:panelGrid>
                        <h:panelGrid binding="#{Page1.addMoviePanel}" columns="2" id="addMoviePanel" style="left: 48px; top: 600px; position: absolute" width="336">
                            <webuijsf:label binding="#{Page1.addLabel}" id="addLabel" style="font-weight: bolder; text-decoration: underline" text="Add Movie"/>
                            <h:panelGrid binding="#{Page1.spacePanel}" id="spacePanel" style="height: 24px" width="23"/>
                            <webuijsf:label binding="#{Page1.addGenreLabel}" id="addGenreLabel" text="Genre:"/>
                            <webuijsf:dropDown binding="#{Page1.addGenreDropDown}" id="addGenreDropDown" items="#{ApplicationBean1.genreOptions}"/>
                            <webuijsf:label binding="#{Page1.addTitleLabel}" id="addTitleLabel" text="Title:"/>
                            <webuijsf:textField binding="#{Page1.addTitleTextField}" id="addTitleTextField"/>
                            <webuijsf:label binding="#{Page1.addYearLabel}" id="addYearLabel" text="Year:"/>
                            <webuijsf:textField binding="#{Page1.addYearTextField}" converter="#{Page1.integerConverter1}" id="addYearTextField" validatorExpression="#{Page1.yearLongRangeValidator.validate}"/>
                            <webuijsf:label binding="#{Page1.addLengthLabel}" id="addLengthLabel" text="Minutes:"/>
                            <webuijsf:textField binding="#{Page1.addLengthTextField}" converter="#{Page1.integerConverter1}" id="addLengthTextField" validatorExpression="#{Page1.minutesLongRangeValidator.validate}"/>
                            <webuijsf:label binding="#{Page1.addRatingLabel}" id="addRatingLabel" text="Rating:"/>
                            <webuijsf:dropDown binding="#{Page1.addRatingDropDown}" id="addRatingDropDown" items="#{ApplicationBean1.ratingOptions}"/>
                            <webuijsf:label binding="#{Page1.addImageLabel}" id="addImageLabel" text="Image:"/>
                            <webuijsf:textField binding="#{Page1.addImageTextField}" id="addImageTextField"/>
                            <webuijsf:label binding="#{Page1.addDescriptionLabel}" id="addDescriptionLabel" text="Description:"/>
                            <webuijsf:textArea binding="#{Page1.addDescriptionArea}" id="addDescriptionArea"/>
                            <webuijsf:button actionExpression="#{Page1.addButton_action}" binding="#{Page1.addButton}" id="addButton" text="Add"/>
                        </h:panelGrid>
                        <webuijsf:messageGroup binding="#{Page1.messageGroup1}" id="messageGroup1" style="height: 46px; left: 408px; top: 600px; position: absolute"/>
                        <div style="left: 0px; top: 0px; position: absolute">
                            <jsp:directive.include file="Header.jspf"/>
                        </div>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
