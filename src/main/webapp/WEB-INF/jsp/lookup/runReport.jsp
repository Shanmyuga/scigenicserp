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



<p> List of Reports</p>

<display:table export="true" sort="list"   pagesize="10" name="ViewReportsData"  id="item"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >
    <c:forEach var="cl" items="${item.beans }" varStatus="index">
        <display:column title="${cl.title}"    >
            <display:table uid="b" name="${cl.value}">
                <c:out value="${b}"></c:out>
            </display:table>
        </display:column>
    </c:forEach>
</display:table>

<br>
<p><input type="button" value="Back to Reports" onclick="eventdirect('backToReports')"/></p>


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
