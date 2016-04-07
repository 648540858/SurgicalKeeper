Ext.onReady(function(){

	Ext.tip.QuickTipManager.init();
	
	var westPanel = Ext.create("Ext.panel.Panel" ,{
		split: true,
        layout: {
                    type: 'accordion',
                    //activeOnTop : true,
                    animate: true
            },
        border : false,
        //renderTo: Ext.getBody(),
        layoutConfig: {
             //activeOnTop : true,
             animate : true,
             titleCollapse : true
        },
        listeners :{
        	'beforerender' : function (){
        		 Ext.Ajax.request({
        		 	 url: root + '/index/menuPanelInit.freda?pId=1',
        		 	 success: function(response){
        		 	 	
        		 	 	var resultArray = Ext.JSON.decode(response.responseText);
        		 	 	
        		 	 	for(var i=0;i< resultArray.length;i++)
        		 	 	{
        		 	 		var accordionPanel = null;
					        	
        		 	 		accordionPanel = Ext.create("Ext.panel.Panel",{
        		 	 			autoScroll : true,
	        		 	 		title : resultArray[i].text,
	        		 	 		id : resultArray[i].id,
					        	items : [Ext.create("Ext.tree.Panel",{
					        		id : 'menuTree_' + resultArray[i].id,
					        		name : 'menuTree_' + resultArray[i].id,
					        		rootVisible: false,
					        		autoScroll : true,
									animate : false,
									useArrows : false,
									border : false,
					        		store: Ext.create("Ext.data.TreeStore",{
					        			//model : 'Ext.data.CommonTreeModel',
					        			proxy: {
									            type: 'ajax',
									            //the store will get the content from the .json file
									            url: root + '/index/menuPanelInit.freda'
									        },
									    root : {
									    	text : resultArray[i].text,
									    	id   : resultArray[i].id,
									    	leaf : 0,
									    	icon : '',
									    	parent : 0
									    }  
					        		}),
					        		listeners : [{
					        			'itemclick' : function( accordionPanel, record, item, index, e, eOpts){
					        				if(record.get('leaf'))
					        				{
					        					var tab = centerPanel.getComponent('tab_' +record.get('id'));
					        					if(!tab)
					        					{
						        					tab = Ext.create("Ext.panel.Panel",{
						        						 title : record.get('text'),
														 layout : 'fit',
														 closable : true,
														 id : 'tab_' +record.get('id'),
														 items : [Ext.create('MyApp.ux.IFrameComponent',{id : 'tab_compontent' + record.get('id'),url : root + '/index/tabPageInit.freda?mId='+record.get('id')})]
						        					});
						        					centerPanel.add(tab);
					        					}
					        					centerPanel.setActiveTab(tab);
					        				}
					        			}
					        		}]
					        	})]
	        		 	 	});
	        		 	 	Ext.getCmp('menuTree_' + resultArray[i].id).expandAll();
	        		 	 	westPanel.add(accordionPanel);
        		 	 	}
        		 	 	Ext.getBody().unmask();
        		 	 }	 
        		 });
        	}
        }
	});
	
	var centerPanel = Ext.create("Ext.tab.Panel",{
		//activeTab: 0,
		region : 'center',
		xtype  : 'panel',
		layout : 'fit',
     	items : [{
					 layout : 'fit',
					 title : '我的工作台',
					 id : 'tab_myWorkPanel',
					 //xtype : 'iframecomponent',
					 items : [Ext.create('MyApp.ux.IFrameComponent',{id : '_myWorkPanel' ,url : root + '/index/myBenchPageInit.freda'})]
				}]

	});
	
	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		maskText : '玩命加载中....',
		items : [{
			title : '<span>菜单导航栏</span>',
			collapsible : true,
			width : 210,
			layout : 'fit',
			minSize : 160,
			maxSize : 280,
			border : false,
			split : true,
			region : 'west',
			autoScroll : true,
			items : [westPanel]
		},{
			region : 'center',
			layout : 'border',
			border : false,
			split : true,
			items : [centerPanel,Ext.create("Ext.panel.Panel",{
								frame : true,
								region : 'south',
								layout : 'fit',
								border : true,
								height : 20,
								contentEl : 'south'
							})]
		},{
			region : 'north',
			layout : 'fit',
			split : false,
			height : 70,
			border : false,
			items : [Ext.create("Ext.panel.Panel",{
							frame : true,
							contentEl : 'top',
							split : true
						
						})]
		}],
		listeners : [{
			'beforerender' : function(o, eOpts){
				Ext.getBody().mask(this.maskText == '' ? parent.window.show_tips.wait_tips_msg : this.maskText);
			}
		}]
	});
	
	logout = function()
	{
		Ext.Msg.show({
		    title: show_tips.alert_tips_title,
		    message: '确定退出系统?',
		    buttons: Ext.Msg.YESNO,
		    icon: Ext.Msg.QUESTION,
		    fn: function(btn) {
		        if (btn === 'yes') {
		       		Ext.ux.Ajax.request({
					    url : root + '/index/logout.freda',
					    method : 'POST',
					    params : {},
					    success: function(response) {
					    	 var result = Ext.JSON.decode(response.responseText);
					    	 Ext.Msg.show({
							    title:show_tips.alert_tips_title,
							    message: result.msg,
							    buttons: Ext.Msg.OK,
							    fn: function(btn) {
							        if (btn === 'ok') {
							           window.location.href = root+'/index/indexPageInit.freda';
							        }
							    }
							});
					    }	  
					});
		        }
		    }
		});
	}
	
	changePwdWinInit = function()
	{
		var addOrEditWin = Ext.create('Ext.window.Window',{
			layout : 'fit',
			width : 350,
			height : 170,
			resizable : false,
			draggable : true,
			closeAction : 'close',
			title : '<span>修改密码</span>',
			modal : true,
			collapsible : true,
			titleCollapse : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			pageY : 220,
			pageX : document.body.clientWidth / 2 - 350 / 2,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [Ext.create('Ext.form.Panel',{
				id : 'pwdFormPanel',
				name : 'pwdFormPanel',
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
					fieldLabel : '密\t\t码',
					name : 'oldPwd',
					id : 'oldPwd',
					cls : 'key',
					inputType : 'password',
					blankText : '密码不能为空!',
					maxLength : 20,
					maxLengthText : '密码长度不能超过20个字符!',
					allowBlank : false
				},{
					fieldLabel : '新密码',
					name : 'newPwd',
					id : 'newPwd',
					cls : 'key',
					inputType : 'password',
					blankText : '密码不能为空!',
					maxLength : 20,
					maxLengthText : '密码长度不能超过20个字符!',
					allowBlank : false
				},{
					fieldLabel : '确认密码',
					name : 'newPwd2',
					id : 'newPwd2',
					cls : 'key',
					inputType : 'password',
					blankText : '密码不能为空!',
					maxLength : 20,
					maxLengthText : '密码长度不能超过20个字符!',
					allowBlank : false
				}]
			})],
			buttons : [{
				text : '保存',
				//iconCls : 'acceptIcon',
				handler : function() {	
					if (Ext.getCmp('pwdFormPanel').form.isValid()) {
						Ext.getCmp('pwdFormPanel').form.submit({
							url : root + '/index/changePwd.freda',
							waitTitle : "",
							method : 'POST',
							waitMsg : show_tips.wait_tips_msg,
							success : function(form, action) {
								showAlert(action.result.msg);
								addOrEditWin.close();
							},
							failure : function(form, action) {
								showAlert(action.result.msg);
							}
						});
						
					}
				}
			},{
				text : '关闭',
				//iconCls : 'deleteIcon',
				handler : function() {
					addOrEditWin.close();
				}
			}]
		}).show(); 
	}
	
});