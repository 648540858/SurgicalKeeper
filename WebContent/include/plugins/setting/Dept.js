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
    				Ext.getCmp('pId').setValue(record.get('id'))
					Ext.data.StoreManager.lookup('deptStore').load({
    					params : {
    						pId : record.get('id'),
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
			id : 'deptGrid',
			name : 'deptGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'deptStore',
				pageSize : 50,
				fields:[{
					name : 'dId'
				}, {
					name : 'dName'
				}, {
					name : 'pId'
				},{
					name : 'dDesc'
				},{
					name : 'dState'
				},{
					name : 'dSortNo'
				},{
					name : 'dLeaf'
				},{
					name : 'pName'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/organization/listDept.freda',
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
						pId : Ext.getCmp('pId').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '部门名称',
				dataIndex: 'dName'
			},{
				text : '部门状态',
				dataIndex: 'dState',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
			},{
				text : '排序号',
				dataIndex: 'dSortNo'
			},{
				text : '描述',
				dataIndex: 'dDesc'
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
							Ext.data.StoreManager.lookup('deptStore').load({
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
					Ext.data.StoreManager.lookup('deptStore').load({
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
					delDept();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('deptStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }],
		    selModel :{
		    	selType : 'checkboxmodel',
		    	mode  : 'SINGLE'
		    }
		})]
	});
	initBorderViewPort([westPanel,centerPanel]);
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 230,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>部门信息</span>',
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
			id : 'deptFormPanel',
			name : 'deptFormPanel',
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
		    	fieldLabel : '父级部门',
		    	disabled : true,
		    	id : 'pName',
		    	name : 'pName',
		    	readOnly : true
		    },{
		    	fieldLabel : '名称',
				blankText : me.show_tips.blank_text_msg,
				name : 'dName',
				id : 'dName',
				allowBlank : false
		    },{
		    	fieldLabel : '描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'dDesc',
				id : 'dDesc',
				allowBlank : false
		    },{
		    	fieldLabel : '排序号',
		    	xtype : 'numberfield',
		    	maxValue: 99,
        		minValue: 0,
        		step: 1,
				blankText : me.show_tips.blank_text_msg,
				name : 'dSortNo',
				id : 'dSortNo',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'dState',
				name : 'dState',
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
		    	id : 'pId',
		    	name : 'pId'
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
			var pId = record.get('id');
			Ext.getCmp('deptFormPanel').getForm().reset();
			Ext.getCmp('pId').setValue(pId);
			Ext.getCmp('pName').setValue(record.get('text'));
		}
		else
		{
			record = Ext.getCmp('deptGrid').getSelectionModel().getSelection();
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record[0].get('dId') == 1)
			{
				me.showAlert(me.show_tips.system_params_no_edit_msg);
				return ;
			}
			Ext.getCmp('deptFormPanel').getForm().loadRecord(record[0]);
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
			submitUrl += "/organization/addDept.freda";
		}
		else
		{
			submitUrl += "/organization/editDept.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('deptFormPanel').getForm().submit({
						clientValidation: true,
						url:submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						success: function(form, action) {
						   addOrEditWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.getCmp('deptTree').store.load({
								node : Ext.getCmp('deptTree').getRootNode()
							});
					       Ext.data.StoreManager.lookup('deptStore').reload();
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
	
	function delDept()
	{
		record = Ext.getCmp('deptGrid').getSelectionModel().getSelection();
		if(Ext.isEmpty(record))
		{
			me.showAlert(me.show_tips.no_choose_msg);
			return ;
		}
		if(record[0].get('dLeaf') != 1)
		{
			me.showAlert(me.show_tips.tree_leaf_del_msg);
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
					    url : root + '/organization/delDept.freda',
					    method : 'POST',
					    params : {
					    	dId : record[0].get('dId'),
					    	pId : record[0].get('pId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    	  Ext.getCmp('deptTree').store.load({
									node : Ext.getCmp('deptTree').getRootNode()
								});
							  Ext.data.StoreManager.lookup('deptStore').reload();
					    }
					});
		        }
		    }
		});
	}
});