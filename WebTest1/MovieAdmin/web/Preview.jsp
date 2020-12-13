<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{Preview.page1}" id="page1">
            <webuijsf:html binding="#{Preview.html1}" id="html1">
                <webuijsf:head binding="#{Preview.head1}" id="head1">
                    <webuijsf:link binding="#{Preview.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{Preview.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{Preview.form1}" id="form1">
                        <webuijsf:hyperlink actionExpression="#{Preview.returnLink_action}" binding="#{Preview.returnLink}" id="returnLink"
                            style="left: 48px; top: 192px; position: absolute; width: 166px" text="Return to Administration Page"/>
                        <h:panelGrid binding="#{Preview.genrePanel}" columns="2" id="genrePanel"
                            style="height: 24px; left: 48px; top: 240px; position: absolute" width="240">
                            <webuijsf:label binding="#{Preview.reviseGenreLabel}" id="reviseGenreLabel" text="Revise Genre:"/>
                            <webuijsf:dropDown binding="#{Preview.genreDropDown}" id="genreDropDown" items="#{ApplicationBean1.genreOptions}" valueChangeListenerExpression="#{Preview.genreDropDown_processValueChange}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{Preview.moviePanel}" id="moviePanel" style="height: 408px; left: 48px; top: 312px; position: absolute" width="504">
                            <webuijsf:staticText binding="#{Preview.titleText}" id="titleText" style="font-size: 18px; font-weight: bold" text="#{SessionBean1.movieListDp.value['title']}"/>
                            <webuijsf:staticText binding="#{Preview.yearText}" id="yearText" text="(#{SessionBean1.movieListDp.value['year']})"/>
                            <webuijsf:image binding="#{Preview.imageComponent1}" id="imageComponent1" url="#{SessionBean1.movieListDp.value['image']}"/>
                            <h:panelGrid binding="#{Preview.detailsPanel}" columns="2" id="detailsPanel" style="height: 143px" width="479">
                                <webuijsf:label binding="#{Preview.genreLabel}" id="genreLabel" text="Genre:"/>
                                <webuijsf:staticText binding="#{Preview.genreText}" id="genreText" text="#{SessionBean1.movieListDp.value['genre']}"/>
                                <webuijsf:label binding="#{Preview.ratingLabel}" id="ratingLabel" text="Rating:"/>
                                <webuijsf:staticText binding="#{Preview.ratingText}" id="ratingText" text="#{SessionBean1.movieListDp.value['rating']}"/>
                                <webuijsf:label binding="#{Preview.lengthLabel}" id="lengthLabel" text="Length:"/>
                                <webuijsf:staticText binding="#{Preview.lengthText}" id="lengthText" text="#{SessionBean1.movieListDp.value['length']} minutes"/>
                                <webuijsf:label binding="#{Preview.descriptionLabel}" id="descriptionLabel" text="Description:"/>
                                <webuijsf:staticText binding="#{Preview.descriptionText}" id="descriptionText" text="#{SessionBean1.movieListDp.value['description']}"/>
                            </h:panelGrid>
                        </h:panelGrid>
                        <webuijsf:messageGroup binding="#{Preview.messageGroup1}" id="messageGroup1" style="left: 336px; top: 192px; position: absolute"/>
                        <div style="position: absolute; left: 0px; top: 0px">
                            <jsp:directive.include file="Header.jspf"/>
                        </div>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
