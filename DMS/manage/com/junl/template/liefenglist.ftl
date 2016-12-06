<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>Administrator</Author>
  <LastAuthor>Microsoft</LastAuthor>
  <Created>2006-09-13T11:21:51Z</Created>
  <LastSaved>2016-09-13T06:34:34Z</LastSaved>
  <Version>12.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <Colors>
   <Color>
    <Index>39</Index>
    <RGB>#E3E3E3</RGB>
   </Color>
  </Colors>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>11640</WindowHeight>
  <WindowWidth>19200</WindowWidth>
  <WindowTopX>0</WindowTopX>
  <WindowTopY>90</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s62">
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s63">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s64">
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s65">
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="29" ss:ExpandedRowCount="${ExcelRowSize!}" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
   <Column ss:AutoFitWidth="0" ss:Width="78.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="81.75" ss:Span="1"/>
   <Column ss:Index="4" ss:AutoFitWidth="0" ss:Width="69"/>
   <Column ss:AutoFitWidth="0" ss:Width="72"/>
   <Column ss:AutoFitWidth="0" ss:Width="102"/>
   <Column ss:AutoFitWidth="0" ss:Width="79.5"/>
   <Column ss:AutoFitWidth="0" ss:Width="96.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="90.75"/>
   <Column ss:Index="11" ss:AutoFitWidth="0" ss:Width="84"/>
   <Column ss:AutoFitWidth="0" ss:Width="99.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="111.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="122.25"/>
   <Column ss:AutoFitWidth="0" ss:Width="112.5"/>
   <Column ss:AutoFitWidth="0" ss:Width="90"/>
   <Column ss:Index="19" ss:AutoFitWidth="0" ss:Width="123.75"/>
   <Column ss:Index="23" ss:AutoFitWidth="0" ss:Width="149.25"/>
   <Column ss:AutoFitWidth="0" ss:Width="168.75"/>
   <Column ss:Index="26" ss:AutoFitWidth="0" ss:Width="153.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="122.25"/>
   <Column ss:AutoFitWidth="0" ss:Width="112.5"/>
   <Column ss:AutoFitWidth="0" ss:Width="110.25"/>
   <Row ss:AutoFitHeight="0" ss:Height="21.75">
    <Cell ss:StyleID="s63"><Data ss:Type="String">开始桩号</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">结束桩号</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">位置</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">天气情况</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">气温℃</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">施工机械</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">维修人员</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">安全员</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">负责人</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">工程评价</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">备注</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">维修时间</Data></Cell>
    <Cell ss:StyleID="s64"><Data ss:Type="String">病害类型</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">养护项目</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">损坏描述</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">数量</Data></Cell>
    <Cell ss:StyleID="s63"><Data ss:Type="String">单位</Data></Cell>
   </Row>
   <#list List as jl>
   <Row ss:Height="14.25">
    <Cell><Data ss:Type="String">K${jl.startZHK!} +${jl.startZHM!}</Data></Cell>
    <Cell><Data ss:Type="String">K${jl.endZHK!}+${jl.endZHM!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.luXian!}/${jl.wzType!}/${jl.wzName!}/${jl.wzMiaoShu!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.weatherState!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.qiWen!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.shiGongJiXie!}</Data></Cell>
    <Cell ss:StyleID="s65"><ss:Data ss:Type="String"
      xmlns="http://www.w3.org/TR/REC-html40"><Font html:Color="#000000">${jl.weiXiuRenYuan!}</Font></ss:Data></Cell>
    <Cell ss:StyleID="s65"><ss:Data ss:Type="String"
      xmlns="http://www.w3.org/TR/REC-html40"><Font html:Color="#000000">${jl.anQuanYuan!}</Font></ss:Data></Cell>
    <Cell><Data ss:Type="String">${jl.fuZeRen!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.pingJia!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.remark!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.weiXiuTime!}</Data></Cell>
    <Cell><Data ss:Type="String">${jl.diseaseType!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.yangHuProject!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.sunHuaiMiaoShu!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.num!}</Data></Cell>
    <Cell ss:StyleID="s62"><Data ss:Type="String">${jl.danWei!}</Data></Cell>
   </Row>
   </#list> 
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>200</HorizontalResolution>
    <VerticalResolution>200</VerticalResolution>
   </Print>
   <Selected/>
   <LeftColumnVisible>1</LeftColumnVisible>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>20</ActiveRow>
     <ActiveCol>12</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
