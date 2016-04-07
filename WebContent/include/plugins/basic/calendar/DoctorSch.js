Ext.define('Ext.calendar.DoctorSch',{

	requires: [
		'Ext.Ajax',
        'Ext.layout.container.Border',
        'Ext.picker.Date',
        'Ext.calendar.util.Date',
        'Ext.calendar.CalendarPanel',
        'Ext.calendar.data.MemoryCalendarStore',
        'Ext.calendar.data.MemoryEventStore',
        'Ext.calendar.data.Events',
        'Ext.calendar.data.Calendars',
        'Ext.calendar.form.EventWindow'
    ],
    
    docId : '',
    
    getDocId : function(){
    	return this.docId;
    },
    
    setDocId : function(dId){
    	this.docId = dId;
    },
    
	constructor : function() {
		
		 this.checkScrollOffset();
		
		this.calendarStore = Ext.create('Ext.calendar.data.MemoryCalendarStore', {
            data: Ext.calendar.data.Calendars.getData()
        });
        
        Ext.create('Ext.window.Window',{
        	layout : 'border',
			width : '100%',
			height : '100%',
			resizable : false,
			draggable : true,
			closeAction : 'destroy',
			title : '<span>医务人员排期信息</span>',
			modal : true,
			collapsible : false,
			titleCollapse : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : false,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [{
				id: 'app-center',
                title: '...', // will be updated to the current view's date range
                region: 'center',
                layout: 'border',
                listeners: {
                    'afterrender': function(){
                        Ext.getCmp('app-center').header.addCls('app-center-header');
                    }
                },
				items : [{
                    xtype: 'container',
                    id:'app-west',
                    region: 'west',
                    items: [{
                        xtype: 'datepicker',
                        id: 'app-nav-picker',
                        cls: 'ext-cal-nav-picker',
                        listeners: {
                            'select': {
                                fn: function(dp, dt){
                                    Ext.getCmp('app-calendar').setStartDate(dt);
                                },
                                scope: this
                            }
                        }
                    }]
                },{
                    xtype: 'calendarpanel',
                    eventStore: Ext.create('Ext.calendar.data.MemoryEventStore', {}),
                    calendarStore: this.calendarStore,
                    showDayView: true,
                    showWeekView:false,
                    docId : this.docId,
                    border: false,
                    id:'app-calendar',
                    region: 'center',
                    activeItem: 3, // month view
                    
                    monthViewCfg: {
                        showHeader: true,
                        showWeekLinks: false,
                        showWeekNumbers: false
                    },
                    
                    listeners: {
                        'eventclick': {
                            fn: function(vw, rec, el){
                                this.showEditWindow(rec, el);
                                //this.clearMsg();
                            },
                            scope: this
                        },
                        'eventover': function(vw, rec, el){
                            //console.log('Entered evt rec='+rec.data.Title+', view='+ vw.id +', el='+el.id);
                        },
                        'eventout': function(vw, rec, el){
                            //console.log('Leaving evt rec='+rec.data.Title+', view='+ vw.id +', el='+el.id);
                        },
                        'eventadd': {
                            fn: function(cp, rec){
                                
                            },
                            scope: this
                        },
                        'eventupdate': {
                            fn: function(cp, rec){
                                //this.eventStore.reload();
                            },
                            scope: this
                        },
                        'eventcancel': {
                            fn: function(cp, rec){
                                // edit canceled
                            },
                            scope: this
                        },
                        'viewchange': {
                            fn: function(p, vw, dateInfo){
                            	if(this.editWin){
                                    this.editWin.hide();
                                }
                                if(dateInfo){
                                    Ext.getCmp('app-nav-picker').setValue(dateInfo.activeDate);
                                    this.updateTitle(dateInfo.viewStart, dateInfo.viewEnd);
                                }
                                
                            },
                            scope: this
                        },
                        'dayclick': {
                            fn: function(vw, dt, ad, el){
	                            this.showEditWindow({
	                                    StartDate: dt,
	                                    IsAllDay: ad
	                                }, el);
	                                this.clearMsg();
                            },
                            scope: this
                        },
                        'rangeselect': {
                            fn: function(win, dates, onComplete){
                            
                            },
                            scope: this
                        },
                        'eventmove': {
                            fn: function(vw, rec){
                                var mappings = Ext.calendar.data.EventMappings,
                                    time = rec.data[mappings.IsAllDay.name] ? '' : ' \\a\\t g:i a';
                                
                                //rec.commit();
                                
                                //this.showMsg('排期['+ rec.data[mappings.Title.name] +']被移动至[ '+
                                    //Ext.Date.format(rec.data[mappings.StartDate.name], ('F jS'+time))+']!');
                            },
                            scope: this
                        },
                        'eventresize': {
                            fn: function(vw, rec){
                                //rec.commit();
                                //this.showMsg('排期 ['+ rec.data.Title +']已更新!');
                            	
                            },
                            scope: this
                        },
                        'eventdelete': {
                            fn: function(win, rec){
                                //this.eventStore.reload();
                            },
                            scope: this
                        },
                        'initdrag': {
                            fn: function(vw){
                            	if(this.editWin && this.editWin.isVisible()){
                                    this.editWin.hide();
                                }
                            },
                            scope: this
                        }
                    }
                }]
			}]
        }).show();
	},
	showEditWindow : function(rec, animateTarget){
        if(!this.editWin){
            this.editWin = Ext.create('Ext.calendar.form.EventWindow', {
                calendarStore: this.calendarStore,
                listeners: {
                    'eventadd': {
                        fn: function(win, rec){
                            Ext.Ajax.request({
								    url: root + '/doctor/addSch.freda',
								    params: {
								        startTime : rec.data.StartDate,
								        endTime : rec.data.EndDate,
								        addr : rec.data.Title,
								        type : rec.data.CalendarId,
								        docId : this.docId
								    },
								    success: function(response){
								        var result = Ext.JSON.decode(response.responseText);
								        me.showAlert(result.msg);
								        win.hide();
								        Ext.getCmp('app-calendar').setActiveView('app-calendar-month');
								    }
								});
                        },
                        scope: this
                    },
                    'eventupdate': {
                        fn: function(win, rec){
                        	
                            Ext.Ajax.request({
							    url: root + '/doctor/editSch.freda',
							    params: {
							        startTime : rec.data.StartDate,
							        endTime : rec.data.EndDate,
							        addr : rec.data.Title,
							        type : rec.data.CalendarId,
							        docId : this.docId,
							        schId : rec.data.EventId
							    },
							    success: function(response){
							        var result = Ext.JSON.decode(response.responseText);
							        me.showAlert(result.msg);
							        win.hide();
							        Ext.getCmp('app-calendar').setActiveView('app-calendar-month');
							    }
							});
                        },
                        scope: this
                    },
                    'eventdelete': {
                        fn: function(win, rec){
                        	
                            Ext.Ajax.request({
							    url: root + '/doctor/delSch.freda',
							    params: {
							        schId : rec.data.EventId
							    },
							    success: function(response){
							        var result = Ext.JSON.decode(response.responseText);
							        me.showAlert(result.msg);
							        win.hide();
							        Ext.getCmp('app-calendar').setActiveView('app-calendar-month');
							    }
							});
                        },
                        scope: this
                    },
                    'editdetails': {
                        fn: function(win, rec){
                            win.hide();
                            Ext.getCmp('app-calendar').showEditForm(rec);
                        }
                    }
                }
            });
        }
        this.editWin.show(rec, animateTarget);
    },
    updateTitle: function(startDt, endDt){
        var p = Ext.getCmp('app-center'),
            fmt = Ext.Date.format;
        
        if(Ext.Date.clearTime(startDt).getTime() === Ext.Date.clearTime(endDt).getTime()){
            p.setTitle(fmt(startDt, 'F j, Y'));
        }
        else if(startDt.getFullYear() === endDt.getFullYear()){
            if(startDt.getMonth() === endDt.getMonth()){
                p.setTitle(fmt(startDt, 'F j') + ' - ' + fmt(endDt, 'j, Y'));
            }
            else{
                p.setTitle(fmt(startDt, 'F j') + ' - ' + fmt(endDt, 'F j, Y'));
            }
        }
        else{
            p.setTitle(fmt(startDt, 'F j, Y') + ' - ' + fmt(endDt, 'F j, Y'));
        }
    },
    
    // This is an application-specific way to communicate CalendarPanel event messages back to the user.
    // This could be replaced with a function to do "toast" style messages, growl messages, etc. This will
    // vary based on application requirements, which is why it's not baked into the CalendarPanel.
    showMsg: function(msg){
        Ext.fly('app-msg').update(msg).removeCls('x-hidden');
    },
    clearMsg: function(){
        Ext.fly('app-msg').update('').addCls('x-hidden');
    },
	checkScrollOffset: function() {
        var scrollbarWidth = Ext.getScrollbarSize ? Ext.getScrollbarSize().width : Ext.getScrollBarWidth();
        
        // We check for less than 3 because the Ext scrollbar measurement gets
        // slightly padded (not sure the reason), so it's never returned as 0.
        if (scrollbarWidth < 3) {
            Ext.getBody().addCls('x-no-scrollbar');
        }
        if (Ext.isWindows) {
            Ext.getBody().addCls('x-win');
        }
    }
},
function() {
    /*
     * A few Ext overrides needed to work around issues in the calendar
     */
    
    Ext.form.Basic.override({
        reset: function() {
            var me = this;
            // This causes field events to be ignored. This is a problem for the
            // DateTimeField since it relies on handling the all-day checkbox state
            // changes to refresh its layout. In general, this batching is really not
            // needed -- it was an artifact of pre-4.0 performance issues and can be removed.
            //me.batchLayouts(function() {
                me.getFields().each(function(f) {
                    f.reset();
                });
            //});
            return me;
        }
    });
});