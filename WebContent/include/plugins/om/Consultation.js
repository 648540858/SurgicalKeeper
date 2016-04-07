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
					name : 'cId'
				},{
					name : 'cSeeCity'
				},{
					name : 'cpName'
				},{
					name : 'sTime'
				},{
					name : 'eTime'
				},{
					name : 'cStatus'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/consultation/queryConsultation.freda',
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
						var new_params = { status : Ext.getCmp('status').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '就诊城市',
				dataIndex : 'cSeeCity'
			},{
				text : '就诊人',
				dataIndex : 'cpName'
			},{
				text : '期望就诊时间(开始)',
				dataIndex: 'sTime'
			},{
				text : '期望就诊时间(结束)',
				dataIndex: 'eTime'
			},{
				text : '状态',
				dataIndex : 'cStatus',
				renderer : function(value){
					return getCodeText('CON_STATUS',value);
				}
			}],
			tbar : [{
		    	xtype : 'combo',
		    	fieldLabel : '状态',
		    	id : 'status',
				name : 'status',
				editable : false,
				triggerAction : 'all',
				queryMode: 'local',
				value : 0,
				allowBlank : false,
				blankText : me.show_tips.blank_text_msg,
				store : Ext.create('Ext.data.Store',{
					fields: ['name', 'code'],
					data : getCodeArray('CON_STATUS')
				}),
				displayField: 'name',
    			valueField: 'code',
    			listeners : [{
    				'select' : function(combo, record, eOpts){
    					Ext.data.StoreManager.lookup('dataStore').load({
							params : {
								status : combo.getValue()
							}
						});
    				}
    			}]
		    },'->','-',{
				text : '查看',
				iconCls : 'page_edit_1Icon',
				handler : function() {
					addOrEditWinInit(SUBMIT_MODE_EDIT);
				}
			},'-'],
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
			limit : 50,
			status : 0
		}
	});
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'column',
		width : 950,
		height : 460,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>疾病信息</span>',
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
			border : false,
			columnWidth : 0.4,
			bodyPadding: 5,
			defaults: {
		        anchor: '99%'
		    },
		    defaultType: 'textfield',
		    items: [{
		    	xtype: 'displayfield',
		        fieldLabel: '就诊城市',
		        id : 'cSeeCity',
		        name: 'cSeeCity'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '医保所在地',
		        id : 'cMiCity',
		        name: 'cMiCity'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '患者姓名',
		        id : 'cpName',
		        name: 'cpName'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '联系电话',
		        id : 'cMobile',
		        name: 'cMobile'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '期望时间(开始)',
		        id : 'cStime',
		        name: 'cStime'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '期望时间(结束)',
		        id : 'cEtime',
		        name: 'cEtime'
		    },{
		    	xtype: 'displayfield',
		        fieldLabel: '疾病信息',
		        id : 'cSi',
		        name: 'cSi'
		    },{
		    	xtype : 'displayfield',
		    	fieldLabel : '病痛描述',
				name : 'cSiDesc',
				id : 'cSiDesc'
		    },{
		    	xtype:'fieldset',
		        title: '客服填写信息',
		        collapsible: false,
		        bodyPadding: 15,
		        layout : 'anchor',
		    	items : [{
			    	xtype : 'combo',
			    	fieldLabel : '回复状态',
			    	id : 'cStatus',
			    	anchor: '99%',
					name : 'cStatus',
					editable : false,
					triggerAction : 'all',
					queryMode: 'local',
					allowBlank : false,
					blankText : me.show_tips.blank_text_msg,
					store : Ext.create('Ext.data.Store',{
						fields: ['name', 'code'],
						data : getCodeArray('CON_STATUS')
					}),
					displayField: 'name',
	    			valueField: 'code'
			    },{
			    	xtype : 'textareafield',
			    	fieldLabel : '客服描述',
					name : 'opInfo',
					id : 'opInfo',
					anchor: '99%',
					height : 50
			    }]
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'cId',
		    	name : 'cId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'submitMode',
		    	name : 'submitMode'
		    }]
		}),{
			xtype:'fieldset',
	        title: '客服操作日志',
	        collapsible: false,
	        bodyPadding: 15,
	        height : 390,
	        layout : 'fit',
	        columnWidth : 0.6,
			items : [Ext.create('Ext.grid.Panel',{
				title : '客服操作日志',
				id : 'opLogGrid',
				name : 'opLogGrid',
				frame : true,
				autoScroll : true,
				//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
				stripeRows : true, // 斑马线
				border : false,
				forceFit : true,
				maskOnDisable : true,
				store : Ext.create('Ext.data.Store',{
					storeId:'opLogStore',
					pageSize : 50,
					fields:[{
						name : 'kStatus'
					},{
						name : 'kName'
					},{
						name : 'kOptime'
					},{
						name : 'kOpInfo'
					}],
					proxy : {
						type : 'ajax',
						url  : root + '/consultation/listOpLog.freda',
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
							var new_params = { cId : Ext.getCmp('cId').getValue()};
							Ext.apply(store.proxy.extraParams, new_params);
						}
					}]
				}),
				columns : [{
					xtype: 'rownumberer'
				},{
					text : '操作人',
					dataIndex : 'kName'
				},{
					text : '操作时间',
					dataIndex : 'kOptime'
				},{
					text : '备注',
					dataIndex: 'kOpInfo'
				},{
					text : '状态',
					dataIndex : 'kStatus',
					renderer : function(value){
						return getCodeText('CON_STATUS',value);
					}
				}],
				dockedItems: [{
			        xtype: 'pagingtoolbar',
			        store: Ext.data.StoreManager.lookup('opLogStore'), // same store GridPanel is using
			        dock: 'bottom',
			        displayInfo: true
			    }]
			})]
		}],
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
		if(mode == 'edit')
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
			Ext.getCmp('dataFormPanel').getForm().load({
				url:root + '/consultation/loadConsultation.freda?cId=' + record[0].get('cId'),
				success : function(form, action){
					Ext.data.StoreManager.lookup('opLogStore').load();
				}
			});
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
			addOrEditWin.show();
		
		}
		else
		{
			return ;
		}
	}
	
	function submitFormInfo()
	{
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
						url : root + "/consultation/setConsultationStatus.freda",
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
});