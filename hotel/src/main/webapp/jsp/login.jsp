<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="k" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="<d:url value="/resources/CSS/bootstrap.css" />">
    <title>Logowanie</title>
</head>


<body>
<k:if test="${message.equals('user successfully added')}">
    <h5>Pomyślnie dodano użytkownika! Możesz się zalogować</h5>
</k:if>
<form class="form-signin" action="<c:url value="/j_spring_security_check"/>" method="POST">
    <h2 class="form-signin-heading">Proszę się zalogować</h2>
    <label for="inputEmail" class="sr-only">Login</label>
    <input name="j_username" type="email" id="inputEmail" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Hasło</label>
    <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Hasło" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
    <a href="<c:url value="/register"/>" class="btn btn-lg btn-primary btn-block">Stwórz nowe konto</a>
</form>

<jsp:include page="footer.jsp"/>
</body>

</html>
