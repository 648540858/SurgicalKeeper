Ext.onReady(function(){

	var westPanel = Ext.create('Ext.panel.Panel',{
		title : '区域树',
		collapsible : true,
		tools : [{
			type: 'refresh',
			handler : function() {
				Ext.getCmp('cityTree').store.load({
					node : Ext.getCmp('cityTree').getRootNode()
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
			id : 'cityTree',
			name : 'cityTree',
			rootVisible: false,
    		autoScroll : true,
			animate : false,
			useArrows : false,
			border : false,
			store: Ext.create("Ext.data.TreeStore",{
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
    		}),
    		listeners : [{
    			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
    				if(record.get('leaf') != 1)
    				{
    					return;
    				}
    				Ext.getCmp('haId').setValue(record.get('id'))
					Ext.data.StoreManager.lookup('dataStore').load({
    					params : {
    						aId : record.get('id'),
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
					name : 'hId'
				},{
					name : 'haId'
				},{
					name : 'haName'
				},{
					name : 'htId'
				},{
					name : 'hlId'
				},{
					name : 'hName'
				},{
					name : 'hDesc'
				},{
					name : 'hAddr'
				},{
					name : 'hTel'
				},{
					name : 'hLogo'
				},{
					name : 'hEmail'
				},{
					name : 'hStatus'
				},{
					name : 'hCtime'
				},{
					name : 'hUtime'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/hosAndDept/listHospital.freda',
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
						aId : Ext.getCmp('haId').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '名称',
				dataIndex: 'hName'
			},{
				text : '类别',
				dataIndex: 'htId',
				renderer : function(value){
					return  getCodeText('HOS_TYPE',value);
				}
			},{
				text : '级别',
				dataIndex: 'hlId',
				renderer : function(value){
					return  getCodeText('HOS_LEVEL',value);
				}
			},{
				text : '备注',
				dataIndex: 'hDesc'
			},{
				text : '地址',
				dataIndex: 'hAddr'
			},{
				text : '电话',
				dataIndex: 'hTel'
			},{
				text : '邮箱',
				dataIndex: 'hEmail'
			},{
				text : '创建时间',
				dataIndex: 'hCtime'
			},{
				text : '最后修改时间',
				dataIndex: 'hUtime'
			},{
				text : '状态',
				dataIndex: 'hStatus',
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
			},{
				text : '绑定科室',
				iconCls : 'arrow_switchIcon',
				handler : function() {
					setHosDeptWinInit();
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
	initBorderViewPort([westPanel,centerPanel]);
	
	var addOrEditWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 700,
		height : 420,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>医疗机构信息</span>',
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
				    	xtype:'fieldset',
				        title: '基本信息',
				        collapsible: false,
				        bodyPadding: 15,
				        layout : 'column',
				        items : [{
					    	columnWidth : 0.74,
					    	bodyPadding: 5,
					    	border : false,
					    	items : [{
					    		xtype : 'textfield',
					    		fieldLabel : '名称',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'hName',
					    		name : 'hName'
					    	},{
						    	xtype : 'combo',
						    	fieldLabel : '类别',
						    	id : 'htId',
								name : 'htId',
								width : '100%',
								editable : false,
								triggerAction : 'all',
								queryMode: 'local',
								allowBlank : false,
								blankText : me.show_tips.blank_text_msg,
								store : Ext.create('Ext.data.Store',{
									fields: ['name', 'code'],
									data : getCodeArray('HOS_TYPE')
								}),
								displayField: 'name',
				    			valueField: 'code'
		    				},{
						    	xtype : 'combo',
						    	fieldLabel : '级别',
						    	id : 'hlId',
								name : 'hlId',
								width : '100%',
								editable : false,
								triggerAction : 'all',
								queryMode: 'local',
								allowBlank : false,
								blankText : me.show_tips.blank_text_msg,
								store : Ext.create('Ext.data.Store',{
									fields: ['name', 'code'],
									data : getCodeArray('HOS_LEVEL')
								}),
								displayField: 'name',
				    			valueField: 'code'
		    				},{
						    	xtype : 'combo',
						    	fieldLabel : '状态',
						    	id : 'hStatus',
								name : 'hStatus',
								width : '100%',
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
						    	xtype: 'comboboxtree',  
				                id:'haName',
				                name: 'haName',
				                allowBlank : false,
				                width : '100%',
								blankText : me.show_tips.blank_text_msg,
				                submitId : 'haId',
				                fieldLabel: '所属区域',
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
						    }]
					    },{
					    	columnWidth : 0.2599,
					    	bodyPadding: 5,
					    	border : false,
					    	layout:'anchor',
					    	items : [Ext.create('Ext.Img', {
								    src: root + '/resources/mine/img/defaut/photo/default.jpg',
								    height : 140,
								    width : 110,
								    border : true,
								    id : 'photo',
								    name : 'photo'
								}),{
							    	xtype: 'button',
							    	text : '设置',
							    	handler : function(){
							    		upLoadWinInit();
							    	}
							    }]
					    },{
					    	columnWidth : 1,
					    	bodyPadding: 5,
					    	border : false,
					    	layout:'anchor',
					    	items : [{
					    		xtype : 'textfield',
					    		fieldLabel : '联系电话',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'hTel',
					    		name : 'hTel'
					    	},{
					    		xtype : 'textfield',
					    		fieldLabel : 'Email',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'hEmail',
					    		name : 'hEmail'
					    	},{
					    		xtype : 'textfield',
					    		fieldLabel : '地址',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'hAddr',
					    		name : 'hAddr'
					    	},{
					    		xtype : 'textareafield',
					    		fieldLabel : '备注',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'hDesc',
					    		name : 'hDesc'
					    	}]
					    }]
				    },{
		    	xtype : 'hiddenfield',
		    	id : 'hId',
		    	name : 'hId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'hLogo',
		    	name : 'hLogo'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'haId',
		    	name : 'haId'
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
	
	var upLoadWin = Ext.create('Ext.window.Window',{
		layout : 'fit',
		width : 450,
		height : 110,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>上传</span>',
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
			id : 'upLoadFormPanel',
			name : 'upLoadFormPanel',
			labelAlign : 'right',
			labelWidth : 110,
			frame : false,
			layout: 'anchor',
			bodyPadding: 5,
			defaults: {
		        anchor: '99%'
		    },
		    defaultType: 'filefield',
		    items: [{
		    	fieldLabel : '上传文件',
				blankText : me.show_tips.blank_text_msg,
				name : 'upLoadFile',
				id : 'upLoadFile',
				allowBlank : false
		    }]
		})],
		buttons : [{
			text : '上传',
			iconCls : 'uploadIcon',
			handler : function() {	
				uploadPhoto();
			}
		},{
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				upLoadWin.hide();
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
			if(Ext.getCmp('hLogo').getValue() != null && Ext.getCmp('hLogo').getValue() != ''){
				Ext.getCmp('photo').setSrc(Ext.getCmp('hLogo').getValue());			
			}
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
		}
		addOrEditWin.show();
	}
	
	function upLoadWinInit()
	{
		upLoadWin.show();
		
		Ext.getCmp('upLoadFormPanel').getForm().reset();
	}
	
	function uploadPhoto()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		        	//sumit
		        	Ext.getCmp('upLoadFormPanel').getForm().submit({
						clientValidation: true,
						method: 'post',
						waitTitle : "",
						waitMsg : me.show_tips.wait_tips_msg,
						url:root + "/hosAndDept/upLoadHosLogo.freda",
						success: function(form, action) {
					      Ext.getCmp('hLogo').setValue(action.result.msg);
					      Ext.getCmp('photo').setSrc(action.result.msg);
					      upLoadWin.hide();
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
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/hosAndDept/addHospital.freda";
		}
		else
		{
			submitUrl += "/hosAndDept/editHospital.freda";
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
					    url : root + '/hosAndDept/delHospital.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'hId')
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
	
	
	function setHosDeptWinInit()
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
		
		Ext.create('Ext.window.Window',{
			layout : 'fit',
			width : 350,
			height : 370,
			resizable : false,
			draggable : true,
			closeAction : 'destroy',
			title : '<span>绑定科室</span>',
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
				id : 'hosId',
				name : 'hosId'
				},Ext.create('Ext.tab.Panel',{
				id : 'treeTabPanel',
				name : 'treeTabPanel',
				layout : 'fit',
				items :[{
					xtype : 'treepanel',
					id : 'hosDeptPanel',
					title : '全部科室',
					rootVisible: false,
		    		autoScroll : true,
					animate : false,
					useArrows : false,
					autoLoad : false,
					border : false,
					store: Ext.create("Ext.data.TreeStore",{
							proxy: {
							            type: 'ajax',
							            url: root + '/correlation/hosDeptTreeWithHosForComb.freda?hId=' + record[0].get('hId')
							        },
							root : {
								    	text : '科室信息',
								    	id   : 1,
								    	leaf : 0,
								    	icon : '',
								    	parent : 0
								    } 
						}),
						buttons : [{
						text : '科室绑定保存',
						handler : function(){
							setHosDeptToHos();
						}
					}]
				}]
			})]
		}).show();
		Ext.getCmp('hosId').setValue(record[0].get('hId'));
	}
	
	function setHosDeptToHos()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/correlation/hosDeptToHos.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(Ext.getCmp('hosDeptPanel').getChecked(),'id'),
					    	hId : Ext.getCmp('hosId').getValue()
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