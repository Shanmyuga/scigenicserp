<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div width="787px"  style="float:left;">
<br>




<div width="787px"  style="float:left;">
<form:form modelAttribute="lookupvalbean"  name="lookupvalbean" >


 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='addlookup' id="_eventId" >



    <table style="float:left;width: 100%">
        <tr><td colspan="1">
            <label for="tags">Select Organization by Name: </label></td>  </tr>
        <tr><td colspan="2">
            <form:input path="reportFilter" size="100" /></td><td>
            <input type="button" value="Filter Client" onclick="eventdirect('filterReport')"/></td>  </tr>
        <tr><td colspan="2">
            <label for="tags">Customer State </label></td>  </tr>
        <tr>   <td colspan="2">
                <form:select path="stateCode"  id="stateCode"  >
                    <form:option value="">All</form:option>
                    <form:options items="${states}"  itemLabel="lovName" itemValue="lovType"/>
                </form:select>
            </td>
        </tr>
    </table>

    <div style="float:left;width:780px;padding:10px">

        <display:table export="true" sort="list"   pagesize="10" name="clientorglist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true"  title="Select" media="html" >

                <form:radiobutton  path="seqClientOrgId"  value="${row.seqClientOrgId}"/>

            </display:column>


            <display:column sortable="true"  title="Organization Name"  property="orgName" >

            </display:column>
            <display:column sortable="true"    property="orgAddress" >

            </display:column>
            <display:column sortable="true"    property="orgCode" >

            </display:column>
        </display:table>

        <input type="button" value="Search Customer"  onclick="eventdirect('searchCustomer')" />
    </div>
</div>

    <div style="float:left;width:780px;padding:10px">

        <display:table export="true" sort="list"   pagesize="10" name="selectedCustomers"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >




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
        </display:table>


    </div>
    </div>





</form:form>


<script language="javascript">

    function eventdirect(event) {

        document.getElementById('_eventId').value = event;

        document.lookupvalbean.submit();
    }
</script>

<script>
    $( function() {
        var temp = "<c:out value='${clientOrgNames}'/>";
        var availableTags =temp.split('|');
        $( "#reportFilter" ).autocomplete({
            source: availableTags
        });
    } );
</script>