<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Search  MI (Set Max results) </font></p>
<p style="padding-left:20px" align="center"></p>
<div width="787px"  style="float:left;">
<form:form modelAttribute="matindbean" name="matindbean">
 
<div width="787px"  style="float:left;">
<table  cellspacing="5" cellpadding="5" align="left" border="0" >

<tr>

<td>Material due date</td>
<fmt:formatDate pattern="dd-MM-yyyy" value="${matindbean.matDuedate}" var="matDuedatestr"/>	
<td><input type="text" name="matDuedate" value="<c:out value="${matDuedatestr}"/>" size="10"/>
<a href="javascript:show_calendar('document.matindbean.matDuedate', document.matindbean.matDuedate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</td>
<td>Approved MI</td>
<td><form:checkbox path="approvalStatus" value="Y"/>
</td>
</tr>
<tr>
<td> Material Dept</td>
<td>
<form:select path="matDept" size="1" id="matDept" onchange="eventdirect('loadCategory')">
<form:option value="">All</form:option>
<form:options items="${matdeptlist}" />
</form:select>
</td>
<td>Material Category</td>
<td  ><form:select path="matCategory" size="1" id="matCategory">
<form:option value="">All</form:option>
<form:options items="${matcatitems}" itemValue="matcatCode" itemLabel="matcatType"/>
</form:select>
</td>
</tr>
<tr>

 

<td>MI created from date</td>
<fmt:formatDate pattern="dd-MM-yyyy" value="${matindbean.fromdate}" var="fromdatestr"/>	
<td><input type="text" name="fromdate" value="<c:out value="${fromdatestr}"/>" size="10"/>
<a href="javascript:show_calendar('document.matindbean.fromdate', document.matindbean.fromdate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</td>

<td>MI created to date</td>
<fmt:formatDate pattern="dd-MM-yyyy" value="${matindbean.todate}" var="todatestr"/>	
<td><input type="text" name="todate" value="<c:out value="${todatestr}"/>" size="10"/>
<a href="javascript:show_calendar('document.matindbean.todate', document.matindbean.todate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</td>
</tr>
<tr>
<td>MI Status </td>
<td  ><form:select path="purStatus" size="1" id="matType">
<form:option value="">All</form:option>
<form:options items="${matstatitems}" itemValue="seqLovId" itemLabel="lovDescription"/>
</form:select>
</td>

<td>MatCode</td>
<td><form:input path="matCodeselected" id="matCodeselected" />
</tr>
<tr>
<td>Work Order</td>
<td><form:select path="seqWorkId"    >
<form:option value="">All</form:option>
<form:options items="${workmastlist}" itemLabel="jobDesc" itemValue="seqWorkId"/>
</form:select>

</td>
<td>Set Max results</td>
<td><form:input path="searchMax" id="searchMax"  />
</td>
</tr>
<tr>
<td>Without Estimated Cost</td>
<td><form:checkbox path="estunitCostStatus" id="estunitCostStatus" value= "N" />
</td>

<td>With Estimated Cost</td>
<td><form:checkbox path="estunitCostStatus" id="estunitCostStatus" value= "Y" />
</td>
</tr>
<tr>
<td>MI ID</td>
<td><form:input path="seqMatindid" id="seqMatindid"  />
</td>
<td colspan="2"> <input  type="button" value="Search MI  Items" onclick="eventdirect('searchMI')"/> </td>

</tr>

</table>

</div>


<div style="float:left;width:750px;padding:10px;margin-top:50px">

<display:table export="true" sort="list"   pagesize="10" name="milist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

<display:column sortable="true"  title='Select' media="html"  >

<form:radiobutton  path="matList[${row_rowNum%10}].matindex"  value="${row_rowNum}" onclick="eventdirect('loadrawmi')"/>

</display:column>

<display:column sortable="true"   title="MI ID"  property="seqMiId">

</display:column>
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

<display:column sortable="true"  title="Created Date"  property="insertedDate" >

</display:column>

<display:column sortable="true"  title="Client Details"   >
<c:out value='${row.sciWorkorderMaster.sciCustomerMaster.customerName}'/>
</display:column>
<display:column sortable="true"  title="Work Order Details"   >
<c:out value='${row.sciWorkorderMaster.jobDesc}'/>
</display:column>
<display:column sortable="true"   title="Material Status"  >
<c:out value='${lovmap[row.purStatus]}'/>
</display:column>
<display:column sortable="true"  title="Request Status"  property="requestStatus" >

