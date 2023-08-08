<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
				exclude-result-prefixes="fo">
	<xsl:template match="PurchaseOrder">


		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format"
				 xmlns:fox="http://xml.apache.org/fop/extensions">

			<!-- Creator="html2fo" Version="0.4.2" -->

			<fo:layout-master-set>

				<fo:simple-page-master margin-right=".3cm"
									   margin-left="2.0cm" margin-bottom="1.0cm" margin-top="3.0cm"
									   page-width="21cm" page-height="29.7cm" master-name="first">


					<fo:region-body margin-bottom="3cm" margin-top="3cm" />
					<fo:region-before extent="4cm"/>
					<fo:region-after extent="3cm"/>

				</fo:simple-page-master>

			</fo:layout-master-set>

			<fo:page-sequence master-reference="first" language="en"
							  hyphenate="true">






				<fo:static-content flow-name="xsl-region-before">


					<fo:table text-align="left" table-layout="fixed">
						<fo:table-column column-width="8.95cm" />
						<fo:table-column column-width="8.15cm" />
						<fo:table-body>

							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="12pt" font-size="10pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always" text-align="center">DELIVERY CHALLAN - SUBCONTRACTOR ADVICE </fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" height="25pt">

									<fo:block font-weight="bold" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  text-align="start" linefeed-treatment="preserve">
										SCIGENICS (INDIA) PVT. LTD.&#xA;Plot No. H-4/5/6, 3rd Cross Street, Nehru Nagar,&#xA; Perungudi, Chennai-600096&#xA;Voice: +91 - 44 - 2454 4100 - 4 Lines. Fax: +91- 44 - 2454 4105 </fo:block>


								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  text-align="center" linefeed-treatment="preserve" >


										<xsl:variable name="newline">
											<xsl:text>&#xA;</xsl:text>
										</xsl:variable>
										<xsl:variable name="MyVar">
											<xsl:choose>
												<xsl:when test="contains(//vendorDetails,'|')">
													<xsl:value-of select="concat(substring-before(//vendorDetails,'|'),$newline,substring-after(//vendorDetails,'|'))"/>
												</xsl:when>
												<xsl:otherwise>
													<xsl:value-of select="vendorDetails"/>
												</xsl:otherwise>
											</xsl:choose>
										</xsl:variable>
										<xsl:value-of select="$MyVar"></xsl:value-of>
									</fo:block>


								</fo:table-cell>
							</fo:table-row>

						</fo:table-body>
					</fo:table>









				</fo:static-content>







				<fo:static-content flow-name="xsl-region-after">
					<fo:block line-height="12pt" font-size="10pt"
							  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
							  keep-together="always" text-align="center">
						<fo:inline white-space-collapse="false" />
					</fo:block>
					<fo:block line-height="12pt" font-size="10pt"
							  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
							  keep-together="always" text-align="center">
						<fo:inline white-space-collapse="false" />
					</fo:block>
					<fo:block text-align="center">
						Page <fo:page-number/> of <fo:page-number-citation ref-id="last-page" />  </fo:block>


				</fo:static-content>



				<fo:flow flow-name="xsl-region-body">


					<fo:table text-align="left" table-layout="fixed">
						<fo:table-column column-width="0.80cm" />
						<fo:table-column column-width="1.96cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.56cm" />
						<fo:table-column column-width="1.9cm" />
						<fo:table-column column-width="0.81cm" />
						<fo:table-column column-width="1.10cm" />
						<fo:table-column column-width="1.48cm" />
						<fo:table-column column-width="1.00cm" />
						<fo:table-column column-width="0.01cm" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  linefeed-treatment="preserve">DC NO</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt" text-align="center"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always"><xsl:value-of select="seqRawMIId" /></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always">Date</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always"><xsl:value-of select="dcDate" /></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always">PO No</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always"><xsl:value-of select="purchaseNo"/></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always">WO NO</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  linefeed-treatment="preserve"><xsl:value-of select="workOrderNo"/></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always">FMT No</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="12pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always"></fo:block>
								</fo:table-cell>

							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="0pt" number-columns-spanned="13">
									<fo:block line-height="14pt" font-size="10pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"
											  keep-together="always">Finished component Description</fo:block>
								</fo:table-cell>

							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:table text-align="left" table-layout="fixed">
						<fo:table-column column-width="0.80cm" />
						<fo:table-column column-width="1.36cm" />
						<fo:table-column column-width="2.30cm" />
						<fo:table-column column-width="2.30cm" />
						<fo:table-column column-width="1.00cm" />
						<fo:table-column column-width="1.00cm" />
						<fo:table-column column-width="1.30cm" />
						<fo:table-column column-width="1.56cm" />
						<fo:table-column column-width="1.9cm" />
						<fo:table-column column-width="0.41cm" />
						<fo:table-column column-width="1.10cm" />
						<fo:table-column column-width="1.08cm" />
						<fo:table-column column-width="1.00cm" />

						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
											  keep-together="always">SNO</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">MI</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Description</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Dimension</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">QTY</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">UOM</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Unit cost</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Total Cost</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="3">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve" >Drawing&#xA; reference</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
									>Delivery Date</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
											  keep-together="always"><xsl:value-of select="sno"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always"><xsl:value-of select="miId"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve" ><xsl:value-of select="miDesc"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve"><xsl:value-of select="miDimen"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always"><xsl:value-of select="miQty"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always"><xsl:value-of select="miUom"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always"><xsl:value-of select="unitCost"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always"><xsl:value-of select="totalCost"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="3" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve" ><xsl:value-of select="drawingRef"></xsl:value-of></fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2" height="2.5cm">
									<fo:block line-height="30pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
									><xsl:value-of select="delDate"></xsl:value-of></fo:block>
								</fo:table-cell>

							</fo:table-row>

							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="13" height="0.5cm">
									<fo:block  font-size="8pt"
											   space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="left"
											   keep-together="always">Instructions</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="13" height="1.5cm">
									<fo:block  font-size="8pt"
											   space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="left"
											   keep-together="always"></fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="9" height="0.7cm">
									<fo:block  font-size="8pt"
											   space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="left"
											   keep-together="always">Material Being Sent</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="4" height="0.7cm">
									<fo:block  font-size="8pt"
											   space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="left"
											   keep-together="always">Returnable Materials</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
											  keep-together="always">SNO</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">MI</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Description</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">Dimension</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">MOC</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">QTY</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  keep-together="always">UOM</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="1">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve">Remarks</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
											  linefeed-treatment="preserve" >Dimension</fo:block>
								</fo:table-cell>
								<fo:table-cell border-style="solid" border-color="black"
											   border-width="1pt" number-columns-spanned="2">
									<fo:block line-height="15pt" font-size="8pt"
											  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
									>Qty</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<xsl:for-each select="rawMIList/rawMI">
								<fo:table-row>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
												  keep-together="always"><xsl:value-of select="sno"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  keep-together="always"><xsl:value-of select="miId"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  linefeed-treatment="preserve"><xsl:value-of select="miDesc"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  linefeed-treatment="preserve"><xsl:value-of select="miDimen"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="2" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  keep-together="always"><xsl:value-of select="miMoc"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  keep-together="always"><xsl:value-of select="miQty"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  keep-together="always"><xsl:value-of select="miUom"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="1" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  linefeed-treatment="preserve"><xsl:value-of select="remarks"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="2" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
												  linefeed-treatment="preserve" ><xsl:value-of select="retDimen"></xsl:value-of></fo:block>
									</fo:table-cell>
									<fo:table-cell border-style="solid" border-color="black"
												   border-width="1pt" number-columns-spanned="2" height="2.0cm">
										<fo:block line-height="30pt" font-size="8pt"
												  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
										><xsl:value-of select="retQty"></xsl:value-of></fo:block>
									</fo:table-cell>

								</fo:table-row>

								<xsl:choose>
									<xsl:when test="position() = last()">
										<fo:table-row>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always">Prepared By</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always">Approved By</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="3" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always">Vendor</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always">Mode of Dispatch</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="4" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always">Vehicle Details</fo:block>
											</fo:table-cell>

										</fo:table-row>
										<fo:table-row>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
														  keep-together="always">Name</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" height="1.0cm" >
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="3" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="4" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>

										</fo:table-row>
										<fo:table-row>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt" text-align="center"
														  keep-together="always">Signature</fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="1" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="3" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="2" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>
											<fo:table-cell border-style="solid" border-color="black"
														   border-width="1pt" number-columns-spanned="4" height="1.0cm">
												<fo:block line-height="15pt" font-size="8pt"
														  space-before.optimum="1.5pt" space-after.optimum="1.5pt"  text-align="center"
														  keep-together="always"></fo:block>
											</fo:table-cell>

										</fo:table-row>

									</xsl:when>
									<xsl:otherwise>
										<xsl:if test="(position() mod 4) =0">
											<xsl:choose>

												<xsl:when test="position() != last()">
													<fo:table-row>
														<fo:table-cell border-style="none" border-color="none"
																	   border-width="1pt" number-columns-spanned="13" height="0.2cm">
															<fo:block page-break-before="always"/>
														</fo:table-cell>
													</fo:table-row>
												</xsl:when>
											</xsl:choose>
										</xsl:if>

									</xsl:otherwise>
								</xsl:choose>

							</xsl:for-each>

						</fo:table-body>
					</fo:table>

					<fo:block id="last-page">
					</fo:block>
				</fo:flow>

			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>

