Ext.onReady(function(){
	
	Ext.tip.QuickTipManager.init();
});

var me = parent.window;
var SUBMIT_MODE_EDIT = "edit";
var SUBMIT_MODE_ADD = "add";

var initBorderViewPort = function(panelArray)
{
	Ext.create('Ext.container.Viewport',{
		layout : 'border',
		items : panelArray
	});
	parent.window.Ext.getBody().unmask();
}
var codeArray =me.codeJson;

/**
 * 根据字典VAR,CODE 获取NAME
 */
var getCodeText = function(_list,_code)
{
	var codeText = "";
	for(var i=0;i<codeArray.length;i++)
	{
		if(codeArray[i].cList == _list && codeArray[i].cCode == _code)
		{
			codeText = codeArray[i].cText;
			break;
		}
	}
	return codeText;
}

var getCodeText2 = function(_var)
{
	var codeText = "";
	for(var i=0;i<codeArray.length;i++)
	{
		if(codeArray[i].cVar == _var)
		{
			codeText = codeArray[i].cText;
			break;
		}
	}
	return codeText;
}
/**
 * 根据VAR 找Code集合
 * @param _var
 * @return {}
 */
var getCodeArray = function(_var)
{
	var code = [];
	for(var i=0;i<codeArray.length;i++)
	{
		if(codeArray[i].cList == _var)
		{
			code.push({
				name : codeArray[i].cText,
				code : codeArray[i].cCode
			});
		}
	}
	return code;
}