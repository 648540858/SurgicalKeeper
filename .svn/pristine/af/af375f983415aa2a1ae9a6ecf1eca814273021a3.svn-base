Ext.onReady(function(){

	var queryContent = {};
	
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
					name : 'orderId'
				},{
					name : 'orderNum'
				},{
					name : 'orderAmount'
				},{
					name : 'orderCtime'
				},{
					name : 'orderStatus'
				},{
					name : 'schId'
				},{
					name : 'schStartTime'
				},{
					name : 'schEndTime'
				},{
					name : 'schAddr'
				},{
					name : 'patientId'
				},{
					name : 'patientLoginName'
				},{
					name : 'patientTrueName'
				},{
					name : 'patientTel'
				},{
					name : 'doctorId'
				},{
					name : 'doctorName'
				},{
					name : 'hosId'
				},{
					name : 'hosName'
				},{
					name : 'doctorPhone'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/orderManager/queryOrder.freda',
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
						var new_params = queryContent;
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '订单号',
				dataIndex : 'orderNum'
			},{
				text : '订单金额',
				dataIndex : 'orderAmount'
			},{
				text : '订单创建时间',
				dataIndex: 'orderCtime'
			},{
				text : '订单状态',
				dataIndex: 'orderStatus',
				renderer : function(value){
					return getCodeText('ORDER_STATE',value);
				}
			},{
				text : '预约时间(开始)',
				dataIndex: 'schStartTime'
			},{
				text : '预约时间(结束)',
				dataIndex: 'schEndTime'
			},{
				text : '预约地点',
				dataIndex: 'schAddr'
			},{
				text : '患者姓名',
				dataIndex: 'patientTrueName'
			},{
				text : '患者电话',
				dataIndex: 'patientTel'
			},{
				text : '医生姓名',
				dataIndex: 'doctorName'
			},{
				text : '所在医院',
				dataIndex: 'hosName'
			},{
				text : '联系方式',
				dataIndex: 'doctorPhone'
			}],
			tbar : [{
				xtype : 'form',
				id : 'queryFormPanel',
				name : 'queryFormPanel',
				labelAlign : 'right',
				labelWidth : 110,
				frame : false,
				layout: 'column',
				bodyPadding: 5,
				width : '95%',
				defaults: {
			        anchor: '99%'
			    },
			    items : [{
		        	xtype:'fieldset',
			        title: '病患信息',
			        collapsible: false,
			        bodyPadding: 15,
			        layout : 'anchor',
			        columnWidth : 0.33,
			        defaults: {
				        anchor: '99%'
				    },
			    	defaultType: 'textfield',//病患信息查询选项
			        items : [{
						xtype : 'textfield',
						fieldLabel : '姓名',
						id : 'patientTrueName',
						name : 'patientTrueName',
						emptyText : '患者姓名...'
					},{
						xtype : 'textfield',
						fieldLabel : '账号',
						id : 'patientLoginName',
						name : 'patientLoginName',
						emptyText : '患者账号...'
					},{
						xtype : 'textfield',
						fieldLabel : '手机号码',
						id : 'patientMobile',
						name : 'patientMobile',
						emptyText : '患者手机号码...'
					},{
						xtype : 'textfield',
						fieldLabel : '身份证号码',
						id : 'patientIdCard',
						name : 'patientIdCard',
						emptyText : '患者身份证号码...'
					}]							
		        },{
		        	xtype:'fieldset',
			        title: '订单信息',
			        collapsible: false,
			        bodyPadding: 15,
			        layout : 'anchor',
			        columnWidth : 0.33,
			        defaults: {
				        anchor: '99%'
				    },
			    	defaultType: 'textfield',//订单信息查询选项
			        items : [{
				    	xtype : 'combo',
				    	fieldLabel : '状态',
				    	id : 'orderStatus',
						name : 'orderStatus',
						editable : false,
						triggerAction : 'all',
						queryMode: 'local',
						value : 1,
						allowBlank : false,
						blankText : me.show_tips.blank_text_msg,
						store : Ext.create('Ext.data.Store',{
							fields: ['name', 'code'],
							data : getCodeArray('ORDER_STATE')
						}),
						displayField: 'name',
		    			valueField: 'code'
				    },{
						xtype : 'textfield',
						fieldLabel : '订单号',
						id : 'orderNum',
						name : 'orderNum',
						emptyText : '订单的订单号...'
					},{
				    	fieldLabel : '开始时间',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'csTime',
						id : 'csTime',
						emptyText : '订单创建时间范围开始...'
				    },{
				    	fieldLabel : '结束时间',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'ceTime',
						id : 'ceTime',
						emptyText : '订单创建时间范围结束...'
				    }]							
		        },{
		        	xtype:'fieldset',
			        title: '医生预约信息',
			        collapsible: false,
			        bodyPadding: 15,
			        layout : 'anchor',
			        columnWidth : 0.33,
			        defaults: {
				        anchor: '99%'
				    },
			    	defaultType: 'textfield',//订单信息查询选项
			        items : [{
						xtype : 'textfield',
						fieldLabel : '姓名',
						id : 'doctorName',
						name : 'doctorName',
						emptyText : '医生姓名...'
					},{
						xtype : 'textfield',
						fieldLabel : '联系方式',
						id : 'doctorPhone',
						name : 'doctorPhone',
						emptyText : '医生联系方式...'
					},{
				    	fieldLabel : '预约开始时间',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'ssTime',
						id : 'ssTime',
						emptyText : '预约时间范围开始...'
				    },{
				    	fieldLabel : '预约结束时间',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'seTime',
						id : 'seTime',
						emptyText : '预约时间范围结束...'
				    }]							
		        }]
			},'-',{
				text : '<font color=red>点击查询</font>',
				height : '50%',
				iconCls : 'page_findIcon',
				handler : function() {
					queryOrder();
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
						loadOrderInfo();
					}
				}]
		})]
	});
	initBorderViewPort([centerPanel]);
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : '80%',
		height : '100%',
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>订单信息</span>',
		modal : true,
		//collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [Ext.create('Ext.form.Panel',{
			id : 'dataFormPanel',
			name : 'dataFormPanel',
			labelAlign : 'right',
			labelWidth : 110,
			autoScroll:true,
			frame : false,
			layout: 'anchor',
			bodyPadding: 5,
			defaults: {
		        anchor: '99%'
		    },
		    items: [{
		    	xtype:'fieldset',
		        title: '订单信息',
		        collapsible: false,
		        bodyPadding: 15,
		        layout : 'column',
		        items : [{
		        	columnWidth : 0.33,
					bodyPadding: 5,
					border : false,
					defaults: {
					        anchor: '99%'
					    },
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '订单号',
				        width : '100%',
				        id : 'oNum',
				        name: 'oNum'
					},{
						xtype: 'displayfield',
				        fieldLabel: '支付时间',
				        width : '100%',
				        id : 'oPaytime',
				        name: 'oPaytime'
					},{
						xtype: 'displayfield',
				        fieldLabel: '支付流水号',
				        width : '100%',
				        id : 'oPayOutNum',
				        name: 'oPayOutNum'
					},{
						xtype: 'displayfield',
				        fieldLabel: '创建时间',
				        width : '100%',
				        id : 'oCtime',
				        name: 'oCtime'
					}]
				},{
					columnWidth : 0.33,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '金额',
				        width : '100%',
				        id : 'oAmount',
				        name: 'oAmount'
					},{
						xtype: 'displayfield',
				        fieldLabel: '支付类型',
				        width : '100%',
				        id : 'oPayType',
				        name: 'oPayType'
					},{
						xtype: 'displayfield',
				        fieldLabel: '退款金额',
				        width : '100%',
				        id : 'oRefund',
				        name: 'oRefund'
					}]
				},{
					columnWidth : 0.33,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '状态',
				        width : '100%',
				        id : 'oStatus',
				        name: 'oStatus'
					},{
						xtype: 'displayfield',
				        fieldLabel: '支付信息',
				        width : '100%',
				        id : 'oPayMsg',
				        name: 'oPayMsg'
					},{
						xtype: 'displayfield',
				        fieldLabel: '退款类型',
				        width : '100%',
				        id : 'oRefundType',
				        name: 'oRefundType'
					}]
				}]
		    },{
		    	xtype:'fieldset',
		        title: '医生信息',
		        collapsible: false,
		        bodyPadding: 15,
		        layout : 'column',
		        items : [{
		        	columnWidth : 0.49,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '姓名',
				        width : '100%',
				        id : 'docName',
				        name: 'docName'
					},{
						xtype: 'displayfield',
				        fieldLabel: '所属医院',
				        width : '100%',
				        id : 'hName',
				        name: 'hName'
					},{
						xtype: 'displayfield',
				        fieldLabel: '年龄',
				        width : '100%',
				        id : 'docAge',
				        name: 'docAge'
					},{
						xtype: 'displayfield',
				        fieldLabel: '性别',
				        width : '100%',
				        id : 'docSex',
				        name: 'docSex'
					},{
						xtype: 'displayfield',
				        fieldLabel: 'Email',
				        width : '100%',
				        id : 'docEmail',
				        name: 'docEmail'
					}]
		        },{
		        	columnWidth : 0.49,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '办公电话',
				        width : '100%',
				        id : 'docPhone',
				        name: 'docPhone'
					},{
						xtype: 'displayfield',
				        fieldLabel: '私人电话',
				        width : '100%',
				        id : 'docTel',
				        name: 'docTel'
					},{
						xtype: 'displayfield',
				        fieldLabel: '职称',
				        width : '100%',
				        id : 'docRank',
				        name: 'docRank'
					},{
						xtype: 'displayfield',
				        fieldLabel: '科室',
				        width : '100%',
				        id : 'docDept',
				        name: 'docDept'
					},{
						xtype: 'displayfield',
				        fieldLabel: '科室职称',
				        width : '100%',
				        id : 'docDeptRank',
				        name: 'docDeptRank'
					}]
		        },{
		        	columnWidth : 0.99,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '擅长',
				        width : '100%',
				        id : 'docGoodAt',
				        name: 'docGoodAt'
					},{
						xtype: 'displayfield',
				        fieldLabel: '主治疾病',
				        width : '100%',
				        id : 'docSickNames',
				        name: 'docSickNames'
					}]
		        }]
		    },{
		    	xtype:'fieldset',
		        title: '病患信息',
		        collapsible: false,
		        bodyPadding: 15,
		        layout : 'column',
		        items : [{
		        	columnWidth : 0.499,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '姓名',
				        width : '100%',
				        id : 'pTrueName',
				        name: 'pTrueName'
					},{
						xtype: 'displayfield',
				        fieldLabel: '身份证号',
				        width : '100%',
				        id : 'pIdCard',
				        name: 'pIdCard'
					},{
						xtype: 'displayfield',
				        fieldLabel: '年龄',
				        width : '100%',
				        id : 'pAge',
				        name: 'pAge'
					}]
		        },{
		        	columnWidth : 0.499,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '账号信息',
				        width : '100%',
				        id : 'pLoginName',
				        name: 'pLoginName'
					},{
						xtype: 'displayfield',
				        fieldLabel: '联系方式',
				        width : '100%',
				        id : 'pMobile',
				        name: 'pMobile'
					},{
						xtype: 'displayfield',
				        fieldLabel: '性别',
				        width : '100%',
				        id : 'pSex',
				        name: 'pSex'
					}]
		        },{
		        	columnWidth : 0.99,
					bodyPadding: 5,
					border : false,
					items : [{
						xtype: 'displayfield',
				        fieldLabel: '疾病信息',
				        width : '100%',
				        id : 'sickNames',
				        name: 'sickNames'
					},{
						xtype: 'displayfield',
				        fieldLabel: '病痛描述',
				        width : '100%',
				        id : 'sickDesc',
				        name: 'sickDesc'
					}]
		        }]
		    },{
		    	xtype:'fieldset',
		        title: '预约信息',
		        collapsible: false,
		        bodyPadding: 15,
		        layout : 'column',
		        items : [{
		        	columnWidth : 0.999,
					bodyPadding: 5,
					border : false,
					items : [{
			    		xtype : 'displayfield',
				    	fieldLabel : '医生',
						width : '100%',
						name : 'scDoc',
						id : 'scDoc',
						width : '100%'
				    }]
		        },{
		        	columnWidth : 0.499,
					bodyPadding: 5,
					border : false,
					items : [{
				    	fieldLabel : '开始时间',
						blankText : me.show_tips.blank_text_msg,
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'scStime',
						id : 'scStime',
						width : '100%',
						allowBlank : false
				    }]
		        },{
		        	columnWidth : 0.499,
					bodyPadding: 5,
					border : false,
					items : [{
				    	fieldLabel : '结束时间',
						blankText : me.show_tips.blank_text_msg,
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						name : 'scEtime',
						id : 'scEtime',
						width : '100%',
						allowBlank : false
				    }]
		        },{
		        	columnWidth : 0.999,
					bodyPadding: 5,
					border : false,
					items : [{
			    		xtype : 'textfield',
				    	fieldLabel : '地址',
						blankText : me.show_tips.blank_text_msg,
						width : '100%',
						name : 'scAddr',
						id : 'scAddr',
						width : '100%',
						allowBlank : false
				    }]
		        }]
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'docId',
		    	name : 'docId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'dScId',
		    	name : 'dScId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'oScId',
		    	name : 'oScId'
		    }]
		})],
		buttons : [{
			text : '选择医生',
			id : 'btnChangeSch',
			iconCls : 'arrow_switchIcon',
			handler : function() {	
				queryDocWinInit();
			}
		},{
			text : '保存预约',
			id : 'btnSave',
			iconCls : 'acceptIcon',
			handler : function() {	
				editSchInfo();
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
	
	var queryDocWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : '65%',
		height : 450,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>医生查询</span>',
		modal : true,
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [Ext.create('Ext.grid.Panel',{
			//title : '医生搜索',
			id : 'queryDocGrid',
			name : 'queryDocGrid',
			frame : true,
			autoScroll : true,
			//region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			stripeRows : true, // 斑马线
			border : false,
			forceFit : true,
			maskOnDisable : true,
			store : Ext.create('Ext.data.Store',{
				storeId:'queryDocStore',
				pageSize : 50,
				fields:[{
					name : 'qDocId'
				},{
					name : 'qDocName'
				},{
					name : 'qHname'
				},{
					name : 'qsickNames'
				},{
					name : 'qsTime'
				},{
					name : 'qeTime'
				},{
					name : 'qdSchId'
				},{
					name : 'qAddr'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/orderManager/queryDoc.freda',
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
							cityId : Ext.getCmp('aId').getValue(),
							sTime : Ext.getCmp('sStime').getValue(),
							eTime : Ext.getCmp('sEtime').getValue(),
							sicknessName : Ext.getCmp('querySickness').getValue()
						};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '医生姓名',
				dataIndex : 'qDocName'
			},{
				text : '所属医院',
				dataIndex : 'qHname'
			},{
				text : '主治疾病',
				dataIndex: 'qsickNames'
			},{
				text : '开始时间',
				dataIndex: 'qsTime'
			},{
				text : '完成时间',
				dataIndex: 'qeTime'
			},{
				text : '地点',
				dataIndex : 'qAddr'
			}],
			tbar : [{
		    	xtype: 'comboboxtree',  
                id:'aName',
                name: 'aName',
                allowBlank : false,
                labelWidth : 60,
                width : 250,
				blankText : me.show_tips.blank_text_msg,
                submitId : 'aId',
                fieldLabel: '区域',
       			maxPickerHeight:160,
       			action : 'only',
       			store : Ext.create('Ext.data.TreeStore',{
	    			proxy: {
				            type: 'ajax',
				            url: root + '/hosAndDept/cityTreeInit.freda'
				        },
				    root : {
				    	text : '中华人民共和国',
				    	//expanded: true,
				    	id   : 1,
				    	leaf : 0,
				    	icon : '',
				    	parent : 0
				    }  
    			})
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'aId',
		    	name : 'aId'
		    },{
		    	fieldLabel : '开始时间',
				blankText : me.show_tips.blank_text_msg,
				xtype : 'datetimefield',
				format : 'Y-m-d H:i:s',
				labelWidth : 60,
				name : 'sStime',
				id : 'sStime',
				allowBlank : false
		    },{
		    	fieldLabel : '结束时间',
				blankText : me.show_tips.blank_text_msg,
				xtype : 'datetimefield',
				format : 'Y-m-d H:i:s',
				labelWidth : 60,
				name : 'sEtime',
				id : 'sEtime',
				allowBlank : false
		    },{
				xtype : 'textfield',
				fieldLabel : '疾病信息',
				labelWidth : 60,
				id : 'querySickness',
				name : 'querySickness',
				emptyText : '请输入疾病..',
				width : 200
			},'-',{
				text : '查询',
				iconCls : 'page_findIcon',
				handler : function() {
					Ext.data.StoreManager.lookup('queryDocStore').load({
						params : {
							cityId : Ext.getCmp('aId').getValue(),
							sTime : Ext.getCmp('sStime').getValue(),
							eTime : Ext.getCmp('sEtime').getValue(),
							sicknessName : Ext.getCmp('querySickness').getValue()
						}
					});
				}
			}],
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('queryDocStore'), // same store GridPanel is using
		        dock: 'bottom',
		        displayInfo: true
		    }]
		})],
		buttons : [{
			text : '选择',
			id : 'btnSave1',
			iconCls : 'acceptIcon',
			handler : function() {	
				setSchToForm();
			}
		},{
			text : '关闭',
			id : 'btnClose1',
			iconCls : 'deleteIcon',
			handler : function() {
				queryDocWin.hide();
			}
		}]
	});
	
	function queryOrder()
	{
		queryContent = Ext.getCmp('queryFormPanel').getForm().getFieldValues();
		
		Ext.data.StoreManager.lookup('dataStore').load({
			params : queryContent
		});
	}
	
	function loadOrderInfo()
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
			url:root + '/orderManager/loadOrderInfo.freda',
			params : {
				oId : record[0].get('orderId'),
				docId : record[0].get('doctorId'),
				pId : record[0].get('patientId'),
				oScId : record[0].get('schId') 
			},
			success : function(form, action){
				Ext.getCmp('oStatus').setValue(getCodeText('ORDER_STATE',action.result.data.oStatus));
				Ext.getCmp('docSex').setValue(getCodeText('SEX',action.result.data.docSex));
				Ext.getCmp('docRank').setValue(getCodeText('DOC_RANK',action.result.data.docRank));
				Ext.getCmp('docDeptRank').setValue(getCodeText('DOC_DEPT_RANK',action.result.data.docDeptRank));
				Ext.getCmp('pSex').setValue(getCodeText('SEX',action.result.data.pSex));
				Ext.getCmp('scDoc').setValue(Ext.getCmp('docName').getValue() + '[' + Ext.getCmp('hName').getValue() + ']');
				addOrEditWin.show();
			}
		});
	}
	
	function queryDocWinInit()
	{
		queryDocWin.show();
	}
	
	function setSchToForm()
	{
		record = Ext.getCmp('queryDocGrid').getSelectionModel().getSelection();
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
		Ext.getCmp('docId').setValue(record[0].get('qDocId'));
		Ext.getCmp('scStime').setValue(record[0].get('qsTime'));
		Ext.getCmp('scEtime').setValue(record[0].get('qeTime'));
		Ext.getCmp('dScId').setValue(record[0].get('qdSchId'));
		Ext.getCmp('scAddr').setValue(record[0].get('qAddr'));
		Ext.getCmp('scDoc').setValue(record[0].get('qDocName') + '[' + record[0].get('qHname') + ']');
		queryDocWin.hide();
	}
	
	function editSchInfo()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: '确定要更改预约信息?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/orderManager/setOrderInfo.freda',
					    method : 'POST',
					    params : {
					    	docSchId : Ext.getCmp('dScId').getValue(),
					    	docId : Ext.getCmp('docId').getValue(),
					    	sTime : Ext.getCmp('scStime').getValue(),
					    	eTime : Ext.getCmp('scEtime').getValue(),
					    	addrStr : Ext.getCmp('scAddr').getValue(),
					    	oScId : Ext.getCmp('oScId').getValue()
					    },
					    success: function(response) {
					    	var result = Ext.JSON.decode(response.responseText);
					    	me.showAlert(result.msg);
					    	addOrEditWin.hide();
					    	Ext.data.StoreManager.lookup('dataStore').reload();
					    	
					    }
					});
		        }
		    }
		});
	}
});