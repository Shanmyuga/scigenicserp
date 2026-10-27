<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Create Purchase Item </font></p>
<p style="padding-left:20px" align="center"></p>
<div width="787px"  style="float:left;">
<form:form modelAttribute="itemcommand" name="itemcommand" >

<table border="0" width="100%" cellpadding="10x" >



<tr>
<td align="left" class="datatext">Item Description</td>
<td ><c:out value="${itemcommand.itemDescription}" escapeXml="true"/></td>



<td align="left" class="datatext">Item Due Date</td>
<td ><form:input path="itemOrderDate" />
<a href="javascript:show_calendar('document.itemcommand.itemOrderDate', document.itemcommand.itemOrderDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
<span style="color:red;">*</span>





</td>
</tr>

<tr>
<td align="left" class="datatext">Item Dimension</td>
<td ><form:input path="itemDimen" /><span style="color:red;">*</span></td>
<td align="left" class="datatext">Item Description</td>
<td ><form:input path="itemDescription" /><span style="color:red;">*</span></td>


</tr>
<tr>
<td align="left" class="datatext">Item Unit of Measurement</td>
<td><form:select path="itemDenom" id="itemDenom" >
<form:option value=""></form:option>
<form:options items="${denomlist}" itemValue="lovDescription" itemLabel="lovDescription"/>
</form:select></td>
<td colspan="2">&nbsp;</td>

</tr>
<tr>
<td align="left" class="datatext" valign="top">Raw MI Details</td>
<td colspan="3">
<table id="rawMiTable" border="0" cellpadding="3px">
<tr>
<td class="datatext">Raw MI ID</td>
<td class="datatext">Raw MI Quantity</td>
<td class="datatext">Raw MI Returnable Quantity</td>
<td>&nbsp;</td>
</tr>
<c:choose>
<c:when test="${not empty itemcommand.rawMiIds}">
<c:forEach items="${itemcommand.rawMiIds}" var="rawMiId" varStatus="status">
<tr>
<td><input type="text" name="rawMiIds" size="10" value="<c:out value='${rawMiId}'/>"/></td>
<td><input type="number" step="any" min="0" name="rawMiQty" size="10" value="<c:out value='${itemcommand.rawMiQty[status.index]}'/>"/></td>
<td><input type="number" step="any" min="0" name="rawMiReturnQty" size="10" value="<c:out value='${itemcommand.rawMiReturnQty[status.index]}'/>"/></td>
<td><input type="button" value="Remove" onclick="removeRawMiRow(this)"/></td>
</tr>
</c:forEach>
</c:when>
<c:otherwise>
<tr>
<td><input type="text" name="rawMiIds" size="10"/></td>
<td><input type="number" step="any" min="0" name="rawMiQty" size="10"/></td>
<td><input type="number" step="any" min="0" name="rawMiReturnQty" size="10"/></td>
<td><input type="button" value="Remove" onclick="removeRawMiRow(this)"/></td>
</tr>
</c:otherwise>
</c:choose>
</table>
<input type="button" value="Add Raw MI" onclick="addRawMiRow()"/>
<input type="button" value="Load Raw Material Cost" onclick="eventdirect('loadRawMiCost')"/>
<c:if test="${rawMaterialCost != null}">
<span class="datatext">Raw Material Cost: <c:out value="${rawMaterialCost}"/></span>
</c:if>
</td>
</tr>
<tr>
<td colspan="3" align="right" >&nbsp;</td>
<td><input type="submit" value="Create New Item" /></td>
</tr>
<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>


<div style="float:left;width:787px;padding-left:10px;margin-top:50px">

<display:table export="true" sort="list"  pagesize="10" name="itemmilist"  id="row"  requestURI="springtest.htm" cellpadding="5px" cellspacing="5px">


<display:column sortable="true"   title="Material Code" property="matcode" >

</display:column>
<display:column sortable="true"  title="Material Specs"  property="matSpec" >

