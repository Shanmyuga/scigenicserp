<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Select Customer for Enquiry
Data</font></p>



<div width="787px"  style="float:left;">
<form:form modelAttribute="lookupvalbean"  name="lookupvalbean" >


 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='addlookup' id="_eventId" >


    <div style="float:left;width:780px;padding:10px">

        <display:table export="true" sort="list"   pagesize="5" name="selectedCustomers"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true"  title="Select"  >

                <form:radiobutton  path="seqCustId" value="${row.seqCustId}"  />

            </display:column>



            <display:column sortable="true"    property="customerAddress" >

            </display:column>
            <display:column sortable="true"    property="customerContact" >

            </display:column>
            <display:column sortable="true"    property="customerPhone" >

            </display:column>
            <display:column sortable="true"    property="customerEmail" >

            </display:column>

            <display:column sortable="true"    property="cusomterCity" >

            </display:column>
            <display:column sortable="true"    property="customerCityCode" >

            </display:column>
            <display:column sortable="true"    property="customerCode" >

            </display:column>

            <display:column sortable="true"    property="customerStatus" >

            </display:column>
        </display:table>

        <input type="button" value="Add New Enquiry"  onclick="eventdirect('selectCustomer')" />
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