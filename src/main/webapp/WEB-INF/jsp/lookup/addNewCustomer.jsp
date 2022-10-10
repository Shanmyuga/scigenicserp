<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add  New Customer 
Data</font></p>



<div width="787px"  style="float:left;">
<form:form modelAttribute="lookupvalbean"  name="lookupvalbean" >

<table border="0" width="100%" cellpadding="10x" height="70px">
<tr>
<td align="right" class="datatext">Org Name</td>
<td ><c:out value="${selectedClientOrg.orgName}"/></td>
<td align="right" class="datatext">Org Address</td>
<td><c:out value="${selectedClientOrg.orgAddress}"/></td>
</tr>
    <tr>
        <td align="right" class="datatext">Customer Contact</td>
        <td><form:input path="customerContact" maxlength="100" size="50"/></td>
        <td  align="right" class="datatext">Customer City</td>
        <td><form:input path="cusomterCity" maxlength="100" size="50"/></td>
    </tr>
    <tr>
        <td align="right" class="datatext">Customer Address</td>
        <td><form:input path="customerAddress" maxlength="100" size="50"/></td>
        <td  align="right" class="datatext">Customer City Code</td>
        <td><form:select path="customerCityCode"  id="customerCityCode"  >

            <form:options items="${stateCityList}"  itemLabel="cityName" itemValue="cityCode"/>
        </form:select></td>
    </tr>
<tr>
<td class="datatext" align="right">Customer State</td>
<td>
<form:select path="stateCode"  id="stateCode" onchange="eventdirect('loadCity')" >

<form:options items="${states}"  itemLabel="lovName" itemValue="lovType"/>
</form:select>
</td>

<td  align="right" class="datatext">Customer Country</td>
<td><form:input path="customerCountry" maxlength="50" size="50"/></td>
</tr>
<tr>
<td align="right" class="datatext">Customer Phone</td>
<td><form:input path="customerPhone" maxlength="50" size="50"/></td>

<td align="right" class="datatext">Customer Email</td>
<td><form:input path="customerEmail" maxlength="50" size="50"/></td>
</tr>

<tr >
<td align="right" >&nbsp;</td>
<td><input type="submit" value="Add New Customer" /></td>
</tr>
<form:errors >
<c:forEach items="${messages}" var="message">
		<tr><td colspan="2"><c:out value="${message}"></c:out></td></tr>
	</c:forEach>

</form:errors>
</table>

 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='addlookup' id="_eventId" >


    <div style="float:left;width:780px;padding:10px">

        <display:table export="true" sort="list"   pagesize="5" name="selectedCustomers"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true"  title="Select"  >

                <form:radiobutton  path="seqCustId" value="${row.seqCustId}"  />

            </display:column>


            <display:column sortable="true"    property="customerContact" >

            </display:column>


            <display:column sortable="true"    property="customerPhone" >

            </display:column>
            <display:column sortable="true"    property="customerEmail" >

            </display:column>

            <display:column sortable="true"    property="cusomterCity" >

            </display:column>

            <display:column sortable="true"    property="customerCode" >

            </display:column>
            <display:column sortable="true"    property="customerStatus" >

            </display:column>
        </display:table>

        <input type="button" value="Edit Customer"  onclick="eventdirect('selectCustomer')" />
    </div>
</div>


    </div>





</form:form>


<script language="javascript">

    function eventdirect(event) {

        document.getElementById('_eventId').value = event;

        document.lookupvalbean.submit();
    }
</script>