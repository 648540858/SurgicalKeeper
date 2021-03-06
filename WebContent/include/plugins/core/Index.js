Ext.onReady(function(){
	
	Ext.tip.QuickTipManager.init();
	
	var panel = Ext.create('Ext.panel.Panel',{
		//el : 'hello-tabs',
		autoTabs : true,
		region : 'center',
		deferredRender : false,
		frame : true,
		border : false,
		items : {
			xtype : 'tabpanel',
			id : 'loginTabs',
			activeTab : 0,
			height : 120,
			border : false,
			layout : 'fit',
			items : [{
				title : '身份验证',
				xtype : 'form',
				frame : true,
				id : 'loginForm',
				defaults : {
					width : 330
				},
				//bodyStyle : 'padding:20 50 50 50;',
				bodyPadding : 12,
				defaultType : 'textfield',
				labelWidth : 40,
				labelSeparator : '：',
				items : [{
							fieldLabel : '账\t\t号',
							name : 'account',
							id : 'account',
							cls : 'user',
							blankText : '账号不能为空!',
							maxLength : 20,
							maxLengthText : '账号长度不可以超过20个字符!',
							allowBlank : false,
							value : 'admin',
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										Ext.getCmp('password').focus();
									}
								}
							}
						}, {
							fieldLabel : '密\t\t码',
							name : 'pwd',
							id : 'password',
							cls : 'key',
							inputType : 'password',
							blankText : '密码不能为空!',
							maxLength : 20,
							maxLengthText : '密码长度不能超过20个字符!',
							allowBlank : false,
							value : '',
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										login();
									}
								}
							}
						}]
			}, {
				title : '公告',
				contentEl : 'news-tab',
				defaults : {
					width : 230
				}
			}, {
				title : '提示',
				contentEl : 'tips-tab',
				defaults : {
					width : 230
				}
			}]
		}
	});
	
	Ext.create('Ext.window.Window',{
		renderTo : Ext.getBody(),
		layout : 'border',
		width : 400,
		height : 220,
		closeAction : 'hide',
		plain : true,
		//modal : true,
		pageX : document.body.clientWidth / 2 ,
		border : false,
		collapsible : false,
		titleCollapse : false,
		maximizable : false,
		draggable : false,
		closable : false,
		resizable : false,
		animateTarget : document.body,
		items : [panel,{
				region : 'north',
				contentEl : 'hello-tabs',
				heigth : 30
			}],
		buttons : [{
			text : '登\t\t录',
			//iconCls : 'acceptIcon',
			handler : function() {
				login();
			}
		}]
	}).show();
	
	function login()
	{
		if (Ext.getCmp('loginForm').form.isValid()) {
			Ext.getCmp('loginForm').form.submit({
				url : root + '/index/login.freda',
				waitTitle : "",
				method : 'POST',
				waitMsg : show_tips.wait_tips_msg,
				success : function(form, action) {
					window.location.href = root+'/index/mainPageInit.freda';
				},
				failure : function(form, action) {
					showAlert(action.result.msg);
				}
			});
			
		}
	}
	
});