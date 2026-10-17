<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Search Lookup Values</font></p>



<div width="787px"  style="float:left;">
<form:form modelAttribute="lookupvalbean"  name="lookupvalbean" >


 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='filterReport' id="_eventId" >


    <div style="float:left;width:780px;padding:10px">
<table>
    <tr><td colspan="2">
        <label for="reportFilter">Search by Lookup Type / Name / Description: </label></td>  </tr>
    <tr><td>
        <form:input path="reportFilter" size="50" /></td><td>
        <input type="button" value="Search" onclick="eventdirect('filterReport')"/></td>  </tr>

</table>
        <display:table export="true" sort="list"   pagesize="10" name="lookupvalueslist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

            <display:column sortable="true"  title="Lookup Type"  property="lovType" >

            </display:column>
            <display:column sortable="true"  title="Lookup Name"  property="lovName" >

            </display:column>
            <display:column sortable="true"  title="Lookup Description"  property="lovDescription" >

            </display:column>
            <display:column sortable="true"  title="Updated By"  property="updatedBy" >

            </display:column>
            <display:column sortable="true"  title="Updated Date"  property="updatedDt" >

            </display:column>
        </display:table>
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
