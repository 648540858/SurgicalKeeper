Ext.define('MyApp.ux.IFrameComponent' ,{
	extend: 'Ext.Component',
	constructor : function(config){
		this.callParent(arguments);
	},
	alias : 'iframecomponent',
	loadMask : '玩命加载中...',
	renderTpl : '<iframe src="{url}" id="{id}-iframeEl" frameBorder="0" width="100%" height="100%" data-ref="iframeEl"></iframe>',
	childEls : ['iframeEl'],
	initComponent : function(){
		this.callParent();
  		Ext.getBody().mask(this.loadMask);
 	},
 	initEvents : function(){
 		this.callParent();
 	},
 	onRender : function(ct, position){
   		this.callParent();
   		this.iframeEl.dom.src = this.url;
 	}
	
});