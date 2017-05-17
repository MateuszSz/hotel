<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <%--<link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">--%>
    <%--<link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">--%>
    <title>Hotel</title>
</head>
<body>
<jsp:include page="classic.jsp"/>
<script type="text/javascript">

    $(document).ready(function () {
        $('li.active').removeClass('active');
        $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    });

    $(function () {
        $('#datetimepicker6').datetimepicker({
            locale: 'pl',
            format: 'YYYY-MM-DD',
            minDate: moment()
        });
        $('#datetimepicker7').datetimepicker({
            locale: 'pl',
            format: 'YYYY-MM-DD',
            minDate: moment().add(1, 'days')
        });

    });
</script>
<c:if test="${message.equals('bad dates')}">
    <h5>Minimalny czas rezerwacji to doba a maksymalny to 7 dni!</h5>
</c:if>
<h2>Podaj dane potrzebne do znalezienia odpowiadającego Ci pokoju</h2>

<form action="<c:url value="/reservation/selectRoom"/>" method="POST">
    <div class="container">
        <div class='col-md-5'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker6'>
                    <input name="startTime" type='text' class="form-control"/>
                    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
                </div>
            </div>
        </div>
        <div class='col-md-5'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker7'>
                    <input name="finishTime" type='text' class="form-control"/>
                    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
                </div>
            </div>
        </div>
    </div>

    <div class="form-signin">
        <button class="btn btn-lg btn-primary btn-block" name="button" type="submit">Wyszukaj dostępne pokoje</button>
    </div>
</form>

<jsp:include page="footer.jsp"/>
</body>
</html>
