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
					name : 'lId'
				},{
					name : 'lSp'
				},{
					name : 'lTitle'
				},{
					name : 'lStime'
				},{
					name : 'lEtime'
				},{
					name : 'lContent'
				},{
					name : 'lCount'
				},{
					name : 'lStatus'
				},{
					name : 'lCom'
				},{
					name : 'lPhoto'
				},{
					name : 'lmUrl'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/lecture/listLecture.freda',
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
				text : '标题',
				dataIndex : 'lTitle'
			},{
				text : '主讲人',
				dataIndex : 'lSp'
			},{
				text : '主办单位',
				dataIndex: 'lCom'
			},{
				text : '视频地址',
				dataIndex: 'lmUrl'
			},{
				text : '开始时间',
				dataIndex: 'lStime'
			},{
				text : '结束时间',
				dataIndex: 'lEtime'
			},{
				text : '点击次数',
				dataIndex: 'lCount'
			},{
				text : '状态',
				dataIndex : 'lStatus',
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
		width : 850,
		height : 620,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>讲堂信息</span>',
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
						    	fieldLabel : '标题',
								blankText : me.show_tips.blank_text_msg,
								width : '100%',
								name : 'lTitle',
								id : 'lTitle',
								allowBlank : false
						    },{
						    	xtype : 'textfield',
						    	fieldLabel : '主讲人',
								blankText : me.show_tips.blank_text_msg,
								width : '100%',
								name : 'lSp',
								id : 'lSp',
								allowBlank : false
						    },{
						    	xtype : 'textfield',
						    	fieldLabel : '主办单位',
								blankText : me.show_tips.blank_text_msg,
								width : '100%',
								name : 'lCom',
								id : 'lCom',
								allowBlank : false
						    },{
						    	xtype : 'textfield',
						    	fieldLabel : '视频地址',
								blankText : me.show_tips.blank_text_msg,
								width : '100%',
								name : 'lmUrl',
								id : 'lmUrl',
								allowBlank : false
						    },{
						    	xtype : 'combo',
						    	fieldLabel : '状态',
						    	id : 'lStatus',
								name : 'lStatus',
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
						    	fieldLabel : '开始时间',
								blankText : me.show_tips.blank_text_msg,
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								name : 'lStime',
								id : 'lStime',
								width : '100%',
								allowBlank : false
						    },{
						    	fieldLabel : '结束时间',
								blankText : me.show_tips.blank_text_msg,
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								name : 'lEtime',
								id : 'lEtime',
								width : '100%',
								allowBlank : false
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
									height : 265,
									id : 'content',
									fieldLabel : '内容'
								}]
							}]
					    }]
				    },{
		    	xtype : 'hiddenfield',
		    	id : 'hId',
		    	name : 'hId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'lPhoto',
		    	name : 'lPhoto'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'lId',
		    	name : 'lId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'lContent',
		    	name : 'lContent'
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
			Ext.getCmp('content').setValue(Ext.getCmp('lContent').getValue());
			if(Ext.getCmp('lPhoto').getValue() != null && Ext.getCmp('lPhoto').getValue() != ''){
				Ext.getCmp('photo').setSrc(Ext.getCmp('lPhoto').getValue());			
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
						url:root + "/lecture/upLoadLecturePav.freda",
						success: function(form, action) {
					      Ext.getCmp('lPhoto').setValue(action.result.msg);
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
			submitUrl += "/lecture/addLecture.freda";
		}
		else
		{
			submitUrl += "/lecture/editLecture.freda";
		}
		Ext.getCmp('lContent').setValue(Ext.getCmp('content').getValue());
		//alert(Ext.getCmp('content').getValue());
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
					    url : root + '/lecture/delLecture.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'lId')
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