</display:column>
<display:column sortable="true"  title="Last Itemized Unit Cost"  property="tempunitCost" >

</display:column>
<display:column sortable="true"  title="Estimated  Unit Cost"  property="estUnintCost" >

</display:column>

<display:column sortable="true"  title="Raw MI"  >

<form:input path="matList[${row_rowNum%10}].rawMis" />

</display:column>

 <display:column sortable="true"  title="Raw MI Qty"  >

  <form:input path="matList[${row_rowNum%10}].rawMIQty" />

 </display:column>


 <display:column sortable="true"  title="Raw MI Dimension"  >

  <form:input path="matList[${row_rowNum%10}].rawMatDimension" />

 </display:column>

 <display:column sortable="true"  title="Raw MI Desc"  >

  <form:textarea path="matList[${row_rowNum%10}].rawMaterialDesc" />

 </display:column>

 <display:column sortable="true"  title="Raw MI UOM"  >

  <form:input path="matList[${row_rowNum%10}].unitOfMeasure" />

 </display:column>

 <display:column sortable="true"  title="Raw MI UnitPrice"  >

  <form:input path="matList[${row_rowNum%10}].unitPrice" />

 </display:column>



 <display:column sortable="true"  title="Raw MI moc"  >

  <form:input path="matList[${row_rowNum%10}].moc" />

 </display:column>
 <display:column sortable="true"  title="Raw MI Remarks"  >

  <form:input path="matList[${row_rowNum%10}].remarks" />

 </display:column>
 <display:column sortable="true"  title="Stage"  >

  <form:select path="matList[${row_rowNum%10}].stageDesc" >
   <form:option value="Stage1">Stage1</form:option>
   <form:option value="Stage2">Stage2</form:option>
   <form:option value="Stage3">Stage3</form:option>
   <form:option value="Stage4">Stage4</form:option>

  </form:select>

 </display:column>
 <display:column sortable="true"  title="Return Dim"  >

  <form:input path="matList[${row_rowNum%10}].retDim" />

 </display:column>
 <display:column sortable="true"  title="Return Qty"  >

  <form:input path="matList[${row_rowNum%10}].retQty" />

 </display:column>
 <display:column sortable="true"  title="Raw MI Vendor"  >

  <form:select path="matList[${row_rowNum%10}].rawSeqVendorId" size="1" >
   <form:option value="">Select</form:option>
   <form:options items="${vendorlist}" itemValue="seqVendorId" itemLabel="vendorName"/>
  </form:select>

 </display:column>
</display:table>

 <c:if test="${fn:length(milist) > 0}" >
  <div style="padding-left:10px;width:787px;float:left">
   <display:table export="true" sort="list"   pagesize="10" name="rawMIDetails"  id="rows"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >




    <display:column sortable="true"   title="SubContractMI" >
     <c:out value="${rows.subcontractMIMaster.seqMiId}" />

    </display:column>
    <display:column sortable="true"   title="Matcode"   >
     <c:out value="${rows.subcontractMIMaster.matcode}" />
    </display:column>

    <display:column sortable="true"   title="matSpec"   >
     <c:out value="${rows.subcontractMIMaster.matSpec}" />
    </display:column>
    <display:column sortable="true"   title="Sub Vendor"   >
     <c:out value="${rows.sciVendorMaster.vendorName}" />
    </display:column>
    <display:column sortable="true"   title="Raw MI"   >
     <c:out value="${rows.rawMIMaster.seqMiId}" />
    </display:column>

    <display:column sortable="true"   title="Raw Matcode"   >
     <c:out value="${rows.rawMIMaster.matcode}" />
    </display:column>
    <display:column sortable="true"   title="Raw Mat Spec"   >
     <c:out value="${rows.rawMIMaster.matSpec}" />
    </display:column>
   </display:table>


   <input type="button"  value="Update Raw MI " onclick="eventdirect('updateCostMI')"/>

  </div>
 </c:if>

</div>
<input type="hidden" name="_flowExecutionKey"  value="<c:out value="${flowExecutionKey}"/>" />
 <input type="hidden" name="_eventId"  id="_eventId" value="submit" >
 




 
</form:form>


</div>
</div>

<script language="javascript">

function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.matindbean.submit();
}
function checkAll(chkbox,status) {

var chklist = document.getElementsByName(chkbox)
for(i =0;i<chklist.length;i++) {
	chklist[i].checked = status;
}


}
</script>
