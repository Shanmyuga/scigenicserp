

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div width="787px"  style="float:left;padding-left:20px">

<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face">Search Enquiry Details</font></p>


<form:form modelAttribute="enqbean" name="enqbean"  enctype="multipart/form-data">
    <div width="787px"  style="float:left;">
        <table>
            <tr><td colspan="2">
                <label for="tags">Select Organization by Name: </label></td>  </tr>
            <tr><td>
                <form:input path="reportFilter" size="100" /></td><td>
                <input type="button" value="Filter Client" onclick="eventdirect('filterReport')"/></td>  </tr>

        </table>
        <table  cellspacing="5" cellpadding="5" align="left" border="0" >


            <tr>
                <td> Org Name </td>
                <td>
                    <form:select path="orgCode" size="1" id="orgCode" onchange="eventdirect('loadcustomers')" >
                        <form:option value="">All</form:option>
                        <form:options items="${clientorglist}" itemLabel="orgName" itemValue="orgCode" />
                    </form:select>
                </td>

            </tr>


            <tr>
                <td> Customers for the Org </td>
                <td>
                    <form:select path="seqCustomerId" size="1" id="seqCustomerId"  >
                        <form:option value="">All</form:option>
                        <form:options items="${selectedCustomers}" itemLabel="customerContact" itemValue="seqCustId" />
                    </form:select>
                </td>

            </tr>
            <tr>

                <td class="datatext" align="right">Customer State</td>
                <td>
                    <form:select path="stateCode"  id="stateCode" onchange="eventdirect('loadCity')" >
                        <form:option value="">Select</form:option>
                        <form:options items="${states}"  itemLabel="lovName" itemValue="lovType"/>
                    </form:select>
                </td>


            </tr>
            <tr>
                <td  align="right" class="datatext">Customer City Code</td>
                <td><form:select path="customerCityCode"  id="customerCityCode"  >
                    <form:option value="">Select</form:option>
                    <form:options items="${stateCityList}"  itemLabel="cityName" itemValue="cityCode"/>
                </form:select></td>

            </tr>
                <tr>
                <td>Enquiry Full Code</td>
                <td  ><form:input path="enqFullCode" id="enqFullCode"></form:input>
                </td>
            </tr>
            <tr>
                <td align="right" class="datatext">Enquiry Priority</td>
                <td ><form:select path="enqPriority" >
                    <form:option value="">Select</form:option>
                    <form:options items="${enqpriorities}" itemLabel="lovDescription" itemValue="seqLovId"/>
                </form:select>


            </tr>
            <tr>
                <td align="right" class="datatext">Enquiry Category</td>
                <td ><form:select path="enqCategory" >
                    <form:option value="">Select</form:option>
                        <form:options items="${enqCategories}" itemLabel="lovDescription" itemValue="seqLovId"/>
                    </form:select>


            </tr>            <tr>
                <td align="left" class="datatext">Enquiry Create From Date</td>
                <td ><form:input path="enqCreateFromDate" />
                    <a href="javascript:show_calendar('document.enqbean.enqCreateFromDate', document.enqbean.enqCreateFromDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>

                </td>
                 <td align="left" class="datatext">Enquiry Create To Date</td>
                <td ><form:input path="enqCreateToDate" />
                    <a href="javascript:show_calendar('document.enqbean.enqCreateToDate', document.enqbean.enqCreateToDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>

                </td>
            </tr>

            <tr>

                <td align="left" class="datatext">Inserted By</td>
                <td><form:input path="insertedBy" /></td>

                <td>Enquiry Status</td>
                <td  ><form:select path="enqStatus" size="1" id="enqStatus">
                    <form:option value="">All</form:option>
                    <form:option value="O">Open</form:option>
                    <form:option value="C">Close</form:option>
                </form:select>
                </td>


            </tr>
<tr>

    <td colspan="4"> <input  type="button" value="Search Enquiry" onclick="eventdirect('searchEnquiry')"/> </td>
