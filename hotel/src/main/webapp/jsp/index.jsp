<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <%--<link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">--%>
    <title>Hotel</title>
</head>
<script>
    $(document).ready(function () {
        $('li.active').removeClass('active');
        $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    });
</script>
<body>
<jsp:include page="classic.jsp"/>
<c:if test="${empty reservations}">
    <h2>Jeszcze nie masz żadnych rezerwacji</h2>
</c:if>
<c:if test="${!empty reservations}">
    <h2>Twoje rezerwacje:</h2>
    <table align="center" id="roomTable" class="sortable" cellspacing="0">
        <tr>
            <th>Dzień rozpoczęcia rezerwacji</th>
            <th>Dzień zakończenia rezerwacji</th>
            <th>piętro</th>
            <th>Max. osób</th>
            <th>Cena za osobę</th>
            <th>Kierunek okien</th>


        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.startDate}</td>
                <td>${reservation.finishDate}</td>
                <c:if test="${reservation.floor == 0}">
                    <td>parter</td>
                </c:if>
                <c:if test="${reservation.floor != 0}">
                    <td>${reservation.floor}</td>
                </c:if>
                <td>${reservation.maxNumberOfGuests}</td>
                <td>${reservation.price}</td>
                <td>${reservation.windowsOrientation}</td>
            </tr>

        </c:forEach>

    </table>
</c:if>

<br>
<c:if test="${empty breakfastDates}">
    <h2>Jeszcze nie masz żadnych śniadań</h2>
</c:if>
<c:if test="${!empty breakfastDates}">
    <h2>Twoje śniadania:</h2>
    <table align="center" id="roomTable" class="sortable" cellspacing="0">
        <tr>
            <th>Dzień wykupionego śniadania</th>
            <th>Ilość osób</th>

        </tr>
        <c:forEach items="${breakfastDates}" var="breakfast">
            <tr>
                <td>${breakfast.date}</td>
                <td>${breakfast.numberOfPeople}</td>
            </tr>
        </c:forEach>

    </table>
</c:if>


<jsp:include page="footer.jsp"/>
</body>
</html>
