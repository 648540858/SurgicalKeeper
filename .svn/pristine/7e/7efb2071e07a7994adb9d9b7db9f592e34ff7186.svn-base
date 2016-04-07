Ext.Loader.setPath('Ext.calendar',  root + '/include/plugins/basic/calendar');

Ext.require([
    'Ext.calendar.DoctorSch'
]);

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
			            url: root + '/doctor/cityTreeWithHosInit.freda'
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
    				Ext.getCmp('hId').setValue(record.get('id').split('-')[1])
					Ext.data.StoreManager.lookup('dataStore').load({
    					params : {
    						hId : record.get('id').split('-')[1],
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
					name : 'docId'
				},{
					name : 'hdName'
				},{
					name : 'hName'
				},{
					name : 'docName'
				},{
					name : 'docSex'
				},{
					name : 'docAge'
				},{
					name : 'docPhone'
				},{
					name : 'docTel'
				},{
					name : 'docRank'
				},{
					name : 'docDrank'
				},{
					name : 'docStatus'
				},{
					name : 'cTime'
				},{
					name : 'uTime'
				}],
				proxy : {
					type : 'ajax',
					url  : root + '/doctor/listAllDoctor.freda',
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
						hId : Ext.getCmp('hId').getValue()};
						Ext.apply(store.proxy.extraParams, new_params);
					}
				}]
			}),
			columns : [{
				xtype: 'rownumberer'
			},{
				text : '医院名称',
				dataIndex: 'hName'
			},{
				text : '科室名称',
				dataIndex: 'hdName'
			},{
				text : '姓名',
				dataIndex: 'docName'
			},{
				text : '性别',
				dataIndex: 'docSex',
				renderer : function(value){
					return  getCodeText('SEX',value);
				}
			},{
				text : '年龄',
				dataIndex: 'docAge'
			},{
				text : '职称',
				dataIndex: 'docRank',
				renderer : function(value){
					return  getCodeText('DOC_RANK',value);
				}
			},{
				text : '科室职称',
				dataIndex: 'docDrank',
				renderer : function(value){
					return  getCodeText('DOC_DEPT_RANK',value);
				}
			},{
				text : '办公电话',
				dataIndex: 'docPhone'
			},{
				text : '个人电话',
				dataIndex: 'docTel'
			},{
				text : '创建时间',
				dataIndex: 'cTime'
			},{
				text : '最后修改时间',
				dataIndex: 'uTime'
			},{
				text : '状态',
				dataIndex: 'docStatus',
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
				text : '绑定疾病信息',
				iconCls : 'arrow_switchIcon',
				handler : function() {
					setSicknessWinInit();
				}
			},{
				text : '设定预约时段',
				iconCls : 'arrow_switchIcon',
				handler : function() {
					schWinInit();
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
		width : 900,
		height : 490,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span>医务人员信息</span>',
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
					    	columnWidth : 0.4,
					    	bodyPadding: 5,
					    	border : false,
					    	items : [{
					    		xtype : 'textfield',
					    		fieldLabel : '名称',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'docName',
					    		name : 'docName'
					    	},{
						    	xtype : 'combo',
						    	fieldLabel : '性别',
						    	id : 'docSex',
								name : 'docSex',
								width : '100%',
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
						    	fieldLabel : '年龄',
						    	xtype : 'numberfield',
						    	maxValue: 99,
				        		minValue: 0,
				        		step: 1,
				        		width : '100%',
								blankText : me.show_tips.blank_text_msg,
								name : 'docAge',
								id : 'docAge',
								allowBlank : false
						    },{
					    		xtype : 'textfield',
					    		fieldLabel : '办公电话',
					    		blankText : me.show_tips.blank_text_msg,
					    		//allowBlank : false,
					    		width : '100%',
					    		id : 'docPhone',
					    		name : 'docPhone'
					    	},{
					    		xtype : 'textfield',
					    		fieldLabel : '个人电话',
					    		blankText : me.show_tips.blank_text_msg,
					    		//allowBlank : false,
					    		width : '100%',
					    		id : 'docTel',
					    		name : 'docTel'
					    	}]
					    },{
					    	columnWidth : 0.4,
					    	bodyPadding: 5,
					    	border : false,
					    	items : [{
						    	xtype : 'combo',
						    	fieldLabel : '科室',
						    	id : 'hdId',
								name : 'hdId',
								editable : false,
								width : '100%',
								triggerAction : 'all',
								queryMode: 'local',
								allowBlank : false,
								blankText : me.show_tips.blank_text_msg,
								store : Ext.create('Ext.data.Store',{
									storeId : 'hosDeptCombo',
									autoLoad: false,
									fields: ['name', 'code'],
									proxy : {
									type : 'ajax',
									url  : root + '/doctor/listAllHosDeptForComb.freda',
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
						    	xtype : 'combo',
						    	fieldLabel : '职称',
						    	id : 'docRank',
								name : 'docRank',
								width : '100%',
								editable : false,
								triggerAction : 'all',
								queryMode: 'local',
								allowBlank : false,
								blankText : me.show_tips.blank_text_msg,
								store : Ext.create('Ext.data.Store',{
									fields: ['name', 'code'],
									data : getCodeArray('DOC_RANK')
								}),
								displayField: 'name',
				    			valueField: 'code'
		    				},{
						    	xtype : 'combo',
						    	fieldLabel : '科室职称',
						    	id : 'docDrank',
								name : 'docDrank',
								width : '100%',
								editable : false,
								triggerAction : 'all',
								queryMode: 'local',
								allowBlank : false,
								blankText : me.show_tips.blank_text_msg,
								store : Ext.create('Ext.data.Store',{
									fields: ['name', 'code'],
									data : getCodeArray('DOC_DEPT_RANK')
								}),
								displayField: 'name',
				    			valueField: 'code'
		    				},{
					    		fieldLabel : '从业时间(年)',
						    	xtype : 'numberfield',
						    	maxValue: 99,
				        		minValue: 0,
				        		step: 1,
				        		width : '100%',
								blankText : me.show_tips.blank_text_msg,
								//allowBlank : false,
								name : 'docWtime',
								id : 'docWtime'
							},{
						    	xtype : 'combo',
						    	fieldLabel : '状态',
						    	id : 'docStatus',
								name : 'docStatus',
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
					    	columnWidth : 0.199,
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
					    		fieldLabel : 'Email',
					    		blankText : me.show_tips.blank_text_msg,
					    		//allowBlank : false,
					    		width : '100%',
					    		id : 'docEmail',
					    		name : 'docEmail'
					    	},{
					    		xtype : 'textfield',
					    		fieldLabel : '办公地址',
					    		blankText : me.show_tips.blank_text_msg,
					    		//allowBlank : false,
					    		width : '100%',
					    		id : 'docDaddr',
					    		name : 'docDaddr'
					    	},{
					    		xtype : 'textfield',
					    		fieldLabel : '常用地址',
					    		blankText : me.show_tips.blank_text_msg,
					    		//allowBlank : false,
					    		width : '100%',
					    		id : 'docOrderAddr',
					    		name : 'docOrderAddr'
					    	},{
					    		xtype : 'textareafield',
					    		fieldLabel : '简介',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'docIntro',
					    		name : 'docIntro'
					    	},{
					    		xtype : 'textareafield',
					    		fieldLabel : '擅长',
					    		blankText : me.show_tips.blank_text_msg,
					    		allowBlank : false,
					    		width : '100%',
					    		id : 'docGoodAt',
					    		name : 'docGoodAt'
					    	}]
					    }]
				    },{
		    	xtype : 'hiddenfield',
		    	id : 'docId',
		    	name : 'docId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'docIcon',
		    	name : 'docIcon'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'hId',
		    	name : 'hId'
		    },{
		    	xtype : 'hiddenfield',
		    	id : 'hName',
		    	name : 'hName'
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
						url:root + "/doctor/uploadPhoto.freda",
						success: function(form, action) {
					      Ext.getCmp('docIcon').setValue(action.result.msg);
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
	
	
	//Ext.data.StoreManager.lookup('hosDeptCombo').load();
	function addOrEditWinInit(mode)
	{	
		Ext.data.StoreManager.lookup('hosDeptCombo').load({
			params : {hId : Ext.getCmp('hId').getValue()}
		});
		
		if(mode == 'add')
		{
			record = Ext.getCmp('cityTree').getSelection()[0];
			if(Ext.isEmpty(record))
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			if(record.get('leaf') != 1)
			{
				me.showAlert(me.show_tips.no_choose_msg);
				return ;
			}
			var hId = record.get('id').split('-')[1];
			Ext.getCmp('dataFormPanel').getForm().reset();
			Ext.getCmp('hId').setValue(hId);
			Ext.getCmp('hName').setValue(record.get('text'));
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
			Ext.getCmp('dataFormPanel').getForm().load({
				url:root + '/doctor/loadDoctor.freda?docId=' + record[0].get('docId'),
				success : function(form, action){
					if(Ext.getCmp('docIcon').getValue() != null && Ext.getCmp('docIcon').getValue() != ''){
						Ext.getCmp('photo').setSrc(Ext.getCmp('docIcon').getValue());			
					}	
				}
			});
			Ext.getCmp('submitMode').setValue(SUBMIT_MODE_EDIT);
		}
		
		addOrEditWin.show();
	}
	
	function submitFormInfo()
	{
		var submitUrl = root;
		if(Ext.isEmpty(Ext.getCmp('submitMode').getValue()))
		{
			submitUrl += "/doctor/addDoctor.freda";
		}
		else
		{
			submitUrl += "/doctor/editDoctor.freda";
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
					    url : root + '/doctor/delDoctor.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(record,'docId')
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
	
	function setSicknessWinInit()
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
			title : '<span>绑定疾病</span>',
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
				id : 'doctorId',
				name : 'doctorId'
				},Ext.create('Ext.tab.Panel',{
				id : 'treeTabPanel',
				name : 'treeTabPanel',
				layout : 'fit',
				items :[{
					xtype : 'treepanel',
					id : 'sicknessPanel',
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
							            url: root + '/correlation/sicknessTreeWithDocForComb.freda?docId=' + record[0].get('docId')
							        },
							root : {
								    	text : '疾病信息',
								    	id   : 1,
								    	leaf : 0,
								    	icon : '',
								    	parent : 0
								    } 
						}),
						buttons : [{
						text : '科室绑定保存',
						handler : function(){
							setSicknessToDoctor();
						}
					}]
				}]
			})]
		}).show();
		Ext.getCmp('doctorId').setValue(record[0].get('docId'));
	}
	
	function setSicknessToDoctor()
	{
		Ext.Msg.show({
		    title:me.show_tips.alert_tips_title,
		    message: me.show_tips.is_want_to_do_msg,
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/correlation/sicknessToDoctor.freda',
					    method : 'POST',
					    params : {
					    	ids : me.jsArray2JsString(Ext.getCmp('sicknessPanel').getChecked(),'id'),
					    	docId : Ext.getCmp('doctorId').getValue()
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
	
	function schWinInit()
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
		var docId = record[0].get('docId');
		params.docId = docId;
		Ext.create('Ext.calendar.DoctorSch',{}).setDocId(docId);
	}
});