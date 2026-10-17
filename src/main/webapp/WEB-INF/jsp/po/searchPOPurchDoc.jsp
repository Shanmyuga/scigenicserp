<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

 <div width="787px"  style="float:left;padding-left:20px">

<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face">Purchase Orders Pending Approval</font></p>


<form:form modelAttribute="pocommand" name="pocommand">

<div width="787px"  style="float:left;">
<display:table export="true" sort="list" pagesize="10" name="createdpolist" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px"  >

<display:column sortable="true"  title="Select" media="html" >
<form:radiobutton path="scipurchID"  value="${row.seqPurchId}"/>
</display:column>
<display:column sortable="true"  title="Purchase ID" property="seqPurchId" >
</display:column>
<display:column sortable="true"  title="Custom PO ID" property="customPOId" >
</display:column>
<display:column sortable="true"  title="Vendor Detail" property="vendorOrder" >
</display:column>
<display:column sortable="true"  title="Status" >
<c:out value='${lovmap[row.purchaseStatus]}'/>
</display:column>
<display:column sortable="true"  title="PO Created Date" property="purchaseCreatedDt" >
</display:column>
<display:column sortable="true"  title="PO Total Cost" property="poTotalcost" >
</display:column>

</display:table>

</div>
<div style="float:left;width:787px;padding: 10px" >
<input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId" value="addpurchdoc" >
 <input type="submit" value="Add Purchase Order Documents"/>
 </div>

</form:form>
</div>

<div width="787px" style="float:left;height: 150px">



</div>
