<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ui:personsTemplate pageTitle="pageTitle.addHearingDefectToPerson">
    <h1><fmt:message key="pageTitle.addHearingDefectToPerson"/></h1>

    <c:url value="/people/add-hearing-defect.html?personId=${personDetail.personId}" var="formUrl"/>
    <form:form action="${formUrl}" method="post" commandName="addDefectToPerson" cssClass="standardInputForm">
        <fieldset>

            <input type="hidden" name="subjectId" value="${personDetail.personId}" />

            <div class="itemBox">
                <form:label path="defectId" cssClass="selectBoxLabel" cssErrorClass="selectBoxLabel errorLabel"><fmt:message key="label.hearingDefect"/></form:label>

                <form:select path="defectId" cssClass="selectBox">
                    <form:option value="-1"><fmt:message key='select.option.noHearingDefectSelected'/></form:option>
                    <c:forEach items="${hearingDefectParams}" var="parameter">
                        <form:option value="${parameter.hearingImpairmentId}">${parameter.description}</form:option>
                    </c:forEach>
                </form:select>

                <form:errors path="defectId" cssClass="errorBox" />
            </div>

            <div class="itemBox">
                <input type="submit" value="<fmt:message key='button.addHearingDefectToPerson'/>" class="submitButton lightButtonLink" />
            </div>

        </fieldset>
    </form:form>
</ui:personsTemplate>