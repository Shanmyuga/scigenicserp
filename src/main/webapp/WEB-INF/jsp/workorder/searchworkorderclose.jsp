<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

 <div width="787px"  style="float:left;padding-left:20px">
 
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face">Search Work Order</font></p>


<form:form modelAttribute="workorderbean" name="workorderbean">


 <label for="tags">Search Work order  by Job Desc: </label>
 <form:input path="reportFilter" size="100" />

 <p><input type="button" value="Filter report" onclick="eventdirect('filterReport')"/>&nbsp;&nbsp;</p>

 <br/>
 <br/>
 <br/>

 <br/>
 <br/><br/>
 <br/>
<div width="787px"  style="float:left;">
<display:table export="true" sort="list"  pagesize="10" name="workorderlist" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px"  >

<display:column sortable="true"  title="Select" media="html" >
<form:radiobutton path="windex"  value="${row_rowNum}"/>

</display:column>
<display:column sortable="true"  property="clientDetails" >

</display:column>
 <display:column sortable="true"  property="miCloseDate" >

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
 <p>
  <label>New MI Close Date</label>
 <form:input path="miCloseDate" />
 <a href="javascript:show_calendar('document.workorderbean.miCloseDate', document.workorderbean.miCloseDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
 </p>
<input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId" value="closeWo" >
 <input type="button" name="close" value="Extend MI Date" onclick="javascript:eventD('extendMICloseDate')"/>
 <input type="button" name="close" value="Close Work Order" onclick="javascript:eventD('closeWo')"/>
  <input type="button" name="dormant" value="Inoperative Work Order" onclick="javascript:eventD('dormantWo')"/>


 </div>
 
</form:form>

<script language="javascript">
function eventD(event) {

document.getElementById('_eventId').value = event;

document.getElementById('workorderbean').submit();
}

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
</div>

<div width="787px" style="float:left;height: 150px">



</div>
