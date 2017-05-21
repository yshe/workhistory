//移除所有下拉框中的值
function removeAllSelects(){
	var selects=document.getElementById("mySelect");
	for(var i=selects.options.length-1;i>=0;i--)
	selects.options.remove(i);
}

function removeAllSelects2(){
	document.getElementById("mySelect").innerHTML = "";
}

//动态删除select中的所有options：  
function delAllOptions(){  
      document.getElementById("mySelect").options.length=0;   
}  
  
//动态删除select中的某一项option：   
function delOneOption(indx){ 
alert("hello"); 
      document.getElementById("mySelect").options.remove(indx);   
}  


// 动态添加select中的项option:   
function addOneOption(){  
      //document.getElementById("user_dm").options.add(new Option(2,"mytest"));   
     
   var selectObj=document.getElementById("mySelect");  

   selectObj.options[selectObj.length] = new Option("mytest", "2");  
}  

//select多选,获取多个值
function selectMultiple(){
var temp = document.getElementById("multiples");  

  var str = "";  
  for(i=0;i<temp.length;i++){     
                if(temp.options[i].selected){  
                    str+=temp.options[i].value+",";  
                }  
            }  
  alert( str.substr(0,str.length-1) );  
}


//checkbox多选
function checkbox(){
	alert("hello");
	var str=document.getElementsByName("box");
	var objarray=str.length;
	var chestr="";
	for (i=0;i<objarray;i++){
		if(str[i].checked == true)
			alert(str[i].value);
	}
}

//radio选中
function radioChoice(){
	var obj=document.getElementsByName("radio");
	for (var i = obj.length - 1; i >= 0; i--) {
		if (obj[i].checked) {
			alert(obj[i].value);
		}
	};
}

//判断radio选中
<script type="text/javascript" language="javascript">
 /*------判断radio是否有选中，获取选中的值--------*/
    $(function(){
         $("#btnSubmit").click(function(){
            var val=$('input:radio[name="sex"]:checked').val();
            if(val==null){
                alert("什么也没选中!");
                return false;
            }
            else{
                alert(val);
            }
            var list= $('input:radio[name="list"]:checked').val();
            if(list==null){
                alert("请选中一个!");
                return false;
            }
            else{
                alert(list);
            }           
         });
     });
</script>

//获取表格信息
getSelectedItemInfos:function(){
      var itemInfos = new Array();
      var rows = ECSideUtil.getRows("interviewDefineList");
      for (var i = 0; i < rows.length; i++) {
        if(rows[i].cells[0] != null && typeof(rows[i].cells[0]) != "undefined" && 
           rows[i].cells[0].firstChild.firstChild != null){
          if (rows[i].cells[0].firstChild.firstChild.checked == true) {
            var itemInfo = new Object();
            itemInfo.PARAM_ID = rows[i].cells[0].firstChild.firstChild.value;
            itemInfo.STATE = rows[i].cells[7].firstChild.innerHTML;
            itemInfo.NUM = rows[i].cells[1].firstChild.innerHTML;
            itemInfo.CREATE_ROLE_TYPE = rows[i].cells[10].firstChild.innerHTML;
            itemInfos.push(itemInfo);
          }
        }
      }
      return itemInfos;
    },



//得出当前时间以及当前时间后一个月的时间
  var exceptLeaveDate = getCurDayAfterAMonth();
        $('S_EXPECT_LEAVE_DATE').innerHTML = exceptLeaveDate;
        $('EXPECT_LEAVE_DATE').value = exceptLeaveDate;
        employeeLeaveApplyPage.expectLeaveDateChange();

//时间获取年月日
expectLeaveDateChange : function() {
      if (isNotEmpty($('EXPECT_LEAVE_DATE').value)) {
        var dataObj = formatStringToDate($('EXPECT_LEAVE_DATE').value);
        $('LEAVE_REASON_EXPLAIN_YMD').innerHTML = dataObj.getFullYear() + "&nbsp;年&nbsp;"
            + (dataObj.getMonth() + 1) + "&nbsp;月&nbsp;" + dataObj.getDate() + "&nbsp;日&nbsp;";
      }
    },


//父页面的更换子页面显示
<div style="width: 980px; height: 600px;">
    <iframe style="overflow-y: hidden; width: 1000px; height: 600px; margin-left: 10px;"
      src="employee_leave_role_confirm.jsp" id="mainframe"> </iframe>
  </div>
parent.document.getElementById("mainframe").src = 'employee_leave_apply_page.jsp?RID=' + 10000; // TODO 开发测试用，后续清除



//获取当前年的第一天（1月1日）如2008-01-01
function getCurYearFirstDay(){
  var beginDate = new Date();
  beginDate.setMonth(0);
  beginDate.setDate(1);
  
  return beginDate.toShort();
}

//获取当前年的最后一天（12月31日）如2008-12-31
function getCurYearEndDay(){
  var endDate = new Date();
  endDate.setMonth(11);
  endDate.setDate(31);
  
  return endDate.toShort();
}

//获取当前日期的一年前的日期
function getCurDayBeforeAYear(){
  var beginDate = new Date();
  beginDate.setYear(beginDate.getYear() -1);
  
  return beginDate.toShort();
}

//获取当前日期的一月前的日期
function getCurDayBeforeAMonth(){
  var beginDate = new Date();
  beginDate.setMonth(beginDate.getMonth() -1);
  
  return beginDate.toShort();
}

//获取当前日期
function getCurDay(){
  var endDate = new Date();
  
  return endDate.toShort();
}

//获取当前年
function getCurYear(){
  var endDate = new Date();
  
  return endDate.getYear();
}

//获取当前月份
function getCurMonth(){
  var endDate = new Date();
  
  return endDate.getMonth() + 1;
}

//校验是否全由数字组成
function isDigit(s)
{
  var patrn=/^[0-9]{1,20}$/;
  if (!patrn.exec(s)) return false
  return true
}

//校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
function isTel(s)
{
  //var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?(\d){1,12})+$/;
  var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
  if (!patrn.exec(s)) return false
  return true
}

//校验手机号码：必须以数字开头，除数字外，可含有“-”
function isMobil(s)
{
  var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
  if (!patrn.exec(s)) return false
  return true
}

//校验邮政编码
function isPostalCode(s)
{
  //var patrn=/^[a-zA-Z0-9]{3,12}$/;
  var patrn=/^[a-zA-Z0-9 ]{3,12}$/;
  if (!patrn.exec(s)) return false
  return true
}