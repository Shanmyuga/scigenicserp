<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add Project Status for this week </font></p>
<p style="padding-left:20px" align="center"> Work Order : <c:out value="${shortKey}" /></p>


<br/>
<form:form modelAttribute="projschebean" name="projschebean">
<div width="787px"  style="float:left;padding-left:10px;padding-right:600px;margin-top:50px">


<table border="0" width="787px" cellpadding="10x" >
<tr>
<td align="left" class="datatext">Project Phase</td>

<td ><form:select path="phaseDetail"   onchange="eventdirect('loadsubphase')">
<form:option value="">All</form:option>
<form:options items="${phaseslist}"/>
</form:select> </td>
<td align="left" class="datatext">Sub Project Phase</td>

<td ><form:select path="phaseDesc" >
<form:option value="">Select</form:option>
<form:options items="${subphaselist}" itemLabel="subPhaseName" itemValue="subPhaseName"/>
</form:select> </td>

</tr>

<tr>


<td align="left" class="datatext">Sub Phase Estimated End Date</td>
<td ><form:input path="subEstEnd" />

<a href="javascript:show_calendar('document.projschebean.subEstEnd', document.projschebean.subEstEnd.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
<span style="color:red;">*</span>

</td></tr>
    <c:if test="${phaseInfo != 'Marketing-New'}">
<tr>
<td align="left"  class="datatext">Estimated Percentage Complete</td>
<td ><form:input path="completionPercentage" /> <span style="color:red;">*</span>
<td colspan="2">&nbsp;</td>

</tr>
    </c:if>
    <c:if test="${phaseInfo == 'Marketing-New'}">
    <tr>
        <td align="left"  class="datatext">Marketing numbers</td>
        <td ><form:input path="estManhours" /> <span style="color:red;">*</span>
        <td colspan="2">&nbsp;</td>

    </tr>
    </c:if>

    <tr>
        <td align="left"  class="datatext">Major risk</td>
        <td ><form:textarea path="risk"  /><span style="color:red;">*</span>
        <td colspan="2">&nbsp;</td>

    </tr>


<tr>
<td align="left" ><input type="button" value="Add Project  Estimated Dates" onclick="eventdirect('addProj')"/></td>

<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>

 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='addUser' id="_eventId" > 
   

</div>
<div width="787px" style="float:left;padding-left:10px;padding-right:600px;">
<p >Master Task Phases</p>
<display:table export="true" sort="list"   pagesize="10" name="wophasesMaster"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

<display:column sortable="true"   title="Project Phase"  property="phaseDetail">

</display:column>
<display:column sortable="true"  title="SubPhase Task"  property="phaseDesc">

</display:column>
<display:column sortable="true"    property="risk" >

</display:column>

<display:column sortable="true"  title="CompletionPercentage"  property="completionPercentage" >

</display:column>
<display:column sortable="true"  title="Marketing Hours"  property="estManhours" >

</display:column>
<display:column sortable="true"  title="Updated Date"  property="updatedDate">

</display:column>
<display:column sortable="true"  title="Updated By"  property="updatedBy">

</display:column>
</display:table>



</div>


<form:hidden path="woTrackId"/>
</form:form>
</div>
      <script language="javascript">

function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.projschebean.submit();
}

function loadsubtasks(radiobject) {

document.getElementById('woTrackId').value = radiobject.value;
document.getElementById('_eventId').value = 'loadsubtasks';

document.projschebean.submit();
}
</script>  