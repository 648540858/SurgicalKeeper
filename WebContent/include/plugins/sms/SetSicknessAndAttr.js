Ext.onReady(function(){

	var westPanel = Ext.create('Ext.panel.Panel',{
		title : '疾病列表',
		collapsible : true,
		tools : [{
			type: 'refresh',
			handler : function() {
				Ext.getCmp('sicknessTree').store.load({
					node : Ext.getCmp('sicknessTree').getRootNode()
				});
				
			}
		}],
		width : 210,
		layout : 'fit',
		minSize : 160,
		maxSize : 280,
		border : false,
		split : true,
		region : 'west',
		autoScroll : true,
		items : [Ext.create('Ext.tree.Panel',{
			id : 'sicknessTree',
			name : 'sicknessTree',
			rootVisible: false,
    		autoScroll : true,
			animate : false,
			useArrows : false,
			border : false,
			store: Ext.create("Ext.data.TreeStore",{
    			proxy: {
			            type: 'ajax',
			            url: root + '/sicknessSetAttr/sicknessTreeInit.freda'
			        },
			    root : {
			    	text : '北京瑞吉康星',
			    	//expanded: true,
			    	id   : -1,
			    	leaf : 0,
			    	icon : '',
			    	parent : 0
			    }  
    		}),
    		listeners : [{
    			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
    				if(record.get('leaf') != 1)
    				{
    					return ;
    				}
    				Ext.getCmp('sId').setValue(record.get('id').split('-')[1]);
					Ext.data.StoreManager.lookup('dataStore').load({
    					params : {
    						sId : record.get('id').split('-')[1],
    						start : 0,
    						limit : 50
    					}
    				});
    			}
    		}]
		})]
	});
	
	var centerPanel = Ext.create('Ext.panel.Panel',{
		region : 'center',
		layout : 'fit',
		border : false,
		autoLoad: false,
		pageSize: 50,
		split : true,
		items : [Ext.create('Ext.grid.Panel',{
			//title : '菜单详情',
			id : 'dataGrid',
			name : 'dataGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'dataStore',
				pageSize : 50,
				fields:[{
					name : 'ssaId'
				}, {
					name : 'sId'
				}, {
					name : 'saId'
				},{
					name : 'ssaStatus'
				},{
					name : 'saName'
				},{
					name : 'content'
				},{
					name : 'lTime'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/sicknessSetAttr/listAttrWithContentBySickness.freda',
					actionMethods: {  
			            read: 'POST'  
			        },
					reader : {
						type : 'json',
						rootProperty : 'ROOT',
						totalProperty : 'TOTALCOUNT'
					}
				},
				listeners : [{
					'beforeload' : function (store, options){
						var new_params = { queryContent : Ext.getCmp('queryContent').getValue(),
						sId : Ext.getCmp('sId').getValue().split('-')[1]};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '属性值',
				dataIndex: 'saName'
			},{
				text : '状态',
				dataIndex: 'ssaStatus',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
			},{
				text : '内容',
				dataIndex: 'content',
				renderer : function(value){
					return "<font color=red>" + value + "</font>";
				}
			},{
				text : '最后修改时间',
				dataIndex: 'lTime'
			}],
			tbar : [{
				xtype : 'textfield',
				id : 'queryContent',
				name : 'queryContent',
				emptyText : '请输入名称..',
				width : 150,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							var queryContent = Ext.getCmp('queryContent').getValue();
							Ext.data.StoreManager.lookup('dataStore').load({
									params : {
										start : 0,
										limit : 50,
										queryContent : queryContent
									}
								});	
						}
					}
				}
			},{
				text : '查询',
				iconCls : 'page_findIcon',
				handler : function() {
					var queryContent = Ext.getCmp('queryContent').getValue();	
					Ext.data.StoreManager.lookup('dataStore').load({
							params : {
								start : 0,
								limit : 50,
								queryContent : queryContent
							}
						});	
				}
			},'->',{
				text : '添加',
				iconCls : 'page_addIcon',
				handler : function() {
					addOrEditWinInit(SUBMIT_MODE_ADD);
				}
			},'-',{
				text : '编辑',
				iconCls : 'page_edit_1Icon',
				handler : function() {
					addOrEditWinInit(SUBMIT_MODE_EDIT);
				}
			},'-',{
				text : '删除',
				iconCls : 'page_delIcon',
				handler : function() {
					delData();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('dataStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }],
		    selModel :{
		    	selType : 'checkboxmodel',
		    	mode  : 'SIMPLE'
		    },
		    listeners : [{
					'celldblclick' : function (grid, td, cellIndex, record, tr, rowIndex, e, eOpts){
						Ext.getCmp('dataGrid').getSelectionModel().select(rowIndex);
						addOrEditWinInit(SUBMIT_MODE_EDIT);
					}
				}]
		})]
	});
	initBorderViewPort([westPanel,centerPanel]);
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 900,
		height : 490,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>管家关爱内容</span>',
		modal : true,
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 40,
		pageX : document.body.clientWidth / 2 - 900 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [Ext.create('Ext.form.Panel',{
			id : 'dataFormPanel',
			name : 'dataFormPanel',
			labelAlign : 'right',
			labelWidth : 110,
			frame : false,
			layout: 'anchor',
			bodyPadding: 5,
			defaults: {
		        anchor: '99%'
		    },
		    defaultType: 'textfield',
		    items: [{
		    	xtype : 'combo',
		    	fieldLabel : '配置属性',
		    	labelWidth : 112,
		    	id : 'saId',
				name : 'saId',
				editable : false,
				triggerAction : 'all',
				queryMode: 'remote',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					id : 'sicknessAttrCombo',
					fields: ['name', 'code'],
					proxy : {
					type : 'ajax',
					url  : root + '/sicknessSetAttr/listAllAttrForCombox.freda',
					actionMethods: {  
			            read: 'POST'  
			        },
					reader : {
						type : 'json'
					}
				}
				}),
				displayField: 'name',
    			valueField: 'code'
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'ssaStatus',
				name : 'ssaStatus',
				labelWidth : 112,
				editable : false,
				triggerAction : 'all',
				queryMode: 'local',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					data : getCodeArray('STATE')
				}),
				displayField: 'name',
    			valueField: 'code'
		    },{
				xtype : 'fieldset',
				title : '内容',
				defaultType : 'textfield',
				layout : 'fit',
				defaults : {
					anchor : '100%'
				},
				items : [ {
					xtype : 'extkindeditor',
					allowBlank : false,
					name : 'content',
					height : 305,
					id : 'content',
					fieldLabel : '内容'
				}]
			},{
		    	xtype : 'hiddenfield',
		    	id : 'ssaId',
		    	name : 'ssaId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'sId',
		    	name : 'sId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'submitMode',
		    	name : 'submitMode'
		    }]
		})],
		buttons : [{
			text : '保存',
			id : 'btnSave',
			iconCls : 'acceptIcon',
			handler : function() {	
				submitFormInfo();
			}
		},{
			text : '关闭',
			id : 'btnClose',
			iconCls : 'deleteIcon',
			handler : function() {
				addOrEditWin.hide();
			}
		}]
	});
	
	Ext.data.StoreManager.lookup('sicknessAttrCombo').load();
	
	function addOrEditWinInit(mode)
	{
		if(mode == 'add')
		{
			record = Ext.getCmp('sicknessTree').getSelection()[0];
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record.get('leaf') != 1)
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			var sId = record.get('id').split('-')[1];
			Ext.getCmp('dataFormPanel').getForm().reset();
			Ext.getCmp('sId').setValue(sId);
		}
		else
		{
			record = Ext.getCmp('dataGrid').getSelectionModel().getSelection();
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record.length > 1)
			{
				me.showAlert(me.show_tips.choose_one_msg);
				return ;
			}
			//Ext.getCmp('dataFormPanel').getForm().loadRecord(record[0]);
			
			Ext.getCmp('dataFormPanel').getForm().load({
				url:root + '/sicknessSetAttr/loadContent.freda?ssaId=' + record[0].get('ssaId'),
				success : function(form, action){
					Ext.getCmp('content').setValue(action.result.data.content);
				}
			});
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
		}
		addOrEditWin.show();
	}
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/sicknessSetAttr/addAttrWithContent.freda";
		}
		else
		{
			submitUrl += "/sicknessSetAttr/editAttrContent.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('dataFormPanel').getForm().submit({
						clientValidation: true,
						url:submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						params : {
							dContent : Ext.getCmp('content').getValue()
						},
						success: function(form, action) {
						   addOrEditWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.data.StoreManager.lookup('dataStore').reload();
					    },
					    failure: function(form, action) {
					        switch (action.failureType) {
					            case Ext.form.action.Action.CLIENT_INVALID:
					                me.showAlert(me.show_tips.form_invalid_error_msg);
					                break;
					            case Ext.form.action.Action.CONNECT_FAILURE:
					                me.showAlert(me.show_tips.ajax_error_msg);
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               me.showAlert(action.result.msg);
					       }
					    }
					});
		        }
		    }
		});
	}
	function delData()
	{
		record = Ext.getCmp('dataGrid').getSelectionModel().getSelection();
		if(Ext.isEmpty(record))
		{
			me.showAlert(me.show_tips.no_choose_msg);
			return ;
		}
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_del_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/sicknessSetAttr/delAttrContent.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'ssaId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
							  Ext.data.StoreManager.lookup('dataStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
});