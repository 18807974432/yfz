<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/19 0019
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="c" %>
<html>
<head>
    <title>试题信息</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
</head>
<body>
    <script type="text/javascript">
        function save(){
            if($("#unitName").val()=="") {
                alert("请输入试题名称!");
                return;
            }
            if($("#unitNO").val()=="" || isNaN($("#unitNO").val())) {
                alert("请输入试题序号，0~9之间的数字字符!");
                return;
            }
            if($("#optCnt").val()=="" || isNaN($("#optCnt").val())) {
                alert("请输入选项个数，0~9之间的数字字符!");
                return;
            }
            document.forms[1].submit();
        }
    </script>
    <DIV id=maintitle>
        <c:form>
            <DIV class=newtitle><STRONG>试题管理</STRONG></DIV>
        </c:form>
    </DIV>
    <div id="tt" class="easyui-tabs" style="height:530px">
        <div title="试题管理" data-options="closable:false,cache:false" style="padding:0px;">
            <div class=newtitle>
                <th>
                    <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
                </th>
            </div>
            <c:form theme="simple" action="researchAction!saveExam.action">
                <c:fielderror></c:fielderror>
                <c:hidden name="exam.examid" id="examid"></c:hidden>
                <input type="hidden" name="research.reseachid" id="reseachid" value="${param.reseachid }">
                <table width="100%" align="center">
                    <tr>
                        <td align="right">题目名称:</td>
                        <td colspan="3"><c:textfield name="exam.unitName" id="unitName" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">试题序号:</td>
                        <td><c:textfield name="exam.unitNO" id="unitNO"  size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">题目类型:</td>
                        <td><c:select list="{'单选题','多选题','问答题'}" name="exam.unitType" id="unitType" cssStyle="width:152px"></c:select>
                        </td>
                        <td align="right">是否启用:</td>
                        <td>
                                <c:select list="{'启用','禁用'}" name="exam.invalid" id="invalid" cssStyle="width:152px"></c:select>
                        <td align="right">选项个数:</td>
                        <td>
                            <c:select list="{1,2,3,4,5,6,7,8}"  name="exam.optCnt" id="optCnt"  cssStyle="width:125px"></c:select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">选项1:</td>
                        <td  colspan="3"><c:textfield name="exam.question1" id="question1" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value1" id="value1" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项2:</td>
                        <td  colspan="3"><c:textfield name="exam.question2" id="question2" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value2" id="value2" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项3:</td>
                        <td  colspan="3"><c:textfield name="exam.question3" id="question3" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value3" id="value3" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项4:</td>
                        <td  colspan="3"><c:textfield name="exam.question4" id="question4" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value4" id="value4" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项5:</td>
                        <td  colspan="3"><c:textfield name="exam.question5" id="question5" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value5" id="value5" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项6:</td>
                        <td  colspan="3"><c:textfield name="exam.question6" id="question6" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value6" id="value6" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项7:</td>
                        <td  colspan="3"><c:textfield name="exam.question7" id="question7" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value7" id="value7" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">选项8:</td>
                        <td  colspan="3"><c:textfield name="exam.question8" id="question8" size="96"/><font size="2" color="red">*</font></td>
                        <td align="right">值:</td>
                        <td><c:textfield name="exam.value8" id="value8" size="15"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td colspan="6"><font size="2" color="red"><b>注意事项：</b>选项个数为1~8个，最多可以填写8个选项!</font></td>
                    </tr>
                </table>
            </c:form>
        </div>
    </div>
</body>
</html>
