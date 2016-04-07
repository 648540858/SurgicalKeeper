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
					name : 'cMobile'
				},{
					name : 'sTime'
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
						var new_params = { 
							status : Ext.getCmp('status').getValue(),
							queryContent : Ext.getCmp('queryContent').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '期望就诊城市',
				dataIndex : 'cSeeCity'
			},{
				text : '就诊人',
				dataIndex : 'cpName'
			},{
				text : '联系电话',
				dataIndex : 'cMobile'
			},{
				text : '期望就诊时间',
				dataIndex: 'sTime'
			},{
				text : '状态',
				dataIndex : 'cStatus',
				renderer : function(value){
					return getCodeText('CONS_STATUS',value);
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
					data : getCodeArray('CONS_STATUS')
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
		    },'-',{
				xtype : 'textfield',
				id : 'queryContent',
				name : 'queryContent',
				emptyText : '输入就诊人或电话进行搜索..',
				width : 200,
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
			},'-',{
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
				text : '查看',
				iconCls : 'page_edit_1Icon',
				handler : function() {
					vipWinInit();
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
		    },
		    listeners : [{
				'celldblclick' : function (grid, td, cellIndex, record, tr, rowIndex, e, eOpts){
					
					Ext.getCmp('dataGrid').getSelectionModel().select(rowIndex);
					
					vipWinInit();
				}
			}]
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
		layout : 'fit',
		width : '100%',
		height : '100%',
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>VIP会诊详细信息</span>',
		modal : true,
		collapsible : false,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [{
			layout : 'anchor',
			items : [Ext.create('Ext.form.Panel',{
				id : 'dataFormPanel',
				name : 'dataFormPanel',
				labelAlign : 'right',
				border : false,
				labelWidth : 110,
				frame : false,
				layout: 'anchor',
				anchor: '100% 65%',
				border : false,
				buttonAlign : 'left',
				bodyPadding: 5,
				defaults: {
			        anchor: '99%'
			    },
			    //defaultType: 'textfield',
			    autoScroll: true,
			    items: [{
			    	xtype:'fieldset',
			        title: '信息',
			        collapsible: false,
			        bodyPadding: 15,
			        layout : 'anchor',
			        items : [{
			        	xtype:'fieldset',
				        title: '用户信息',
				        collapsible: false,
				        bodyPadding: 15,
				        layout : 'column',
				        items : [{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '就诊城市',
						        id : 'cSeeCity',
						        name: 'cSeeCity'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '医保城市',
						        id : 'cMiCity',
						        name: 'cMiCity'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '患者姓名',
						        id : 'cPatName',
						        name: 'cPatName'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '联系方式',
						        id : 'cPatMobile',
						        name: 'cPatMobile'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '提交时间',
						        id : 'cTime',
						        name: 'cTime'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '疾病信息',
						        id : 'cSickStr',
						        name: 'cSickStr'
						    }]
				        },{
				        	columnWidth : 0.99,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '期望时间',
						        id : 'cHTime',
						        name: 'cHTime'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '疾病描述',
						        id : 'cSickDesc',
						        name: 'cSickDesc'
						    },{
						    	xtype : 'hiddenfield',
						    	id : 'cMainUserId',
						    	name : 'cMainUserId'
						    }]
				        }]
			        },{
			        	xtype:'fieldset',
				        title: '预约信息',
				        collapsible: false,
				        bodyPadding: 15,
				        layout : 'column',
				        items : [{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '医生姓名',
						        id : 'schDocName',
						        name: 'schDocName'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '预约时间',
						        id : 'schTime',
						        name: 'schTime'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '创建时间',
						        id : 'schCreateTime',
						        name: 'schCreateTime'
						    }]
				        },{
				        	columnWidth : 0.99,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '预约地址',
						        id : 'schAddr',
						        name: 'schAddr'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '预约描述',
						        id : 'schDesc',
						        name: 'schDesc'
						    },{
						    	xtype : 'hiddenfield',
						    	id : 'schDocId',
						    	name : 'schDocId'
						    }]
				        }]
			        },{
			        	xtype:'fieldset',
				        title: '订单信息',
				        collapsible: false,
				        bodyPadding: 15,
				        layout : 'column',
				        items : [{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '订单编号',
						        id : 'oNum',
						        name: 'oNum'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '订单状态',
						        id : 'oStatusForDis',
						        name: 'oStatusForDis'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '总已支付',
						        id : 'oPayTotal',
						        name: 'oPayTotal'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '订单总价',
						        id : 'oNeedPayTotal',
						        name: 'oNeedPayTotal'
						    }]
				        },{
				        	columnWidth : 0.33,
						    bodyPadding: 5,
						    border : false,
						    items : [{
						    	xtype: 'displayfield',
						        fieldLabel: '收费类型',
						        id : 'oPayModeForDis',
						        name: 'oPayModeForDis'
						    },{
						    	xtype: 'displayfield',
						        fieldLabel: '现需支付',
						        id : 'oNeedPayAmount',
						        name: 'oNeedPayAmount'
						    }]
				        }]
			        },{
				    	xtype : 'hiddenfield',
				    	id : 'oStatus',
				    	name : 'oStatus'  
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'oIsNeedPay',
				    	name : 'oIsNeedPay'
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'oPayMode',
				    	name : 'oPayMode'
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'schId',
				    	name : 'schId'
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'oId',
				    	name : 'oId'
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'schDocId',
				    	name : 'schDocId'
			        },{
			        	xtype : 'hiddenfield',
				    	id : 'cId',
				    	name : 'cId'
			        },{
			        	xtype : 'hiddenfield',
			        	id : 'cStatus',
			        	name : 'cStatus'
			        }]
			    }],
			    buttons : [{
			    	text : '发送确认',
			    	handler : function(){
			    		
			    		sendConfirmMsg();
			    	}
			    },'-',{
			    	text : '退还定金',
			    	handler : function(){
			    	
			    		refundDJPay();
			    	}
			    },'-',{
			    	text : '发送支付',
			    	handler : function(){
			    		
			    		sendPayMsg();
			    	}
			    },'-','->','-',{
			    	text : '审核',
			    	handler : function(){
			    		
			    		verifyVip();
			    	}
			    },'-',{
			    	text : '废弃订单',
			    	handler : function(){
			    		
			    		abrogate();
			    	}
			    },'-']
			}),{
				border : true,
				layout : 'border',
				anchor: '100% 35%',
				items : [{
					layout : 'fit',
		        	columnWidth : 0.66,
		        	region : 'center',
					items : [{
						xtype : 'gridpanel',
						title : '客服操作日志',
						id : 'opLogGrid',
						name : 'opLogGrid',
						frame : true,
						//anchor: '100% 100%',
						autoScroll : true,
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
									var new_params = { };
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
							text : '操作状态',
							dataIndex : 'kStatus',
							renderer : function(value){
								return getCodeText('CONS_STATUS',value);
							}
						},{
							text : '备注',
							dataIndex: 'kOpInfo'
						}],
						dockedItems: [{
					        xtype: 'pagingtoolbar',
					        store: Ext.data.StoreManager.lookup('opLogStore'), // same store GridPanel is using
					        dock: 'bottom',
					        displayInfo: true
					    }]
					}]
				},{
					layout : 'fit',
					region : 'east',
					width : 450,
		        	items : [Ext.create('Ext.form.Panel',{
		        		id : 'schFormPanel',
						name : 'schFormPanel',
						labelAlign : 'right',
						labelWidth : 110,
						frame : false,
						title : '编辑预约信息',
						layout: 'anchor',
						border : false,
						buttonAlign : 'right',
						bodyPadding: 5,
						defaults: {
					        anchor: '99%'
					    },
					    defaultType: 'textfield',
					    autoScroll: true,
					    items : [{
					    	xtype      : 'fieldcontainer',
				            fieldLabel : '预约修改',
				            defaultType: 'radiofield',
				            defaults: {
				                flex: 1
				            },
				            layout: 'hbox',
				            items : [{
				            	boxLabel : '下单',
			                    name : 'schTypeForEdit',
			                    checked : true,
			                    inputValue : '1',
			                    id : 'schTypeForCreate',
			                    listeners : [{
			                    	
			                    	'change' : function(d, newValue, oldValue, eOpts){
			                    		
			                    		if(!newValue){
			                    			
			                    			Ext.getCmp('needPayTotal').setVisible(false);
			                    			Ext.getCmp('needPayTotal').setValue(0);
			                    		}else{
			                    			
			                    			Ext.getCmp('needPayTotal').setVisible(true);
			                    		}
			                    	}
			                    }]
				            },{
				            	boxLabel : '患者',
			                    name : 'schTypeForEdit',
			                    inputValue : '2',
			                    id : 'schTypeForPat'
				            },{
				            	boxLabel : '医生',
			                    name : 'schTypeForEdit',
			                    inputValue : '3',
			                    id : 'schTypeForDoc'
				            }]
					    },{
					    	xtype:"fieldcontainer" , 
					    	layout:"hbox",
					    	fieldLabel : '预约医生',
					    	width : '99%',
					    	items : [{
					    		xtype : 'hiddenfield',
					    		id : 'schDocIdForEdit',
					    		name : 'schDocIdForEdit'
					    	},{
					    		xtype : 'textfield',
					    		id : 'schDocForEdit',
					    		name : 'schDocForEdit',
					    		readOnly : true,
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		emptyText : '点击选择',
					    		width : '86%'
					    	},{
					    		xtype:"button" ,
					    		width : '13%',
					    		text:"选择",
					    		handler : function(){
					    			
					    			chooseDocWinInit();
					    		}
					    	}]
					    },{
				    		fieldLabel : '预约价格',
				    		xtype : 'numberfield',
				    		maxValue: 9999,
			        		minValue: 0,
			        		step: 1000,
				    		width : '99%',
				    		id : 'needPayTotal',
				    		name : 'needPayTotal',
				    		emptyText : '输入预约的价格..',
				    		value : 2000,
				    		blankText : me.show_tips.blank_text_msg,
					    	allowBlank : false
				    	},{
				    		fieldLabel : '预约地址',
				    		width : '99%',
				    		id : 'schAddrForEdit',
				    		name : 'schAddrForEdit',
				    		emptyText : '输入地址..',
				    		blankText : me.show_tips.blank_text_msg,
					    	allowBlank : false
				    	},{
				    		fieldLabel : '预约时间',
				    		width : '99%',
							xtype : 'datetimefield',
							format : 'Y-m-d H:i:s',
							name : 'schTimeForEdit',
							id : 'schTimeForEdit',
							emptyText : '预约时间..',
				    		blankText : me.show_tips.blank_text_msg,
					    	allowBlank : false
					    },{
				    		xtype : 'textareafield',
				    		fieldLabel : '预约描述',
				    		width : '99%',
				    		id : 'schDescForEdit',
				    		name : 'schDescForEdit',
				    		blankText : me.show_tips.blank_text_msg,
					    	allowBlank : false
				    	}],
					    buttons : [{
					    	text : '保存',
					    	handler : function(){
					    		setSch();
					    	}
					    },{
					    	text : '重置',
					    	handler : function(){
					    		Ext.getCmp('schFormPanel').getForm().reset();
					    	}
					    }]
		        	})]
				}]
			}]
		}]
	});
	
	/**
	 * 窗口Init
	 */
	function vipWinInit(){
		
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
		
		Ext.getCmp('dataFormPanel').getForm().reset();
		
		Ext.getCmp('schFormPanel').getForm().reset();
		
		Ext.getCmp('dataFormPanel').getForm().load({
			url:root + '/consultation/loadConsultation.freda',
			params : {
				cId : record[0].get('cId')
			},
			success : function(form, action){
				
				Ext.getCmp('oStatusForDis').setValue(getCodeText('CONS_STATUS',action.result.data.oStatus));
				Ext.getCmp('oPayModeForDis').setValue(getCodeText('CONS_PAY_MODE',action.result.data.oPayMode));
				
				Ext.data.StoreManager.lookup('opLogStore').load({
					params : {
						cId : Ext.getCmp('cId').getValue()
					}
				});
				
				if(addOrEditWin.isHidden()){
					
					addOrEditWin.show();
				}
			}
		});
	}
	/**
	 * 审核订单
	 */
	function verifyVip(){
		
		if(Ext.getCmp('cStatus').getValue() == '1'){
			
			me.showAlert('现在不是审核的时候..');
			
			return ;
		}
		
		var submitParams = {
			cId : Ext.getCmp('cId').getValue()
		};
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '是否通过审核?',
		    buttons: Ext.Msg.YESNOCANCEL,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		submitParams.cStatus = 1;
		       		submitParams.opInfo = '审核通过';
		        }else if(btn == 'no'){
		        	submitParams.cStatus = 5;
		        	submitParams.opInfo = '审核未通过';
		        }else{}
		        submitVerify(submitParams);
		    }
		});
		
		function submitVerify(params){
			Ext.ux.Ajax.request({
			    url : root + '/consultation/verifyVip.freda',
			    method : 'POST',
			    params : params,
			    success: function(response) {
			    	  var result = Ext.JSON.decode(response.responseText);
			    	  me.showAlert(result.msg);
					  Ext.data.StoreManager.lookup('dataStore').reload();
					  addOrEditWin.hide();
			    }
			});
		}
	}
	
	function chooseDocWinInit(){
	
		var chooseDocWin = Ext.create('Ext.window.Window',{
			layout : 'fit',
			width : 350,
			height : 370,
			resizable : false,
			draggable : true,
			closeAction : 'destroy',
			title : '<span>医生选择</span>',
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
			items : [Ext.create('Ext.tab.Panel',{
				id : 'treeTabPanel',
				name : 'treeTabPanel',
				layout : 'fit',
				items :[{
					xtype : 'treepanel',
					id : 'docTreePanel',
					title : '医院医生选择',
					rootVisible: false,
		    		autoScroll : true,
					animate : false,
					useArrows : false,
					autoLoad : false,
					border : false,
					store: Ext.create("Ext.data.TreeStore",{
						proxy: {
						            type: 'ajax',
						            url: root + '/consultation/cityHosDocTreeInit.freda'
						        },
						root : {
							    	text : '中华人民共和国',
							    	id   : 'c-1',
							    	leaf : 0,
							    	icon : '',
							    	parent : 0
								} 
					}),
					listeners : [{
						'itemclick' : function (panel, record, item, index, e, eOpts ){
							if(record.get('leaf') == 1){
								Ext.getCmp('schDocIdForEdit').setValue(record.get('id').split('-')[1]);
								Ext.getCmp('schDocForEdit').setValue(record.get('text'));
								chooseDocWin.close();
							}
						}
					}]
				}]
			})]
		}).show();
	}
	
	function setSch(){
		
		if(Ext.getCmp('schTypeForCreate').getValue()){
			
			if(Ext.getCmp('oStatus').getValue() != 1){
				
				me.showAlert('现在不是下单的时候..');
				
				return ;				
			}
			if(Ext.getCmp('needPayTotal').getValue() <= 0){
			
				me.showAlert('总价是0还玩个屁啊..');
				
				return ;
			}
		}
		else if(Ext.getCmp('schTypeForPat').getValue() || Ext.getCmp('schTypeForDoc').getValue())
		{
			if(Ext.getCmp('oStatus').getValue() != 3){
				
				me.showAlert('现在不是修改预约的时候..');
				
				return ;
			}
			
			if(Ext.getCmp('oPayMode').getValue() == 3){
		
				me.showAlert('确认都发了..还改什么啊..');
				
				return ;
			}
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('schFormPanel').getForm().submit({
						clientValidation: true,
						url: root + '/consultation/setSch.freda',
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						params : {
							cId : Ext.getCmp('cId').getValue(),
							oId : Ext.getCmp('oId').getValue(),
							oStatus : Ext.getCmp('oStatus').getValue()
						},
						success: function(form, action) {
						  me.showAlert(action.result.msg);
						  if(Ext.getCmp('schTypeForCreate').getValue()){
						  	
						  	Ext.data.StoreManager.lookup('dataStore').reload();
						  	
						  	addOrEditWin.hide();
													  
						  }else{
						  	
						  	vipWinInit();
						  
						  }
						  	
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
	
	function sendPayMsg(){
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '确定发送支付?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        
		        	Ext.ux.Ajax.request({
					    url : root + '/consultation/sendPay.freda',
					    method : 'POST',
					    params : {oId : Ext.getCmp('oId').getValue()},
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    }
					});
		        	
		        }
		    }
		});
	}
	
	function sendConfirmMsg(){
		
		if(Ext.getCmp('oStatus').getValue() != 3){
		
			me.showAlert('现在不能发确认哦..亲.');
			
			return ;
		}
		if(Ext.getCmp('oIsNeedPay').getValue() != 0){
		
			me.showAlert('用户上一笔费用还没有结算..');
			
			return ;
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '确定发送确认与尾款支付消息?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        
		        	Ext.ux.Ajax.request({
					    url : root + '/consultation/sendConfirm.freda',
					    method : 'POST',
					    params : {
					    	oId : Ext.getCmp('oId').getValue(),
					    	cId : Ext.getCmp('cId').getValue(),
					    	oStatus : Ext.getCmp('oStatus').getValue()
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    	  
					    	  vipWinInit();
					    }
					});
		        	
		        }
		    }
		});
	}
	/**
	 * 废除
	 */
	function abrogate(){
		
		if(Ext.getCmp('oStatus').getValue() == '' || Ext.getCmp('oStatus').getValue() >= 4){
			
			me.showAlert('现在无法废除订单或已经在垃圾桶中..');
			
			return ;
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '确定将订单投入垃圾桶?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        
		        	Ext.ux.Ajax.request({
					    url : root + '/consultation/abrogateVipOrder.freda',
					    method : 'POST',
					    params : {
					    	oId : Ext.getCmp('oId').getValue(),
					    	cId : Ext.getCmp('cId').getValue(),
					    	oStatus : Ext.getCmp('oStatus').getValue()
					    },
					    success: function(response) {
					    	  var result = Ext.JSON.decode(response.responseText);
					    	  me.showAlert(result.msg);
					    	  
					    	  Ext.data.StoreManager.lookup('dataStore').reload();
					    	  
					    	  vipWinInit();
					    }
					});
		        	
		        }
		    }
		});
	}
	/**
	 * 退还定金
	 */
	function refundDJPay(){
		
		if(Ext.getCmp('oStatus').getValue() != 3){
			
			me.showAlert('现在不能退还定金..');
			
			return ;
		}
		
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '确定将订单定金退回并投入垃圾桶?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        
		        	Ext.ux.Ajax.request({
					    url : root + '/consultation/getRefundInfo.freda',
					    method : 'POST',
					    params : {
					    	oId : Ext.getCmp('oId').getValue(),
					    	cId : Ext.getCmp('cId').getValue(),
					    	oStatus : Ext.getCmp('oStatus').getValue(),
					    	opInfo : '平台原因退还定金.',
					    	payMode : 1
					    },
					    success: function(response) {
					    	
				    	  var result = Ext.JSON.decode(response.responseText);
				    	  
				    	  if(result.payType == 2){
				    	  	
					    	  	Ext.ux.Ajax.request({
								    url : root + '/pingpp/refundOrder.freda',
								    method : 'POST',
								    params : {
								    	amount : result.amount,
								    	ch_id : result.outOrderNum,
								    	description : '因平台原因.退还用户定金(VIP)'
								    },
								    success: function(response) {
								    	  var pingppResult = Ext.JSON.decode(response.responseText);
								    	  
								    	  if(pingppResult.success){
								    	  
								    	  	Ext.ux.Ajax.request({
											    url : root + '/consultation/abrogateVipOrder.freda',
											    method : 'POST',
											    params : {
											    	oId : Ext.getCmp('oId').getValue(),
											    	cId : Ext.getCmp('cId').getValue(),
											    	refund : 1,
											    	oStatus : Ext.getCmp('oStatus').getValue()
											    },
											    success: function(response) {
											    	  var aResult = Ext.JSON.decode(response.responseText);
											    	  me.showAlert(aResult.msg);
											    	  
											    	  Ext.data.StoreManager.lookup('dataStore').reload();
											    	  
											    	  addOrEditWin.hide();
											    }
											});
								    	  	
								    	  }else{
								    	  	me.showAlert(pingppResult.msg);
								    	  }
								    }
								});
				    	  	 }else if(result.payType == 1){
				    	  	 	//AJAX 增加余额
				    	  	 	Ext.ux.Ajax.request({
								    url : root + '/consultation/addRefundToBalance.freda',
								    method : 'POST',
								    params : {
								    	oPayAmount : result.amount,
								    	mainUserId : result.mainUserId
								    },
								    success: function(response) {
								    	  var balanceResult = Ext.JSON.decode(response.responseText);
								    	  
								    	  if(balanceResult.success){
								    	  
								    	  	Ext.ux.Ajax.request({
											    url : root + '/consultation/abrogateVipOrder.freda',
											    method : 'POST',
											    params : {
											    	oId : Ext.getCmp('oId').getValue(),
											    	cId : Ext.getCmp('cId').getValue(),
											    	refund : 1,
											    	oStatus : Ext.getCmp('oStatus').getValue()
											    },
											    success: function(response) {
											    	  var aResult = Ext.JSON.decode(response.responseText);
											    	  me.showAlert(aResult.msg);
											    	  
											    	  Ext.data.StoreManager.lookup('dataStore').reload();
											    	  
											    	  addOrEditWin.hide();
											    }
											});
								    	  	
								    	  }else{
								    	  	me.showAlert(balanceResult.msg);
								    	  }
								    }
								});
				    	  	 	
				    	  	 }else{return ;}
					    }
					});
		        	
		        }
		    }
		});
	}
	
});