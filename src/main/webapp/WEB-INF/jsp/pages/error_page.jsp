<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PhoneBook</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/css/css.css"
          rel="stylesheet" type="text/css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
</head>
<body>

<jsp:include
        page="/WEB-INF/jsp/parts/navbar.jsp"
        flush="true"/>
<div class="container">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
            <c:out value="${error}"/>
        </div>
    </c:if>
    <c:remove var="error"/>
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
            <span class="sr-only">Well done!</span>
            <c:out value="${success}"/>
        </div>
    </c:if>
    <c:remove var="success"/>
</div>
</html>

