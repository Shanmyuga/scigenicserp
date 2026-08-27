<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

 <div width="787px"  style="float:left;padding-left:20px">
 
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face">Search Work Order</font></p>


<form:form modelAttribute="workorderbean"  name="workorderbean" >

 <label for="tags">Select Workorder by Job desc: </label>
 <form:input path="reportFilter" size="100" />

 <p><input type="button" value="Filter Client" onclick="eventdirect('filterReport')"/>&nbsp;&nbsp;</p>

 <br/>
 <br/>
 <br/>

 <br/>
 <br/><br/>
 <br/>


 <div width="787px"  style="float:left;">
<display:table export="true" sort="list"  pagesize="10" name="workorderlist" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px">

<display:column sortable="true"  title="Select" media="html" >
<form:radiobutton path="windex"  value="${row_rowNum}"/>

</display:column>
<display:column sortable="true"  property="clientDetails" >

</display:column>
<display:column sortable="true"  property="jobDesc" >

</display:column>
<display:column sortable="true"  property="workCreateDt" >

</display:column>
<display:column sortable="true"  property="wordOrderType" >

</display:column>

</display:table>

</div>
<div style="float:left;width:787px;padding: 10px" >
<input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId" value="addamend" >
 <c:if test="${userPreferences.roleName == 'marketing' || userPreferences.roleName == 'marketingoffice'}">
 <input type="submit" value="Add Amendment"/>
 </c:if>
 <input type="button" value="View Work Order Details" onclick="eventdirect('viewwodetails')"/>
 </div>

</form:form>
</div>

<c:if test="${not empty selectedwo}">
<div style="float:left;width:787px;padding: 10px">
<p style="padding-left:20px"><font color="#0080ff" size="3" face="Baskerville Old Face">Work Order Details</font></p>
<table border="1" width="100%" cellpadding="3px">
<tr>
<td align="right" class="datatext" width="30%">Old Work Order Id</td>
<td><c:out value="${selectedwo.oldWordId}"/></td>
</tr>
<tr>
<td align="right" class="datatext">PO Number</td>
<td><c:out value="${selectedwo.poNumber}"/></td>
</tr>
<tr>
<td align="right" class="datatext">PO Date</td>
<td><fmt:formatDate pattern="dd-MM-yyyy" value="${selectedwo.poDate}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Date of Delivery as Per PO</td>
<td><fmt:formatDate pattern="dd-MM-yyyy" value="${selectedwo.datePerPo}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Delivery Address</td>
<td><c:out value="${selectedwo.deliveryAdd}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Packing</td>
<td><c:out value="${selectedwo.packing}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Insurance</td>
<td><c:out value="${selectedwo.insurance}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Frieght</td>
<td><c:out value="${selectedwo.frieght}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Transporter</td>
<td><c:out value="${selectedwo.transportation}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Octroi</td>
<td><c:out value="${selectedwo.octroi}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Road Permit</td>
<td><c:out value="${selectedwo.roadPermit}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Sales Tax</td>
<td><c:out value="${selectedwo.salesTax}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Excise Duty</td>
<td><c:out value="${selectedwo.exciseDuty}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Inspection By</td>
<td><c:out value="${selectedwo.inspectionBy}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Basis</td>
<td><c:out value="${selectedwo.basis}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Prepared By</td>
<td><c:out value="${selectedwo.preparedBy}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Approved By</td>
<td><c:out value="${selectedwo.approvedBy}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Proposal Work Order Type</td>
<td><c:out value="${selectedwo.propWoType}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Proposal Work Order Volume</td>
<td><c:out value="${selectedwo.propWoVolume}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Installation Workorder Short Key</td>
<td><c:out value="${selectedwo.installationWoShortkey}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Work Order Status</td>
<td><c:out value="${selectedwo.woStatus}"/></td>
</tr>
<tr>
<td align="right" class="datatext">MI Close Date</td>
<td><fmt:formatDate pattern="dd-MM-yyyy" value="${selectedwo.miCloseDate}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Work Order Close Date</td>
<td><fmt:formatDate pattern="dd-MM-yyyy" value="${selectedwo.woCloseDate}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Updated By</td>
<td><c:out value="${selectedwo.updatedBy}"/></td>
</tr>
<tr>
<td align="right" class="datatext">Updated Date</td>
<td><fmt:formatDate pattern="dd-MM-yyyy" value="${selectedwo.updatedDt}"/></td>
</tr>
</table>
</div>
</c:if>

<div width="787px" style="float:left;height: 150px">



</div>
<script  language="javascript">
    function eventdirect(event) {

        document.getElementById('_eventId').value = event;

        document.workorderbean.submit();
    }

</script>
<script>
    $( function() {
        var temp = "<c:out value='${workorderlistNames}'/>";
        var availableTags =temp.split('|');
        $( "#reportFilter" ).autocomplete({
            source: availableTags
        });
    } );
</script>
