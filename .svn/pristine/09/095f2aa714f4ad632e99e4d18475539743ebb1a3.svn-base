Ext.onReady(function(){

	var westPanel = Ext.create('Ext.panel.Panel',{
		title : '组织结构树',
		collapsible : true,
		tools : [{
			type: 'refresh',
			handler : function() {
				Ext.getCmp('deptTree').store.load({
					node : Ext.getCmp('deptTree').getRootNode()
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
			id : 'deptTree',
			name : 'deptTree',
			rootVisible: true,
    		autoScroll : true,
			animate : false,
			useArrows : false,
			border : false,
			store: Ext.create("Ext.data.TreeStore",{
    			proxy: {
			            type: 'ajax',
			            url: root + '/treeLoad/deptTreeInit.freda'
			        },
			    root : {
			    	text : '北京瑞吉康星',
			    	//expanded: true,
			    	id   : 1,
			    	leaf : 0,
			    	icon : '',
			    	parent : 0
			    }  
    		}),
    		listeners : [{
    			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
    				Ext.getCmp('dId').setValue(record.get('id'))
    				if(record.get('leaf') == 1){
    					Ext.data.StoreManager.lookup('userStore').load({
	    					params : {
	    						dId : record.get('id'),
	    						start : 0,
	    						limit : 50
	    					}
	    				});
    				}
    				else{
    					me.showAlert(me.show_tips.tree_leaf_sel_msg);
    				}
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
			id : 'userGrid',
			name : 'userGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'userStore',
				pageSize : 50,
				fields:[{
					name : 'uId'
				}, {
					name : 'uName'
				}, {
					name : 'uAccount'
				},{
					name : 'uTel'
				},{
					name : 'uSex'
				},{
					name : 'uLocked'
				},{
					name : 'uState'
				},{
					name : 'uDesc'
				},{
					name : 'dName'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/organization/listUser.freda',
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
						dId : Ext.getCmp('dId').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '姓名',
				dataIndex: 'uName'
			},{
				text : '账号',
				dataIndex: 'uAccount'
			},{
				text : '联系方式',
				dataIndex: 'uTel'
			},{
				text : '性别',
				dataIndex: 'uSex',
				renderer : function(value){
					return getCodeText('SEX',value);
				}
			},{
				text : '锁定',
				dataIndex: 'uLocked',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('LOCKED',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('LOCKED',value) + "</font>";
						
				}
			},{
				text : '部门',
				dataIndex : 'dName'
			},{
				text : '状态',
				dataIndex: 'uState',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
			},{
				text : '描述',
				dataIndex: 'uDesc'
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
							Ext.data.StoreManager.lookup('userStore').load({
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
					Ext.data.StoreManager.lookup('userStore').load({
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
					delUser();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('userStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }],
		    selModel :{
		    	selType : 'checkboxmodel',
		    	mode  : 'SIMPLE'
		    }
		})]
	});
	initBorderViewPort([westPanel,centerPanel]);
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 340,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>用户信息</span>',
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
			id : 'userFormPanel',
			name : 'userFormPanel',
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
		    	fieldLabel : '部门',
		    	disabled : true,
		    	id : 'dName',
		    	name : 'dName',
		    	readOnly : true
		    },{
		    	fieldLabel : '名称',
				blankText : me.show_tips.blank_text_msg,
				name : 'uName',
				id : 'uName',
				allowBlank : false
		    },{
		    	fieldLabel : '账号',
				blankText : me.show_tips.blank_text_msg,
				name : 'uAccount',
				id : 'uAccount',
				allowBlank : false
		    },{
		    	fieldLabel : '密码',
		    	inputType : 'password',
				blankText : me.show_tips.blank_text_msg,
				name : 'uPwd',
				id : 'uPwd',
				allowBlank : false
		    },{
		    	fieldLabel : '联系方式',
				blankText : me.show_tips.blank_text_msg,
				name : 'uTel',
				id : 'uTel',
				allowBlank : false
		    },{
		    	fieldLabel : '描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'uDesc',
				id : 'uDesc',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '性别',
		    	id : 'uSex',
				name : 'uSex',
				editable : false,
				triggerAction : 'all',
				queryMode: 'local',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					data : getCodeArray('SEX')
				}),
				displayField: 'name',
    			valueField: 'code'
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '锁定',
		    	id : 'uLocked',
				name : 'uLocked',
				editable : false,
				triggerAction : 'all',
				queryMode: 'local',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					data : getCodeArray('LOCKED')
				}),
				displayField: 'name',
    			valueField: 'code'
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'uState',
				name : 'uState',
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
		    	id : 'dId',
		    	name : 'dId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'uId',
		    	name : 'uId'
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
			record = Ext.getCmp('deptTree').getSelection()[0];
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record.get('leaf') == 0)
			{
				me.showAlert(me.show_tips.system_params_no_edit_msg);
				return ;
			}
			Ext.getCmp('userFormPanel').getForm().reset();
			Ext.getCmp('dId').setValue(record.get('id'));
			Ext.getCmp('dName').setValue(record.get('text'));
		}
		else
		{
			record = Ext.getCmp('userGrid').getSelectionModel().getSelection();
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record.length > 1)
			{
				me.showAlert(me.show_tips.choose_one_msg);
			}
			Ext.getCmp('userFormPanel').getForm().loadRecord(record[0]);
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
			//Ext.getCmp('superMenu').setValue();
		}
		addOrEditWin.show();
	}
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/organization/addUser.freda";
		}
		else
		{
			submitUrl += "/organization/editUser.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('userFormPanel').getForm().submit({
						clientValidation: true,
						url:submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						success: function(form, action) {
						   addOrEditWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.data.StoreManager.lookup('userStore').reload();
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
	
	function delUser()
	{
		record = Ext.getCmp('userGrid').getSelectionModel().getSelection();
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
					    url : root + '/organization/delUser.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'uId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
							  Ext.data.StoreManager.lookup('userStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
	function authorityWinInit()
	{
		record = Ext.getCmp('userGrid').getSelectionModel().getSelection();
		if(Ext.isEmpty(record))
		{
			me.showAlert(me.show_tips.no_choose_msg);
			return ;
		}
		if(record.length > 1)
		{
			me.showAlert(me.show_tips.choose_one_msg);
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
				id : 'saveUserId',
				name : 'saveUserId'
				},Ext.create('Ext.tab.Panel',{
				id : 'treeTabPanel',
				name : 'treeTabPanel',
				layout : 'fit',
				items :[{
					xtype : 'treepanel',
					id : 'roleTreePanel',
					title : '角色绑定',
					rootVisible: false,
		    		autoScroll : true,
					animate : false,
					useArrows : false,
					autoLoad : false,
					border : false,
					store: Ext.create("Ext.data.TreeStore",{
							proxy: {
							            type: 'ajax',
							            url: root + '/treeLoad/roleTreeWithSelectedInit.freda?uId=' + record[0].get('uId')
							        },
							root : {
								    	text : '角色列表',
								    	id   : 1,
								    	leaf : 0,
								    	icon : '',
								    	parent : 0
								    } 
						}),
						buttons : [{
						text : '角色绑定保存',
						handler : function(){
							saveRoleToUser();
						}
					}]
					}
				]
			})]
		}).show();
		Ext.getCmp('saveUserId').setValue(record[0].get('uId'));
		Ext.getCmp('roleTreePanel').expandAll();
	}
	
	function saveRoleToUser()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/authority/setRoleForUser.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(Ext.getCmp('roleTreePanel').getChecked(),'id'),
					    	uId : Ext.getCmp('saveUserId').getValue()
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    }
					});
		        }
		    }
		});
	}
});