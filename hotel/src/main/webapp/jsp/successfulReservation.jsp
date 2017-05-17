<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Rezerwacja udana</title>
    <link rel="stylesheet" href="<d:url value="/resources/CSS/bootstrap.css"/>">
</head>
<body>
<jsp:include page="classic.jsp"/>
<script type="text/javascript">

    $(document).ready(function () {
        $('li.active').removeClass('active');
    });
</script>

<h2 class="form-signin-heading">Pomyślnie dokonano rezerwacji!</h2>
<h4>Możesz teraz wykupić ofertę śniadaniową</h4>

<div class="centered">
    <a href="<c:url value="/breakfast/selectDays?reservationId=${reservationId}&startTime=${startTime}&finishTime=${finishTime}"/>"
       class="btn btn-lg btn-primary btn-block">Wykup ofertę śniadaniową</a>
    <a href="<c:url value="/index"/>" class="btn btn-lg btn-primary btn-block">Nie dziękuje</a>
</div>


<jsp:include page="footer.jsp"/>

</body>

</html>
