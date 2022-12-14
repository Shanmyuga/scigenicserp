<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

 <div width="787px"  style="float:left;padding-left:20px">
 
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face">Search Work Order</font></p>


<form:form modelAttribute="workorderbean" name="workorderbean">





 <br/>
 <br/>

 <br/>
 <br/><br/>
 <br/>
<div width="787px"  style="float:left;">
<display:table export="true" sort="list"  pagesize="10" name="beanList" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px"  >

<display:column sortable="true"  title="Select" media="html" >
<form:radiobutton path="windex"  value="${row.lovName}"/>

</display:column>
<display:column sortable="true"  property="lovName" >

</display:column>



</display:table>
 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId" value="addProjsche" >
 <input type="submit" value="Add Project Status"/>
</div>
<div style="float:left;width:787px;padding: 10px" >

 </div>
 
</form:form>
</div>

<div width="787px" style="float:left;height: 150px">



</div>
<script language="javascript">

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