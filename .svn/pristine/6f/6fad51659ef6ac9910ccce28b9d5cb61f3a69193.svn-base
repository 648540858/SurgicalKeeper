Ext.define('MyApp.ux.Ajax',{
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