Ext.onReady(function(){

	var centerPanel = Ext.create('Ext.panel.Panel',{
		region : 'center',
		layout : 'fit',
		border : false,
		autoLoad: false,
		pageSize: 50,
		split : true,
		items : [Ext.create('Ext.grid.Panel',{
			//title : '菜单详情',
			id : 'roleGrid',
			name : 'roleGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'roleStore',
				pageSize : 50,
				fields:[{
					name : 'rId'
				}, {
					name : 'rName'
				}, {
					name : 'rDesc'
				},{
					name : 'cTime'
				},{
					name : 'rState'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/organization/listRole.freda',
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
						var new_params = { queryContent : Ext.getCmp('queryContent').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '名称',
				dataIndex: 'rName'
			},{
				text : '描述',
				dataIndex : 'rDesc'
			},{
				text : '状态',
				dataIndex: 'rState',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
			},{
				text : '创建时间',
				dataIndex: 'cTime'
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
							Ext.data.StoreManager.lookup('roleStore').load({
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
					Ext.data.StoreManager.lookup('roleStore').load({
							params : {
								start : 0,
								limit : 50,
								queryContent : queryContent
							}
						});	
				}
			},'->',{
				text : '权限管理',
				iconCls : 'edit1Icon',
				handler : function() {
					authorityWinInit();
				}
			},'-',{
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
					delRole();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('roleStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }],
		    selModel :{
		    	selType : 'checkboxmodel',
		    	mode  : 'SIMPLE'
		    }
		})]
	});
	initBorderViewPort([centerPanel]);
	
	Ext.data.StoreManager.lookup('roleStore').load({
		params : {
			start : 0,
			limit : 50
		}
	});	
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 170,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>角色信息</span>',
		modal : true,
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 40,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [Ext.create('Ext.form.Panel',{
			id : 'roleFormPanel',
			name : 'roleFormPanel',
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
		    	fieldLabel : '名称',
				blankText : me.show_tips.blank_text_msg,
				name : 'rName',
				id : 'rName',
				allowBlank : false
		    },{
		    	fieldLabel : '描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'rDesc',
				id : 'rDesc',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'rState',
				name : 'rState',
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
		    	xtype : 'hiddenfield',
		    	id : 'rId',
		    	name : 'rId'
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
	
	function addOrEditWinInit(mode)
	{
		if(mode == 'add')
		{
			Ext.getCmp('roleFormPanel').getForm().reset();
		}
		else
		{
			record = Ext.getCmp('roleGrid').getSelectionModel().getSelection();
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
			Ext.getCmp('roleFormPanel').getForm().loadRecord(record[0]);
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
		}
		addOrEditWin.show();
	}
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/organization/addRole.freda";
		}
		else
		{
			submitUrl += "/organization/editRole.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('roleFormPanel').getForm().submit({
						clientValidation: true,
						url:submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						success: function(form, action) {
						   addOrEditWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.data.StoreManager.lookup('roleStore').reload();
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
	
	function delRole()
	{
		record = Ext.getCmp('roleGrid').getSelectionModel().getSelection();
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
					    url : root + '/organization/delRole.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'rId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
							  Ext.data.StoreManager.lookup('roleStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
	function authorityWinInit()
	{
		record = Ext.getCmp('roleGrid').getSelectionModel().getSelection();
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
		
		Ext.create('Ext.window.Window',{
			layout : 'fit',
			width : 350,
			height : 370,
			resizable : false,
			draggable : true,
			closeAction : 'destroy',
			title : '<span>角色授权</span>',
			modal : true,
			collapsible : true,
			titleCollapse : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			pageY : 40,
			pageX : document.body.clientWidth / 2 - 420 / 2,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [{
				xtype : 'hiddenfield',
				id : 'saveRoleId',
				name : 'saveRoleId'
				},Ext.create('Ext.tab.Panel',{
				id : 'treeTabPanel',
				name : 'treeTabPanel',
				layout : 'fit',
				items :[{
					xtype : 'treepanel',
					id : 'menuTreePanel',
					title : '菜单权限',
					rootVisible: false,
		    		autoScroll : true,
					animate : false,
					useArrows : false,
					autoLoad : false,
					border : false,
					store: Ext.create("Ext.data.TreeStore",{
							proxy: {
							            type: 'ajax',
							            url: root + '/treeLoad/menuTreeWithSelectedInit.freda?rId=' + record[0].get('rId')
							        },
							root : {
								    	text : '系统菜单',
								    	id   : 1,
								    	leaf : 0,
								    	icon : '',
								    	parent : 0
								    } 
						}),
						buttons : [{
						text : '菜单权限保存',
						handler : function(){
							saveMenuToRole();
						}
					}]
					},{
					xtype : 'treepanel',
					id : 'userTreePanel',
					title : '绑定人员',
					rootVisible: false,
		    		autoScroll : true,
					animate : false,
					useArrows : false,
					autoLoad : false,
					border : false,
					store: Ext.create("Ext.data.TreeStore",{
							proxy: {
							            type: 'ajax',
							            url: root + '/treeLoad/userTreeWithSelectedInit.freda?rId=' + record[0].get('rId')
							        },
							root : {
								    	text : '',
								    	id   : 1,
								    	leaf : 0,
								    	icon : '',
								    	parent : 0
								    } 
						}),
					buttons : [{
						text : '绑定人员保存',
						handler : function(){
							saveUserToRole();
						}
					}]
					}
				]
			})]
		}).show();
		Ext.getCmp('saveRoleId').setValue(record[0].get('rId'));
		Ext.getCmp('userTreePanel').expandAll();
		Ext.getCmp('menuTreePanel').expandAll();
	}
	
	function saveMenuToRole()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/authority/setMenuForRole.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(Ext.getCmp('menuTreePanel').getChecked(),'id'),
					    	rId : Ext.getCmp('saveRoleId').getValue()
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
							  //Ext.data.StoreManager.lookup('roleStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
	function saveUserToRole()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/authority/setUserForRole.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(Ext.getCmp('userTreePanel').getChecked(),'id'),
					    	rId : Ext.getCmp('saveRoleId').getValue()
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
							  //Ext.data.StoreManager.lookup('roleStore').reload();
					    }
					});
		        }
		    }
		});
	}
});