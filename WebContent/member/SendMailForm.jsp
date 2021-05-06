<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�������</title>
<style type="text/css">
.sendmail{
 width: 300px;
 margin: auto;
}

.field {
  margin-bottom: 10px;
}

.field label {
  display: block;
  font-size: 15px;
  color: #777;
}

.field input {
  display: block;
  min-width: 250px;
  line-height: 1.5;
  font-size: 14px;
}

input[type="submit"] {
  display: block;
  padding: 6px 30px;
  font-size: 14px;
  background-color: black;
  margin-left: 70px;
  color: #fff;
  border: none
}
</style>
</head>
<body>
<h1 align="center">�����ڿ��� ���Ϻ�����</h1>
<div class="sendmail">

	<form id="form">
  <div class="field">
    <label for="to_name">�޴� ���</label>
    <input type="text" name="to_name" id="to_name" value="Ratatouille Team">
  </div>
  <div class="field">
    <label for="from_name">������ ���</label>
    <input type="text" name="from_name" id="from_name">
  </div>
  <div class="field">
    <label for="reply_to">���� ���� �ּ�</label>
    <input type="text" name="reply_to" id="reply_to">
  </div>
  <div class="field">
    <label for="message">�޼��� ����</label>
  <!--   <input type="text" style="width:300px;height:200px;" name="message" id="message"> -->
	<textarea id="message" name="message" rows="10" cols="35"></textarea>
	<!-- ���� ������� text ��ſ�  textarea ���·� ������. -�鼺��--->
  </div>

  <input type="submit" id="button" style="height: 35px;" value="Send Email" >
	</form>
	
	</div>
	
	<script type="text/javascript"
	  src="https://cdn.jsdelivr.net/npm/emailjs-com@2/dist/email.min.js"></script>
	
	<script type="text/javascript">
	  emailjs.init('user_dYQ8zrPiKbhx89E0MAnEu')
	</script>
	<script type="text/javascript">
	const btn = document.getElementById('button');

	document.getElementById('form')
	 .addEventListener('submit', function(event) {
	   event.preventDefault();

	   btn.value = 'Sending...';

	   const serviceID = 'default_service';
	   const templateID = 'template_k4ko05w';

	   emailjs.sendForm(serviceID, templateID, this)
	    .then(() => {
	      btn.value = 'Send Email';
	      alert('�޼��� ���� ���� !');
	      window.close();
	    }, (err) => {
	      btn.value = 'Send Email';
	      alert(JSON.stringify(err));
	    });
	});
	</script>


</body>
</html>