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
<td align="right" class="datatext">Client Organization Name</td>
<td ><form:input path="orgName" maxlength="100" size="50"/></td>
<td align="right" class="datatext">Client Organization Address</td>
<td><form:textarea path="orgAddress"/></td>
</tr>

    <tr >
        <td align="right" >&nbsp;</td>
        <td><input type="submit" value="Add New Client Organization" /></td>
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
<table>
    <tr><td colspan="2">
        <label for="tags">Select Organization by Name: </label></td>  </tr>
    <tr><td>
        <form:input path="reportFilter" size="100" /></td><td>
        <input type="button" value="Filter Client" onclick="eventdirect('filterReport')"/></td>  </tr>

</table>
        <display:table export="true" sort="list"   pagesize="10" name="clientorglist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true"  title="Select" media="html" >

                <form:radiobutton  path="seqClientOrgId"  value="${row.seqClientOrgId}"/>

            </display:column>


            <display:column sortable="true"  title="Organization Name"  property="orgName" >

            </display:column>
            <display:column sortable="true"    property="orgAddress" >

            </display:column>

        </display:table>

        <input type="button" value="Edit Organization"  onclick="eventdirect('editLookup')" />
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

<script>
    $( function() {
        var temp = "<c:out value='${clientOrgNames}'/>";
        var availableTags =temp.split('|');
        $( "#reportFilter" ).autocomplete({
            source: availableTags
        });
    } );
</script>