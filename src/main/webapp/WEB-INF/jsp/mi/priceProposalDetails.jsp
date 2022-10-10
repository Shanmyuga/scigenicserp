<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Search  Proposals </font></p>
<p style="padding-left:20px" align="center"></p>
<div width="787px"  style="float:left;">
<form:form modelAttribute="proposalbean" name="proposalbean" >
<div width="787px"  style="float:left;">

 <display:table export="true" sort="list"  pagesize="10" name="proposals" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px">

  <display:column sortable="true"  title="Select" media="html" >
   <form:radiobutton path="seqProposalId"  value='${row.seqPropId}' />

  </display:column>
  <display:column sortable="true"  property="proposalName" >

  </display:column>
  <display:column sortable="true"  property="proposalRemarks" >

  </display:column>
  <display:column sortable="true"  property="proposalOrg" >

  </display:column>
  <display:column sortable="true"  property="proposalClientName" >

  </display:column>

 </display:table>
 <input type="hidden" name="_flowExecutionKey"  value="<c:out value="${flowExecutionKey}"/>" />
 <input type="hidden" name="_eventId"  id="_eventId" value="searchItem" >
<p align="left">
 <input type="button" value="Select Proposal Items" onclick="javascript:eventdirect('selectPropItems')"/>
</p>
</div>


 
 

</div>



</div>
 <div width="787px"  style="float:left;width: 100%">

  <display:table export="true" sort="list"  pagesize="10" name="proposalItemMasterListSelected" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px">

   <display:column sortable="true"  title="Select" media="html" >

 <form:radiobutton path="selectItem" value='${row.seqPropItemId}'/>
   </display:column>
   <display:column sortable="true"  property="schedule" >

   </display:column>
   <display:column sortable="true"  property="type" >

   </display:column>
   <display:column sortable="true"  property="description" >

   </display:column>
   <display:column sortable="true"  property="mocCategory" >

   </display:column>

  </display:table>
  <input type="button" value="Select for Pricing " onclick="javascript:eventdirect('startPricing')"/>

 </div>

    <div width="787px"  style="float:left;width: 100%">
<table>
    <tr>



        <td> Material Dept</td>
        <td>
            <form:select path="productType" size="1" id="productType">
                <form:option value="">All</form:option>
                <form:options items="${matTypes}" itemValue="matCode" itemLabel="matType" />
            </form:select>
        </td>

        <td>Material Category</td>
        <td  ><form:select path="productCat" size="1" id="productCat">
            <form:option value="">All</form:option>
            <form:options items="${matCategorys}" itemValue="matcatCode" itemLabel="matcatType"/>
        </form:select>
        </td>

        <td>Material Specs</td>
        <td  ><form:select path="productSpec" size="1" id="productSpec">
            <form:option value="">All</form:option>
            <form:options items="${matspecs}" itemValue="specCode" itemLabel="materialSpec"/>
        </form:select>
        </td>
    </tr>
</table>
<form:hidden path="productDept"/>
        <input type="button" value="Search MI" onclick="javascript:eventdirect('searchMI')"/>

    </div>


    <div width="787px"  style="float:left;width: 100%">

        <display:table export="true" sort="list"  pagesize="10" name="matindMasterList" requestURI="springtest.htm"  id="row"  cellpadding="5px" cellspacing="5px">

            <display:column sortable="true"  title="Select" media="html" >

                <form:radiobutton path="selectItem" value='${row.seqMiId}'/>
            </display:column>

            <display:column sortable="true"  property="seqMiId" >

            </display:column>
            <display:column sortable="true"  property="matSpec" >

            </display:column>
            <display:column sortable="true"  property="updatedDate" >

            </display:column>
            <display:column sortable="true"  property="unitCost" >

            </display:column>
            <display:column sortable="true"  property="matcode" >

            </display:column>

        </display:table>


    </div>
</form:form>
<script language="javascript">

function eventdirect(event) {

document.getElementById('_eventId').value = event;

document.proposalbean.submit();
}
function saveSelect(event) {


 if(event.checked) {
  document.getElementById('_eventId').value = 'addProposalItems';
  document.getElementById('selectItem').value = event.value
  document.proposalbean.submit();
 }

 if(!event.checked) {
  document.getElementById('_eventId').value = 'removeProposalItems';
  document.getElementById('unSelectItem').value = event.value
  document.proposalbean.submit();
 }
// document.proposalbean.submit();
}


</script>


<script language="javascript">

function openfile(idkeyval) {
document.myloginform.idkey.value=idkeyval;
document.myloginform.submit();
}
</script>
