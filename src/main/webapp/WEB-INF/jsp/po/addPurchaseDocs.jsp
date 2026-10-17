<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <div width="787px"  style="float:left;">
<br>
<p style="padding-left:20px" align="center"><font color="#0080ff" size="4" face="Baskerville Old Face"> Add  the Purchase Order Documents </font></p>
<p style="padding-left:20px" align="center"> Purchase ID : <c:out value="${selectedpurchase.seqPurchId}" />
<c:if test="${not empty selectedpurchase.customPOId}"> ( <c:out value="${selectedpurchase.customPOId}" /> )</c:if></p>
<p style="padding-left:20px" align="center"> Vendor Details : <c:out value="${selectedpurchase.vendorOrder}" /></p>
<div width="787px"  style="float:left;">
<form:form modelAttribute="purchdocbean" name="purchdocbean"  enctype="multipart/form-data" >

<table border="0" width="100%" cellpadding="10x" height="70px">

<tr>
<td align="right" class="datatext">Document Name</td>
<td ><form:input path="purchDocName"/>
<span style="color:red;">*</span></td>
<td colspan="2">&nbsp</td>
</tr>

<tr>
<td  align="left" class="datatext">Document file </td>
<td ><input type="file" name="filedoc" id="filedoc"/><span style="color:red;">*</span></td>
<td colspan="2">&nbsp</td>
</tr>

<tr>
<td align="right" class="datatext">Document  Description</td>
<td ><form:textarea path="purchDocDesc" /><span style="color:red;">*</span></td>
<td colspan="2">&nbsp</td>
</tr>
<tr>
<td align="right" class="datatext">Document Version Number</td>
<td ><form:input path="docVersion" size="3"  maxlength="3"/><span style="color:red;">*</span></td>
<td colspan="2">&nbsp</td>
</tr>

 <tr>
  <td align="right" class="datatext">Document Type</td>
  <td ><form:select path="docType">

<form:options items="${poDocTypeList}" itemLabel="lovDescription" itemValue="seqLovId"/>

  </form:select></td>
  <td colspan="2">&nbsp</td>
 </tr>
<tr>
<td  colspan="4" align="right">
<input type="button" value="Back to Search" onclick="eventdirect('backtosearch')"/>
<input type="submit" value="Add Documents " /></td>
</tr>

</table>

 <input type="hidden" name="_flowExecutionKey" value='<c:out value="${flowExecutionKey}"/>'>
 <input type="hidden" name="_eventId"  id="_eventId"  value="addpurchdoc">


</div>
</div>
<div width="787px" style="float:left;">

<div style="float:left;width:750px;padding:10px;margin-top:50px">
<display:table export="true" sort="list"   pagesize="10" name="purchasedoclist"  id="row"  requestURI="springtest.htm"  cellpadding="5px" cellspacing="3px" >

<display:column sortable="true"   title="Document Name" property="purchDocName" >
</display:column>
<display:column sortable="true"  title="Document Desc" property="purchDocDesc" >
</display:column>
<display:column sortable="true"  title="Document Version" property="docVersion"  >
</display:column>
<display:column sortable="true"  title="Document Type"  >
<c:out value='${lovmap[row.docType]}'/>
</display:column>
<display:column sortable="true"  title="Uploaded File"   >
<a href="javascript:openfile('<c:out value="${row.seqPurchDocId}"/>')"><c:out value="${row.originalDoc}" /></a>
</display:column>
<display:column sortable="true"  title="Date uploaded"   >
<c:out value="${row.updatedDate}" />
</display:column>
<display:column sortable="true"  title="Uploaded By" property="updatedBy" >
</display:column>

</display:table>

</div>
</form:form>
<form name="purchdocform" action="streamer.purchdocservlet" method="post">
<input type="hidden" name="key" id="key" value="purchasedocs"/>
<input type="hidden" name="idkey" id="idkey" value=""/>
</form>
</div>
<script language="javascript">

function openfile(idkeyval) {
document.purchdocform.idkey.value=idkeyval;
document.purchdocform.submit();
}

function eventdirect(event) {
document.getElementById('_eventId').value = event;
document.purchdocbean.submit();
}
</script>
