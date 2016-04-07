Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath('MyApp.ux', root + '/resources/pro/js/ux');
Ext.require([
	'MyApp.ux.datetimepicker.DateTime',
	'MyApp.ux.Ajax',
	'MyApp.ux.Comboboxtree',
	'MyApp.ux.ExtKindEditor',
	'MyApp.ux.IFrameComponent',
	'MyApp.ux.ImgView'
]);

Ext.Ajax.on('requestcomplete', function(conn,response,options){
	try{
		if(response.getResponseHeader("sessionstatus")){
			window.location = "/SurgicalKeeper/index/indexPageInit.freda";
		}
	}
	catch(e){
		
	}
});

Ext.define('Ext.ux.Ajax',{
	extend: 'Ext.data.Connection',
	method : 'POST',
	maskText : '',
	autoAbort : false,
	singleton: true,
    
	getSuperPage : function(){
		return parent.window;
	},
	listeners : [{
		'beforerequest' : function(conn, options, eOpts){
			Ext.getBody().mask(this.maskText == '' ? this.getSuperPage().show_tips.wait_tips_msg : maskText);
		}
	},{
		'requestcomplete' : function(conn, response, options, eOpts){
			Ext.getBody().unmask();
		}
	},{
		'requestexception' : function( conn, response, options, eOpts ){
			Ext.getBody().unmask();
			this.getSuperPage().showAlert(this.getSuperPage().show_tips.ajax_error_msg);
		}
	}]
});

Ext.apply(Ext.tree.Panel,{
		listeners : {
			'checkchange': function(node, checked){
			
				listenerCheck(node, checked);
				
			}
		}
	});
	
var listenerCheck = function(node, checked) {
	childHasChecked(node, checked);
	var parentNode = node.parentNode;
	if (parentNode != null && !Ext.isEmpty(parentNode.get('checked'))) {
		parentCheck(parentNode, checked);
	}
};
	// 级联选中父节点
var parentCheck = function(node, checked) {
	var childNodes = node.childNodes;
	
	if(checked){
		node.set('checked', true);
	}else{
		var a = true;
		for (var i = 0; i < childNodes.length; i++){
			if (childNodes[i].get('checked')){
				node.set('checked', true);
				a = false;
				continue;
			}
		}
		if(a){node.set('checked', false);}
	}
	var parentNode = node.parentNode;
	if (parentNode != null) {
		parentCheck(parentNode, checked);
	}
}
// 级联选择子节点
var childHasChecked = function(node, checked) {
	node.cascadeBy(function(child) {
				child.set("checked", checked)
			});
}


