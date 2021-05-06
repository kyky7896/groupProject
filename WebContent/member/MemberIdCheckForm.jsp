<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<style type="text/css">
    #wrap {
        width: 490px;
        text-align :center;
        margin: 0 auto 0 auto;
    }
    
    #chk{
        text-align :center;
    }
    
    #cancelBtn{
        visibility:visible;
    }
    
    #useBtn{
         visibility:hidden;
    }
</style>
<script src="./js/memberJS/idCheckScript.js"></script>
</head>
<!-- <body onload="pValue()"> -->
<body>
<div id="wrap">
    <br>
    <b><font size="4" color="gray">아이디 중복체크</font></b>
    <hr size="1" width="460">
    <br>
    <div id="chk">
        <form id="checkForm">
            <input type="text" name="idinput" id="userId">
            <input type="button" value="중복확인" onclick="idCheck()">
        </form>
        <div id="msg"></div>
        <br>
        <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
        <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
    </div>
</div>    
</body>
</html>