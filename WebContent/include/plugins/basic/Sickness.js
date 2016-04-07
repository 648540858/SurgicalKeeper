Ext.onReady(function(){

	var node;
	
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
					name : 'sId'
				},{
					name : 'sName'
				},{
					name : 'stId'
				},{
					name : 'stName'
				},{
					name : 'sDesc'
				},{
					name : 'sStatus'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/sickness/listSickness.freda',
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
				dataIndex : 'sName'
			},{
				text : '分类',
				dataIndex : 'stName'
			},{
				text : '描述',
				dataIndex: 'sDesc'
			},{
				text : '状态',
				dataIndex : 'sStatus',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
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
			},'->','-',{
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
					delSickness();
				}
			},'-',{
				text : '分类维护',
				iconCls : 'arrow_switchIcon',
				handler : function() {
					sicknessCateWinInit();
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
		    }
		})]
	});
	initBorderViewPort([centerPanel]);
	
	Ext.data.StoreManager.lookup('dataStore').load({
		params : {
			start : 0,
			limit : 50
		}
	});
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 200,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>疾病科室信息</span>',
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
		    	fieldLabel : '类型',
		    	id : 'stId',
				name : 'stId',
				editable : false,
				triggerAction : 'all',
				queryMode: 'remote',
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					proxy : {
					type : 'ajax',
					url  : root + '/sickness/listAllSicknessTypeForCombox.freda',
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
		    	fieldLabel : '名称',
				blankText : me.show_tips.blank_text_msg,
				name : 'sName',
				id : 'sName',
				allowBlank : false
		    },{
		    	fieldLabel : '描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'sDesc',
				id : 'sDesc',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'sStatus',
				name : 'sStatus',
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
	
	var cateViewWin = Ext.create('Ext.window.Window',{
		layout : 'border',
		width : '100%',
		height : '100%',
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>疾病分类信息</span>',
		modal : true,
		collapsible : false,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'center',
		border : false,
		animCollapse : true,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [Ext.create('Ext.panel.Panel',{
			title : '下级分类树',
			collapsible : false,
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
				rootVisible: true,
	    		autoScroll : true,
				animate : false,
				useArrows : false,
				border : false,
				store: Ext.create("Ext.data.TreeStore",{
	    			proxy: {
				            type: 'ajax',
				            url: root + '/sickness/sicknessSubTreeInit.freda'
				        },
				    root : {
				    	text : '未定义',
				    	//expanded: true,
				    	id   : 0,
				    	leaf : 0,
				    	icon : '',
				    	parent : 0
				    }  
	    		}),
	    		listeners : [{
	    			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
	    				node = record.get('id').split('-');
	    				Ext.data.StoreManager.lookup('subDataStore').load({
	    					params : {
	    						sId : node[0],
	    						pId : node[1],
	    						start : 0,
								limit : 50
	    					}
	    				});
	    			}
	    		}]
			})]
		}),Ext.create('Ext.grid.Panel',{
			title : '级联详情',
			id : 'subDataGrid',
			name : 'subDataGrid',
			frame : true,
			autoScroll : true,
			region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'subDataStore',
				pageSize : 50,
				fields:[{
					name : 'subId'
				},{
					name : 'subName'
				},{
					name : 'subDesc'
				},{
					name : 'subSort'
				},{
					name : 'pId'
				},{
					name : 'subLeaf'
				},{
					name : 'subSId'
				},{
					name : 'subStatus'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/sickness/listSicknessSub.freda',
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
						var new_params = { sId : node[0] , pId : node[1]};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '名称',
				dataIndex : 'subName'
			},{
				text : '描述',
				dataIndex : 'subDesc'
			},{
				text : '排序号',
				dataIndex: 'subSort'
			},{
				text : '状态',
				dataIndex : 'subStatus',
				renderer : function(value){
					if(value == 1)
						return "<font color=green>" + getCodeText('STATE',value) + "</font>";
					else
						return "<font color=red>" + getCodeText('STATE',value) + "</font>";
				}
			}],
			tbar : ['->','-',{
				text : '添加',
				iconCls : 'page_addIcon',
				handler : function() {
					addOrEditCateWinInit(SUBMIT_MODE_ADD);
				}
			},'-',{
				text : '编辑',
				iconCls : 'page_edit_1Icon',
				handler : function() {
					addOrEditCateWinInit(SUBMIT_MODE_EDIT);
				}
			},'-',{
				text : '删除',
				iconCls : 'page_delIcon',
				handler : function() {
					delSub();
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('subDataStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }],
		    selModel :{
		    	selType : 'checkboxmodel',
		    	mode  : 'SINGLE'
		    }
		})],
		buttons : [{
			text : '关闭',
			id : 'subBtnClose',
			iconCls : 'deleteIcon',
			handler : function() {
				cateViewWin.hide();
			}
		}]
	}); 
	
	var addOrEditSubWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 200,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>级联信息</span>',
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
			id : 'subDataFormPanel',
			name : 'subDataFormPanel',
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
				name : 'subName',
				id : 'subName',
				allowBlank : false
		    },{
		    	fieldLabel : '描述',
				blankText : me.show_tips.blank_text_msg,
				name : 'subDesc',
				id : 'subDesc',
				allowBlank : false
		    },{
		    	fieldLabel : '排序号',
		    	xtype : 'numberfield',
		    	maxValue: 99,
        		minValue: 0,
        		step: 1,
				blankText : me.show_tips.blank_text_msg,
				name : 'subSort',
				id : 'subSort',
				allowBlank : false
		    },{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'subStatus',
				name : 'subStatus',
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
		    	id : 'subId',
		    	name : 'subId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'pId',
		    	name : 'pId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'subSId',
		    	name : 'subSId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'subSubmitMode',
		    	name : 'subSubmitMode'
		    }]
		})],
		buttons : [{
			text : '保存',
			id : 'abSubBtnSave',
			iconCls : 'acceptIcon',
			handler : function() {	
				subFormSubmit();
			}
		},{
			text : '关闭',
			id : 'aeSubBtnClose',
			iconCls : 'deleteIcon',
			handler : function() {
				addOrEditSubWin.hide();
			}
		}]
	}); 
	
	
	function addOrEditWinInit(mode)
	{
		if(mode == 'add')
		{
			Ext.getCmp('dataFormPanel').getForm().reset();
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
			Ext.getCmp('dataFormPanel').getForm().loadRecord(record[0]);
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
		}
		addOrEditWin.show();
	}
	/**
	 * 分类Window Init
	 */
	function sicknessCateWinInit()
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
		Ext.getCmp('sicknessTree').setRootNode({
	    	text : record[0].get('sName'),
	    	id   : record[0].get('sId')+ '-0',
	    	leaf : 0,
	    	icon : '',
	    	parent : 0
	    });
	    Ext.getCmp('sicknessTree').collapseAll();
	    Ext.data.StoreManager.lookup('subDataStore').removeAll();
		cateViewWin.show();
	}
	/**
	 * 添加修改分类Window Init
	 */
	function addOrEditCateWinInit(mode)
	{
		
		
		if(mode == 'add')
		{
			record = Ext.getCmp('sicknessTree').getSelection()[0];
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			Ext.getCmp('subDataFormPanel').getForm().reset();
			Ext.getCmp('pId').setValue(node[1]);
			Ext.getCmp('subSId').setValue(node[0]);
		}
		else
		{
			record = Ext.getCmp('subDataGrid').getSelectionModel().getSelection();
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
			Ext.getCmp('subDataFormPanel').getForm().reset();
			Ext.getCmp('subDataFormPanel').getForm().loadRecord(record[0]);
			Ext.getCmp('subSubmitMode').setValue(SUBMIT_MODE_EDIT);
		}
		addOrEditSubWin.show();
	}
	/**
	 * ADD OR EDIT SUB
	 */
	function subFormSubmit()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('subSubmitMode').getValue()))
		{
			submitUrl += "/sickness/addSicknessSub.freda";
		}
		else
		{
			submitUrl += "/sickness/editSicknessSub.freda";
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('subDataFormPanel').getForm().submit({
						clientValidation: true,
						url : submitUrl,
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						success: function(form, action) {
						   addOrEditSubWin.hide();
					       me.showAlert(action.result.msg);
					       Ext.getCmp('sicknessTree').store.load({
								node : Ext.getCmp('sicknessTree').getRootNode()
							});
					       Ext.data.StoreManager.lookup('subDataStore').reload();
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
	 * DELETE CATE
	 */
	function delSub()
	{
		record = Ext.getCmp('subDataGrid').getSelectionModel().getSelection();
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
		if(record[0].get('subLeaf') != 1)
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
					    url : root + '/sickness/delSicknessSub.freda',
					    method : 'POST',
					    params : {
					    	subId : record[0].get('subId'),
					    	pId : record[0].get('pId'),
					    	sId : record[0].get('subSId')
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    	   Ext.getCmp('sicknessTree').store.load({
									node : Ext.getCmp('sicknessTree').getRootNode()
								});
							  Ext.data.StoreManager.lookup('subDataStore').reload();
					    }
					});
		        }
		    }
		});
	}
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/sickness/addSickness.freda";
		}
		else
		{
			submitUrl += "/sickness/editSickness.freda";
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
	
	function delSickness()
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
					    url : root + '/sickness/delSickness.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'sId')
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