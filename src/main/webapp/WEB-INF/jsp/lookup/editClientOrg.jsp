<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div width="787px"  style="float:left;height: 150px;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Edit  Org
Data</font></p>



<div width="787px"  style="float:left;height: 87px;">
<form:form modelAttribute="lookupvalbean" >

<table border="0" width="100%" cellpadding="10x" height="70px">
<tr>
<td align="right" class="datatext">Organization Name</td>
<td ><form:input path="orgName" maxlength="100" size="50"/></td>
<td align="right" class="datatext">Organization Address</td>
<td><form:textarea path="orgAddress"/></td>
    <td align="right" class="datatext">Organization Code</td>
    <td><form:input path="orgCode"/></td>
</tr>

<tr >
<td align="right" >&nbsp;</td>
<td><input type="submit" value="Edit Organization" /></td>
</tr>
<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>

 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='editCustomer' id="_eventId" >
   
</form:form>

</div>



 </div>
<div width="787px" style="float:left;height: 150px">


</div>