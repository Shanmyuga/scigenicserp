<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add  New Report 
Data</font></p>



<div width="787px"  style="float:left;">


<form:form modelAttribute="lookupvalbean" name="lookupvalbean" >


 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId" value='addNewReport' id="_eventId" > 
   


</div>


<div width="787px" style="float:left">

        <label for="tags">Report Filter: </label>
        <form:input path="reportFilter" size="100" />

        <p><input type="button" value="Filter report" onclick="eventdirect('filterReport')"/>&nbsp;&nbsp;</p>

   <br/>
    <br/>
    <br/>

    <br/>
    <br/><br/>
    <br/>


<p> List of Reports</p>
<c:if test="${fn:length(reportsdata) > 0 }">
<display:table export="true" sort="list"   pagesize="10" name="reportsdata"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >
<display:column sortable="true"  title="Select" media="html" >
<form:radiobutton path="seqReportID"  value='${row.seqRepConfigId}'></form:radiobutton>

</display:column>

<display:column sortable="true"   title="Report Name"   property="reportSubject">

</display:column>
<display:column sortable="true"   title="Report Query"  ><a href="" title="<c:out value='${row.reportQuery}'/>">Report Query</a>

</display:column>
<display:column sortable="true"  title="ReportEmails"  property="reportEmail">

</display:column>

<display:column sortable="true"  title="Report Schedule"  property="reportSchedule">

</display:column>

<display:column sortable="true"  title="Report Status"  property="reportStatus">

</display:column>
</display:table>

<br>
<p><input type="button" value="Send Report By email " onclick="eventdirect('runSelectedReport')"/></p>
    <p><input type="button" value="View Report" onclick="eventdirect('viewSelectedReport')"/></p>
</c:if>

</div>
     </form:form>






<script  language="javascript">
function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.lookupvalbean.submit();
}

</script>
    <script>
        $( function() {
            var temp = "<c:out value='${reportNames}'/>";
            var availableTags =temp.split('|');
            $( "#reportFilter" ).autocomplete({
                source: availableTags
            });
        } );
    </script>
