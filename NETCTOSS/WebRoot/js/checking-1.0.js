$.fn.checking=function(object,errormsg,check_value){
				var flag = false;
				var val = object.val();
				if( val == null || val == "" ){
					this.text(errormsg);
					return flag;
				}else if(val.length > check_value){
					this.text("");
					flag = true;
					return flag;
				}
};
//��֤600���ڵ�����
$.fn.checking2=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^[0-9]{1,3}$/;
	if( !c.test(val) || val > 600 || val < 0){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤���ִ�С0-100000
$.fn.checking3=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^\d+(\.\d+){0,}$/;
	if( !c.test(val) || val > 100000 || val < 0){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤�绰��ʽ
$.fn.checking4=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^0?\d{11}$/;
	var c1 = /\(?\d{3,4}[-\)]?\d{7,8}$/;
	if( !c.test(val) || !c1.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val) || c1.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤���֤
$.fn.checking5=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^\d{15}|\d{18}$/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};

//��֤email
$.fn.checking6=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤�ʱ�
$.fn.checking7=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /[1-9]\d{5}(?!\d)/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤QQ��
$.fn.checking8=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /[1-9][0-9]{4,}/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤�������������Ƿ���ͬ
$.fn.checking9=function(pwd1,pwd2,object,msg){
	var flag = false;
	var n1 = pwd1.val();
	var n2 = pwd2.val();
	if(n1 == n2){
		flag = true;
		return flag;
	}else{
		object.text(msg);
		return flag;
	}
};
//��֤��½�˺� ��ĸ��ͷ 4-15λ
$.fn.checking10=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤�û�����
$.fn.checking11=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^[\u4e00-\u9fa5]{2,4}$/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};
//��֤�Ƿ��ַ�',",/,%,$,+
$.fn.checking12=function(object){
	var flag = false;
	var val = object.val();
	var c = /\'|\"|\/|\%|\+\$|\(|\)/;
	var c1 = /\n[\s| ]*\r /;
	if( c.test(val)){
		return flag;
	}else if(!c.test(val) || c1.test(val)){
		flag = true;
		return flag;
	}
};
//��֤��ɫ���� 2-10λ����
$.fn.checking13=function(object,errormsg){
	var flag = false;
	var val = object.val();
	var c = /^[\u4e00-\u9fa5]{2,10}$/;
	if( !c.test(val)){
		this.text(errormsg);
		return flag;
	}else if(c.test(val)){
		this.text("");
		flag = true;
		return flag;
	}
};