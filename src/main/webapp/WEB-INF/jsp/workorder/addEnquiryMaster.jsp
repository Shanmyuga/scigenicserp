<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add New Enquiry </font></p>

<p>Organization Name - <c:out value="${selectedClientOrg.orgName}"/></p>
     <p>Organization Code  - <c:out value="${selectedClientOrg.orgCode}"/></p>
     <p>Customer code - <c:out value="${selectedCustomer.customerCode}"/></p>
     <p>Customer city code - <c:out value="${selectedCustomer.customerCityCode}"/></p>
     <div width="787px"  style="float:left;">
<form:form modelAttribute="enqbean" name="enqbean" >

<table border="1" width="100%" cellpadding="3x" >

    <tr>
        <td align="right" class="datatext">Enquiry or Visit</td>
        <td >Actual Enquiry  <form:radiobutton path="enqOrVisit" value="E"  onclick="hideData('showEnquiry')"/> <br/>
            Prospect/Scan Visit <form:radiobutton path="enqOrVisit" value="PV"  onclick="hideData('hideEnquiry')"/>
            Follow up visit for an enquiry <form:radiobutton path="enqOrVisit" value="EV"  onclick="hideData('hideEnquiry')"/>

            <span style="color:red;">*</span></td>
    </tr>
<tr>
<td align="right" class="datatext">VisitEnquiry Details</td>
<td ><form:textarea path="enqDetails" /></td>
</tr>


<tr>
<td align="right" class="datatext">Visit/Enquiry Date</td>
<td ><form:input path="enqDate" />
<a href="javascript:show_calendar('document.enqbean.enqDate', document.enqbean.enqDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</td>
</tr>

    <tr>
        <td align="right" class="datatext">Visit Enquiry Follow up Date</td>
        <td ><form:input path="enqFollowUpDate" />
            <a href="javascript:show_calendar('document.enqbean.enqFollowUpDate', document.enqbean.enqFollowUpDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
        </td>
    </tr>

    <c:if test="${checkEnquiry == true}" >
    <tr>
        <td align="right" class="datatext">Enquiry Priority</td>
        <td ><form:select path="enqPriority" >
            <form:options items="${enqpriorities}" itemLabel="lovDescription" itemValue="seqLovId"/>
        </form:select>

            <span style="color:red;">*</span></td>
    </tr>


    <tr>
        <td align="right" class="datatext">Enquiry Category</td>
        <td ><form:select path="enqCategory" >
            <form:options items="${enqCategories}" itemLabel="lovDescription" itemValue="seqLovId"/>
        </form:select>

            <span style="color:red;">*</span></td>
    </tr>


<tr>
<td align="right" class="datatext">Enquiry Source</td>
<td ><form:select path="enqSource" >
    <form:options items="${enqSources}" itemLabel="lovDescription" itemValue="lovDescription"/>
</form:select></td>
</tr>
        <tr>
            <td align="right" class="datatext">Enquiry Attendee</td>
            <td ><form:select path="enqAttendee" >
                <form:options items="${usersmarketing}" itemLabel="userId" itemValue="userId"/>
            </form:select></td>
        </tr>
<tr>
<td align="right" class="datatext">Enquiry Timeline</td>


    <td ><form:select path="enquiryTimeline" >
        <form:options items="${enqTimelines}" itemLabel="lovDescription" itemValue="lovDescription"/>
    </form:select><span style="color:red;">*</span></td>
</tr>
    </c:if>
    <c:if test="${enquiryVisit == true }">
    <tr>
        <td align="right" class="datatext">Visit Enquiry Reference Code</td>
        <td ><form:input path="visitEnqRefCode" /></td>
    </tr>
    </c:if>



<tr>

<td align="right" >&nbsp;</td>
<td>

   
 
 <input type="button" value="Add Enquiry Master" onclick="changeDtd('addEnqData')" />
  

</td>
</tr>

</table>

   <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
   <input type="hidden" name="_eventId"  id="_eventId" value="addEnqData"> 
</form:form>
</div>
</div>
<div width="787px" style="float:left;">



</div>

<script language="javascript">

function changeDtd(event) {

document.enqbean._eventId.value=event;
document.enqbean.submit();

}

function hideData(event) {

    document.enqbean._eventId.value=event;
    document.enqbean.submit();

}
</script>