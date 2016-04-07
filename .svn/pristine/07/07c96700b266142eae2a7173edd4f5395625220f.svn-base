/*****************************************************************
                  jQuery Validate扩展验证方法  (ligen)       
*****************************************************************/
$(function(){
	//检查号码是否符合规范，包括长度，类型
	isCardNo = function(card)
	{
	    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
	    if(reg.test(card) === false)
	    {
	        return false;
	    }
	    return true;
	};

	//身份证省的编码 
	var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古", 
	        21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏", 
	        33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南", 
	        42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆", 
	        51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃", 
	        63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外" 
	       }; 
	
	//取身份证前两位,校验省份
	checkProvince = function(card)
	{
	    var province = card.substr(0,2);
	    if(vcity[province] == undefined)
	    {
	        return false;
	    }
	    return true;
	};

	//检查生日是否正确
	checkBirthday = function(card)
	{
	    var len = card.length;
	    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
	    if(len == '15')
	    {
	        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/; 
	        var arr_data = card.match(re_fifteen);
	        var year = arr_data[2];
	        var month = arr_data[3];
	        var day = arr_data[4];
	        var birthday = new Date('19'+year+'/'+month+'/'+day);
	        return verifyBirthday('19'+year,month,day,birthday);
	    }
	    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
	    if(len == '18')
	    {
	        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
	        var arr_data = card.match(re_eighteen);
	        var year = arr_data[2];
	        var month = arr_data[3];
	        var day = arr_data[4];
	        var birthday = new Date(year+'/'+month+'/'+day);
	        return verifyBirthday(year,month,day,birthday);
	    }
	    return false;
	};

	//校验日期
	verifyBirthday = function(year,month,day,birthday)
	{
	    var now = new Date();
	    var now_year = now.getFullYear();
	    //年月日是否合理
	    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)
	    {
	        //判断年份的范围（3岁到100岁之间)
	        var time = now_year - year;
	        if(time >= 3 && time <= 100)
	        {
	            return true;
	        }
	        return false;
	    }
	    return false;
	};
	//校验位的检测
	checkParity = function(card)
	{
	    //15位转18位
	    card = changeFivteenToEighteen(card);
	    var len = card.length;
	    if(len == '18')
	    {
	        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
	        var cardTemp = 0, i, valnum; 
	        for(i = 0; i < 17; i ++) 
	        { 
	            cardTemp += card.substr(i, 1) * arrInt[i]; 
	        } 
	        valnum = arrCh[cardTemp % 11]; 
	        if (valnum == card.substr(17, 1)) 
	        {
	            return true;
	        }
	        return false;
	    }
	    return false;
	};
	//15位转18位身份证号
	changeFivteenToEighteen = function(card)
	{
	    if(card.length == '15')
	    {
	        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
	        var cardTemp = 0, i; 
	        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
	        for(i = 0; i < 17; i ++) 
	        { 
	            cardTemp += card.substr(i, 1) * arrInt[i]; 
	        } 
	        card += arrCh[cardTemp % 11]; 
	        return card;
	    }
	    return card;
	};
	isIdCardNo = function(card)
	{
	    //是否为空
	    if(card === '')
	    {
	        return false;
	    }
	    //校验长度，类型
	    if(isCardNo(card) === false)
	    {
	        return false;
	    }
	    //检查省份
	    if(checkProvince(card) === false)
	    {
	        return false;
	    }
	    //校验生日
	    if(checkBirthday(card) === false)
	    {
	        return false;
	    }
	    //检验位的检测
	    if(checkParity(card) === false)
	    {
	        return false;
	    }
	    return true;
	};
	    // 身份证号码验证 
    jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
     return this.optional(element) || isIdCardNo(value); 
    }, "请正确输入您的身份证号码");
	    // 护照号格式验证
    jQuery.validator.addMethod("isPassport", function(value,element) {
          var passport = "/(P/d{7})|(G/d{8})/";
         return this.optional(element) || (passport.test(value));
        }, "请正确填写您的护照号");
    // 判断整数value是否等于0 
    jQuery.validator.addMethod("isIntEqZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value==0;       
    }, "整数必须为0"); 
      
    // 判断整数value是否大于0
    jQuery.validator.addMethod("isIntGtZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value>0;       
    }, "整数必须大于0"); 
      
    // 判断整数value是否大于或等于0
    jQuery.validator.addMethod("isIntGteZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value>=0;       
    }, "整数必须大于或等于0");   
    
    // 判断整数value是否不等于0 
    jQuery.validator.addMethod("isIntNEqZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value!=0;       
    }, "整数必须不等于0");  
    
    // 判断整数value是否小于0 
    jQuery.validator.addMethod("isIntLtZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value<0;       
    }, "整数必须小于0");  
    
    // 判断整数value是否小于或等于0 
    jQuery.validator.addMethod("isIntLteZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || value<=0;       
    }, "整数必须小于或等于0");  
    
    // 判断浮点数value是否等于0 
    jQuery.validator.addMethod("isFloatEqZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value==0;       
    }, "浮点数必须为0"); 
      
    // 判断浮点数value是否大于0
    jQuery.validator.addMethod("isFloatGtZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value>0;       
    }, "浮点数必须大于0"); 
      
    // 判断浮点数value是否大于或等于0
    jQuery.validator.addMethod("isFloatGteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value>=0;       
    }, "浮点数必须大于或等于0");   
    
    // 判断浮点数value是否不等于0 
    jQuery.validator.addMethod("isFloatNEqZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value!=0;       
    }, "浮点数必须不等于0");  
    
    // 判断浮点数value是否小于0 
    jQuery.validator.addMethod("isFloatLtZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value<0;       
    }, "浮点数必须小于0");  
    
    // 判断浮点数value是否小于或等于0 
    jQuery.validator.addMethod("isFloatLteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value<=0;       
    }, "浮点数必须小于或等于0");  
    
    // 判断浮点型  
    jQuery.validator.addMethod("isFloat", function(value, element) {       
         return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
    }, "只能包含数字、小数点等字符"); 
     
    // 匹配integer
    jQuery.validator.addMethod("isInteger", function(value, element) {       
         return this.optional(element) || (/^[-\+]?\d+$/.test(value) && parseInt(value)>=0);       
    }, "匹配integer");  
     
    // 判断数值类型，包括整数和浮点数
    jQuery.validator.addMethod("isNumber", function(value, element) {       
         return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
    }, "匹配数值类型，包括整数和浮点数");  
    
    // 只能输入[0-9]数字
    jQuery.validator.addMethod("isDigits", function(value, element) {       
         return this.optional(element) || /^\d+$/.test(value);       
    }, "只能输入0-9数字");  
    
    // 判断中文字符 
    jQuery.validator.addMethod("isChinese", function(value, element) {       
         return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
    }, "只能包含中文字符。");   
 
    // 判断英文字符 
    jQuery.validator.addMethod("isEnglish", function(value, element) {       
         return this.optional(element) || /^[A-Za-z]+$/.test(value);       
    }, "只能包含英文字符。");   
 
     // 手机号码验证    
    jQuery.validator.addMethod("isMobile", function(value, element) {    
      var length = value.length;    
      return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(17[0,1,3,5,6,7,8]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
    }, "请正确填写您的手机号码。");

    // 电话号码验证    
    jQuery.validator.addMethod("isPhone", function(value, element) {    
      var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
      return this.optional(element) || (tel.test(value));    
    }, "请正确填写您的电话号码。");

    // 联系电话(手机/电话皆可)验证   
    jQuery.validator.addMethod("isTel", function(value,element) {   
        var length = value.length;   
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0,1,3,5,6,7,8]{1})|(18[0-9]{1}))+\d{8})$/;   
        var tel = /^(\d{3,4}-?)?\d{7,9}$/g;       
        return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));   
    }, "请正确填写您的联系方式"); 
 
     // 匹配qq      
    jQuery.validator.addMethod("isQq", function(value, element) {       
         return this.optional(element) || /^[1-9]\d{4,12}$/;       
    }, "匹配QQ");   
 
     // 邮政编码验证    
    jQuery.validator.addMethod("isZipCode", function(value, element) {    
      var zip = /^[0-9]{6}$/;    
      return this.optional(element) || (zip.test(value));    
    }, "请正确填写您的邮政编码。");  
    
    // 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。      
    jQuery.validator.addMethod("isPwd", function(value, element) {       
         return this.optional(element) || /^[a-zA-Z]\\w{6,12}$/.test(value);       
    }, "以字母开头，长度在6-12之间，只能包含字符、数字和下划线。");  
    
    // IP地址验证   
    jQuery.validator.addMethod("ip", function(value, element) {    
      return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
    }, "请填写正确的IP地址。");
   
    // 字符验证，只能包含中文、英文、数字、下划线等字符。    
    jQuery.validator.addMethod("stringCheck", function(value, element) {       
         return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
    }, "只能包含中文、英文、数字、下划线等字符");  
   
    // 匹配english  
    jQuery.validator.addMethod("isEnglish", function(value, element) {       
         return this.optional(element) || /^[A-Za-z]+$/.test(value);       
    }, "匹配english");  
    
    // 匹配汉字  
    jQuery.validator.addMethod("isChinese", function(value, element) {       
         return this.optional(element) || /^[\u4e00-\u9fa5]+$/.test(value);       
    }, "匹配汉字");
    
    // 匹配中文(包括汉字和字符) 
    jQuery.validator.addMethod("isChineseChar", function(value, element) {       
         return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
    }, "匹配中文(包括汉字和字符) ");
      
    // 判断是否为合法字符(a-zA-Z0-9-_)
    jQuery.validator.addMethod("isRightfulString", function(value, element) {       
         return this.optional(element) || /^[A-Za-z0-9_-]+$/.test(value);       
    }, "判断是否为合法字符(a-zA-Z0-9-_)");
    
    // 判断是否包含中英文特殊字符，除英文"-_"字符外
    jQuery.validator.addMethod("isContainsSpecialChar", function(value, element) {  
         var reg = RegExp(/[(\ )(\`)(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\+)(\=)(\|)(\{)(\})(\')(\:)(\;)(\')(',)(\[)(\])(\.)(\<)(\>)(\/)(\?)(\~)(\！)(\@)(\#)(\￥)(\%)(\…)(\&)(\*)(\（)(\）)(\—)(\+)(\|)(\{)(\})(\【)(\】)(\‘)(\；)(\：)(\”)(\“)(\’)(\。)(\，)(\、)(\？)]+/);   
         return this.optional(element) || !reg.test(value);       
    }, "含有中英文特殊字符");
});