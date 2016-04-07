/**
 * 是否为mobile
 * @param {} testStr
 * @return {Boolean}
 */
function isMobile(testStr)
{
	var reg = new RegExp('^1(3[0-9]|4[5,7]|7[0,1,3,6,7,8]|5[0,1,2,3,5,6,7,8,9]|8[0-9])\\d{8}$');
	if(reg.test(testStr))
		return true;
	else
		return false;
}
/**
 * 将JS数组转换为JS字符串 表格复选框专用
 */
function jsArray2JsString(arrayChecked, id) {
	var strChecked = "";
	for (var i = 0; i < arrayChecked.length; i++) {
		strChecked = strChecked + arrayChecked[i].get(id) + ',';
	}
	return strChecked.substring(0, strChecked.length - 1)
}
/**
 * 
 * @param {} value
 * @return {}
 */
function dateFormat(value){ 
    if(null != value){ 
        return Ext.Date.format(new Date(value),'Y-m-d H:i:s'); 
    }else{ 
        return null; 
    } 
} 
/**
 * 系统提示
 * @param {} msg
 */
function showWaitMsg(msg) 
{
	Ext.MessageBox.show({
				title : '系统提示',
				msg : msg == null ? '正在处理数据,请稍候...' : msg,
				progressText : 'processing now,please wait...',
				width : 300,
				wait : true,
				waitConfig : {
					interval : 150
				}
			});
}
/**
 * 隐藏请求等待进度条窗口
 */
function hideWaitMsg() 
{
	Ext.MessageBox.hide();
}

/**
 * 通过iFrame实现类ajax文件下载
 */
function exportExcel(url) 
{
	var exportIframe = document.createElement('iframe');
	exportIframe.src = url;
	exportIframe.style.display = "none";
	document.body.appendChild(exportIframe);
	hideWaitMsg();
}
/**
 * Ext.alert
 */
function showAlert(msg)
{
	Ext.MessageBox.alert(show_tips.alert_tips_title,show_tips.alert_tips_head_msg + msg);
}
var show_tips = {
		wait_tips_msg : "数据加载中..请稍后.." ,
		alert_tips_title : "温馨提示",
		alert_tips_head_msg : "<span><font color=blue>提示:</font></span>&nbsp;&nbsp;",
		blank_text_msg : '所填项不能为空!',
		no_choose_msg : '该项操作需点选后操作!',
		choose_one_msg : '编辑选项只可选择一个进行修改!',
		system_params_no_edit_msg : '该项为系统内置类型,不可进行操作!',
		system_params_no_msg : '选中项含有系统内置类型,请重新选择!',
		form_invalid_error_msg : '填写有误,请认真核对!',
		ajax_error_msg : '系统内部出错,请重新提交或联系管理员!',
		ajax_timeout_msg : 'SESSION过期啦,请出新登录!',
		tree_leaf_del_msg : '请从最底端逐级删除,不可跨级删除!',
		tree_leaf_sel_msg : '请点选至叶子节点!',
		is_want_to_del_msg : '确定要删除该项?',
		is_want_to_do_msg : '确定要保存数据?'
		
}