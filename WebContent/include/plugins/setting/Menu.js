Ext.onReady(function(){

	var westPanel = Ext.create('Ext.panel.Panel',{
		title : '菜单树',
		collapsible : true,
		tools : [{
			type: 'refresh',
			handler : function() {
				Ext.getCmp('menuTree').store.load({
					node : Ext.getCmp('menuTree').getRootNode()
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
			id : 'menuTree',
			name : 'menuTree',
			rootVisible: true,
    		autoScroll : true,
			animate : false,
			useArrows : false,
			border : false,
			store: Ext.create("Ext.data.TreeStore",{
    			proxy: {
			            type: 'ajax',
			            url: root + '/treeLoad/menuTreeInit.freda'
			        },
			    root : {
			    	text : '外科管家后台管理系统',
			    	//expanded: true,
			    	id   : 1,
			    	leaf : 0,
			    	icon : '',
			    	parent : 0
			    }  
    		}),
    		listeners : [{
    			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
    				Ext.getCmp('pId').setValue(record.get('id'));
    				Ext.data.StoreManager.lookup('menuStore').load({
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
			id : 'menuGrid',
			name : 'menuGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'menuStore',
				pageSize : 50,
				fields:[{
					name : 'mId'
				}, {
					name : 'mName'
				}, {
					name : 'pId'
				},{
					name : 'mRequest'
				},{
					name : 'mType'
				},{
					name : 'mState'
				},{
					name : 'mSortNo'
				},{
					name : 'mDesc'
				},{
					name : 'mLeaf'
				},{
					name : 'pName'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/menu/listMenu.freda',
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
				text : '名称',
				dataIndex: 'mName'
			},{
				text : '类型',
				dataIndex: 'mType',
				renderer : function(value){
					return getCodeText('MENU_TYPE',value);
				}
			},{
				text : '状态',
				dataIndex: 'mState',
				renderer : function(value){
					return getCodeText('STATE',value);
				}
			},{
				text : 'URL',
				dataIndex: 'mRequest'
			},{
				text : '排序号',
				dataIndex: 'mSortNo'
			},{
				text : '描述',
				dataIndex: 'mDesc'
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
							Ext.data.StoreManager.lookup('menuStore').load({
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
					Ext.data.StoreManager.lookup('menuStore').load({
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
					addOrEditMenuWinInit(SUBMIT_MODE_ADD);
				}
			},'-',{
				text : '编辑',
				iconCls : 'page_edit_1Icon',
				handler : function() {
					addOrEditMenuWinInit(SUBMIT_MODE_EDIT);
				}
			},'-',{
				text : '删除',
				iconCls : 'page_delIcon',
				handler : function() {
					delMenu();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('menuStore'), // same store GridPanel is using
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
		height : 280,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>菜单详情</span>',
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
			id : 'menuFormPanel',
			name : 'menuFormPanel',
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
		    	fieldLabel : '父级菜单',
		    	disabled : true,
		    	id : 'pName',
		    	name : 'pName',
		    	readOnly : true
		    },{
		    	fieldLabel : '菜单名称',
				blankText : me.show_tips.blank_text_msg,
				name : 'mName',
				id : 'mName',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '类型',
		    	id : 'mType',
				name : 'mType',
				editable : false,
				triggerAction : 'all',
				queryMode: 'local',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					data : getCodeArray('MENU_TYPE')
				}),
				displayField: 'name',
    			valueField: 'code'
		    },{
		    	fieldLabel : '请求地址',
				blankText : me.show_tips.blank_text_msg,
				name : 'mRequest',
				id : 'mRequest',
				allowBlank : false
		    },{
		    	fieldLabel : '菜单描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'mDesc',
				id : 'mDesc',
				allowBlank : false
		    },{
		    	fieldLabel : '排序号',
		    	xtype : 'numberfield',
		    	maxValue: 99,
        		minValue: 0,
        		step: 1,
				blankText : me.show_tips.blank_text_msg,
				name : 'mSortNo',
				id : 'mSortNo',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'mState',
				name : 'mState',
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
		    	id : 'mId',
		    	name : 'mId'
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
	
	function addOrEditMenuWinInit(mode)
	{
		if(mode == 'add')
		{
			record = Ext.getCmp('menuTree').getSelection()[0];
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			var pId = record.get('id');
			Ext.getCmp('menuFormPanel').getForm().reset();
			Ext.getCmp('pId').setValue(pId);
			Ext.getCmp('pName').setValue(record.get('text'));
		}
		else
		{
			record = Ext.getCmp('menuGrid').getSelectionModel().getSelection();
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			Ext.getCmp('menuFormPanel').getForm().reset();
			Ext.getCmp('menuFormPanel').getForm().loadRecord(record[0]);
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
			//Ext.getCmp('superMenu').setValue();
		}
		addOrEditWin.show();
	}
	/**
	 * 
	 */
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/menu/addMenu.freda";
		}
		else
		{
			submitUrl += "/menu/editMenu.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('menuFormPanel').getForm().submit({
						clientValidation: true,
						url:submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						success: function(form, action) {
						   addOrEditWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.getCmp('menuTree').store.load({
								node : Ext.getCmp('menuTree').getRootNode()
							});
					       Ext.data.StoreManager.lookup('menuStore').reload();
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
	/**
	 * DELETE
	 */
	function delMenu()
	{
		record = Ext.getCmp('menuGrid').getSelectionModel().getSelection();
		if(Ext.isEmpty(record))
		{
			me.showAlert(me.show_tips.no_choose_msg);
			return ;
		}
		if(record[0].get('mLeaf') != 1)
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
					    url : root + '/menu/delMenu.freda',
					    method : 'POST',
					    params : {
					    	mId : record[0].get('mId'),
					    	pId : record[0].get('pId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    	  Ext.getCmp('menuTree').store.load({
									node : Ext.getCmp('menuTree').getRootNode()
								});
							  Ext.data.StoreManager.lookup('menuStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
});