<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <title>Dodaj ksiazke</title>
    <link rel="stylesheet" href="<d:url value="/resources/CSS/bootstrap.css" />">
</head>
<body>
<script language='javascript' type='text/javascript'>
    function check(input) {
        if (input.value !== document.getElementById('inputPassword').value) {
            input.setCustomValidity('Hasła muszą sie zgadzać');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
</script>
<%--<body background="<c:url value="/resources/images/bodybg.png"/>">--%>
<h2>Proszę wpisać dane nowego użytkownika</h2>
<form class="form-signin" action="<c:url value="/registerNewUser"/>" method="POST">
    <label for="inputEmail" class="sr-only">E-mail</label>
    <input name="email" type="email" id="inputEmail" class="form-control" placeholder="E-mail" required>
    <label for="inputName" class="sr-only">Imię i nazwisko</label>
    <input type="text" name="name" id="inputName" class="form-control" placeholder="Imię i naziwsko" required>
    <label for="inputPassword" class="sr-only">Hasło</label>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="hasło" required>
    <input type="password" , name="confirmPassword" , id="inputConfirmPassword" class="form-control"
           oninput="check(this)" placeholder="Powtórz hasło" required>

    <button class="btn btn-lg btn-primary btn-block" name="button" type="submit">Dodaj nowego użytkownika</button>

</form>
</body>
</html>
