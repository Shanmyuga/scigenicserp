<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> View Invoice Details  </font></p>
<p style="padding-left:20px" align="center"></p>
<div width="787px"  style="float:left;">
<form:form modelAttribute="pocommand" name="pocommand" >

<table border="0" width="100%" cellpadding="10x" >



<tr>


<td align="left" class="datatext">Bill Vendor Details</td>
<td ><form:select path="seqVendorId" size="1" id="seqVendorId" >
<form:option value="">Select</form:option>
<form:options items="${vendorlist}" itemValue="seqVendorId" itemLabel="vendorName"/>
</form:select><span style="color:red;">*</span></td>


</tr>



<tr>
<td align="left" class="datatext">After Purchase  Date</td>
<td ><form:input path="purchaseDate" />
<a href="javascript:show_calendar('document.pocommand.purchaseDate', document.pocommand.purchaseDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
<span style="color:red;">*</span>





</td>
<td colspan="2"><input type="button" value="Search Invoice Payment Purchase Details" onclick="eventdirect('searchVoice')" /></td>
</tr>
<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>
<div style="float:left;width:750px;padding:10px;margin-top:50px">

<display:table export="true" sort="list"   pagesize="10" name="vendorpaymentlist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

<display:column sortable="true" title="Vendor Information"  property="vendorName">

</display:column>


<display:column sortable="true" title="Purchase ID"  property="poId">

</display:column>

    <display:column sortable="true" title="Purchase date"  property="purchaseDate" format=" {0,date,dd/MM/yyyy}" >

    </display:column>
    <display:column sortable="true" title="Purchase cost"  property="purchaseCost">

    </display:column>

    <display:column  sortable="true" title="Bill Date" property="invoiceDate"  format=" {0,date,dd/MM/yyyy}">
    </display:column>

    <display:column sortable="true" title="Bill Amt" property="invoiceAmt"  >
    </display:column>

    <display:column sortable="true" title="Bill No" property="invoicePurchaseOrder"  >
    </display:column>
    <display:column sortable="true" title="Payment date"  property="paymentDate" format=" {0,date,dd/MM/yyyy}">

    </display:column>
    <display:column sortable="true" title="Payment Amount"  property="paymentAmt">
    </display:column>

    <display:column sortable="true" title="Payment Remarks"  property="paymentRemarks">

    </display:column>

</display:table>


        <display:table export="true" sort="list"   pagesize="10" name="vendorAgg"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true" title="Vendor Information"  property="vendorName">

            </display:column>





            <display:column sortable="true" title="Purchase cost"  property="purchaseCost" format=" {0,number,0,000.00}">

            </display:column>


            <display:column sortable="true" title="Bill Amt" property="invoiceAmt" format=" {0,number,0,000.00}"  >
            </display:column>



            <display:column sortable="true" title="Payment Amount"  property="paymentAmt" format=" {0,number,0,000.00}">
            </display:column>



        </display:table>
    </div>
<input type="hidden" name="_flowExecutionKey"  value="<c:out value="${flowExecutionKey}"/>" />
 <input type="hidden" name="_eventId"  id="_eventId" value="addInvoiceDetail" >
 

</form:form>
</div>
</div>

<script language="javascript">

function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.pocommand.submit();
}
</script>