</tr>

        </table>

    </div>


    <div width="787px"  style="float:left;">
        <display:table export="true" sort="list"  pagesize="10" name="openenqlist" requestURI="springtest.htm"  id="row1" htmlId="2"  cellpadding="5px" cellspacing="5px">

            <display:column sortable="true"  title="Select" media="html" >
                <form:radiobutton path="seqenqmasterid"  value="${row1.seqEnqryId}" onclick="eventdirect('loadEnqDetails') "/>

            </display:column>

            <display:column sortable="true"  property="seqEnqryId" title="Enquiry ID">

            </display:column>
            <display:column sortable="true"  property="enqDetails" >

            </display:column>
            <display:column sortable="true"  property="enqDate" >

            </display:column>
            <display:column sortable="true"  title="Customer Details" >
                <c:out value='${row1.sciCustomerMaster.customerContact}'/>
            </display:column>
            <display:column sortable="true"  title="Organization Name" >
                <c:out value='${row1.sciCustomerMaster.sciClientOrgMaster.orgName}'/>
            </display:column>

            <display:column sortable="true"  title="Enquiry Category" >

                <c:out value='${lovmap[row1.enqCategory]}'/>
            </display:column>
            <display:column sortable="true"  property="insertedDate" >

            </display:column>
            <display:column sortable="true"  property="enqSource" >

            </display:column>
            <display:column sortable="true"  property="enqAttendee" >

            </display:column>
            <display:column sortable="true"  property="insertedBy" >

            </display:column>
            <display:column sortable="true"  title="EnqType" >
                <c:out value='${lovmap[row1.enqType]}'/>
            </display:column>

            <display:column sortable="true"  title="EnqPriority" >
                <c:out value='${lovmap[row1.enqPriority]}'/>
            </display:column>
            <display:column sortable="true"  property="enqFollowUpDate" >

            </display:column>


            <display:column sortable="true"  property="enqFullCode" >

            </display:column>
            <display:column sortable="true"  property="actionTaken"  title="Last Action Taken" >

            </display:column>
            <display:column sortable="true"  property="actionDate" title="Last action Date">

            </display:column>
            <display:column sortable="true"  property="lastUpdatedBy" title="Last updated by" >

        </display:column>
            <display:column sortable="true"  property="bringForwardDate" title="Bring forward Date" >

            </display:column>

        </display:table>
        <div style="float:left;width:787px;padding: 10px" >
            <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
            <input type="hidden" name="_eventId"  id="_eventId" value="addEnq" >

        </div>
    </div>
    <c:if test="${fn:length(openenqlist) > 0 }" >
        <div style="float:left;width:900px">
            <display:table export="true" sort="list"  pagesize="10" name="openenqdetails" requestURI="springtest.htm"  id="row2"  htmlId="3" cellpadding="5px" cellspacing="5px">

                <display:column sortable="true"  property="seqEnqDetId" >

                </display:column>
                <display:column sortable="true"  property="actionTaken" >

                </display:column>

                <display:column sortable="true"  property="actionDate" title="Action Date">

                </display:column>

                <display:column sortable="true"  property="bringForwardDate"   title="Bring Forward Date" >

                </display:column>
                <display:column sortable="true"  property="purpose" title="Action to be Taken" >

                </display:column>
                <display:column sortable="true"  property="insertedDate" >

                </display:column>
                <display:column sortable="true"  property="insertedBy" >

                </display:column>
                <display:column sortable="true"  property="competitors" >

                </display:column>
                <display:column sortable="true"  property="followupMethod" >

                </display:column>
            </display:table>
            <div style="float:left;width:900px">
                <display:table export="true" sort="list"   pagesize="10" name="enqdoclist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

                    <display:column sortable="true"   title="Document Name" property="enqDocName" >

                    </display:column>
                    <display:column sortable="true"  title="Document Desc" property="enqDocDesc" >

                    </display:column>

                    <display:column sortable="true"  title="Uploaded File"   >
                        <a href="javascript:openfile('<c:out value="${row.seqEnquiryDocId}"/>')"><c:out value="${row.originalDoc}" /></a>
                    </display:column>
                    <display:column sortable="true"  title="Date uploaded"   >
                        <c:out value="${row.updatedDate}" />
                    </display:column>


                </display:table>
            </div>
        </div>


    </c:if>
    </div>






</form:form>
<form name="myloginform" action="streamer.enqDocServlet" method="post">
    <input type="hidden" name="key" id="key" value="workdes"/>
    <input type="hidden" name="idkey" id="idkey" value=""/>
</form>
<script language="javascript">

    function eventdirect(event) {

        document.getElementById('_eventId').value = event;

        document.enqbean.submit();
    }


    function openfile(idkeyval) {
        document.myloginform.idkey.value=idkeyval;
        document.myloginform.submit();
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