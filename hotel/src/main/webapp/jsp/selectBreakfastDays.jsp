<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wybierz dni</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link type="text/css" href="<c:url value="/resources/CSS/bootstrap.css" />" rel="stylesheet">

</head>
<body>
<jsp:include page="classic.jsp"/>
<script type="text/javascript">

    jQuery.fn.dynamicInput = function () {
        $(this).each(function () {
            var checkboxes = $(this).find("input:checkbox");


            checkboxes.each(function () {
                var checkbox = $(this);
                // Highlight pre-selected checkboxes
                if (checkbox.prop("checked")) {
                }

                // Highlight checkboxes that the user selects
                checkbox.click(function () {
                    if (checkbox.prop("checked")) {

                        var input = document.createElement("input");
                        input.id = "val" + checkbox.val();
                        input.type = "number";
                        input.name = "numbersOfPeople";
                        input.placeholder = "ilość osób";
                        input.required = true;
                        var div = document.createElement("div");
                        div.id = checkbox.name;
                        div.appendChild(input);
                        document.getElementById(checkbox.val()).appendChild(div);
                    }
                    else {
                        var input2 = document.getElementById("val" + checkbox.val());
                        input2.parentElement.removeChild(input2);

                    }
                });
            });
        });
    };

    $(function () {
        $(".dynamicInput").dynamicInput();
    });
    $(document).ready(function () {
        $('li.active').removeClass('active');
    });
</script>
<h2 class="form-signin-heading">Wybierz dni aby wykupić ofertę śniadaniową</h2>
<h4>Opłata dodatkowa to 5 zł za śniadanie dla pojedyńczej osoby</h4>

<form action="<c:url value="/breakfast/purchase?reservationId=${reservationId}"/>" method="POST">
    <div class="dynamicInput">

        <c:forEach items="${days}" var="day">
            <label id="${day}"><input type="checkbox" name="days" value="${day}"/>${day}</label>
            <br>
        </c:forEach>

    </div>
    <div class="centered">
        <button class="btn btn-lg btn-primary btn-block" name="button" type="submit">Wykup</button>
    </div>
</form>

</body>
</html>
