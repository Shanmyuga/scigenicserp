<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div width="787px"  style="float:left;">
 <br>
 <p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Search  MI </font></p>
 <p style="padding-left:20px" align="center"></p>
 <div width="787px"  style="float:left;">
  <form:form modelAttribute="matindbean" name="matindbean">
   <div width="787px"  style="float:left;">
    <table width="600px" cellspacing="5" cellpadding="5" align="left" border="0" >

     <tr>

      <td>Material Created date</td>
      <td><form:input path="createdDate" />
       <a href="javascript:show_calendar('document.matindbean.createdDate', document.matindbean.createdDate.value);"><img src="images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
      </td>
     <td colspan="2"></td>
     </tr>

     <tr>



      <td> Material Dept</td>
      <td>
       <form:select path="matDept" size="1" id="matDept" onchange="eventdirect('loadCategory')">
        <form:option value="">All</form:option>
        <form:options items="${matdeptlist}" />
       </form:select>
      </td>

      <td>Material Category</td>
      <td  ><form:select path="matCategory" size="1" id="matCategory">
       <form:option value="">All</form:option>
       <form:options items="${matcatitems}" itemValue="matcatCode" itemLabel="matcatType"/>
      </form:select>
      </td>
     </tr>
     <tr>


      <td>MI Status</td>
      <td  ><form:select path="purStatus" size="1" id="matType">
       <form:option value="">All</form:option>
       <form:options items="${matstatitems}" itemValue="seqLovId" itemLabel="lovDescription"/>
      </form:select>
      </td>
      <td>Stores request Status</td>
      <td  ><form:select path="ststatus" size="1" id="matType">
       <form:option value="">All</form:option>
       <form:option value="Y">Request Created and Issued</form:option>
       <form:option value="N">Request Created but not Issued</form:option>
       <form:option value="P">Request Not Created and Pending</form:option>
       <form:option value="C">Request created but Cancelled by Stores </form:option>
       <form:option value="R">Request created but rejected by managers </form:option>
      </form:select>
      </td>
     </tr>


     <tr>
      <td>Work Order</td>
      <td><form:select path="seqWorkId"    >
       <form:option value="">All</form:option>
       <form:options items="${workmastlist}" itemLabel="jobDesc" itemValue="seqWorkId"/>
      </form:select>

      </td>
      <td colspan="2" align="right"><input type="submit" value="Search MI"/></td>

     </tr>
    </table>

   </div>

   <div style="float:left;width:750px;padding:10px;margin-top:50px">
    <c:if test="${itemerror != null}">
     <p>
      Cannot select different material code to create items.
     </p>
    </c:if>
    <display:table export="true" sort="list"   pagesize="10" name="milist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

     <display:column sortable="true"  title="Select" media="html" >

         <form:radiobutton path="miindexID"  value='${row.seqMiId}'></form:radiobutton>

     </display:column>

     <display:column sortable="true"   title="MI ID"  property="seqMiId">

     </display:column>
     <display:column sortable="true"   title="Material Code" property="matcode" >

     </display:column>
     <display:column sortable="true"  title="Material Specs"  property="matSpec" >

     </display:column>
     <display:column sortable="true"  title="Material Cat"  property="matType" >

     </display:column>

     <display:column sortable="true"  title="Qty"  property="matQty">

     </display:column>
     <display:column sortable="true"  title="Dimen"  property="matDimesion">

     </display:column>

     <display:column sortable="true"  title="Due Date"  property="matDuedate" >

     </display:column>

     <display:column sortable="true"  title="Created Date"  property="insertedDate" >

     </display:column>
     <display:column sortable="true"  title="Recomm"  property="recommend" >

     </display:column>

     <display:column sortable="true"  title="Work Order Details"  property="workorderDesc"  >

     </display:column>
     <display:column sortable="true"   title="Material Status"  >
      <c:out value='${lovmap[row.purStatus]}'/>
     </display:column>

        <display:column sortable="true"  title="Stores Request Status"  >
            <c:if test="${not empty row.requestStatus }">
            <c:out value='${row.requestStatus}'/>
            </c:if>
            <c:if test="${ empty row.requestStatus }">
               'Not Raised'
            </c:if>
        </display:column>
    </display:table>
   </div>
   <input type="hidden" name="_flowExecutionKey"  value="<c:out value="${flowExecutionKey}"/>" />
   <input type="hidden" name="_eventId"  id="_eventId" value="searchMI" >


    <c:if test="${fn:length(milist) > 0 && (matindbean.purStatus == Openmi || matindbean.purStatus == approveMI ||  matindbean.purStatus == AssignedMI )}" >
     <div style="padding-left:10px;width:787px;float:left">
      <input type="button"  value="Cancel MI " onclick="eventdirect('updateMI')"/>
      <input type="button"  value="Update Remarks" onclick="eventdirect('updateRemarks')"/>
      <p>New Remarks</p>
      <form:textarea path="recommend"/>
     </div>
    </c:if>

   <c:out value="${openMI}"></c:out>
  </form:form>
 </div>
</div>


<script language="javascript">

    function eventdirect(event) {

        document.getElementById('_eventId').value = event;


        document.matindbean.submit();
    }

    function openfile(idkeyval) {
        document.myloginform.idkey.value=idkeyval;
        document.myloginform.submit();
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
