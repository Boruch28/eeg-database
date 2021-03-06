<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="auth" tagdir="/WEB-INF/tags/auth/" %>

<ui:experimentsTemplate pageTitle="pageTitle.experimentDetail">
  <script src="<c:url value="/files/js/visualization/browserControl.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/files/js/visualization/visualization.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/files/js/visualization/dhtmlxcommon.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/files/js/visualization/dhtmlxtree.js"/>" type="text/javascript"></script>

    <div id="popupContact">
        <a id="popupContactClose">x</a>
        <h1><fmt:message key="pageTitle.unsupportedBrowser"/></h1>
        <div id="contactArea">
            <p>
                <fmt:message key="text.unsupportedBrowser.intro"/>
                <br/><br/>
                <fmt:message key="text.unsupportedBrowser.description"/>
            </p>
            <div class="center">
                <a href="http://www.opera.com" title="Opera"><img class="browserIco" src="<c:url value="/files/images/ico_opera.png"/>" alt="Opera"/></a>
                <a href="http://www.google.com/chrome" title="Chrome"><img class="browserIco" src="<c:url value="/files/images/ico_chrome.png"/>" alt="Chrome"/></a>
                <a href="http://www.mozilla.com" title="Firefox"><img class="browserIco" src="<c:url value="/files/images/ico_firefox.png"/>" alt="Firefox"/></a>
                <a href="http://www.apple.com/safari/" title="Safari"><img class="browserIco" src="<c:url value="/files/images/ico_safari.png"/>" alt="Safari"/></a>
                <a href="http://www.microsoft.com/windows/products/winfamily/ie/default.mspx" title="Internet Explorer"><img class="browserIco" src="<c:url value="/files/images/ico_ie.png"/>" alt="Internet Explorer"/></a>
            </div>
        </div>
    </div>
    <div id="backgroundPopup"></div>

  <h1><fmt:message key="pageTitle.experimentDetail"/></h1>

  <table class="standardValueTable">
    <tr>
      <th><fmt:message key="label.measurationId"/></th>
      <td><c:out value="${experimentDetail.experimentId}"/></td>
    </tr>
    <tr>
      <th><fmt:message key="label.beginningOfMeasuration"/></th>
      <td><fmt:formatDate value="${experimentDetail.startTime}" pattern="dd.MM.yyyy, HH:mm" /></td>
    </tr>
    <tr>
      <th><fmt:message key="label.endOfMeasuration"/></th>
      <td><fmt:formatDate value="${experimentDetail.endTime}" pattern="dd.MM.yyyy, HH:mm" /></td>
    </tr>
    <tr>
      <th><fmt:message key="label.temperature"/></th>
      <td><c:out value="${experimentDetail.temperature}"/></td>
    </tr>
    <tr>
      <th><fmt:message key="label.weather"/></th>
      <td><c:out value="${experimentDetail.weather.title}"/></td>
    </tr>
    <tr>
      <th><fmt:message key="label.weatherNote"/></th>
      <td><c:out value="${experimentDetail.environmentNote}"/></td>
    </tr>
    <tr>
      <th><fmt:message key="label.private" /></th>
      <td><c:out value="${experimentDetail.privateExperiment}"/></td>
    </tr>
  </table>

  <h2><fmt:message key="heading.subjectPerson"/></h2>
  <table class="standardValueTable">
    <tr>
      <th><fmt:message key="label.gender"/></th>
      <td>
        <c:if test="${experimentDetail.personBySubjectPersonId.gender == 'M'}">
          <fmt:message key="label.gender.male"/>
        </c:if>
        <c:if test="${experimentDetail.personBySubjectPersonId.gender == 'F'}">
          <fmt:message key="label.gender.female"/>
        </c:if>
      </td>
    </tr>
    <tr>
      <th><fmt:message key="label.yearOfBirth"/></th>
      <td><fmt:formatDate value="${experimentDetail.personBySubjectPersonId.dateOfBirth}" pattern="yyyy" /></td>
    </tr>
    <c:if test="${userCanViewPersonDetails}">
      <tr>
        <td colspan="2"><a href="<c:url value='/people/detail.html?personId=${experimentDetail.personBySubjectPersonId.personId}' />"><fmt:message key="link.viewDetailOfPerson"/></a></td>
      </tr>
    </c:if>
  </table>

  <h2><fmt:message key="heading.scenario"/></h2>
  <table class="standardValueTable">
    <tr>
      <th><fmt:message key="label.scenarioTitle"/></th>
      <td><c:out value="${experimentDetail.scenario.title}"/></td>
    </tr>
    <tr>
      <td colspan="2"><a href="<c:url value='/scenarios/detail.html?scenarioId=${experimentDetail.scenario.scenarioId}' />"><fmt:message key="link.viewDetailOfScenario"/></a></td>
    </tr>
  </table>

  <h2><fmt:message key="heading.usedHardware"/></h2>
  <table class="dataTable" style="width: 450px;">
    <thead>
      <tr>
        <th style="width: 250px;"><fmt:message key="dataTable.heading.hardwareTitle"/></th>
        <th><fmt:message key="dataTable.heading.hardwareType"/></th>
      </tr>
    </thead>
    <c:forEach items="${experimentDetail.hardwares}" var="usedHardware">
      <tr>
        <td><c:out value="${usedHardware.title}"/></td>
        <td><c:out value="${usedHardware.type}"/></td>
      </tr>
    </c:forEach>
  </table>

  <h2><fmt:message key="heading.optionalParameters"/></h2>
  <table class="dataTable" style="width: 450px;">
    <thead>
      <tr>
        <th style="width: 250px;"><fmt:message key="dataTable.heading.measurationOptionalParamName"/></th>
        <th><fmt:message key="dataTable.heading.measurationOptionalParamValue"/></th>
      </tr>
    </thead>
    <c:forEach items="${experimentDetail.experimentOptParamVals}" var="additionalParameter">
      <tr>
        <td><c:out value="${additionalParameter.experimentOptParamDef.paramName}"/></td>
        <td><c:out value="${additionalParameter.paramValue}"/></td>
      </tr>
    </c:forEach>
  </table>
  <h2><fmt:message key="heading.dataFiles"/></h2>
  <table class="dataTable" style="width: 500px;">
    <thead>
      <tr>
        <th style="width: 250px;"><fmt:message key="dataTable.heading.fileName"/></th>
        <th style="width: 150px;"><fmt:message key="dataTable.heading.description"/></th>
        <th><!-- column with link for detail --></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${experimentDetail.dataFiles}" var="dataItem">
        <tr>
          <td><c:out value="${dataItem.filename}"/></td>
          <td><c:out value="${dataItem.description}"/></td>
          <td><a href="<c:url value='data/detail.html?fileId=${dataItem.dataFileId}' />"><fmt:message key="link.detail"/></a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <div class="actionBox">
    <c:if test="${userIsOwnerOrCoexperimenter}">
      <a href="<c:url value='add-optional-parameter.html?experimentId=${experimentDetail.experimentId}' />" class="lightButtonLink"><fmt:message key="button.addOptionalParameter"/></a>
      <a href="<c:url value='data/add.html?experimentId=${experimentDetail.experimentId}' />" class="lightButtonLink"><fmt:message key="button.addDataFile"/></a>
      <a href="<c:url value='edit.html?id=${experimentDetail.experimentId}' />" class="lightButtonLink"><fmt:message key="button.editExperiment"/></a>
    </c:if>
    <a href="<c:url value='choose-metadata.html?id=${experimentDetail.experimentId}' />" class="lightButtonLink"><fmt:message key="button.downloadExperiment"/></a>
  </div>
    <c:if test="${filesAvailable}">

        <span id="show" title="<fmt:message key="text.visualization.toggle"/>" onclick="toggleDiv('visualization')"><fmt:message key="link.toggleVisualization"/></span>
        <div id="visualization" style="display:none; width: 730px;">
            <div id="treeboxbox_tree" style="width: 110px; background-color: #f5f5f5; border: 1px solid Silver; float: right;"></div>
            <div id="encaps" style="width: 600px; height: 430px; overflow: auto;">
                <canvas id="canvas" width="1040" height="400"></canvas>
            </div>
            <script type="text/javascript">
                tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
                tree.setSkin('dhx_skyblue');
                tree.setImagePath("<c:url value='/files/images/imgs/'/>");
                tree.enableDragAndDrop(false);
                tree.setOnClickHandler(tonclick);
                tree.deleteChildItems(0);
                tree.insertNewChild(0, 1, "Channels");
                <c:forEach items="${channels}" var="channel" varStatus="counter">
                    tree.insertNewChild(1,10 + <c:out value="${counter.count}"/>,"<c:out value="${channel.name}"/>");
                </c:forEach>
            </script>
            <script type="text/javascript">
            <c:forEach items="${signalData}" varStatus="index">
                createArray(<c:out value="${index.count}" />);
                <c:forEach items="${signalData[index.count]}" varStatus="index2">
                    <c:if test="${not empty signalData[index.count][index2.count]}">
                        addToArray(<c:out value="${index.count}"/>, <c:out value="${index2.count}"/>, <c:out value="${signalData[index.count][index2.count]}" />);
                    </c:if>
                </c:forEach>
            </c:forEach>
            </script>
            <p>
                <input id="playbtn" type="button" value="Play" onclick="javascript:animationStart()" class="grey" />
                <input id="pausebtn" type="button" value="Pause" onclick="javascript:animationPause()" class="grey" />
                <input id="stopbtn" type="button" value="Stop" onclick="javascript:animationStop()" class="grey" />
                <label id="slider_label" for="slider">Speed: </label>
                <input id="slider" type="range" min="1" max="10" value="5" onchange="javascript:setSpeed()"/><span id="pause_value"></span>
                <select name="export" onchange="exportAs(this.value)">
                    <option value="0" selected="selected">Export as...</option>
                    <option value="1">PNG</option>
                    <option value="2">JPEG</option>
                </select>
            </p>
        </div>
    </c:if>
</ui:experimentsTemplate>
