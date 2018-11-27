<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>选择城市</title>
  </head>
  <body>
  <br>
  &nbsp;&nbsp;省&nbsp;&nbsp;份：<select name="province" id="province" style="width:200px" onchange="pchange();">
  
  </select><br>
 &nbsp;&nbsp;城&nbsp;&nbsp;市：<select name="city" id="city" style="width:200px"  onchange="cchange();">
  
  </select><br>
 &nbsp;&nbsp;区&nbsp;&nbsp;县：<select name="county" id="county" style="width:200px">
  
  </select><br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" name="btnclose" id="btnclose" value="关闭" onclick=" close2();">
  </body>
</html>
