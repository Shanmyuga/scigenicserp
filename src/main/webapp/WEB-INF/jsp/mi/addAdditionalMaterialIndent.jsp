<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add  Addtional MI </font></p>
<p style="padding-left:20px" align="center"> Work Order : <c:out value="${workorderbean.jobDesc}" /></p>
<p style="padding-left:20px" align="center"> Client Details : <c:out value="${workorderbean.clientDetails}" /></p>
<div   style="float:left;overflow:auto;width:850px;margin:10px">

<form:form modelAttribute="matindbean" name="matindbean"  >

<table  width="100%" height="70px" border="1" style="border-style:solid;border-width:2px;" >
<tr >
<td align="right" class="datatext">Additional Information</td>
<td align="right" class="datatext">Additional Data</td>




</tr>
<c:forEach items="${addInfoMessages}"  var="addtionalInfoMessage"  varStatus="rowcnt">

<tr>
<td ><c:out value="${addtionalInfoMessage.label}"/></td>
<td>
 <c:if test="${addtionalInfoMessage.datatype == 'Text'}">
 <form:input path="additionalInfoCommandList[${rowcnt.index}].additionalDetailText"  size="20" />
 </c:if>
 <c:if test="${addtionalInfoMessage.datatype == 'Radio'}">
  <c:forEach items="{addInfoMessages.dropValues}" var="drop">
 <form:radiobutton path="additionalInfoCommandList[${rowcnt.index}].additionalDetailText"  value="${drop}">


 </form:radiobutton>
  </c:forEach>
 </c:if>
 <c:if test="${addtionalInfoMessage.datatype == 'DropDown'}">
  <form:select path="additionalInfoCommandList[${rowcnt.index}].additionalDetailText" size="1" id="productCat"  multiple="true">
   <form:option value="" >Select</form:option>


  <c:forEach items="{addInfoMessages.dropValues}" var="drop">
   <form:option value="${drop}" > <c:out value="${drop}"></c:out> </form:option>
  </c:forEach>
  </form:select>
 </c:if>
 <c:if test="${addtionalInfoMessage.datatype == 'File'}">
  <input type="file" name="additionalInfoCommandList[<c:out value='${rowcnt.index}'/>].additionalFile">
 </c:if>
</td>




</tr>

</c:forEach>

<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>
<input type="submit" value="Add Additional Spec Details" />
 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId"  value="addAdditionalMaterialMI">
  <form:hidden path="rowindex"/>  

</div>
</div>



</form:form>
</div>
<script language="javascript">
function loadSpecs(selectbx) {

document.matindbean._eventId.value = 'loadSpecs';
var sbname = selectbx.name;
document.matindbean.rowindex.value = sbname.substring(8,9);

document.matindbean.submit();

}
function loadCategory(selectbx) {

document.matindbean._eventId.value = 'loadCats';
var sbname = selectbx.name;
document.matindbean.rowindex.value = sbname.substring(8,9);

document.matindbean.submit();

}

function addRemarks(eventid) {
document.matindbean._eventId.value = eventid;
document.matindbean.submit();
}



function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.matindbean.submit();
}

</script>

<script>


</script>
