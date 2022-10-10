<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<div width="787px"  style="float:left;" class="container-fluid" >
    <br>
    <p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add New Sprint Job </font></p>
    <p style="padding-left:20px" align="center"></p>
    <div width="787px"  style="float:left;" >
        <form:form modelAttribute="sprintjobbean" name="sprintjobbean">

            <div class="form-group">
            <label for="jobDescription">    Job Desc</label>
                   <form:textarea path="jobDescription" class="form-control"  />
            </div>
            <div class="form-group">
                <label for="seqWorkId">Work Order</label>
                    <form:select path="seqWorkId"  class="form-control"   >
                        <form:option value="">All</form:option>
                        <form:options items="${workmastlist}" itemLabel="jobDesc" itemValue="seqWorkId"/>
                    </form:select>
            </div>


            <div class="form-group">
                <label for="jobPhase">Job Phase</label>
                <form:select path="jobPhase"  class="form-control"  >
                    <form:options items="${phaseslist}"/>
                </form:select>



            </div>
            <div class="form-group">
                <label for="jobAssignedTo">Job Assigned To</label>
                <form:select path="jobAssignedTo" class="form-control"  >

                    <form:options items="${userlistitems}" itemValue="userId" itemLabel="userName"/>
                </form:select>


            </div>



            <button type="submit" class="btn btn-default">Submit</button>





            <div style="float:left;width:750px;padding:10px;margin-top:50px;">

                <display:table export="true" sort="list"   pagesize="10" name="milist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

                    <display:column sortable="true"  title='Select' media="html"  >

                        <form:checkbox  path="matList[${row_rowNum%10}].matindex"  value="${row_rowNum}"/>

                    </display:column>

                    <display:column sortable="true"   title="Material Code" property="matcode" >

                    </display:column>
                    <display:column sortable="true"  title="Material Specs"  property="matSpec" >

                    </display:column>
                    <display:column sortable="true"  title="Material Cat"  property="matType" >

                    </display:column>
                    <display:column sortable="true"  title="Description"  property="matDesc">

                    </display:column>
                    <display:column sortable="true"  title="Qty"  property="matQty">

                    </display:column>
                    <display:column sortable="true"  title="Dimen"  property="matDimesion">

                    </display:column>
                    <display:column sortable="true"  title="Remarks"  property="recommend">

                    </display:column>
                    <display:column sortable="true"  title="MI Created By"  property="insertedBy">

                    </display:column>
                    <display:column sortable="true"  title="Due Date"  >
                        <fmt:formatDate value="${row.matDuedate}" pattern="dd-MM-yyyy"/>
                        <form:input path="matList[${row_rowNum%10}].matDuedatestr" />
                    </display:column>

                    <display:column sortable="true"  title="Drawing Ref"  >
                        <a href="javascript:openfile('<c:out value="${row.drawingRef}"/>')"><c:out value="${row.designRef}" /></a>
                    </display:column>
                    <display:column sortable="true"   title="Material Status"  >
                        <c:out value='${lovmap[row.purStatus]}'/>
                    </display:column>
                    <display:column sortable="true"  title="Work Order Details"  property="workorderDesc"  >

                    </display:column>


                </display:table>

            </div>
            <input type="hidden" name="_flowExecutionKey"  value="<c:out value="${flowExecutionKey}"/>" />
            <input type="hidden" name="_eventId"  id="_eventId" value="searchMI" >

            <c:if test="${fn:length(milist) > 0}" >
                <div style="padding-left:10px;width:787px;float:left">
                    <input type="button"  value="Check All " onclick="checkAll('miindex',true)"/>
                    <input type="button"  value="Uncheck All " onclick="checkAll('miindex',false)"/>
                    <input type="button"  value="Approve MI " onclick="eventdirect('approveMI')"/>
                    <input type="button"  value="Reject MI " onclick="eventdirect('rejectMI')"/>
                </div>
            </c:if>
        </form:form>
    </div>
    <form name="myloginform" action="streamer.servlet" method="post">
        <input type="hidden" name="key" id="key" value="workdes"/>
        <input type="hidden" name="idkey" id="idkey" value=""/>
    </form>
</div>

<script language="javascript">

    function eventdirect(event) {

        document.getElementById('_eventId').value = event;

        document.sprintjobbean.submit();
    }
    function checkAll(chkbox,status) {

        var chklist = document.getElementsByName(chkbox)
        for(i =0;i<chklist.length;i++) {
            chklist[i].checked = status;
        }


    }

    function openfile(idkeyval) {
        document.myloginform.idkey.value=idkeyval;
        document.myloginform.submit();
    }
</script>