</display:column>
<display:column sortable="true"  title="Material Cat"  property="matType" >

</display:column>

<display:column sortable="true"  title="Qty"  property="matQty">

</display:column>
<display:column sortable="true"  title="Dimen"  property="matDimesion">

</display:column>

<display:column sortable="true"  title="Due Date"  property="matDuedate" >

</display:column>

<display:column sortable="true"  title="Additional Info"  >
<c:out value="${addInfoTextByMi[row.seqMiId]}" />
</display:column>
<c:forEach items="${itemAddInfoLabels}" var="addInfoLabel">
<display:column sortable="false"  title="${addInfoLabel}"  >
<c:out value="${addInfoByMi[row.seqMiId][addInfoLabel]}" />
</display:column>
</c:forEach>

<display:column sortable="true"  title="Previous Estimated  Unit Cost" property="tempunitCost" >

</display:column>
<%-- plain inputs indexed by row, so values loaded by "Load Raw Material Cost" show in their own row --%>
<display:column sortable="true"  title="Modified Quantity"  >
<input type="text" name="miquantity" value="<c:out value='${itemcommand.miquantity[row_rowNum - 1]}'/>" />
</display:column>
<display:column sortable="true"  title="Estimated  Unit Cost"  >
<input type="text" name="micost" value="<c:out value='${itemcommand.micost[row_rowNum - 1]}'/>" />
</display:column>

<display:column sortable="true"  title="Labor Cost"  >
<input type="text" name="milaborcost" value="<c:out value='${itemcommand.milaborcost[row_rowNum - 1]}'/>" />
</display:column>

<display:column sortable="true"  title="Recommendation"  >
<input type="text" name="miremarks" value="<c:out value='${itemcommand.miremarks[row_rowNum - 1]}'/>" />
</display:column>
</display:table>

    <display:table export="true" sort="list"  pagesize="10" name="childMiList"  id="row"  requestURI="springtest.htm" cellpadding="5px" cellspacing="5px">/8-5



        <display:column sortable="true"   title="Material Code" property="matcode" >

        </display:column>
        <display:column sortable="true"  title="Material Specs"  property="matSpec" >

        </display:column>
        <display:column sortable="true"  title="Material Cat"  property="matType" >

        </display:column>

        <display:column sortable="true"  title="Qty"  property="matQty">

        </display:column>
        <display:column sortable="true"  title="Dimen"  property="matDimesion">

        </display:column>

        <display:column sortable="true"  title="Due Date"  property="matDuedate" >

        </display:column>


        <display:column sortable="true"  title="Cost Percentage"  >

            <input type="text" name="miCostPercentage" value="<c:out value='${itemcommand.miCostPercentage[row_rowNum - 1]}'/>" />
        </display:column>


        <display:column sortable="true"  title="Recommendation"  >
            <input type="text" name="childMiRemarks" value="<c:out value='${itemcommand.childMiRemarks[row_rowNum - 1]}'/>" />
        </display:column>
    </display:table>
</div>


    <div style="float:left;width:787px;padding-left:10px;margin-top:50px">


    </div>
 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId"  value="addItem"> 
 
</form:form>
</div>
</div>

<script language="javascript">

function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.itemcommand.submit();
}

function addRawMiRow() {
var table = document.getElementById('rawMiTable');
var row = table.insertRow(-1);
row.insertCell(0).innerHTML = '<input type="text" name="rawMiIds" size="10"/>';
row.insertCell(1).innerHTML = '<input type="number" step="any" min="0" name="rawMiQty" size="10"/>';
row.insertCell(2).innerHTML = '<input type="number" step="any" min="0" name="rawMiReturnQty" size="10"/>';
row.insertCell(3).innerHTML = '<input type="button" value="Remove" onclick="removeRawMiRow(this)"/>';
}

function removeRawMiRow(button) {
var row = button.parentNode.parentNode;
row.parentNode.removeChild(row);
}
</script>
